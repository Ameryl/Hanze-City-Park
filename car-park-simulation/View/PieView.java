package View;
import Logic.SimulatorModel;


import javax.swing.*;
import java.awt.*;

public class PieView extends AbstractView {
    private static JLabel regularCarLabel;
    private static JLabel parkPassCarLabel;
    private static JLabel reservationCarLabel;


    public PieView(SimulatorModel sim) {
        super(sim);
        regularCarLabel = new JLabel("Amount of Regular Cars : " + sim.getAmountOfRegularCarsInSim());
        parkPassCarLabel = new JLabel("Amount of parkpass Cars : " + sim.getAmountOfParkPassCarsInSim());
        reservationCarLabel = new JLabel("Amount of reservation Cars : " + sim.getAmountOfReservationCarsInSim());
        setLayout(null);
    }

    public void paintComponent(Graphics g){

        regularCarLabel.setBounds(35, 204, 200, 50);
        parkPassCarLabel.setBounds(35, 229, 200, 50);
        reservationCarLabel.setBounds(35, 254, 200, 50);

        add(parkPassCarLabel);
        add(reservationCarLabel);
        add(regularCarLabel);

        regularCarLabel.setText("Amount of Regular Cars : " + sim.getAmountOfRegularCarsInSim());
        parkPassCarLabel.setText("Amount of parkpass Cars : " + sim.getAmountOfParkPassCarsInSim());
        reservationCarLabel.setText("Amount of reservation Cars : " + sim.getAmountOfReservationCarsInSim());

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