package Controller;

import java.awt.event.ActionListener;
import java.awt.event.*;

import Logic.SimulatorModel;
import javax.swing.*;

public class SimulatorController extends AbstractController implements ActionListener {
    private JButton start;
    private JButton stop;

    public SimulatorController(SimulatorModel sim) {
        super(sim);

        start = new JButton("START");
        start.addActionListener(this);

        stop = new JButton("STOP");
        stop.addActionListener(this);


        add(start);
        add(stop);


        stop.setBounds(10, 10, 150, 30);
        start.setBounds(10, 70, 150, 30);

        setVisible(true);
    }


    public void start() {
        sim.start();
    }


    public void stop() {
        sim.run = false;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start) {
            start();
        }
        if (e.getSource()==stop) {
            stop();
        }
    }
    }
