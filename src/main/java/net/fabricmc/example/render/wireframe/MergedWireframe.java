package net.fabricmc.example.render.wireframe;

import net.fabricmc.example.render.GraphicsWrapper;

import java.util.List;

public class MergedWireframe implements WireframeObject {
    private final List<WireframeObject> objects;

    public MergedWireframe(List<WireframeObject> objects) {
        this.objects = objects;
    }

    @Override
    public void draw(GraphicsWrapper g) {
        for (var object : objects) {
            object.draw(g);
        }
    }
}
