package net.fabricmc.example;

import eu.pb4.mapcanvas.api.core.DrawableCanvas;
import net.fabricmc.example.render.WireframeTicker;
import net.fabricmc.example.render.wireframe.WireframeObject;

import java.util.ArrayList;
import java.util.List;

public class Wireframes {
    public static List<WireframeTicker> wireframes = new ArrayList<>();

    public static WireframeTicker create(WireframeObject object) {
        var wireframe = new WireframeTicker(DrawableCanvas.create(4, 4), object, 60);
        wireframes.add(wireframe);
        return wireframe;
    }

    public static void stopAll() {
        wireframes.forEach(WireframeTicker::stop);
        wireframes.clear();
    }
}
