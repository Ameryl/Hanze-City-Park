package Controller;

import java.awt.event.ActionListener;
import java.awt.event.*;

import Logic.SimulatorModel;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
/**
 * This class is responsible for making the buttons functional
 */
public class SimulatorController extends AbstractController implements ActionListener {
    private JButton start;
    private JButton stop;
    private JButton onestep;
    private JButton onehunderdsteps;
    private JLabel title;
    private boolean isRunning = false;


    /**
     * In the constructor we create several buttons with different function.
     * @param sim
     */
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



        this.setLayout(null);

        title.setBounds(50, -40, 150, 100);

        stop.setBounds(100, 20, 95, 30);
        start.setBounds(5, 20, 95, 30);


        onestep.setBounds(5, 65, 95, 30);
        onehunderdsteps.setBounds(100, 65, 95, 30);
        setVisible(true);
    }

    /**
     * Starts the sim.
     */
    public void start() {
        if(!isRunning){
            sim.start();
            isRunning = true;
        }
        else {
            showMessageDialog(null, "EEN SIM IS ALFREDDIE ROENNING!");
        }
    }

    /**
     * Pauses the simulator.
     */
    public void stop() {
        sim.run = false;
        isRunning = false;
    }

    /**
     * Runs the simulator just for one step.
     */
    public void oneStep() {
            sim.tick();
    }

    /**
     * Runs the simulator for steps.
     */
    public void oneHunderdSteps() {

    }


    @Override
    /**
     * Creates the ActionEvents of the JButtons.
     */
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
