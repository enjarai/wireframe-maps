package net.fabricmc.example.render;

import net.fabricmc.example.render.math.Pair;
import net.fabricmc.example.render.math.Vec3d;
import net.fabricmc.example.render.wireframe.WireframeShape;

import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var testObj = new WireframeShape(List.of(
                new Vec3d(128, 128, 128),
                new Vec3d(128, 128, -128),
                new Vec3d(128, -128, 128),
                new Vec3d(128, -128, -128),
                new Vec3d(-128, 128, 128),
                new Vec3d(-128, 128, -128),
                new Vec3d(-128, -128, 128),
                new Vec3d(-128, -128, -128)
        ), List.of(
                new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 4),
                new Pair<>(1, 3), new Pair<>(1, 5), new Pair<>(2, 3),
                new Pair<>(2, 6), new Pair<>(3, 7), new Pair<>(4, 5),
                new Pair<>(4, 6), new Pair<>(5, 7), new Pair<>(6, 7)
        ));
        var canvas = new Canvas();

//        var window = new Window(testObj);

//        var ticker = new WireframeTicker(window, object, 60);
//        ticker.start();
    }
}
