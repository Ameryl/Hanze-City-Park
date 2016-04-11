package View;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import Logic.*;
import Runner.Simulator;

public class SimulatorView extends JFrame  {
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private Car[][][] cars;
    private JFrame frame;
    private Simulator sim;
    private JMenuItem runSim;
    private JMenuItem runTick;
    private JMenuItem runSteps;
    private JMenuItem quitSim;
    private JMenuItem pauseSim;
    private JButton plus;
    private JButton minus;
    private JLabel stepCount;

    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.sim = sim;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        carParkView = new CarParkView();
        makeFrame();
       // menuBar = new JMenuBar();

    }

    public void makeFrame(){
        frame = new JFrame("Car Park Simulator");
        makeMenuBar(frame);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        plus = new JButton("+");
        minus = new JButton("-");
        stepCount = new JLabel("Steps : 0");

        contentPane.add(plus);
        contentPane.add(minus);
        contentPane.add(stepCount);
        //contentPane.add(stepLabel, BorderLayout.NORTH);
        contentPane.add(carParkView, BorderLayout.CENTER);
        contentPane.add(carParkView);

        //contentPane.add(population, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        updateView();



    }

    private void makeMenuBar(JFrame frame){
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();


        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the File manu
        JMenu fileMenu = new JMenu("Simulation");
        menubar.add(fileMenu);

        runSim = new JMenuItem("Run");
        runSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
        fileMenu.add(runSim);

        runTick = new JMenuItem("Tick");
        runTick.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        fileMenu.add(runTick);

        runSteps = new JMenuItem("100 Ticks");
        runSteps.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
        fileMenu.add(runSteps);


        quitSim = new JMenuItem("Quit");
        quitSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, SHORTCUT_MASK));
        fileMenu.add(quitSim);
        fileMenu.add(quitSim);

        pauseSim = new JMenuItem("pause");
        pauseSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        fileMenu.add(pauseSim);

    }

    public void updateView() {
        carParkView.updateView();
    }
    
     public int getNumberOfFloors() {
            return numberOfFloors;
        }
    
        public int getNumberOfRows() {
            return numberOfRows;
        }
    
        public int getNumberOfPlaces() {
            return numberOfPlaces;
        }
    
        public Car getCarAt(Location location) {
            if (!locationIsValid(location)) {
                return null;
            }
            return cars[location.getFloor()][location.getRow()][location.getPlace()];
        }
    
        public boolean setCarAt(Location location, Car car) {
            if (!locationIsValid(location)) {
                return false;
            }
            Car oldCar = getCarAt(location);
            if (oldCar == null) {
                cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
                car.setLocation(location);
                return true;
            }
            return false;
        }
    
        public Car removeCarAt(Location location) {
            if (!locationIsValid(location)) {
                return null;
            }
            Car car = getCarAt(location);
            if (car == null) {
                return null;
            }
            cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
            car.setLocation(null);
            return car;
        }
    
        public Location getFirstFreeLocation() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        if (getCarAt(location) == null) {
                            return location;
                        }
                    }
                }
            }
            return null;
        }
    
        public Car getFirstLeavingCar() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                            return car;
                        }
                    }
                }
            }
            return null;
        }
    
        public void tick() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        if (car != null) {
                            car.tick();
                        }
                    }
                }
            }
        }
    
        private boolean locationIsValid(Location location) {
            int floor = location.getFloor();
            int row = location.getRow();
            int place = location.getPlace();
            if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
                return false;
            }
            return true;
        }
    
    private class CarParkView extends JPanel {
        
        private Dimension size;
        private Image carParkImage;    
    
        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView() {
            size = new Dimension(0, 0);
        }
    
        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }
    
        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }
    
            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }
    
        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        if(car instanceof AdHocCar) {
                            Color color = car == null ? Color.white : Color.red;
                            drawPlace(graphics, location, color);
                        }

                        else if(car instanceof ParkPassCar) {
                            Color color = car == null ? Color.white : Color.green;
                            drawPlace(graphics, location, color);
                        }

                        else {
                            Color color = car == null ? Color.white : Color.green;
                            drawPlace(graphics, location, color);
                        }

                    }
                }
            }
            repaint();
        }


    
        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }

    public void addRunListener(ActionListener listenForButtons) {
        runSim.addActionListener(listenForButtons);
    }

    public void addTickListener(ActionListener listenForButtons) {
        runTick.addActionListener(listenForButtons);
    }

    public void addRunStepsListener(ActionListener listenForButtons) {
        runSteps.addActionListener(listenForButtons);
    }

    public void addQuitSimListener(ActionListener listenForButtons) {
        quitSim.addActionListener(listenForButtons);
    }

    public void addStopSimListener(ActionListener listenForButtons) {
        pauseSim.addActionListener(listenForButtons);
    }

    public void addPlusSimListener(ActionListener listenForButtons) {
        plus.addActionListener(listenForButtons);
    }

    public void addMinusSimListener(ActionListener listenForButtons) {
        minus.addActionListener(listenForButtons);
    }

    public void setStepCounterValue(int steps) {
        try {
            stepCount.setText("Steps : " + steps);
        }

        catch (Exception ex) {

        }
    }

}

