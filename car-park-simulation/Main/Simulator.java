package Main; /**
 * The simulation of a carpark system. It simulates the possibilities of how
 * a car is parked.
 *
 */


import Controller.*;
import View.AbstractView;
import Logic.*;
import View.SimulatorView;

import javax.swing.*;


public class Simulator {
    /**
     * Constructor for the simulati
     */
    private JFrame frame;
    private AbstractView SimulatorView;
    private SimulatorModel simModel;
    private AbstractController SimulatorController;

    public Simulator() {
        simModel = new SimulatorModel(3, 6, 30);
        SimulatorController = new SimulatorController(simModel);
        SimulatorView = new SimulatorView(simModel);

        frame = new JFrame("Car Park Simulator");
        frame.setSize(1100, 500);
        frame.setResizable(false);
        frame.setLayout(null);

        frame.getContentPane().add(SimulatorController);
        frame.getContentPane().add(SimulatorView);

        SimulatorView.setBounds(10,10,800,500);
        SimulatorController.setBounds(850,200,900,500);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //simModel.notifyViews();
        frame.setVisible(true);
    }
}
