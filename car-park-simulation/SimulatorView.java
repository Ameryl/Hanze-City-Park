import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/**
 * Jframe for the simulator
 * Draws parking spots
 */
public class SimulatorView extends JFrame   {
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private Car[][][] cars;
    private JFrame frame;
    private Simulator sim;

    /**
     * Constructor
     *
     * @param numberOfFloors int amount of floors to draw
     * @param numberOfRows int amount of rows to draw
     * @param numberOfPlaces int amount of spots per row to draw
     * @param sim
     */
    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces, Simulator sim) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.sim = sim;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        carParkView = new CarParkView();
        makeFrame();
       // menuBar = new JMenuBar();

    }

    /**
     * Creates the window
     */
    public void makeFrame(){
        frame = new JFrame("ParkingGarage Simulator");
        makeMenuBar(frame);


        Container contentPane = frame.getContentPane();
        //contentPane.add(stepLabel, BorderLayout.NORTH);
        contentPane.add(carParkView, BorderLayout.CENTER);
        contentPane.add(carParkView);



        //contentPane.add(population, BorderLayout.SOUTH);


        frame.pack();
        frame.setVisible(true);



        updateView();



    }

    /**
     * Creates menubar
     * @param frame
     */
    private void makeMenuBar(JFrame frame){
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();


        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the File manu
        JMenu fileMenu = new JMenu("Simulation");
        menubar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Run");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
        openItem.addActionListener(new ActionListener() {

            /**
             * If run button is clicked or key combination is used, perform an action
             * @param e the action
             */
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread() {
                    public void run() {
                        sim.run(10000);
                    }
                };
                thread.start();

            }
        });
        fileMenu.add(openItem);

        JMenuItem runStep = new JMenuItem("Tick");
        runStep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        runStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Thread thread = new Thread() {
                    public void run() {
                        sim.run(1);
                    }
                };
                thread.start();
            }
        });
        fileMenu.add(runStep);

        JMenuItem runSteps = new JMenuItem("100 Ticks");
        runSteps.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
        runSteps.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Thread thread = new Thread() {
                    public void run() {
                        sim.run(100);
                    }
                };
                thread.start();
            }
        });
        fileMenu.add(runSteps);


        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, SHORTCUT_MASK));
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { quit(); }
        });
        fileMenu.add(quitItem);





    }


    /**
     * Quit the program
     */
    private void quit()
    {
        System.exit(0);
    }

    /**
     * Update the view
     */
    public void updateView() {
        carParkView.updateView();
    }

    /**
     * Get the amount of floors
     * @return Amount of floors
     */
     public int getNumberOfFloors() {
            return numberOfFloors;
        }

    /**
     * Get the amount of rows
     * @return amount of rows
     */
    public int getNumberOfRows() {
            return numberOfRows;
        }

    /**
     * Get the amount of places
     * @return amount of places
     */
    public int getNumberOfPlaces() {
            return numberOfPlaces;
        }

    /**
     * Get a car from a location
     * @param location Location where to get the car from
     * @return Car that is located at the given location
     */
    public Car getCarAt(Location location) {
            if (!locationIsValid(location)) {
                return null;
            }
            return cars[location.getFloor()][location.getRow()][location.getPlace()];
        }

    /**
     * Set a car in a locaiton
     * @param location Location where to put the car
     * @param car The car object
     * @return Boolean if the car is placed
     */
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

    /**
     * Remove a car
     * @param location Location of the to be removed car
     * @return Null if failed, Car object when it succeeded
     */
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

    /**
     * Gets the first free parking spot
     * @return Location of a free spot, otherwise null
     */
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

    /**
     * Retrieve the first leaving car in the garage
     * @return Car when a ar is found, otherwise null
     */
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

    /**
     * A tick of the simulation
     */
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

    /**
     * Check if a location is valid or not
     * @param location Location to check
     * @return Boolean if it is valid
     */
    private boolean locationIsValid(Location location) {
            int floor = location.getFloor();
            int row = location.getRow();
            int place = location.getPlace();
            if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
                return false;
            }
            return true;
        }

    /**
     * Graphical look of the car park. Draws floors, rows and parking spots
     */
    private class CarParkView extends JPanel {
        
        // The size
        private Dimension size;

        // Carpark image
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

}
