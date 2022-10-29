package net.fabricmc.example.render;

import net.fabricmc.example.render.math.MathHelper;
import net.fabricmc.example.render.math.Pair;
import net.fabricmc.example.render.math.Vec2i;
import net.fabricmc.example.render.math.Vec3d;
import net.fabricmc.example.render.transformation.Transformation;

public class GraphicsWrapper {
    private final Graphics graphics;
    private final Transformation transformation;
    public final int focalLength;
    public final int width;
    public final int height;

    public GraphicsWrapper(Graphics graphics, Transformation transformation, int focalLength, int width, int height) {
        this.graphics = graphics;
        this.transformation = transformation;
        this.focalLength = focalLength;
        this.width = width;
        this.height = height;
    }

    public void drawPoint(Vec2i point) {
        graphics.drawPixel(point.x, point.y);
    }

    public void drawPoint(Vec3d point) {
        drawPoint(MathHelper.screenPosition(transformation.apply(point), focalLength, width, height));
    }

    public void drawLine(Pair<Vec3d> line) {
//        drawPoint(line.left);
//        drawPoint(line.right);
        MathHelper.forLine(
                MathHelper.screenPosition(transformation.apply(line.left), focalLength, width, height),
                MathHelper.screenPosition(transformation.apply(line.right), focalLength, width, height),
                this::drawPoint
        );
    }
}
