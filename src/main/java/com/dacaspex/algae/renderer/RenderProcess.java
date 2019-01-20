package com.dacaspex.algae.renderer;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.renderer.event.EventDispatcher;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RenderEvent;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RenderProcess extends Thread {

    private final UUID id;
    private final Fractal fractal;
    private final ColorScheme colorScheme;
    private final Scale scale;
    private final RenderSettings renderSettings;
    private final int threadCount;
    private final EventDispatcher eventDispatcher;

    private TaskQueue taskQueue;
    private ImageCollector collector;

    private int preProcessingProgressUnits;
    private int renderingProgressUnits;

    public RenderProcess(
            Fractal fractal,
            ColorScheme colorScheme,
            Scale scale,
            RenderSettings renderSettings,
            int threadCount,
            EventDispatcher eventDispatcher
    ) {
        this.id = UUID.randomUUID();
        this.fractal = fractal;
        this.colorScheme = colorScheme;
        this.scale = scale;
        this.renderSettings = renderSettings;
        this.threadCount = threadCount;
        this.eventDispatcher = eventDispatcher;
        this.collector = new ImageCollector();

        this.preProcessingProgressUnits = (int) ((100 * renderSettings.preProcessorQuality)
                / (renderSettings.preProcessorQuality + renderSettings.renderQuality));
        this.renderingProgressUnits = (int) ((100 * renderSettings.renderQuality)
                / (renderSettings.preProcessorQuality + renderSettings.renderQuality));
    }

    @Override
    public void run() {
        try {
            // Startup tasks
            eventDispatcher.dispatchOnRenderStartedEvent(new RenderEvent(id, RenderStage.BUILDING_TASKS, 0));
            buildTaskQueue();

            // Pre-process the image
            preProcess();

            // Calculate the progress thus far
            eventDispatcher.dispatchOnPreProcessingCompleted(new RenderEvent(id, RenderStage.RENDERING, preProcessingProgressUnits));

            // Actually render the image
            render();

            if (isInterrupted()) {
                throw new InterruptedException();
            }

            // Stitch image and give result
            BufferedImage result = stitch();

            if (isInterrupted()) {
                throw new InterruptedException();
            }

            eventDispatcher.dispatchOnRenderCompleted(new RenderCompletedEvent(id, result));

        } catch (InterruptedException e) {
            // Stop the execution of this process because the thread was interrupted.
            // This solution is not the best, besides, stopping a process can be considered
            // "normal" program flow, so an exception might not be the best idea.
            // Instead of checking everywhere if the thread is interrupted, I simply thrown an exception.
            // Ugly, but it works... Not working a lot with threads so bare with me.

            // Throw the appropriate event so the caller knows it has been stopped.
            eventDispatcher.dispatchOnRenderCanceledEvent(new RenderEvent(id, RenderStage.COMPLETED, 0));
        }
    }

    private void buildTaskQueue() {
        List<RenderTask> tasks = new ArrayList<>();

        // TODO: Extract value of 100
        for (int x = 0; x < renderSettings.getRenderWidth(); x += 100) {
            for (int y = 0; y < renderSettings.getRenderHeight(); y += 100) {
                int width = 100;
                int height = 100;
                // TODO: Refactor calculation (can be done with subtraction)
                if (x + 100 > renderSettings.getRenderWidth()) {
                    width = renderSettings.getRenderWidth() % 100;
                }
                if (y + 100 > renderSettings.getRenderHeight()) {
                    height = renderSettings.getRenderHeight() % 100;
                }

                tasks.add(new RenderTask(x, y, width, height));
            }
        }

        taskQueue = new TaskQueue(tasks);
    }

    private void preProcess() throws InterruptedException {
        if (colorScheme.getPreProcessor() == null) {
            return;
        }

        Vector2d[][] points = scale.getPoints(
                renderSettings.getPreProcessorWidth(),
                renderSettings.getPreProcessorHeight()
        );

        // Progress calculation
        double progress;
        int index = 0;

        for (Vector2d[] row : points) {
            if (isInterrupted()) {
                // See exception handler in this class for explanation
                throw new InterruptedException();
            }

            // Calculate progress
            index++;
            progress = ((double) index / points.length);
            progress = progress * preProcessingProgressUnits;

            eventDispatcher.dispatchOnProgressEvent(new RenderEvent(id, RenderStage.PRE_PROCESSING, (int) progress));

            for (Vector2d point : row) {
                // Construct complex from point
                Complex complex = new Complex(point.x, point.y);

                // Pass sequence through pre-processor
                colorScheme.getPreProcessor().read(complex, fractal.getSequence(complex));
            }
        }
    }

    private void render() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Vector2d[][] points = scale.getPoints(renderSettings.getRenderWidth(), renderSettings.getRenderHeight());
        int initialTaskSize = taskQueue.size();
        double progress = 0;

        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(new RenderThread(fractal, colorScheme, points, taskQueue, collector)));
        }

        // Start each thsread
        threads.forEach(Thread::start);

        while (threads.stream().anyMatch(Thread::isAlive)) {
            for (Thread t : threads) {
                t.join(10);

                // Dispatch a progress event
                progress = (double) (initialTaskSize - taskQueue.size()) / initialTaskSize;
                progress = progress * renderingProgressUnits + preProcessingProgressUnits;
                eventDispatcher.dispatchOnProgressEvent(new RenderEvent(id, RenderStage.RENDERING, (int) progress));
            }
        }
    }

    private BufferedImage stitch() {
        BufferedImage image = new BufferedImage(
                renderSettings.getRenderWidth(),
                renderSettings.getRenderHeight(),
                BufferedImage.TYPE_INT_RGB
        );
        Graphics g = image.getGraphics();

        for (Pair<RenderTask, BufferedImage> result : collector.getResults()) {
            RenderTask task = result.getKey();
            BufferedImage subImage = result.getValue();

            g.drawImage(subImage, task.xStart, task.yStart, null);
        }

        return image;
    }

    @Override
    public void interrupt() {
        super.interrupt();

        taskQueue.clear();
    }
}
