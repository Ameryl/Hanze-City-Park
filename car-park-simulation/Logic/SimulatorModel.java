package Logic;

import Controller.SimulatorController;
import View.*;

import java.util.Random;

public class SimulatorModel extends AbstractModel implements Runnable{

    private static int numberOfFloors;
    private static int numberOfRows;
    private static int numberOfPlaces;

    public boolean run;

    private Car[][][] cars;

    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int omzet = 0;
    private int aantalCars = 0;

    private int tickPause = 100;
    private int typeCar = 0;
    


    int weekDayArrivals = 200; // average number of arriving cars per hour
    int weekendArrivals = 300; // average number of arriving cars per hour

    int enterSpeed = 9; // number of cars that can enter per minute
    int paymentSpeed = 10; // number of cars that can pay per minute
    int exitSpeed = 9; // number of cars that can leave per minute
    int parkPassChance = 1; // chance x/10 of a car having a parkpass instead of a normal customer
    int Reservationchance = 2; // chance of a car having a reservation instead of a normal customer

    public SimulatorModel(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;

        this.entranceCarQueue = new CarQueue();
        this.paymentCarQueue = new CarQueue();
        this.exitCarQueue = new CarQueue();

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {
      run = true;
        while(run){
           tick();
       }
    }

    private void tick() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int) Math.round(numberOfCarsPerHour / 60);

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            if (random.nextInt(10) < parkPassChance) {
                Car car = new ParkPassCar();
                entranceCarQueue.addCar(car);
                aantalCars++;
            }
                else if(random.nextInt(10) < Reservationchance) {
                    Car car = new ReservationCar();
                    entranceCarQueue.addCar(car);
                aantalCars++;
                }

             else {
                Car car = new AdHocCar();
                entranceCarQueue.addCar(car);
                aantalCars++;
            }
            infoView.setCarCount(aantalCars);
        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car instanceof ReservationCar){
                typeCar = 0;
            }
            else if (car instanceof ParkPassCar){
                typeCar = 0;
            }
            else {
                typeCar = 2;
            }
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = getFirstFreeLocation();
            if (freeLocation != null) {
                setCarAt(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }
        }

        // Perform car park tick.
        ticker();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = getFirstLeavingCar();
            if (car == null) {
                break;
            }

            if (car instanceof AdHocCar) {
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            } else if (car instanceof ParkPassCar) {
                removeCarAt(car.getLocation()); // Since no payment is required, directly remove the car.
                exitCarQueue.addCar(car);
            }
            else if(car instanceof ReservationCar) {
                removeCarAt(car.getLocation()); // Since no payment is required, directly remove the car.
                exitCarQueue.addCar(car);
            }
        }

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // TODO Handle payment.
            omzet += 5;
            infoView.setOmzetCount(omzet);

            removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
        }

        // Let cars leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Bye!
        }

        // Update the car park view.
         notifyViews();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        for (int floor = typeCar; floor < getNumberOfFloors(); floor++) {
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

    private void ticker() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = this.getCarAt(location);
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

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public int getWeekDayArrivals() {
        return weekDayArrivals;
    }

    public int getWeekendArrivals() {
        return weekendArrivals;
    }

    public int getEnterSpeed() {
        return enterSpeed;
    }

    public int getPaymentSpeed() {
        return paymentSpeed;
    }

    public int getExitSpeed() {
        return exitSpeed;
    }

    public int getParkPassChance() {
        return parkPassChance;
    }

    public int getTickPause() {
        return tickPause;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }

    public CarQueue getPaymentCarQueue() {
        return paymentCarQueue;
    }

    public CarQueue getExitCarQueue() {
        return exitCarQueue;
    }

    public void addEntranceCarQueue(Car addtoqueue) {
        entranceCarQueue.addCar(addtoqueue);
    }

    public void addPaymentCarQueue(Car addtoqueue) {
        paymentCarQueue.addCar(addtoqueue);
    }

    public void addExitCarQueue(Car addtoqueue) {
        exitCarQueue.addCar(addtoqueue);
    }

    public void incrementMinute() {
        minute++;
    }

    public void incrementHour() {
        hour++;
    }

    public void incrementDay() {
        day++;
    }

    public void setMinute(int value) {
        minute = value;
    }

    public void setHour(int value) {
        hour = value;
    }

    public void setDay(int value) {
        day = value;
    }

    public Car removeEntranceCarQueue() {
        return entranceCarQueue.removeCar();
    }

    public Car removePaymentCarQueue() {
        return paymentCarQueue.removeCar();
    }

    public Car removeExitCarQueue() {
        return exitCarQueue.removeCar();
    }

}
