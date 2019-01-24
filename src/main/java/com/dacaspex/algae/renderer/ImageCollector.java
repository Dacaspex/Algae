package com.dacaspex.algae.renderer;

import com.dacaspex.algae.uitl.Pair;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageCollector {

    private List<Pair<RenderTask, BufferedImage>> results;

    public ImageCollector() {
        results = new ArrayList<>();
    }

    public synchronized void submit(RenderTask task, BufferedImage image) {
        results.add(new Pair<>(task, image));
    }

    public List<Pair<RenderTask, BufferedImage>> getResults() {
        return results;
    }
}
