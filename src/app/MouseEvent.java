package app;

import java.awt.*;
import java.awt.event.MouseListener;

public class MouseEvent implements MouseListener{
    private Organism organism;

    public MouseEvent(Organism organism) {
        this.organism = organism;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        int x = Math.round(e.getPoint().x / Cell.size) * Cell.size;
        int y = Math.round(e.getPoint().y / Cell.size) * Cell.size;

        organism.addRemoveCell(new Point(x,y));
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}
