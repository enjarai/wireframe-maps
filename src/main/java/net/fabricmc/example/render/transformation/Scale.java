package net.fabricmc.example.render.transformation;


import net.fabricmc.example.render.math.Vec3d;

public class Scale implements Transformation {
    private final double x;
    private final double y;
    private final double z;

    private Scale(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Scale of(double x, double y, double z) {
        return new Scale(x, y, z);
    }

    public static Scale of(double scale) {
        return new Scale(scale, scale, scale);
    }

    @Override
    public Vec3d apply(Vec3d point) {
        return new Vec3d(point.x * x, point.y * y, point.z * z);
    }
}
