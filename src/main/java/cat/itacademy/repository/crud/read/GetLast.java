package cat.itacademy.repository.crud.read;

import cat.itacademy.model.Client;
import cat.itacademy.repository.crud.Create;

import java.sql.SQLException;
import java.util.Optional;

public interface GetLast<T> extends Create<T> {
    Optional<T> getLast() throws SQLException;
}
