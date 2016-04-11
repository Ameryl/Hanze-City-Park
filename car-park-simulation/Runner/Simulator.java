package Runner; /**
 * The simulation of a carpark system. It simulates the possibilities of how
 * a car is parked.
 *
 */

import Logic.*;
import View.SimulatorView;

import java.util.Random;

public class Simulator {

    // Object for entering cars
    private CarQueue entranceCarQueue;

    // Object for paying cars
    private CarQueue paymentCarQueue;

    //Object for exiting cars
    private CarQueue exitCarQueue;

    // Instance or the graphical display of the simulation
    private SimulatorView simulatorView;

    // Time intervals
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 10; // number of cars that can pay per minute
    int exitSpeed = 9; // number of cars that can leave per minute
    int parkPassChance = 1; // chance x/10 of a car having a parkpass instead of a normal customer
    int Reservationchance = 2; // chance of a car having a reservation instead of a normal customer

    /**
     * Constructor for the simulation
     */
    public Simulator() {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30);
    }


    /**
     * Running the simulation for a duration
     * @param steps amount of steps
     */
    public void run(int steps) {
        for (int i = 0; i < steps; i++) {
            tick();
        }
    }

    /**
     * Executing the simulation per minutes
     */
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
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            if(random.nextInt(10) < parkPassChance) {
                Car car = new ParkPassCar();
                entranceCarQueue.addCar(car);
            }

            else if(random.nextInt(10) < Reservationchance) {
                Car car = new ReservationCar();
                entranceCarQueue.addCar(car);
            }
            else {
                Car car = new AdHocCar();
                entranceCarQueue.addCar(car);
            }


        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = simulatorView.getFirstFreeLocation();
            if (freeLocation != null) {
                simulatorView.setCarAt(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }
        }

        // Perform car park tick.
        simulatorView.tick();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = simulatorView.getFirstLeavingCar();
            if (car == null) {
                break;
            }

            if(car instanceof AdHocCar) {
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            }

            else if(car instanceof ParkPassCar) {
                simulatorView.removeCarAt(car.getLocation()); // Since no payment is required, directly remove the car.
                exitCarQueue.addCar(car);
            }
            else if(car instanceof ReservationCar) {
                simulatorView.removeCarAt(car.getLocation());
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


            simulatorView.removeCarAt(car.getLocation());
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
        simulatorView.updateView();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}