package tristtr.toepen.exceptions;

public class GameAlreadyExistsException extends Exception {
    public GameAlreadyExistsException() {
        super("Game already exists");
    }

    public GameAlreadyExistsException(String message) {
        super(message);
    }
}
