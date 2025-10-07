package cat.itacademy.repository.crud.read;

import cat.itacademy.model.EscapeRoom;
import cat.itacademy.repository.crud.Create;

import java.sql.SQLException;
import java.util.Optional;

public interface GetById<T> extends Create<T> {
    Optional<T> getById(int id) throws SQLException;
}
