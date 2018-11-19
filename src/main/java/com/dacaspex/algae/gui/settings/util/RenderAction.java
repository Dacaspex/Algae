package com.dacaspex.algae.gui.settings.util;

import com.dacaspex.algae.gui.settings.ColorSchemeSettingsDisplay;
import com.dacaspex.algae.gui.settings.FractalSettingsDisplay;
import com.dacaspex.algae.gui.settings.colorScheme.ColorSchemeSettings;
import com.dacaspex.algae.gui.settings.fractal.FractalSettings;
import com.dacaspex.algae.main.Application;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.render.listener.RenderCompletedListener;
import com.dacaspex.algae.render.settings.RenderSettings;
import com.dacaspex.propertysheet.action.Action;
import com.dacaspex.propertysheet.property.IntegerProperty;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RenderAction implements Action, RenderCompletedListener {

    private FractalSettingsDisplay fractalSettingsDisplay;
    private ColorSchemeSettingsDisplay colorSchemeSettingsDisplay;
    private Scale scale;
    private IntegerProperty widthProperty;
    private IntegerProperty heightProperty;

    public RenderAction(
            FractalSettingsDisplay fractalSettingsDisplay,
            ColorSchemeSettingsDisplay colorSchemeSettingsDisplay,
            Scale scale,
            IntegerProperty widthProperty,
            IntegerProperty heightProperty
    ) {
        this.fractalSettingsDisplay = fractalSettingsDisplay;
        this.colorSchemeSettingsDisplay = colorSchemeSettingsDisplay;
        this.scale = scale;
        this.widthProperty = widthProperty;
        this.heightProperty = heightProperty;

        Application.get().getExportRenderer().addListener(this);
    }

    @Override
    public void execute() {
        Application.get().getExportRenderer().render(
                fractalSettingsDisplay.getSettings().getFractal(),
                colorSchemeSettingsDisplay.getSettings().getColorScheme(),
                scale,
                new RenderSettings(
                        widthProperty.getValue(),
                        heightProperty.getValue(),
                        1,
                        1,
                        4
                )
        );
    }

    @Override
    public void onCompleted(BufferedImage image) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss");
        Date date = new Date();
        String fileName = "export-" + dateFormat.format(date) + ".png";
        try {
            ImageIO.write(image, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
