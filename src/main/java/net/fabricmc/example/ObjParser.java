package net.fabricmc.example;

import net.fabricmc.example.render.math.Pair;
import net.fabricmc.example.render.math.Vec3d;
import net.fabricmc.example.render.wireframe.WireframeObject;
import net.fabricmc.example.render.wireframe.WireframeShape;
import net.minecraft.client.MinecraftClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class ObjParser {
    private static final Path OBJ_PATH = MinecraftClient.getInstance().runDirectory.toPath().resolve("obj");

    public static WireframeObject parse(Path path) throws IOException {
        path = OBJ_PATH.resolve(path);
        var vertices = new ArrayList<Vec3d>();
        var lines = new ArrayList<Pair<Integer>>();

        try (var reader = new BufferedReader(new FileReader(path.toFile()))) {
            reader.lines().forEach(line -> {
                var split = line.split(" ");

                if (split[0].equals("v")) {
                    vertices.add(new Vec3d(
                            Double.parseDouble(split[1]),
                            Double.parseDouble(split[2]),
                            Double.parseDouble(split[3])
                    ));
                } else if (split[0].equals("l")) {
                    lines.add(new Pair<>(
                            Integer.parseInt(split[1]) - 1,
                            Integer.parseInt(split[2]) - 1
                    ));
                }
            });
        }

        return new WireframeShape(vertices, lines);
    }
}
