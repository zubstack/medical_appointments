package repository;

import java.util.List;

public interface Repository<T>{
    List<T> findAll();

    T findById (String id);

    void save (T t);

    void update (Object value, String field, int id);

    void deleteById (Integer id);
}
