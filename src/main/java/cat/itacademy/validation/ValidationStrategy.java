package cat.itacademy.validation;

import cat.itacademy.exception.DuplicateException;
import cat.itacademy.exception.EntityNotFoundException;
import cat.itacademy.exception.InvalidAttributeException;
import cat.itacademy.exception.NullObjectException;

import java.sql.SQLException;

public interface ValidationStrategy <T>{
    void validate(T entity) throws DuplicateException, EntityNotFoundException, InvalidAttributeException, NullObjectException, SQLException;
}
