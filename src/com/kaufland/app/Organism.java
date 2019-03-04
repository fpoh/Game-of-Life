package com.kaufland.app;

import com.kaufland.gui.Window;

import java.awt.*;
import java.util.ArrayList;

public class Organism {

    private ArrayList<Cell> cells;
    private ArrayList<Cell> oldCells;
    private Window window;

    public Organism (Window window, ArrayList<Cell> cells) {

        this.cells = cells;
        this.window = window;
    }

    public void killAllCells () {
        cells.clear();
    }

    public void addRemoveCell (Point pPoint) {
        oldCells = cells;
        Cell cell = getAliveCellAtPos(pPoint);

        if (cell == null) {
            cells.add(new Cell(pPoint));
        }
        else {
            cells.remove(cell);
        }
    }

    public void cellCheck () {
        oldCells = new ArrayList<Cell>();

        for (int i = 0; i < cells.size(); i++) {
            oldCells.add(cells.get(i));
        }


        for (int x = 0; x <= window.getWidth(); x = x + Cell.size) {
            for (int y = 0; y <= window.getHeight(); y = y + Cell.size) {

                Point point = new Point(x,y);
                Cell cell = getAliveCellAtPos(point);
                int neighbourCount = countAliveNeighbors(point);

                if (cell == null) {
                    if (neighbourCount == 3) {
                        cells.add(new Cell(point));
                        System.out.println("(" + point.x + ", " + point.y + ") " + "Zelle erstellt");
                    }
                }
                else if (neighbourCount == 2||neighbourCount == 3) {
                    System.out.println("(" + point.x + ", " + point.y + ") " + "Zelle bleibt erhalten");
                }
                else  {
                    cells.remove(cell);
                    System.out.println("(" + point.x + ", " + point.y + ") " + "Zelle stirbt");
                }
            }
    }


    private int countAliveNeighbors (Point pPoint) {
        int anzahl = 0;

        for (int x = pPoint.x - Cell.size; x <= pPoint.x + Cell.size; x = x + Cell.size) {

            for (int y = pPoint.y - Cell.size; y <= pPoint.y + Cell.size; y = y + Cell.size) {
                if (x != pPoint.x || y != pPoint.y) {

                    if (isAliveCellAtPos(new Point(x, y))) {
                        anzahl++;
                    }

                }

            }

        }

        return anzahl;
    }

    private boolean isAliveCellAtPos (Point pPoint) {
        boolean yes = false;

        for (int i = 0; i < oldCells.size(); i++) {
            Point point = oldCells.get(i).getPoint();

            if (point.x == pPoint.x && point.y == pPoint.y) {
                yes = true;
                break;
            }
        }

        return yes;
    }

    private Cell getAliveCellAtPos (Point pPoint) {
        Cell cell = null;

        for (int i = 0; i < oldCells.size(); i++) {
            Point point = oldCells.get(i).getPoint();

            if (point.x == pPoint.x && point.y == pPoint.y) {
                cell = oldCells.get(i);
                break;
            }
        }

        return cell;
    }

}
