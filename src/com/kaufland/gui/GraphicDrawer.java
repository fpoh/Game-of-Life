package com.kaufland.gui;

import com.kaufland.app.Cell;

import java.awt.*;
import java.util.ArrayList;

public class GraphicDrawer implements Runnable{

    private Canvas canvas;
    private ArrayList<Cell> cells;
    private ArrayList<Cell> lastCells = new ArrayList<Cell>();

    public GraphicDrawer(Canvas canvas, ArrayList<Cell> cells) {
        this.canvas = canvas;
        this.cells = cells;

    }

    public void clear () {
        Graphics graphics = canvas.getGraphics();
        if(graphics != null) {
            graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }

    private ArrayList<Cell> getNewCells(ArrayList<Cell> pCells) {
        ArrayList<Cell> newCells = new ArrayList<Cell>();
        Cell cell;

        for (int i = 0; i < pCells.size(); i++) {
            cell = pCells.get(i);

            if (lastCells.contains(cell) == false) {
                newCells.add(cell);
            }
        }

        return newCells;
    }

    private ArrayList<Cell> getDeadCells(ArrayList<Cell> pCells) {
        ArrayList<Cell> deletedCells = new ArrayList<Cell>();
        Cell cell;

        for (int i = 0; i < lastCells.size(); i++) {
            cell = lastCells.get(i);

            if (pCells.contains(cell) == false) {
                deletedCells.add(cell);
            }
        }

        return deletedCells;
    }

    public void run() {

        while (true) {
            Graphics graphics = canvas.getGraphics();
            if(graphics != null && cells != null) {
                ArrayList<Cell> cellsCopy = new ArrayList<Cell>();

                for (int i = 0; i < cells.size(); i++) {
                    cellsCopy.add(cells.get(i));
                }

                ArrayList<Cell> deletedCells = getDeadCells(cellsCopy);
                Cell deadCell;

                for (int i = 0; i < deletedCells.size(); i++) {
                    deadCell = deletedCells.get(i);
                    graphics.clearRect(deadCell.getPoint().x, deadCell.getPoint().y, deadCell.getSize(), deadCell.getSize());
                }

                ArrayList<Cell> newCells = getNewCells(cellsCopy);
                Cell newCell;

                for (int i = 0; i < newCells.size(); i++) {
                    newCell = newCells.get(i);
                    graphics.setColor(newCell.getColor());
                    //graphics.fill3DRect(newCell.getPoint().x, newCell.getPoint().y, newCell.getSize(), newCell.getSize(), true);
                    graphics.fillRect(newCell.getPoint().x, newCell.getPoint().y, newCell.getSize(), newCell.getSize());
                }

                lastCells = cellsCopy;

                try {
                    Thread.sleep(17L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
