package ui;

import model.Auth;
import model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ui.UIMenu.message;
import static ui.UIMenu.showOptions;

public class UIDevMenu {
    private final Scanner scan;

    public UIDevMenu(Scanner scan) {
        this.scan = scan;
    }

    public void showDevMenu() {
        int response = 0;
        String[] options = new String[]{"Show users", "Show auths", "Exit"};
        message.info("DEV MENU");
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
                        break;
                    default:
                        message.error("Invalid option");
                }
            } catch (InputMismatchException e) {
                message.error("Please insert a number as your option.");
                scan.next();  // Consume the invalid input
            }

        } while (response != options.length);
    }
}
