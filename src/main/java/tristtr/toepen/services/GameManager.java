package tristtr.toepen.services;

import org.springframework.stereotype.Service;
import tristtr.toepen.commons.Game;
import tristtr.toepen.commons.Player;
import tristtr.toepen.exceptions.GameAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of different games.
 */
@Service
public class GameManager {
    private Map<String, Game> games;

    public GameManager() {
        this.games = new HashMap<>();
    }

    public Game getGame(String gameName) {
        return games.get(gameName);
    }

    /**
     * Creates a new game with the given name.
     * Also makes the creator of the game join it.
     * If the player is already in a game, they will be removed from that game.
     *
     * @param gameName Name of the game
     * @return The created game
     * @throws IllegalArgumentException   If the game name is null, empty, or longer than 16 characters
     * @throws GameAlreadyExistsException If a game with the given name already exists
     */
    public Game createGame(String gameName) throws IllegalArgumentException, GameAlreadyExistsException {
        if (gameName == null || gameName.isEmpty() || gameName.length() > 16) {
            throw new IllegalArgumentException("Game name must be between 1 and 16 characters long");
        }
        if (games.containsKey(gameName)) {
            throw new GameAlreadyExistsException("Game already exists with name: " + gameName);
        }

        Game game = new Game(gameName);

        games.put(gameName, game);
        return game;
    }

    public Player joinGame(Game game, Player player) {
        game.getPlayers().add(player);
        return player;
    }
}
