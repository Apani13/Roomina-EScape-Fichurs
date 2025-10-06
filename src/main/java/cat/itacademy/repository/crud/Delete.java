package cat.itacademy.repository.crud;

import java.sql.SQLException;

public interface Delete<T> extends Create<T> {
    void delete(int id) throws SQLException;
}
