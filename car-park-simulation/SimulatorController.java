import org.omg.CORBA.Environment;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.*;


public  class SimulatorController {
    private SimulatorView view;
    private SimulatorModel model;

    public SimulatorController(SimulatorView view, SimulatorModel model) {
        this.view = view;
        this.model = model;

        this.view.addRunListener(new RunListener());
        this.view.addRunStepsListener(new RunStepsListener());
        this.view.addQuitSimListener(new QuitSimListener());
        this.view.addTickListener(new TickListener());
    }

    class RunListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            try {

                run(10000);
            }

            catch(Exception e) {

            }
        }
    }

    class TickListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            try {
                tick();
            }

            catch(Exception e) {

            }
        }
    }

    class RunStepsListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            try {
                run(100);
            }

            catch(Exception e) {

            }
        }
    }

    class QuitSimListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            try {
                System.exit(0);
            }

            catch(Exception e) {

            }
        }
    }

    public void run(int steps) {
        Thread thread = new Thread() {
            public void run() {
                for (int i = 0; i < steps; i++) {
                    tick();
                }
            }

        };
        thread.start();

    }

    private void tick() {
        // Advance the time by one minute.
        model.incrementMinute();
        while (model.getMinute() > 59) {
            model.setMinute(model.getMinute() - 60);
            model.incrementHour();
        }
        while (model.getHour() > 23) {
            model.setHour(model.getHour() - 24);
            model.incrementDay();
        }
        while (model.getDay() > 6) {
            model.setDay(model.getDay() - 7);
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = model.getDay() < 5
                ? model.getWeekDayArrivals()
                : model.getWeekendArrivals();

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            if(random.nextInt(10) < model.getParkPassChance()) {
                Car car = new ParkPassCar();
                model.addEntranceCarQueue(car);
            }
            else {
                Car car = new AdHocCar();
                model.addEntranceCarQueue(car);
            }


        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < model.getEnterSpeed(); i++) {
            Car car = model.removeEntranceCarQueue();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = view.getFirstFreeLocation();
            //TODO relocate method to controller.
            if (freeLocation != null) {
                view.setCarAt(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }
        }

        // Perform car park tick.
        //TODO port call to controller.
        view.tick();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = view.getFirstLeavingCar();
            if (car == null) {
                break;
            }

            if(car instanceof AdHocCar) {
                car.setIsPaying(true);
                model.addPaymentCarQueue(car);
            }

            else if(car instanceof ParkPassCar) {
                view.removeCarAt(car.getLocation());
                model.addExitCarQueue(car);
            }
        }

        // Let cars pay.
        for (int i = 0; i < model.getPaymentSpeed(); i++) {
            Car car = model.removePaymentCarQueue();
            if (car == null) {
                break;
            }
            // TODO Handle payment.


            view.removeCarAt(car.getLocation());
            model.addExitCarQueue(car);
        }

        // Let cars leave.
        for (int i = 0; i < model.getExitSpeed(); i++) {
            Car car = model.removeExitCarQueue();
            if (car == null) {
                break;
            }
            // Bye!
        }

        // Update the car park view.
        view.updateView();

        // Pause.
        try {
            Thread.sleep(model.getTickPause());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}