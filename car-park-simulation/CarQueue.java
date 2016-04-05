import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for creating a queue of car objects
 * This class is used for adding or removing cars to a queue
 */
public class CarQueue {

    // Queue collection
    private Queue<Car> queue = new LinkedList<>();

    /**
     * Check whether a car has been added to the queue
     * @param car added to queue
     * @return boolean if the car has been added
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Obtain a car object from the queue and remove it
     * @return car object from the queue
     */
    public Car removeCar() {
        return queue.poll();
    }

}
