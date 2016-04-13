package View;

import Logic.SimulatorModel;

import javax.swing.*;

/**
 * This class creates a new info view.
 * In te view itself there will be several JLables which provide some text base information.
 */
public class infoView extends AbstractView {
    private static JLabel revenueCount;
    private static JLabel carCount;
    private static JLabel currentCount;
    private static JLabel currentTime;
    private static JLabel title;
    private static JLabel expectedRevenue;

    /**
     * The constructor for the infoview.
     * @param sim
     */
    public infoView(SimulatorModel sim) {
        super(sim);
        revenueCount = new JLabel("Revenue : "+ sim.getRevenue() + "€");
        carCount = new JLabel("Cars visited: " + sim.getAmountOfCars());
        currentCount = new JLabel("Amount of current cars: " + sim.getCurrentCars());
        currentTime = new JLabel("Time : " + sim.getHour() + ":" + sim.getMinute());
        title = new JLabel("Information");
        expectedRevenue = new JLabel("expectedRevenue : " + sim.getExpectedRevenue() + "€");


        this.setLayout(null);
        currentTime.setBounds(5, 0 , 200, 50);
        revenueCount.setBounds(5, 20 , 200, 50);
        expectedRevenue.setBounds(5, 40, 200, 50);
        currentCount.setBounds(5, 60 , 200, 50);
        carCount.setBounds(5, 80 , 200, 50);
        title.setBounds(70, -60, 150, 100);
        add(currentTime);
        add(revenueCount);
        add(carCount);
        add(currentCount);
        add(title);
        add(expectedRevenue);
    }
    @Override
    /**
     * This method will  update the whole view.
     * which means it sets the text on the lables.
     */
    public void updateView() {
        revenueCount.setText("Revenue : "+ sim.getRevenue() + "€");
        carCount.setText("Cars visited: " + sim.getAmountOfCars());
        currentCount.setText("Amount of current cars: " + sim.getCurrentCars() + " / " + sim.getTotalSpots());
        currentTime.setText("Time : " + sim.getHour() + ":" + sim.getMinute());
        expectedRevenue.setText("ExpectedRevenue : " + sim.getExpectedRevenue() + "€");
    }
}
