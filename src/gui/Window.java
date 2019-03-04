package gui;

import app.Cell;
import app.MouseEvent;
import app.Organism;
import app.OrganismEngine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame{
    private JButton btn1;
    private JPanel contentPane;
    private int width = 1200;
    private int height = 800;

    private Thread graphicThread;
    private Thread engine;

    private boolean newSim;

    private Organism organism;
    private GraphicDrawer graphicDrawer;

    public Window () {
        setResizable(false);
        setTitle("Game of life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, width, height);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(60, 63, 65));
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btn1 = new JButton("Start");
        btn1.setSize(100, 30);
        btn1.setLocation(40, 730);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        add(btn1);
        Canvas canvas = new Canvas();

        canvas.setBackground(new Color(60, 63, 65));
        canvas.setBounds(0, 0, getWidth(), getHeight());
        add(canvas);

        ArrayList<Cell> cells = new ArrayList<Cell>();
        organism = new Organism(this, cells);

        MouseEvent mouseEvent = new MouseEvent(organism);
        canvas.addMouseListener(mouseEvent);

        graphicDrawer = new GraphicDrawer(canvas, cells);
        graphicThread = new Thread(graphicDrawer);
        engine = new Thread(new OrganismEngine(organism));
        graphicThread.start();
    }

    private void start () {
        if (newSim) {
            btn1.setText("Start");
            organism.killAllCells();
            engine.stop();
            engine = new Thread(new OrganismEngine(organism));
            newSim = false;
            graphicDrawer.clear();
        }
        else {
            engine.start();
            newSim = true;
            btn1.setText("New");
        }

    }
}
