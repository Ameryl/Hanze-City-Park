package View;
import Logic.SimulatorModel;

import java.awt.*;

public class PieView extends AbstractView {


    public PieView(SimulatorModel sim) {
        super(sim);
    }

    public void paintComponent(Graphics g){

        int amountofregcars = sim.getAmountOfRegularCarsInSim();
        int amountofparkpasscars = sim.getAmountOfParkPassCarsInSim();
        int amountofreservationcars = sim.getAmountOfReservationCarsInSim();

        //Draw white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 225, 300);

        //Draw chart legend
        g.setColor(Color.RED);
        g.fillRect(10, 225, 20 - 1, 10 - 1);

        g.setColor(Color.GREEN);
        g.fillRect(10, 250, 20 - 1, 10 - 1);

        g.setColor(Color.BLUE);
        g.fillRect(10, 275, 20 - 1, 10 - 1);

        //Set size of pieview
        Rectangle abs = new Rectangle();
        abs.setSize(225, 200);

        Slice[] slices = {new Slice(amountofregcars, Color.RED),
                new Slice(amountofparkpasscars, Color.GREEN),
                new Slice(amountofreservationcars, Color.BLUE)};
        drawPie((Graphics2D) g, abs, slices);
    }

    private void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
        double total = 0.0D;
        for (int i = 0; i < slices.length; i++) {
            total += slices[i].value;
        }
        double curValue = 0.0D;
        int startAngle = 0;
        for (int i = 0; i < slices.length; i++) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices[i].value * 360 / total);
            g.setColor(slices[i].color);
            g.fillArc(area.x, area.y, area.width, area.height,
                    startAngle, arcAngle);
            curValue += slices[i].value;
        }
    }
}

class Slice {
    double value;
    Color color;
    public Slice(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}