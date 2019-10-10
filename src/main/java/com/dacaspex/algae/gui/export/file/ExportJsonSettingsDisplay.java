package com.dacaspex.algae.gui.export.file;

import com.dacaspex.algae.gui.settings.JsonExportable;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExportJsonSettingsDisplay {
    public static void export(JsonExportable exportable, Component parent) {
        // Get save location
        JFileChooser fileChooser = new JFileChooser();

        int returnVal = fileChooser.showOpenDialog(parent);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }

        // Get the file and make sure it ends with .json
        File saveLocation = fileChooser.getSelectedFile();
        if (!saveLocation.getPath().endsWith(".json")) {
            saveLocation = new File(saveLocation.getPath() + ".json");
        }

        // If the file already exists, ask the user if he would like to overwrite this file
        if (saveLocation.exists()) {
            int dialogResult = JOptionPane.showConfirmDialog(
                    parent,
                    "You are about to overwrite this file, continue?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION
            );
            if (dialogResult == JOptionPane.NO_OPTION) {
                return;
            }
        }

        // Get and transform the data
        String settings = exportable.exportSettings().toString();
        byte[] data = settings.getBytes();

        // Save file
        Path path = Paths.get(saveLocation.getPath());
        try {
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO
        }
    }
}
