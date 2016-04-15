package Main; /**
 * The simulation of a carpark system. It simulates the possibilities of how
 * a car is parked.
 */


import Controller.*;
import View.*;
import Logic.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class Simulator {
    /**
     * Constructor for the simulator
     */
    private JFrame screen;
    private AbstractView  simulatorview;
    private SimulatorModel simModel;
    private AbstractController simulatorcontroller;
    private AbstractView infoview;
    private AbstractView pieview;
   // private GraphView graphview;
    private QueueView queueview;

    public Simulator() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Border redline = BorderFactory.createLineBorder(Color.red);

        simModel = new SimulatorModel(3, 6, 30);

        simulatorcontroller = new SimulatorController(simModel);
        simulatorview = new SimulatorView(simModel);
        infoview = new infoView(simModel);
        pieview = new PieView(simModel);
      //  graphview = new GraphView(simModel, 200 , 200, 500);
        queueview = new QueueView(simModel);

        screen = new JFrame("Car Park Simulation");
        screen.setSize(1350, 475);
        screen.setResizable(true);
        screen.setLayout(null);

        screen.getContentPane().add(simulatorview);
        screen.getContentPane().add(infoview);
        screen.getContentPane().add(pieview);
        screen.getContentPane().add(simulatorcontroller);
      //  screen.getContentPane().add(graphview);
        screen.getContentPane().add(queueview);

        queueview.setBorder(redline);
        pieview.setBorder(redline);
        infoview.setBorder(redline);
        simulatorview.setBorder(blackline);
        simulatorcontroller.setBorder(blackline);
     //   graphview.setBorder(redline);

        queueview.setBounds(825, 275, 200, 70);
        simulatorview.setBounds(5, 5, 800, 400);
        infoview.setBounds(825, 5, 200, 130);
        pieview.setBounds(1075, 5, 225, 300);
        simulatorcontroller.setBounds(825, 150, 200, 100);
    //    graphview.setBounds(1350, 5, 250, 300);

        screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        screen.setVisible(true);
    }
}
