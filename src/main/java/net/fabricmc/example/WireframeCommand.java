package net.fabricmc.example;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import eu.pb4.mapcanvas.api.utils.VirtualDisplay;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

import java.nio.file.Path;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class WireframeCommand {
    private static VirtualDisplay display; // TODO

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("wireframe").then(
                        argument("file", StringArgumentType.string()).then(
                                argument("scale", DoubleArgumentType.doubleArg())
                                        .executes(ctx -> create(
                                                ctx,
                                                StringArgumentType.getString(ctx, "file"),
                                                DoubleArgumentType.getDouble(ctx, "scale")
                                        ))
                        )
                                .executes(ctx -> create(
                                        ctx,
                                        StringArgumentType.getString(ctx, "file"),
                                        128
                                ))
                )
        );
    }

    private static int create(CommandContext<ServerCommandSource> context, String file, double scale) {
        if (display != null) {
            display.destroy();
        }
        Wireframes.stopAll();

        try {
//            var wireframe = Wireframes.create(new WireframeShape(List.of(
//                    new Vec3d(128, 128, 128),
//                    new Vec3d(128, 128, -128),
//                    new Vec3d(128, -128, 128),
//                    new Vec3d(128, -128, -128),
//                    new Vec3d(-128, 128, 128),
//                    new Vec3d(-128, 128, -128),
//                    new Vec3d(-128, -128, 128),
//                    new Vec3d(-128, -128, -128)
//            ), List.of(
//                    new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 4),
//                    new Pair<>(1, 3), new Pair<>(1, 5), new Pair<>(2, 3),
//                    new Pair<>(2, 6), new Pair<>(3, 7), new Pair<>(4, 5),
//                    new Pair<>(4, 6), new Pair<>(5, 7), new Pair<>(6, 7)
//            )));
            var wireframe = Wireframes.create(ObjParser.parse(Path.of(file)));
            wireframe.setScale(scale);

            display = VirtualDisplay.builder()
                    .canvas(wireframe.canvas)
                    .invisible()
                    .glowing()
                    .pos(context.getSource().getEntityOrThrow().getBlockPos())
                    .direction(Direction.NORTH)
                    .rotation(BlockRotation.NONE)
                    .build();
            for (var player : context.getSource().getServer().getPlayerManager().getPlayerList()) {
                wireframe.canvas.addPlayer(player);
                display.addPlayer(player);
            }
            wireframe.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
