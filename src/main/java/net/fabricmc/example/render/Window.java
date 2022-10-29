package net.fabricmc.example.render;

import net.fabricmc.example.render.wireframe.WireframeObject;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame implements ComponentListener {
    public final WireframeObject wireframe;

    public Window(WireframeObject wireframe) {
        super("canvas");

        this.wireframe = wireframe;

        setIgnoreRepaint(true);
        setVisible(true);
        createBufferStrategy(2);

        setSize(512, 512);
        addComponentListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void componentResized(ComponentEvent e) {
        createBufferStrategy(2);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}