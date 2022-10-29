package net.fabricmc.example.render;

import eu.pb4.mapcanvas.api.core.CanvasColor;
import eu.pb4.mapcanvas.api.core.CombinedPlayerCanvas;
import eu.pb4.mapcanvas.api.utils.CanvasUtils;
import net.fabricmc.example.render.transformation.MultiTransformation;
import net.fabricmc.example.render.transformation.Rotation;
import net.fabricmc.example.render.transformation.Scale;
import net.fabricmc.example.render.transformation.Translation;
import net.fabricmc.example.render.wireframe.WireframeObject;

import static net.fabricmc.example.render.math.MathHelper.TORAD;

public class WireframeTicker {
    public final CombinedPlayerCanvas canvas;
    private final WireframeObject object;
    private final int fps;
    private final Thread thread;
    private final Graphics graphics;
    private double scale = 128;

    public WireframeTicker(CombinedPlayerCanvas canvas, WireframeObject object, int fps) {
        this.canvas = canvas;
        this.object = object;
        this.fps = fps;
        this.thread = new Thread(this::run);
        this.graphics = (x, y) -> canvas.set(x, y, CanvasColor.EMERALD_GREEN_NORMAL); // EMERALD_GREEN_NORMAL
    }

    private void run() {
        while (true) {
            tick();
            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        CanvasUtils.fill(canvas, 0, 0, canvas.getWidth(), canvas.getHeight(), CanvasColor.BLACK_LOWEST);
        var translation = new MultiTransformation(
                Scale.of(1, -1, 1),
                Scale.of(scale),
                Rotation.aroundY((System.currentTimeMillis() / 10) % 360 * TORAD),
//                Rotation.aroundX((System.currentTimeMillis() / 20) % 360 * TORAD),
                Translation.of(0, 128, 256)
        );
        object.draw(new GraphicsWrapper(
                graphics, translation, 256,
                512, 512)
        );
        canvas.sendUpdates();
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }
}
