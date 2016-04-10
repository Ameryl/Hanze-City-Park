package Controller;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

import Logic.*;
import Logic.Car;
import Logic.SimulatorModel;
import Main.Simulator;
import View.SimulatorView;

import javax.swing.*;

public class SimulatorController extends AbstractController implements ActionListener {
    private JButton mineen;
    private JButton pluseen;
    private JButton start;
    private JButton stop;
    private Thread thread;
    private boolean isRunning = false;

    public SimulatorController(SimulatorModel sim) {
        super(sim);

        // menuBar = new JMenuBar();
        setSize(500, 50);
        mineen = new JButton("-1");
        mineen.addActionListener(this);
        pluseen = new JButton("+1");
        pluseen.addActionListener(this);
        start = new JButton("Start");
        start.addActionListener(this);
        stop = new JButton("Stop");
        stop.addActionListener(this);

        this.setLayout(null);
        add(mineen);
        add(pluseen);
        add(start);
        add(stop);
        mineen.setBounds(50, 10, 70, 30);
        pluseen.setBounds(140, 10, 70, 30);
        start.setBounds(229, 10, 70, 30);
        stop.setBounds(319, 10, 70, 30);

        setVisible(true);

    }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==start) {
                sim.run();
            }
        }
    }
