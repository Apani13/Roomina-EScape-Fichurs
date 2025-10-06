package cat.itacademy.repository.crud.read;

import cat.itacademy.repository.crud.Create;

import java.sql.SQLException;

public interface ExistsByName<T> extends Create<T> {
    boolean existsByName(String name) throws SQLException;
}
