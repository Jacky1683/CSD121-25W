package lab1;

import java.io.*;
import java.util.*;


public class Main {


    public static double calculateAverage(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (double) sum / numbers.size();
    }

    public static void main(String[] args) {

        String userName; // String variable for the user's name
        int userAge;     // Integer variable for the user's age
        double average;  // Double variable to store the average of user-entered numbers
        List<Integer> numbersList = new ArrayList<>(); // List to store user numbers
        Scanner scanner = new Scanner(System.in);

        // Obtain keyboard input from the user
        try {
            System.out.print("Enter your name: ");
            userName = scanner.nextLine();

            System.out.print("Enter your age: ");
            userAge = scanner.nextInt();

            /* Conditional statement based on the user's age */
            if (userAge < 18) {
                System.out.println("You are a minor.");
            } else if (userAge <= 60) {
                System.out.println("You are an adult.");
            } else {
                System.out.println("You are a senior citizen.");
            }

            /* Asking the user to enter 5 numbers for the list */
            System.out.println("Please enter 5 numbers to calculate the average:");
            for (int i = 0; i < 5; i++) {
                System.out.print("Enter number " + (i + 1) + ": ");
                int num = scanner.nextInt(); // Get the number
                numbersList.add(num); // Add the number to the list
            }

            /* Print information to the console */
            System.out.println("\nHello, " + userName + "!");
            System.out.println("You are " + userAge + " years old.");
            System.out.println("The numbers you entered are: " + numbersList);

            /* Calculate and print the average */
            average = calculateAverage(numbersList);
            System.out.println("The average of the numbers you entered is: " + average);

            /* Write information to a file */
            try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
                writer.println("User Name: " + userName);
                writer.println("User Age: " + userAge);
                writer.println("Entered Numbers: " + numbersList);
                writer.println("Calculated Average: " + average);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}


