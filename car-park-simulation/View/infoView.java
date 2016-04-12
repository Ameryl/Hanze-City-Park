package View;

import Logic.SimulatorModel;

import javax.swing.*;

/**
 * Created by Cyriel on 11-4-2016.
 */
public class infoView extends AbstractView {
    private static JLabel revenueCount;
    private static JLabel carCount;
    private static JLabel currentCount;
    private static JLabel currentTime;


    public infoView(SimulatorModel sim) {
        super(sim);
        revenueCount = new JLabel("Revenue : "+ sim.getRevenue() + "€");
        carCount = new JLabel("Cars visited: " + sim.getAmountOfCars());
        currentCount = new JLabel("Amount of current cars: " + sim.getCurrentCars());
        currentTime = new JLabel("Time : " + sim.getHour() + ":" + sim.getMinute());


        // REMINDER : DE LAYOUT AANPASSEN IS WERK VOOR NIEK
        // - Cyriel

        /*
        this.setLayout(null);
        currentTime.setBounds(50, 50 , 50, 50);
        revenueCount.setBounds(50, 50 , 50, 50);
        currentCount.setBounds(50, 50 , 50, 50);
        carCount.setBounds(50, 50 , 50, 50);
        */
        add(currentTime);
        add(revenueCount);
        add(carCount);
        add(currentCount);

    }
    @Override
    public void updateView() {
        revenueCount.setText("Revenue : "+ sim.getRevenue() + "€");
        carCount.setText("Cars visited: " + sim.getAmountOfCars());
        currentCount.setText("Amount of current cars: " + sim.getCurrentCars());
        currentTime.setText("Time : " + sim.getHour() + ":" + sim.getMinute());

    }
}
