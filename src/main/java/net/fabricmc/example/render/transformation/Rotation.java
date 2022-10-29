package net.fabricmc.example.render.transformation;

import net.fabricmc.example.render.math.Vec3d;

public class Rotation implements Transformation {
    private final double[][] matrix;

    private Rotation(double[][] matrix) {
        this.matrix = matrix;
    }

    public static Rotation aroundX(double angle) {
        return new Rotation(new double[][] {
                {1, 0, 0},
                {0, Math.cos(angle), -Math.sin(angle)},
                {0, Math.sin(angle), Math.cos(angle)}
        });
    }

    public static Rotation aroundY(double angle) {
        return new Rotation(new double[][] {
                {Math.cos(angle), 0, Math.sin(angle)},
                {0, 1, 0},
                {-Math.sin(angle), 0, Math.cos(angle)}
        });
    }

    public static Rotation aroundZ(double angle) {
        return new Rotation(new double[][] {
                {Math.cos(angle), -Math.sin(angle), 0},
                {Math.sin(angle), Math.cos(angle), 0},
                {0, 0, 1}
        });
    }

    @Override
    public Vec3d apply(Vec3d point) {
        return new Vec3d(
                (int) (point.x * matrix[0][0] + point.y * matrix[0][1] + point.z * matrix[0][2]),
                (int) (point.x * matrix[1][0] + point.y * matrix[1][1] + point.z * matrix[1][2]),
                (int) (point.x * matrix[2][0] + point.y * matrix[2][1] + point.z * matrix[2][2])
        );
    }
}
