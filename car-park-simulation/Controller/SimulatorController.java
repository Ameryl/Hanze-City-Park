package Controller;

import java.awt.event.ActionListener;
import java.awt.event.*;

import Logic.SimulatorModel;
import javax.swing.*;

public class SimulatorController extends AbstractController implements ActionListener {
    private JButton start;
    private JButton stop;
    private JButton onestep;
    private JButton onehunderdsteps;


    public SimulatorController(SimulatorModel sim) {
        super(sim);

        start = new JButton("Start");
        start.addActionListener(this);

        stop = new JButton("Stop");
        stop.addActionListener(this);

        onestep = new JButton("One Step");
        onestep.addActionListener(this);

        onehunderdsteps = new JButton("100 Steps");
        onehunderdsteps.addActionListener(this);

        add(start);
        add(stop);
        add(onestep);
        add(onehunderdsteps);


        stop.setBounds(10, 10, 150, 30);
        start.setBounds(10, 70, 150, 30);

        onestep.setBounds(10, 20, 150, 30);
        onehunderdsteps.setBounds(10, 20, 150, 30);

        setVisible(true);
    }


    public void start() {
        sim.start();
    }


    public void stop() {
        sim.run = false;
    }

    public void oneStep() {
        for (int i = 0; i < 1; i++) {
            sim.tick();
        }
    }

    public void oneHunderdSteps() {
    // DUNNO LOL ?
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start) {
            start();
        }
        if (e.getSource()==stop) {
            stop();
        }
        if (e.getSource()==onestep) {
            oneStep();
        }
        if (e.getSource()==onehunderdsteps) {
            oneHunderdSteps();
        }
    }
    }
