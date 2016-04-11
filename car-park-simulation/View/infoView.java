package View;

import Logic.AbstractModel;
import Logic.SimulatorModel;

import javax.swing.*;

/**
 * Created by Cyriel on 11-4-2016.
 */
public class infoView extends AbstractView {
    private JLabel Labeltje;

    public infoView(SimulatorModel sim) {
        super(sim);
        this.Labeltje = new JLabel();
        Labeltje.setText("DIT IS HET ENIGE WAT IN DIE KUT VIEW GEZIEN MOET WORDEN");
        add(Labeltje);
    }
}
