// File: Customer.java
// Purpose: Build customer objects from inputted parameters and calculate pricing.

public class Customer {
    // create variables for Customer objects
    String name; // holds name
    int hours; // holds number of hours
    int usageChoice; // holds cafe usage choice
    String usageString = ""; // holds cafe usage choice as string
    int speedChoice; // holds internet speed choice
    String speedString = ""; // holds internet speed choice as string

    // constructor creates object from inputted variables
    public Customer(String name, int hours, int usageChoice, int speedChoice)
    {
        this.name = name;
        this.hours = hours;
        this.usageChoice = usageChoice;
        this.speedChoice = speedChoice;
        setUsageString(usageChoice);
        setSpeedString(speedChoice);

    }

    // sets usage method as string
    private void setUsageString(int choice)
    {
        if (choice == 1)
            usageString = "BYOD";
        else if (choice == 2)
            usageString = "Shared Space";
        else if (choice == 3)
            usageString = "Private room";
    }

    // sets chosen speed as string
    private void setSpeedString(int choice)
    {
        if (choice == 1)
            speedString = "350KB/s";
        else if (choice == 2)
            speedString = "1MB/s";
        else if (choice == 3)
            speedString = "2MB/s";
        else if (choice == 4)
            speedString = "5MB/s";
        else if (choice == 5)
            speedString = "10MB/s";
    }

    // retrieves usage method string
    public String getUsageString() {
        return usageString;
    }

    // retrieves chosen speed string
    public String getSpeedString() {
        return speedString;
    }

    // retrieves customer name
    public String getName() {
        return name;
    }

    // retrieves customer hours
    public int getHours() {
        return hours;
    }

    // calculates cost for customer
    public double calculateCharge()
    {
        // declare variables for calculations
        double result = 0;
        final double BYOD_PRICE = 2.50;
        final double SHARED_DEVICE_PRICE = 3.00;
        final double PRIVATE_ROOM_PRICE = 4.25;

        // perform calculations based on chosen usage method and speed
        if (usageChoice == 1)
        {
            result = hours * (BYOD_PRICE + (BYOD_PRICE * determineSpeedPrice()));
            return result;
        }
        else if (usageChoice == 2)
        {
            result = hours * (SHARED_DEVICE_PRICE + (SHARED_DEVICE_PRICE * determineSpeedPrice()));
            return result;

        }
        else if (usageChoice == 3)
        {
            result = hours * (PRIVATE_ROOM_PRICE + (PRIVATE_ROOM_PRICE * determineSpeedPrice()));
            return result;
        }
        return 0;
    }

    // determines increase in hourly cost based on speed choice
    private double determineSpeedPrice()
    {
        // declare percentage price increases for internet speeds
        final double DEFAULT_SPEED = 0;         // 1
        final double MEDIUM_SPEED_1 = 0.10;     // 2
        final double MEDIUM_SPEED_2 = 0.20;     // 3
        final double HIGH_SPEED_1 = 0.30;       // 4
        final double HIGH_SPEED_2 = 0.40;       // 5

        double speed = 0.0;

        // sets percent increase in cost
        if (speedChoice == 1)
            speed = DEFAULT_SPEED;
        else if (speedChoice == 2)
            speed = MEDIUM_SPEED_1;
        else if (speedChoice == 3)
            speed = MEDIUM_SPEED_2;
        else if (speedChoice == 4)
            speed = HIGH_SPEED_1;
        else if (speedChoice == 5)
            speed = HIGH_SPEED_2;

        return speed;
    }
}
