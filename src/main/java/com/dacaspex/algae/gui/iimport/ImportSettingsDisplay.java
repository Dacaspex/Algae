package com.dacaspex.algae.gui.iimport;

import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettingsProvider;
import com.dacaspex.algae.gui.settings.fractal.FractalSettingsProvider;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImportSettingsDisplay {

    private final Component parent;
    private final List<FractalSettingsProvider> fractalSettingsProviders;
    private final List<ColorSchemeSettingsProvider> colorSchemeSettingsProviders;

    public ImportSettingsDisplay(
            Component parent,
            List<FractalSettingsProvider> fractalSettingsProviders,
            List<ColorSchemeSettingsProvider> colorSchemeSettingsProviders
    ) {
        this.parent = parent;
        this.fractalSettingsProviders = fractalSettingsProviders;
        this.colorSchemeSettingsProviders = colorSchemeSettingsProviders;

        validateFractalSettingsProviders(fractalSettingsProviders);
        validateColorSchemeSettingsProviders(colorSchemeSettingsProviders);
    }

    public FractalSettingsProvider importFractalSettings() {
        try {
            JSONObject data = readFile();

            // TODO: Perhaps add a custom dialogue?
            if (data == null) {
                return null;
            }

            String key = data.getString("id");

            FractalSettingsProvider matchedProvider = fractalSettingsProviders.stream()
                    .filter(provider -> provider.getIdentification().equals(key))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);

            matchedProvider.importSettings(data);

            return matchedProvider;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    parent,
                    "Something went wrong while importing the file.",
                    "Import error",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    parent,
                    "Could not read json",
                    "Import error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return null;
    }

    public ColorSchemeSettingsProvider importColorSchemeSettings() {
        try {
            JSONObject data = readFile();

            // TODO: Perhaps add a custom dialogue?
            if (data == null) {
                return null;
            }

            String key = data.getString("id");

            ColorSchemeSettingsProvider matchedProvider = colorSchemeSettingsProviders.stream()
                    .filter(provider -> provider.getIdentification().equals(key))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);

            matchedProvider.importSettings(data);

            return matchedProvider;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    parent,
                    "Something went wrong while importing the file.",
                    "Import error",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    parent,
                    "Could not read json",
                    "Import error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return null;
    }

    private void validateColorSchemeSettingsProviders(List<ColorSchemeSettingsProvider> settingsProviders) {
        Set<String> keys = new HashSet<>();

        for (ColorSchemeSettingsProvider settingsProvider : settingsProviders) {
            if (keys.contains(settingsProvider.getIdentification())) {
                throw new IllegalStateException("Two settings providers cannot have the same key");
            }

            keys.add(settingsProvider.getIdentification());
        }
    }

    private void validateFractalSettingsProviders(List<FractalSettingsProvider> settingsProviders) {
        Set<String> keys = new HashSet<>();

        for (FractalSettingsProvider settingsProvider : settingsProviders) {
            if (keys.contains(settingsProvider.getIdentification())) {
                throw new IllegalStateException("Two settings providers cannot have the same key");
            }

            keys.add(settingsProvider.getIdentification());
        }
    }

    private JSONObject readFile() throws IOException {
        // Get the file
        JFileChooser fileChooser = new JFileChooser();

        int returnVal = fileChooser.showOpenDialog(parent);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return null;
        }

        // Check if the file exists
        File file = fileChooser.getSelectedFile();
        if (!file.exists()) {
            return null;
        }

        // Read and parse json file
        Path path = Paths.get(file.getPath());
        String raw = new String(Files.readAllBytes(path));

        return new JSONObject(raw);
    }
}
