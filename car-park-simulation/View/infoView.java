package View;

import Logic.SimulatorModel;

import javax.swing.*;

/**
 * Created by Cyriel on 11-4-2016.
 */
public class infoView extends AbstractView {
    private static JLabel omzetCount;

    public infoView(SimulatorModel sim) {
        super(sim);
        omzetCount = new JLabel("Omzet : 0€");
        add(omzetCount);
    }


}
