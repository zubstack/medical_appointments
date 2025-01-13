package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;

public interface Repository<T>{
    List<T> findAll();

    T findById (String id);

    void save (T t);

    void update (Object value, String field, int id);

    void deleteById (Integer id);
}
