package Logic;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();

    /**
     * Add car to queue
     * @param car The car object
     * @return The car
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Remove a car
     * @return A car from the queue
     */
    public Car removeCar() {
        return queue.poll();
    }

}
