package net.fabricmc.example.render.wireframe;

import net.fabricmc.example.render.GraphicsWrapper;
import net.fabricmc.example.render.math.Pair;
import net.fabricmc.example.render.math.Vec3d;

import java.util.List;

public class WireframeShape implements WireframeObject {
    private final List<Vec3d> points;
    private final List<Pair<Integer>> lines;

    public WireframeShape(List<Vec3d> points, List<Pair<Integer>> lines) {
        this.points = points;
        this.lines = lines;
    }

    @Override
    public void draw(GraphicsWrapper g) {
        lines.stream()
                .map(line -> new Pair<>(points.get(line.left), points.get(line.right)))
                .forEach(g::drawLine);
    }
}
