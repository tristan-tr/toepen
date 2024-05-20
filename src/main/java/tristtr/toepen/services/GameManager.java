package tristtr.toepen.services;

import org.springframework.stereotype.Service;
import tristtr.toepen.commons.Game;
import tristtr.toepen.commons.Player;

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

    public Game createGame(String gameName) {
        Game game = new Game();
        game.setName(gameName);
        games.put(gameName, game);
        return game;
    }

    public Player joinGame(Game game, Player player) {
        game.getPlayers().add(player);
        return player;
    }
}
