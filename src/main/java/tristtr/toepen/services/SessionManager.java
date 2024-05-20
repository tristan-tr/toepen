package tristtr.toepen.services;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import tristtr.toepen.commons.Game;
import tristtr.toepen.commons.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of different sessions and their respective players and games.
 */
@Service
public class SessionManager {
    private Map<WebSocketSession, Player> sessionPlayerMap;
    private Map<WebSocketSession, Game> sessionGameMap;

    public SessionManager() {
        this.sessionPlayerMap = new HashMap<>();
        this.sessionGameMap = new HashMap<>();
    }

    public void addSession(WebSocketSession session, Player player, Game game) {
        sessionPlayerMap.put(session, player);
        sessionGameMap.put(session, game);
    }

    public Player getPlayer(WebSocketSession session) {
        return sessionPlayerMap.get(session);
    }

    public Game getGame(WebSocketSession session) {
        return sessionGameMap.get(session);
    }
}
