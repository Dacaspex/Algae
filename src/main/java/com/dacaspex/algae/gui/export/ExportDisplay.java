package com.dacaspex.algae.gui.export;

import com.dacaspex.algae.colorScheme.ColorScheme;
import com.dacaspex.algae.fractal.Fractal;
import com.dacaspex.algae.math.Scale;
import com.dacaspex.algae.renderer.RenderSettings;
import com.dacaspex.algae.renderer.Renderer;
import com.dacaspex.algae.renderer.event.RenderCompletedEvent;
import com.dacaspex.algae.renderer.event.RendererEventAdapter;
import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.PropertySheetOptions;
import com.dacaspex.propertysheet.action.Action;
import com.dacaspex.propertysheet.property.ActionProperty;
import com.dacaspex.propertysheet.property.IntegerProperty;
import com.dacaspex.propertysheet.validator.integer.IntegerValidatorFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is an experimental, first, take on the export GUI. I do want to create a custom UI in the future,
 * but this can do for now.
 */
public class ExportDisplay extends JFrame {

    private final Renderer renderer;

    private final PropertySheet propertySheet;
    private final IntegerProperty widthProperty;
    private final IntegerProperty heightProperty;
    private final ActionProperty renderAction;

    private Fractal fractal;
    private ColorScheme colorScheme;
    private Scale scale;

    public ExportDisplay() {
        this.renderer = new Renderer();

        this.propertySheet = new PropertySheet(new PropertySheetOptions());
        this.widthProperty = new IntegerProperty(
                "Width",
                1920,
                new IntegerValidatorFactory()
                        .setLowerBound(0)
                        .build()
        );
        this.heightProperty = new IntegerProperty(
                "Height",
                1080,
                new IntegerValidatorFactory()
                        .setLowerBound(0)
                        .build()
        );
        this.renderAction = new ActionProperty("Render", new RenderAction());
    }

    public void build() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Build property sheet
        propertySheet.addProperty(widthProperty);
        propertySheet.addProperty(heightProperty);
        propertySheet.addProperty(renderAction);

        add(new JScrollPane(propertySheet));

        // TODO: Key listener for closing

        setVisible(false);
        pack();

        renderer.addEventListener(new RendererEventListener());
    }

    public void open(Fractal fractal, ColorScheme colorScheme, Scale scale) {
        this.fractal = fractal;
        this.colorScheme = colorScheme;
        this.scale = scale;
        setVisible(true);
    }

    public void close() {
        setVisible(false);
    }

    private class RenderAction implements Action {
        @Override
        public void execute() {
            RenderSettings renderSettings = new RenderSettings(
                    widthProperty.getValue(),
                    heightProperty.getValue(),
                    1,
                    1
            );

            renderer.render(fractal, colorScheme, scale, renderSettings);
        }
    }

    private class RendererEventListener extends RendererEventAdapter {
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
