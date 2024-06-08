package tristtr.toepen.validators;

import org.springframework.stereotype.Service;

/**
 * Contains helper methods for validating data.
 */
// TODO: Use Spring Validation annotations instead
@Service
public class ValidationService {
    /**
     * Asserts that the game name is valid. A valid game name is between 1 and 16 characters long.
     *
     * @param gameName Name of the game
     * @throws IllegalArgumentException If the game name is null, empty, or longer than 16 characters
     */
    public void assertValidGameName(String gameName) throws IllegalArgumentException {
        if (gameName == null || gameName.isEmpty() || gameName.length() > 16) {
            throw new IllegalArgumentException("Game name must be between 1 and 16 characters long");
        }
    }

    /**
     * Asserts that the player name is valid. A valid player name is between 1 and 16 characters long.
     *
     * @param playerName Name of the player
     * @throws IllegalArgumentException If the player name is null, empty, or longer than 16 characters
     */
    public void assertValidPlayerName(String playerName) throws IllegalArgumentException {
        if (playerName == null || playerName.isEmpty() || playerName.length() > 16) {
            throw new IllegalArgumentException("Player name must be between 1 and 16 characters long");
        }
    }
}
