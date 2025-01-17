package service;

import model.Auth;
import model.User;
import repository.AuthRepository;

import static ui.UIMenu.message;

public class AuthService {

    private static User currentUser;

    static public boolean login(String username, String password, AuthRepository authRepository) {
        try {
            Auth auth = authRepository.findByUsername(username);
            if (auth == null || auth.getPassword() == null || !auth.getPassword().equals(password)) {
                message.error("Invalid username or password");
                return false;
            }
            User user = auth.getUser();
            if (user == null) {
                message.error("User not found for the provided username");
                return false;
            }
            currentUser = user;
            return true;
        } catch (Exception e) {
            message.error("An error occurred during login");
            return false;
        }

    }

    static public void logout() {
        currentUser = null;
    }

    static public User getCurrentUser() {
        return currentUser;
    }
}
