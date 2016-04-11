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
import java.awt.*;


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
        frame.setBackground(Color.blue);
        frame.setResizable(false);
        frame.setLayout(null);


        frame.getContentPane().add(SimulatorView);
        frame.getContentPane().add(SimulatorController);

        SimulatorView.setBounds(0,0,800,500);
        SimulatorController.setBounds(850,200,200,200);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }
}
