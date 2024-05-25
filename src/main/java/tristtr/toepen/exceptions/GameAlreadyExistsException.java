package tristtr.toepen.exceptions;

public class GameAlreadyExistsException extends RuntimeException {

    public GameAlreadyExistsException() {
    }

    public GameAlreadyExistsException(String message) {
        super(message);
    }
}
