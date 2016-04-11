package Main; /**
 * The simulation of a carpark system. It simulates the possibilities of how
 * a car is parked.
 *
 */


import Controller.*;
import View.AbstractView;
import Logic.*;
import View.SimulatorView;
import View.infoView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class Simulator {
    /**
     * Constructor for the simulati
     */
    private JFrame frame;
    private AbstractView SimulatorView;
    private SimulatorModel simModel;
    private AbstractController SimulatorController;
    private AbstractView infoView;

    public Simulator() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Border redline = BorderFactory.createLineBorder(Color.red);

        simModel = new SimulatorModel(3, 6, 30);

        SimulatorController = new SimulatorController(simModel);
        SimulatorView = new SimulatorView(simModel);
        infoView = new infoView(simModel);

        frame = new JFrame("Car Park Simulation");
        frame.setSize(1100, 500);
        frame.setResizable(true);
        frame.setLayout(null);

        frame.getContentPane().add(SimulatorView);
        frame.getContentPane().add(SimulatorController);
        frame.getContentPane().add(infoView);

        infoView.setBorder(redline);
        SimulatorView.setBorder(blackline);
        SimulatorController.setBorder(blackline);

        SimulatorView.setBounds(10,10,800,400);
        infoView.setBounds(850,50,200,100);
        SimulatorController.setBounds(850,200,200,100);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }
}
