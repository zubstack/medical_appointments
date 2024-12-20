package ui;

import model.Auth;
import model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ui.UIMenu.showOptions;

public class UIDevMenu {
    private final Scanner scan;
    public UIDevMenu (Scanner scan){
        this.scan = scan;
    }
    public void showDevMenu() {
        int response = 0;
        String[] options = new String[]{"Show users", "Show auths", "Exit"};
        System.out.println("\n**DEV MENU**");
        do {
            try {
                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        User.showUsers();
                        break;
                    case 2:
                        Auth.showAuths();
                        break;
                    case 3:
                        System.out.println();
                        break;
                    default:
                        System.out.println("[ERROR]: INVALID OPTION");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR]: Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }

        } while (response != options.length);
    }
}
