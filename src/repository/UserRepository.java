package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository  implements Repository<User>{
    Object conn;
    static ArrayList<User> users = new ArrayList<>();

    public UserRepository(Object conn){
        this.conn = conn;
    }

    @Override
    public List<User> findAll() {
        // Usa conn para hacer un query "SELECT" a la base de datos
        return users;
    }

    @Override
    public User findById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(Object value, String field, int id) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
