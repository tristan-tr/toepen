package tristtr.toepen.services;

import org.springframework.beans.factory.annotation.Autowired;
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

    private SessionManager sessionManager;
    private Map<String, Game> games;

    @Autowired
    public GameManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;

        this.games = new HashMap<>();
    }

    public Game getGame(String gameName) {
        return games.get(gameName);
    }

    /**
     * Creates a new game with the given name.
     * Also makes the creator of the game join it.
     * If the player is already in a game, they will be removed from that game.
     * <p>
     * Assumes that the game name and player name stored in the session is valid.
     * Game name in the session can be null if the player is not in a game.
     * </p>
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

        // TODO: Reuse the player object from a shared context instead of creating a new one
        Player player = new Player(sessionManager.getPlayerName());

        // If the player is already in a game, they will be removed from that game.
        if (sessionManager.getGameName() != null) {
            Game oldGame = getGame(sessionManager.getGameName());
            leaveGame(oldGame, player);
            // We don't have to update the session info here because we do that when we join the new game
        }

        // Creates a new game with the given name.
        Game game = new Game(gameName);
        games.put(gameName, game);

        // Also makes the creator of the game join it.
        joinGame(game, player);
        sessionManager.setGameName(gameName);

        return game;
    }

    /**
     * Adds a player to the game.
     * This does not update session info.
     *
     * @param game   the game to join
     * @param player the player to join the game
     * @return the player that joined the game
     */
    public Player joinGame(Game game, Player player) {
        game.getPlayers().add(player);
        return player;
    }

    /**
     * Removes a player from the game.
     * This does not update session info.
     * @param game the game to leave
     * @param player the player to leave the game
     * @return the player that left the game
     */
    public Player leaveGame(Game game, Player player) {
        game.getPlayers().remove(player);
        return player;
    }
}
