package View;

import Logic.SimulatorModel;

import javax.swing.*;

/**
 * Created by Cyriel on 11-4-2016.
 */
public class infoView extends AbstractView {
    private static JLabel omzetCount;
    private static JLabel carCount;
    private static JLabel currentCount;

    public infoView(SimulatorModel sim) {
        super(sim);
        omzetCount = new JLabel("Omzet : 0€");
        carCount = new JLabel("Aantal bezochte auto's: 0");
        currentCount = new JLabel("aantal huidige auto's: 0");




        add(omzetCount);
        add(carCount);
        add(currentCount);
    }

    public static void setOmzetCount(int omzet) {
        try {
            omzetCount.setText("Omzet: " + omzet + "€");
        }

        catch (Exception ex) {

        }
    }

    public static void setCarCount(int aantalCars) {
        try {
            carCount.setText("Aantal bezochte auto's: " + aantalCars);
        }

        catch (Exception ex) {

        }
    }

    public static void setCurrentCarCount(int currentCars) {
        try {
            currentCount.setText("Aantal huidige auto's: " + currentCars);
        }

        catch (Exception ex) {

        }
    }
//HENK IS DIK!
    // Niek is ook dik!!

}
