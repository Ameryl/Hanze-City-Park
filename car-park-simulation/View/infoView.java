package View;

import Logic.SimulatorModel;

import javax.swing.*;

/**
 * Created by Cyriel on 11-4-2016.
 */
public class infoView extends AbstractView {
    private static JLabel omzetCount;
    private static JLabel carCount;

    public infoView(SimulatorModel sim) {
        super(sim);
        omzetCount = new JLabel("Omzet : 0€");
        carCount = new JLabel("Aantal auto's: 0");


        add(omzetCount);
        add(carCount);
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
            carCount.setText("Aantal auto's: " + aantalCars);
        }

        catch (Exception ex) {

        }
    }


}
