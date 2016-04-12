package View;
import Logic.SimulatorModel;

import java.awt.*;

public class PieView extends AbstractView {

    public PieView(SimulatorModel sim) {
        super(sim);
    }

    public void paintComponent(Graphics g) {
        int typeofcar = getModel().getTypeCar();

        int amount = getModel().getAmountOfCars();
        int amountpercentage = amount / 3;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 200, 200);

        if (typeofcar == 0) {
            // Reservation Car BLUE

            g.setColor(Color.BLUE);
        } else if (typeofcar == 1) {
            // Park Pass car GREEN
            g.setColor(Color.GREEN);
        } else if (typeofcar == 2) {
            // Regular car RED
            g.setColor(Color.RED);
        }
        g.fillArc(10, 10, 180, 180, 0, amountpercentage);
    }

}