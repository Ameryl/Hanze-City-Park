package View;

import Logic.SimulatorModel;

import javax.swing.*;

/**
 * This class creates a new info view.
 * In te view itself there will be several JLables which provide some text base information.
 */
public class QueueView extends AbstractView {
    private static JLabel queueCountIn;
    private static JLabel queueCountOut;
    private static JLabel title;

    /**
     * The constructor for the infoview.
     * @param sim
     */
    public QueueView(SimulatorModel sim) {
        super(sim);
        queueCountIn = new JLabel("Enter queue : "+ sim.getEnterQueueCount());
        queueCountOut = new JLabel("Exit queue : "+ sim.getExitQueueCount());
        title = new JLabel("Information");



        this.setLayout(null);
        queueCountIn.setBounds(5, 0 , 200, 50);
        queueCountOut.setBounds(5, 20 , 200, 50);
        title.setBounds(70, -60, 150, 100);

        add(queueCountIn);
        add(queueCountOut);
        add(title);

    }
    @Override
    /**
     * This method will  update the whole view.
     * which means it sets the text on the lables.
     */
    public void updateView() {
        queueCountIn.setText("Enter queue : "+ sim.getEnterQueueCount());
        queueCountOut.setText("Exit queue: " + sim.getExitQueueCount());
        System.out.println(sim.getExitQueueCount());

    }
}
