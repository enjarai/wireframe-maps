package net.fabricmc.example.render.transformation;

import net.fabricmc.example.render.math.Vec3d;

public class MultiTransformation implements Transformation {
    private final Transformation[] transformations;

    public MultiTransformation(Transformation... transformations) {
        this.transformations = transformations;
    }

    @Override
    public Vec3d apply(Vec3d point) {
        for (Transformation transformation : transformations) {
            point = transformation.apply(point);
        }

        return point;
    }
}
