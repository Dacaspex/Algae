package com.dacaspex.algae.gui.settings.util;

import com.dacaspex.algae.gui.settings.ColorSchemeSettingsDisplay;
import com.dacaspex.algae.gui.settings.FractalSettingsDisplay;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;
import com.dacaspex.propertysheet.action.Action;
import com.dacaspex.propertysheet.property.IntegerProperty;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RenderAction implements Action {

    private FractalSettingsDisplay fractalSettingsDisplay;
    private ColorSchemeSettingsDisplay colorSchemeSettingsDisplay;
    private Scale scale;
    private IntegerProperty widthProperty;
    private IntegerProperty heightProperty;
    private Renderer renderer;

    public RenderAction(
            FractalSettingsDisplay fractalSettingsDisplay,
            ColorSchemeSettingsDisplay colorSchemeSettingsDisplay,
            Scale scale,
            IntegerProperty widthProperty,
            IntegerProperty heightProperty,
            Renderer renderer
    ) {
        this.fractalSettingsDisplay = fractalSettingsDisplay;
        this.colorSchemeSettingsDisplay = colorSchemeSettingsDisplay;
        this.scale = scale;
        this.widthProperty = widthProperty;
        this.heightProperty = heightProperty;
        this.renderer = renderer;

        renderer.addEventListener(new EventListener());
    }

    @Override
    public void execute() {
        renderer.render(
                fractalSettingsDisplay.getSettings().getFractal(),
                colorSchemeSettingsDisplay.getSettings().getColorScheme(),
                scale,
                new RenderSettings(widthProperty.getValue(), heightProperty.getValue(), 1, 1)
        );
    }

    class EventListener extends RendererEventAdapter {
        @Override
        public void onRenderCompleted(RenderCompletedEvent event) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss");
            Date date = new Date();
            String fileName = "export-" + dateFormat.format(date) + ".png";
            try {
                ImageIO.write(event.getImage(), "png", new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
