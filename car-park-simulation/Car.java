public abstract class Car {

    //Location for a car
    private Location location;

    // Minutes the car has left for parking
    private int minutesLeft;

    // Boolean, if the car is paying
    private boolean isPaying;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }

    /**
     * Getting the location of the car object
     * @return Location of the car object
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set the location of a car in the garage
     * @param location Location of a car
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Get the time the car is staying in the garage
     * @return int amount of minutes
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Set the location of a car in the garage
     * @param minutesLeft int amount of minutes the car is going to be in the garage
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * Checks if the car is paying
     * @return boolean if the car is paying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * Set if the car is paying
     * @param isPaying boolean if the car is paying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * Method for decreasing the amount of minutes the car has left for staying at the garage
     */
    public void tick() {
        minutesLeft--;
    }

}