package cat.itacademy.repository.crud;

import java.sql.SQLException;

public interface Create<T> {
    void insert(T t) throws SQLException;
}
