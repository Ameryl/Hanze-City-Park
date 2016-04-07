import java.util.Random;


public  class SimulatorController {
    private SimulatorView view;
    private SimulatorModel model;

    public SimulatorController(SimulatorView view, SimulatorModel model) {
        this.view = view;
        this.model = model;
    }

    public void run(int steps) {
        for (int i = 0; i < steps; i++) {
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
                ? model.getWeekDayArrivals
                : model.getWeekendArrivals;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = model.getAverageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            if(random.nextInt(10) < model.getParkPassChance) {
                Car car = new ParkPassCar();
                model.addEntranceCarQueue(car);
            }
            else {
                Car car = new AdHocCar();
                model.addEntranceCarQueue(car);
            }


        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
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
                car.setIsPaying(true);
                model.addExitCarQueue(car);
            }
        }

        // Let cars pay.
        for (int i = 0; i < model.getPaymentSpeed; i++) {
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
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}