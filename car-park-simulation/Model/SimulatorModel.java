package Model;

import Logic.Car;
import Logic.CarQueue;

public class SimulatorModel {

    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

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
    int reservationChance = 2;

    public SimulatorModel() {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
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

    public int getReservationChance() {return reservationChance;}

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

    public void incrementMinute() { minute++; }

    public void incrementHour() { hour++; }

    public void incrementDay() { day++; }

    public void setMinute(int value) { minute = value; }

    public void  setHour(int value) {hour = value; }

    public void setDay(int value) {day = value; }



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
