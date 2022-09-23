// File: BitsAndBytesInternetCafe.java
// Purpose: Use GUI to enter info and calculate pricing for an internet cafe

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BitsAndBytesInternetCafe extends JFrame implements ActionListener {

    // create variables
    private final int MAX_GUESTS = 9; // determines maximum number of guests
    private Customer customerArray[] = new Customer[MAX_GUESTS]; // array will hold customer objects.
    private int arrayIndex = 0; // index used to iterate through the customer array.

    // create panels
    JPanel namePanel = new JPanel();
    JPanel nameOutPanel = new JPanel();
    JPanel hourPanel = new JPanel();
    JPanel hourOutPanel = new JPanel();
    JPanel usagePanel = new JPanel();
    JPanel speedPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    // create labels
    JLabel nameLabel = new JLabel("Enter your name");
    JLabel givenNameLabel = new JLabel("Name given is: ");
    JLabel nameOutLabel = new JLabel("");
    JLabel hourLabel = new JLabel("Enter number of hours");
    JLabel givenHourLabel = new JLabel("Hours entered is: ");
    JLabel hourOutLabel = new JLabel("");
    JLabel usageLabel = new JLabel("Please select how you intend to use the internet cafe");
    JLabel speedLabel = new JLabel("Please select your internet speed package");

    // create text fields for user input
    JTextField nameField = new JTextField(10);
    JTextField hourField = new JTextField(7);

    // create radio buttons for internet speed selection
    JRadioButton byod = new JRadioButton("BYOD");
    JRadioButton sharedSpace = new JRadioButton("shared space");
    JRadioButton privateRoom = new JRadioButton("Private room");
    JRadioButton defaultSpeed = new JRadioButton("350KB/s");
    JRadioButton mediumSpeed1 = new JRadioButton("1MB/s");
    JRadioButton mediumSpeed2 = new JRadioButton("2MB/s");
    JRadioButton highSpeed1 = new JRadioButton("5MB/s");
    JRadioButton highSpeed2 = new JRadioButton("10MB/s");

    // create radio button groups
    ButtonGroup internetGroup = new ButtonGroup();
    ButtonGroup usageGroup = new ButtonGroup();

    // create buttons
    JButton enterButton = new JButton("Enter");
    JButton DisplayChargesButton = new JButton("Display all charges");
    JButton exitButton = new JButton("Exit");

    // constructor creates JFrame object
    private BitsAndBytesInternetCafe()
    {

        // add preferred usage radio buttons to button group
        usageGroup.add(byod);
        usageGroup.add(sharedSpace);
        usageGroup.add(privateRoom);

        // add internet speed radio buttons to button group
        internetGroup.add(defaultSpeed);
        internetGroup.add(mediumSpeed1);
        internetGroup.add(mediumSpeed2);
        internetGroup.add(highSpeed1);
        internetGroup.add(highSpeed2);

        // implement button functionality
        this.enterButton.addActionListener(this);
        this.DisplayChargesButton.addActionListener(this);
        this.exitButton.addActionListener(this);

        // add prompt for name into panel
        this.namePanel.add(nameLabel);
        this.namePanel.add(nameField);

        // add info for entered name into panel
        this.nameOutPanel.add(givenNameLabel);
        this.nameOutPanel.add(nameOutLabel);

        // add prompt for hours into panel
        this.hourPanel.add(hourLabel);
        this.hourPanel.add(hourField);

        // add info for entered hours into panel
        this.hourOutPanel.add(givenHourLabel);
        this.hourOutPanel.add(hourOutLabel);

        // add usage radio buttons into panel
        this.usagePanel.add(byod);
        this.usagePanel.add(sharedSpace);
        this.usagePanel.add(privateRoom);

        // add internet speed radio buttons into panel
        this.speedPanel.add(defaultSpeed);
        this.speedPanel.add(mediumSpeed1);
        this.speedPanel.add(mediumSpeed2);
        this.speedPanel.add(highSpeed1);
        this.speedPanel.add(highSpeed2);

        // add buttons into panel
        this.buttonPanel.add(enterButton);
        this.buttonPanel.add(DisplayChargesButton);
        this.buttonPanel.add(exitButton);

        // implement GUI components
        this.add(namePanel);
        this.add(nameOutPanel);
        this.add(hourPanel);
        this.add(hourOutPanel);
        this.add(usageLabel);
        this.add(usagePanel);
        this.add(speedLabel);
        this.add(speedPanel);
        this.add(buttonPanel);

        // configure GUI specifications
        this.setTitle("BitsAndBytes System");
        this.setSize(420,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridLayout(9,1));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    // Displays welcome message
    private static void welcomeMessgage()
    {
        displayMessage("Welcome to the BitsAndBytes internet cafe!\n" +
                "---------------------------------------------------------------\n" +
                "Pricing is hourly and is as follows\n" +
                "BYOD:         $2.50 per hour\n" +
                "Shared space: $3.00 per hour\n" +
                "Private room: $4.25 per hour\n" +
                "---------------------------------------------------------------\n" +
                "speed packages are as follows\n" +
                "350KB/s:   default\n" +
                "1MB/s:      hourly rate +10%\n" +
                "2MB/s:      hourly rate +20%\n" +
                "5MB/s:      hourly rate +30%\n" +
                "10MB/s:     hourly rate +40%");
    }

    // resets text fields for GUI
    private void resetFields(JTextField field1, JTextField field2)
    {
        field1.setText("");
        field2.setText("");
    }

    // sets usage method based on selected radio button
    private int selectUsage()
    {
        int usage = 0;
        if (byod.isSelected())
            usage = 1;
        else if (sharedSpace.isSelected())
            usage = 2;
        else if (privateRoom.isSelected())
            usage = 3;
        return usage;
    }

    // sets internet speed choice based on selected radio button
    private int selectSpeed()
    {
        int speed = 0;
        if (defaultSpeed.isSelected())
            speed = 1;
        else if (mediumSpeed1.isSelected())
            speed = 2;
        else if (mediumSpeed2.isSelected())
            speed = 3;
        else if (highSpeed1.isSelected())
            speed = 4;
        else if (highSpeed2.isSelected())
            speed = 5;
        return speed;
    }

    // calculates total charges of all entries
    private double calculateTotalCharges()
    {
        double result = 0; //holds calculation result

        // adds all charges together
        for (int i = 0; i < customerArray.length && customerArray[i] != null; i++)
        {
            result += customerArray[i].calculateCharge();
        }
        return result;
    }

    // calculates average price of all entries
    private double calculateAverage()
    {

        // create variables for calculating average
        double divisor = 0;                           // holds the divisor
        double dividend = calculateTotalCharges();    // holds the dividend
        double quotient = 0;                          // holds result of calculation

        // determine number of customers
        for (int i = 0; i < customerArray.length && customerArray[i] != null; i++)
        {
            divisor++;
        }

        quotient = dividend / divisor; // calculates
        return quotient;
    }

    // displays total charges and average charge
    private void displayCharges()
    {
        // error control checks for empty array
        if (customerArray[0] == null)
        {
            displayError("No entries");
            nameField.requestFocus();
            return;
        }


        displayMessage(String.format("Total charges is: $%.2f\nAverage cost is: $%.2f" //displays info popup
                ,calculateTotalCharges()
                ,calculateAverage()));

        nameField.requestFocus();
    }

    // display a message pop up
    private static void displayMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "BitsAndBytes System", JOptionPane.INFORMATION_MESSAGE);
    }

    // display an error pop up
    private static void displayError(String message)
    {
        JOptionPane.showMessageDialog(null,message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Enters information, adding values to the Customer array
    private void enter()
    {
        // checks if both name and hours are not entered
        if (nameField.getText().isEmpty() && hourField.getText().isEmpty())
        {
            displayError("Please enter both your name and number of hours");
            nameField.requestFocus();
            return;
        }
        //checks if name is not entered
        else if (nameField.getText().isEmpty())
        {
            displayError("Please enter your name");
            nameField.requestFocus();
            return;
        }
        // checks if hours is not entered
        else if (hourField.getText().isEmpty())
        {
            displayError("Please enter the number of hours");
            hourField.requestFocus();
            return;
        }
        // checks if maximum guests is exceeded
        else if (arrayIndex == MAX_GUESTS)
        {
            displayError("Maximum number of guests reached");
            resetFields(nameField, hourField);
            nameField.requestFocus();
            return;
        }
        // checks if user has not selected an intended usage radio box
        else if (!byod.isSelected() && !sharedSpace.isSelected() && !privateRoom.isSelected())
        {
            displayError("Please select how you intend to use the internet cafe");
            return;
        }
        // checks if user has not selected an internet speed
        else if (!defaultSpeed.isSelected() && !mediumSpeed1.isSelected() && !mediumSpeed2.isSelected() && !highSpeed1.isSelected() && !highSpeed2.isSelected())
        {
            displayError("Please select an internet speed");
            return;
        }

        // assign entered information to variables
        String name = nameField.getText();
        int hours = Integer.parseInt(hourField.getText());

        // create array objects with entered variables
        customerArray[arrayIndex] = new Customer(name,hours,selectUsage(),selectSpeed());

        // display entered name and hours in JFrame window
        nameOutLabel.setText(name);
        hourOutLabel.setText(Integer.toString(hours));

        // display entered name, hours and calculated price in pop up
        displayMessage(String.format("Entry number: %d\nGiven name is: %s\nGiven hours is: %s\ntotal price is: $%.2f" +
                        "\nSelected usage method is: %s\nSelected speed is: %s"
                ,arrayIndex + 1
                ,customerArray[arrayIndex].getName()
                ,customerArray[arrayIndex].getHours()
                ,customerArray[arrayIndex].calculateCharge()
                ,customerArray[arrayIndex].getUsageString(),customerArray[arrayIndex].getSpeedString()));

        resetFields(nameField, hourField);  // resets the name and hour text fields
        nameField.requestFocus();
        arrayIndex++;
    }

    // exit button functionality. exits program
    private void exit()
    {
        // exits system if user has not added an entry
        if (customerArray[0] == null)
        {
            System.exit(0);
        }

        // display a thank you message if user has entered an entry
        displayMessage("Thank you for using the BitsAndBytes system");
        System.exit(0);
    }

    // executes methods depending on button pressed
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == enterButton) // enter button is pressed
        {
            enter();
        }
        if (e.getSource() == DisplayChargesButton) // display all charges button is pressed
        {
            displayCharges();
        }
        if (e.getSource() == exitButton) // exit button is pressed
        {
            exit();
        }
    }

    // runs program
    public static void main(String[] args)
    {
        new BitsAndBytesInternetCafe();
        welcomeMessgage();
    }
}


