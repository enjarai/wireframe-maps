package net.fabricmc.example.render.transformation;

import net.fabricmc.example.render.math.Vec3d;

public interface Transformation {
    Vec3d apply(Vec3d point);
}
