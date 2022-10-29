package net.fabricmc.example.render.math;

import java.util.function.Consumer;

public class MathHelper {
    public static final double TORAD = Math.PI / 180;
    public static final double TODEG = 1 / TORAD;

    public static Vec2i screenPosition(Vec3d point, int focalDistance, int width, int height) {
        double pointScaling = focalDistance * focalDistance / (point.z + focalDistance);

        double x = point.x * pointScaling / focalDistance;
        double y = point.y * pointScaling / focalDistance;

        return new Vec2i((int) (x * 2 + width / 2), (int) (y * 2 + height / 2));
    }

    public static void forLine(Vec2i point1, Vec2i point2, Consumer<Vec2i> consumer) {
        int mx = point2.x - point1.x;
        int my = point2.y - point1.y;

        int dx = Math.abs(mx);
        int dy = Math.abs(my);

        int max = Math.max(dx, dy);

        for (int i = 0; i < max; i++) {
            int x = point1.x + mx * i / max;
            int y = point1.y + my * i / max;

            consumer.accept(new Vec2i(x, y));
        }
    }
}
