package ui;

import model.User;
import repository.UserRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static ui.UIMenu.message;
import static ui.UIMenu.showOptions;

public class UIDevMenu {
    private final Scanner scan;
    private final UserRepository userRepository;

    public UIDevMenu(Scanner scan, UserRepository userRepository) {
        this.scan = scan;
        this.userRepository = userRepository;
    }

    public void showDevMenu() {
        int response = 0;
        String[] options = new String[]{"Show users", "Exit"};
        message.info("DEV MENU");
        do {
            try {
                showOptions(options);
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        showUsers();
                        break;
                    case 2:
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

    private void showUsers() {
        message.info("Users in database");
        List<User> users = userRepository.findAll();
        users.forEach(message::listItem);
    }
}
