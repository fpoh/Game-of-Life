package com.kaufland;

import com.kaufland.gui.Window;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Window frame = new Window();
                    //frame.addKeyListener();
                    frame.setVisible(true);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
