package Logic;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;

    /**
     * Constructor for objects of class Logic.Car
     */
    public Car() {

    }

    /**
     * Get the location
     * @return The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set the location
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Get the amount of minutes left
     * @return The amount of minutes left
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Set the amount of minutes left
     * @param minutesLeft The minutes left
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }


    /**
     * Check if the car is paying
     * @return Boolean If the car is paying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * Set if the car is paying
     * @param isPaying Boolean If it is paying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public void tick() {
        minutesLeft--;
    }

}