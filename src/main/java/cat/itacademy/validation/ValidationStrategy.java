package cat.itacademy.validation;

import cat.itacademy.exception.*;

import java.sql.SQLException;

public interface ValidationStrategy <T>{
    void validateAvailableInventory(T entity) throws DuplicateException, EntityNotFoundOnDBException, InvalidAttributeException, NullObjectException, SQLException, EmptyListException;
}
