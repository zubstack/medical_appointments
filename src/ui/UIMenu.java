package ui;

import java.util.Scanner;

public class UIMenu {
    static final String[] MONTHS =  {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
static Scanner scan =  new Scanner(System.in);

    public static void showMenu(){
        int response;
        System.out.println("Welcome to Medical Appointments");
        do {
            System.out.println(">> Select an option:\n(1).Doctor\n(2).Patient\n(3).Exit");
            System.out.print("Your option: ");
            response = scan.nextInt();

            switch (response){
                case 1:
                    System.out.println("Doctor");
                    break;
                case 2:
                    showPatientMenu();
                    break;
                case 3:
                    System.out.println("Thank you for you visit!.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }while (response != 3);
    }

    static void showPatientMenu(){
        int response;
        System.out.println("Welcome unknown patient!");
        do {
            System.out.println(">> Select an option:\n(1).Book an appointment\n(2).Cancel appointment\n(3).Exit");
            System.out.print("Your option: ");
            response = scan.nextInt();

            switch (response){
                case 1:
                    String month = getMonth();
                    System.out.println("Your month: " + month);
                    break;
                case 2:
                    System.out.println("** CANCELING **");
                    break;
                case 3:
                    System.out.println("Good bye unknown patient!.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }while (response != 3);
    }

    static String getMonth(){
        int response;
        int LIMIT = 3;
        do {
            System.out.println(">> Select a month:");
            for (int i = 0; i < LIMIT; i++) {
                System.out.printf("(%d).%s\n", i+1, MONTHS[i]);
            }
            System.out.print("Your option: ");
            response = scan.nextInt() - 1;
        }while (response < 1 || response > LIMIT);
        return MONTHS[response];
    }
}
