package tristtr.toepen.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tristtr.toepen.commons.Game;
import tristtr.toepen.exceptions.GameAlreadyExistsException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GameManagerTest {

    private static final String PLAYER_NAME = "player1";
    @Mock
    private SessionManager sessionManager;
    @InjectMocks
    private GameManager gameManager;

    @MethodSource
    static Stream<String> invalidGameNames() {
        return Stream.of(null, "", "0123456789ABCDEFG");
    }

    @ParameterizedTest
    @MethodSource("invalidGameNames")
    void testAssertValidGameName(String gameName) {
        // * @throws IllegalArgumentException   If the game name is null, empty, or longer than 16 characters
        assertThrows(IllegalArgumentException.class, () -> gameManager.assertValidGameName(gameName));
    }

    @ParameterizedTest
    @MethodSource("invalidGameNames")
    void testCreateGameInvalidGameName(String gameName) {
        // * @throws IllegalArgumentException   If the game name is null, empty, or longer than 16 characters
        assertThrows(IllegalArgumentException.class, () -> gameManager.createGame(gameName));
    }

    @Test
    void testCreateGamePreconditions() {
        // * @throws GameAlreadyExistsException If a game with the given name already exists
        gameManager.createGame("game1");
        assertThrows(GameAlreadyExistsException.class, () -> gameManager.createGame("game1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "0123456789ABCDEF"})
    void testCreateGame(String gameName) {
        Mockito.when(sessionManager.getPlayerName()).thenReturn(PLAYER_NAME);

        // * Creates a new game with the given name.
        Game game = gameManager.createGame(gameName);
        assertEquals(game, gameManager.getGame(gameName));

        // * Also makes the creator of the game join it.
        assertEquals(1, game.getPlayers().size());
        assertEquals(PLAYER_NAME, game.getPlayers().get(0).getPlayerName());
        Mockito.verify(sessionManager).setGameName(gameName);

        // * If the player is already in a game, they will be removed from that game.
        Mockito.when(sessionManager.getGameName()).thenReturn(gameName);
        gameManager.createGame("game2");

        assertEquals(0, gameManager.getGame(gameName).getPlayers().size());
        Mockito.verify(sessionManager).setGameName("game2");
    }
}