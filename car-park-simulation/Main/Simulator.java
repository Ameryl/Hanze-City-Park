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
import java.awt.event.KeyEvent;


public class Simulator {
    /**
     * Constructor for the simulati
     */
    private JFrame frame;
    private JMenuItem runSim;
    private JMenuItem runTick;
    private JMenuItem runSteps;
    private JMenuItem quitSim;
    private JMenuItem pauseSim;
    private AbstractView SimulatorView;
    private SimulatorModel simModel;
    private AbstractController SimulatorController;

    public Simulator() {
        simModel = new SimulatorModel(3, 6, 30);
        SimulatorController = new SimulatorController(simModel);
        SimulatorView = new SimulatorView(simModel);
        frame = new JFrame("Car Park Simulator");
        frame.setSize(900, 500);
        makeMenuBar(frame);
        //contentPane.add(stepLabel, BorderLayout.NORTH);

        frame.getContentPane().add(SimulatorController);
        frame.getContentPane().add(SimulatorView);
        frame.setVisible(true);
        simModel.notifyViews();

    }

    private void makeMenuBar(JFrame frame){
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the File manu
        JMenu fileMenu = new JMenu("Simulation");
        menubar.add(fileMenu);

        runSim = new JMenuItem("Run");
        runSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
        fileMenu.add(runSim);

        runTick = new JMenuItem("Tick");
        runTick.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        fileMenu.add(runTick);

        runSteps = new JMenuItem("100 Ticks");
        runSteps.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
        fileMenu.add(runSteps);


        quitSim = new JMenuItem("Quit");
        quitSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, SHORTCUT_MASK));
        fileMenu.add(quitSim);
        fileMenu.add(quitSim);

        pauseSim = new JMenuItem("pause");
        pauseSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        fileMenu.add(pauseSim);

    }
}


