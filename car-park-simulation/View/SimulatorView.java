package View;

import java.awt.*;

import Logic.*;


public class SimulatorView extends AbstractView {

    private Image carParkImage;
    private Dimension size;

    public SimulatorView(SimulatorModel sim) {
        super(sim);
        size = new Dimension();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(carParkImage, 0, 0, null);
        SimulatorModel sim = (SimulatorModel) super.sim;

        size = getSize();
        carParkImage = createImage(size.width, size.height);

        Graphics graphics = carParkImage.getGraphics();
        for (int floor = 0; floor < sim.getNumberOfFloors(); floor++) {
            for (int row = 0; row < sim.getNumberOfRows(); row++) {
                for (int place = 0; place < sim.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = sim.getCarAt(location);
                    if (car instanceof AdHocCar) {
                        Color color = car == null ? Color.white : Color.red;
                        drawPlace(graphics, location, color);
                    } else if (car instanceof ParkPassCar) {
                        Color color = car == null ? Color.white : Color.green;
                        drawPlace(graphics, location, color);
                    } else {
                        Color color = car == null ? Color.white : Color.green;
                        drawPlace(graphics, location, color);
                    }
                }
            }
        }
    }
        // Create a new car park image if the size has changed.

    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}

