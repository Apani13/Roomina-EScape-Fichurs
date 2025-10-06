package cat.itacademy.exception;

public class EntityNotFoundOnDBException extends RuntimeException {
    public EntityNotFoundOnDBException(String message) {
        super(message);
    }
}
