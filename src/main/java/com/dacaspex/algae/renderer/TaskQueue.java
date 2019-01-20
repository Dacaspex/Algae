package com.dacaspex.algae.renderer;

import java.util.List;

public class TaskQueue {

    private final List<RenderTask> tasks;

    public TaskQueue(List<RenderTask> tasks) {
        this.tasks = tasks;
    }

    public synchronized RenderTask get() {
        if (tasks.isEmpty()) {
            return null;
        }

        return tasks.remove(0);
    }

    public synchronized void clear() {
        tasks.clear();
    }

    public synchronized int size() {
        return tasks.size();
    }
}
