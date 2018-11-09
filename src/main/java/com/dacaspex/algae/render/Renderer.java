package com.dacaspex.algae.render;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.render.listener.PreProcessorCompletedListener;
import com.dacaspex.algae.render.listener.RenderCompletedListener;
import com.dacaspex.algae.render.settings.RenderSettings;
import com.dacaspex.algae.render.thread.PreProcessorThread;
import com.dacaspex.algae.render.thread.RenderThread;
import javafx.util.Pair;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Renderer {

    private List<RenderCompletedListener> renderCompletedListeners;
    private List<PreProcessorCompletedListener> preProcessorCompletedListeners;

    private RenderRequest renderRequest;
    private RenderRequest futureRequest;

    private PreProcessorThread preProcessorThread;
    private RenderThreadManager renderThreadManager;
    private List<RenderThread> renderThreads;

    private List<RenderJob> jobs;
    private List<Pair<RenderJob, BufferedImage>> subImages;

    private AtomicBoolean isRendering;
    private AtomicBoolean stopRendering;

    public Renderer() {
        renderCompletedListeners = new ArrayList<>();
        preProcessorCompletedListeners = new ArrayList<>();
        renderThreads = new ArrayList<>();
        jobs = new ArrayList<>();
        subImages = new ArrayList<>();
        isRendering = new AtomicBoolean(false);
        stopRendering = new AtomicBoolean(false);
    }

    public void addListener(RenderCompletedListener listener) {
        renderCompletedListeners.add(listener);
    }

    public void render(RenderRequest request) {
        render(request.fractal, request.colorScheme, request.scale, request.renderSettings);
    }

    public void render(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderSettings) {
        // Check, atomically, if there is already a render going on
        System.out.println("Render request");
        synchronized (this) {
            if (isRendering.get()) {
                futureRequest = new RenderRequest(fractal, colorScheme, scale, renderSettings);
                stopRendering.set(true);
                System.out.println("Postponed render");

                return;
            }

            isRendering.set(true);
        }

        // Clear render threads TODO: Perhaps this should move
        renderThreads.clear();
        futureRequest = null;

        // Create and save required render information
        renderRequest = new RenderRequest(fractal, colorScheme, scale, renderSettings);
        Vector2d[][] points = scale.getPoints(renderSettings.getRenderWidth(), renderSettings.getRenderHeight());
        jobs = this.createJobPool(renderSettings);

        // Create render threads
        for (int i = 0; i < renderSettings.getThreads(); i++) {
            renderThreads.add(new RenderThread(fractal, colorScheme, points, this));
        }

        // Start render
        this.applyPreProcessor(fractal, colorScheme, scale, renderSettings);
    }

    private List<RenderJob> createJobPool(RenderSettings renderSettings) {
        List<RenderJob> jobs = new ArrayList<>();

        for (int x = 0; x < renderSettings.getRenderWidth(); x += 100) {
            for (int y = 0; y < renderSettings.getRenderHeight(); y += 100) {
                int width = 100;
                int height = 100;
                if (x + 100 > renderSettings.getRenderWidth()) {
                    width = renderSettings.getRenderWidth() % 100;
                }
                if (y + 100 > renderSettings.getRenderHeight()) {
                    height = renderSettings.getRenderHeight() % 100;
                }

                jobs.add(new RenderJob(x, y, width, height));
            }
        }

        return jobs;
    }

    private void applyPreProcessor(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderOptions) {
        if (colorScheme.getPreProcessor() != null) {

            Vector2d[][] points = scale.getPoints(
                    renderOptions.getPreProcessorWidth(),
                    renderOptions.getPreProcessorHeight()
            );

            preProcessorThread = new PreProcessorThread(colorScheme.getPreProcessor(), fractal, points, this);
            preProcessorThread.start();
        } else {
            preProcessorCompleted();
        }
    }

    public synchronized void preProcessorCompleted() {
        preProcessorCompletedListeners.forEach(l -> l.onCompleted(0));

        synchronized (this) {
            if (stopRendering.get()) {
                stopRendering.set(false);
                isRendering.set(false);
                if (futureRequest != null) {
                    render(futureRequest);
                }

                return;
            }
        }

        renderThreads.forEach(RenderThread::start);
        renderThreadManager = new RenderThreadManager();
        renderThreadManager.start();
    }

    public synchronized RenderJob getJob() {
        if (stopRendering.get()) {
            return null;
        }

        if (jobs.size() > 0) {
            return jobs.remove(0);
        }

        return null;
    }

    public synchronized void submitImage(BufferedImage image, RenderJob job) {
        subImages.add(new Pair<>(job, image));
    }

    private void stitch() {
        int width = renderRequest.renderSettings.getRenderWidth();
        int height = renderRequest.renderSettings.getRenderHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        subImages.forEach(p -> {
            RenderJob job = p.getKey();
            BufferedImage subImage = p.getValue();

            g.drawImage(subImage, job.xStart, job.yStart, null);
        });

        renderCompletedListeners.forEach(l -> l.onCompleted(image));
        isRendering.set(false);
    }

    private class RenderThreadManager extends Thread {
        @Override
        public void run() {
            try {
                for (Thread t : renderThreads) {
                    t.join();
                    System.out.println("Thread joined");
                }
            } catch (InterruptedException e) {
                // TODO: Exception handling
            }

            // If the process was stopped early
            synchronized (this) {
                if (stopRendering.get()) {
                    stopRendering.set(false);
                    isRendering.set(false);
                    if (futureRequest != null) {
                        render(futureRequest);
                    }

                    return;
                }
            }

            stitch();
        }
    }
}
