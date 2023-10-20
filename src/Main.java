import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {

        // Creating the window for the App.
        JFrame topFrame = new JFrame("Age Calculator");
        topFrame.setSize(300, 200);
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.setVisible(true);

        // The main panel to add Swing components to. Set background to light gray. Add to topFrame.
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        topFrame.add(mainPanel);

        // Creates a label prompting user to enter their birthday. Adds component to the main panel.
        JLabel label1 = new JLabel("Enter your birthday MM-DD-YYYY:");
        label1.setForeground(Color.BLUE);
        mainPanel.add(label1);

        // Creates a text field for user to enter their birthday.
        JTextField inputField = new JTextField(6);
        inputField.setBackground(Color.WHITE);
        inputField.setEditable(true);
        mainPanel.add(inputField);

        // Creates a label for the Age output field. Adds component to the main panel.
        JLabel label2 = new JLabel("Your age is:");
        label2.setForeground(Color.BLUE);
        mainPanel.add(label2);

        // Output field to display age. Adds component to the main panel.
        JTextField outputField = new JTextField(14);
        outputField.setBackground(Color.WHITE);
        outputField.setEditable(false);
        mainPanel.add(outputField);

        /* Button for user to click to calculate and show age. Adds component to the main panel. */
        JButton calcButton = getCalcButton(inputField, outputField);

        mainPanel.add(calcButton);

        topFrame.pack();
    }
    private static JButton getCalcButton(JTextField inputField, JTextField outputField) {
        JButton calcButton = new JButton("Calculate");
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get text from text field.
                String input = inputField.getText();

                // Split the string based on "-" into an array.
                String[] strDate = input.split("-");

                // Separating DOB into variables specifying appropriate element of the array.
                int bYear = parseInt(strDate[2]);
                int bMonth = parseInt(strDate[0]);
                int bDay = parseInt(strDate[1]);

                // Create a DOB object.
                LocalDate dob = LocalDate.of(bYear,bMonth, bDay);

                // Create a current date object.
                LocalDate currentDate = LocalDate.now();

                // Calculates the difference between DOB and today's date.
                Period period = Period.between(dob,currentDate);

                // Converts integers to string and formats for output field into one variable "message".
                String getMonth = Integer.toString(period.getMonths());
                String getDay = Integer.toString(period.getDays());
                String getYear = Integer.toString(period.getYears());
                String message = String.format("%s years %s months %s days", getYear, getMonth, getDay);

                // Displays the formatted output in the outputField.
                outputField.setText(message);
            }
        });
        return calcButton;
    }
}