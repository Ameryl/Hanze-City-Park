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
    private JLabel title;


    public SimulatorController(SimulatorModel sim) {
        super(sim);

        title = new JLabel();
        title.setText("Simulatie controls");

        start = new JButton("Start");
        start.addActionListener(this);

        stop = new JButton("Stop");
        stop.addActionListener(this);

        onestep = new JButton("One Step");
        onestep.addActionListener(this);

        onehunderdsteps = new JButton("100 Steps");
        onehunderdsteps.addActionListener(this);

        add(title);
        add(start);
        add(stop);
        add(onestep);
        add(onehunderdsteps);

        // REMINDER : DE LAYOUT AANPASSEN IS WERK VOOR NIEK
        // - Cyriel

        this.setLayout(null);

        title.setBounds(50, -40, 150, 100);

        stop.setBounds(100, 20, 95, 30);
        start.setBounds(5, 20, 95, 30);


        onestep.setBounds(5, 65, 95, 30);
        onehunderdsteps.setBounds(100, 65, 95, 30);
        setVisible(true);
    }


    public void start() {
        sim.start();
    }


    public void stop() {
        sim.run = false;
    }

    public void oneStep() {
            sim.tick();
    }

    public void oneHunderdSteps() {
        JOptionPane.showMessageDialog(null, "ik ben niek ik heb deze methode nog niet geïmplementeerd");
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
