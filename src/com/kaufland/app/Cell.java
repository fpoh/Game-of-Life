package com.kaufland.app;

import java.awt.*;

public class Cell {

    public static int size = 5;

    private Point point;
    private Color color;

    public Cell(Point point) {
        this.point = point;
        color = new Color(54, 177, 87);
    }

    public Point getPoint() {
        return point;
    }


    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}
