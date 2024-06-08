package tristtr.toepen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tristtr.toepen.commons.Game;
import tristtr.toepen.commons.Player;
import tristtr.toepen.exceptions.GameAlreadyExistsException;
import tristtr.toepen.validators.ValidationService;

import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of different games.
 */
@Service
public class GameManager {

    private ValidationService validationService;
    private SessionManager sessionManager;
    private Map<String, Game> games;

    @Autowired
    public GameManager(ValidationService validationService, SessionManager sessionManager) {
        this.validationService = validationService;
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
     * @param gameName Name of the game
     * @return The created game
     * @throws IllegalArgumentException   If the game name is invalid, see {@link ValidationService#assertValidGameName(String)}
     * <br>                               If the player name in the session is invalid, see {@link ValidationService#assertValidPlayerName(String)}
     * @throws GameAlreadyExistsException If a game with the given name already exists
     */
    public Game createGame(String gameName) throws IllegalArgumentException, GameAlreadyExistsException {
        validationService.assertValidGameName(gameName);
        validationService.assertValidPlayerName(sessionManager.getPlayerName());
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
