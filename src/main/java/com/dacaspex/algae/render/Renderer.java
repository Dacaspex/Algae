package com.dacaspex.algae.render;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Complex;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.math.Vector2d;
import com.dacaspex.algae.render.listener.PreProcessorCompletedListener;
import com.dacaspex.algae.render.listener.RenderCompletedListener;
import com.dacaspex.algae.render.settings.RenderSettings;
import com.dacaspex.algae.render.thread.PreProcessorThread;
import com.dacaspex.algae.render.thread.RenderThread;
import javafx.util.Pair;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private List<RenderCompletedListener> renderCompletedListeners;
    private List<PreProcessorCompletedListener> preProcessorCompletedListeners;

    private RenderRequest renderRequest;

    private PreProcessorThread preProcessorThread;
    private RenderThreadManager renderThreadManager;
    private List<RenderThread> renderThreads;

    private List<RenderJob> jobs;
    private List<Pair<RenderJob, BufferedImage>> subImages;


    public Renderer() {
        renderCompletedListeners = new ArrayList<>();
        preProcessorCompletedListeners = new ArrayList<>();
        renderThreads = new ArrayList<>();
        jobs = new ArrayList<>();
        subImages = new ArrayList<>();
    }

    public void addListener(RenderCompletedListener listener) {
        renderCompletedListeners.add(listener);
    }

    public void render(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderSettings) {

        // TODO: Proper cleanup
        // TODO: Option to stop rendering

        renderThreads.clear();

        renderRequest = new RenderRequest(fractal, colorScheme, scale, renderSettings);

        Vector2d[][] points = scale.getPoints(renderSettings.getRenderWidth(), renderSettings.getRenderHeight());

        for (int i = 0; i < renderSettings.getThreads(); i++) {
            renderThreads.add(new RenderThread(fractal, colorScheme, points, this));
        }

        // Create job pool
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

        applyPreProcessor(fractal, colorScheme, scale, renderSettings);
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

        // TODO: Check if not stopped

        renderThreads.forEach(RenderThread::start);
        renderThreadManager = new RenderThreadManager();
        renderThreadManager.start();
    }

    public synchronized RenderJob getJob() {
        if (jobs.size() > 0) {
            return jobs.remove(0);
        }

        return null;
    }

    public synchronized void submitImage(BufferedImage image, RenderJob job) {
        subImages.add(new Pair<>(job, image));
    }

    public void stitch() {
        int width = renderRequest.renderSettings.getRenderWidth();
        int height = renderRequest.renderSettings.getRenderHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        subImages.forEach(p -> {
            RenderJob job = p.getKey();
            BufferedImage subImage = p.getValue();

            g.drawImage(subImage, job.xStart, job.yStart, null);
        });

        System.out.println("Stitched");

        renderCompletedListeners.forEach(l -> l.onCompleted(image));
    }

    private class RenderRequest {
        public Fractal fractal;
        public ColorScheme colorScheme;
        public Scale scale;
        public RenderSettings renderSettings;

        public RenderRequest(Fractal fractal, ColorScheme colorScheme, Scale scale, RenderSettings renderSettings) {
            this.fractal = fractal;
            this.colorScheme = colorScheme;
            this.scale = scale;
            this.renderSettings = renderSettings;
        }
    }

    private class RenderThreadManager extends Thread {
        @Override
        public void run() {
            try {
                for (Thread t : renderThreads) {
                    t.join();
                }
            } catch (InterruptedException e) {
                // TODO: Exception handling
            }

            stitch();
        }
    }
}
