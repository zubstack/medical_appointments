package repository;

import model.Auth;

import java.util.ArrayList;
import java.util.List;

public class AuthRepository  implements Repository<Auth>{
    Object conn;
    static ArrayList<Auth> auths = new ArrayList<>();

    public AuthRepository(Object conn){
        this.conn = conn;
    }

    @Override
    public List<Auth> findAll() {
        return auths;
    }

    @Override
    public Auth findById(String authId) {
        for (Auth auth : auths) {
            if (auth.getId().equals(authId)) {
                return auth;
            }
        }
        return null;
    }

    @Override
    public void save(Auth auth) {
        auths.add(auth);
    }

    @Override
    public void update(Object value, String field, int id) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    public Auth findByUsername(String username) {
        for (Auth auth : auths) {
            if (auth.getUsername().equals(username)) {
                return auth;
            }
        }
        return null;
    }
}
