package Controller;

import java.awt.event.ActionListener;
import java.awt.event.*;

import Logic.SimulatorModel;
import javax.swing.*;

public class SimulatorController extends AbstractController implements ActionListener {
    private JButton start;
    private JButton stop;
    private static JLabel omzetCount;

    public SimulatorController(SimulatorModel sim) {
        super(sim);

        start = new JButton("START");
        start.addActionListener(this);

        stop = new JButton("STOP");
        stop.addActionListener(this);

        omzetCount = new JLabel("Omzet : 0€");

        add(start);
        add(stop);
        add(omzetCount);

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

    public static void setOmzetCount(int omzet) {
        try {
            omzetCount.setText("Omzet: " + omzet + "€");
        }

        catch (Exception ex) {

        }
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
