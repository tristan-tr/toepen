package tristtr.toepen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tristtr.toepen.exceptions.GameAlreadyExistsException;
import tristtr.toepen.services.GameManager;

@RestController("/game")
public class GameController {

    private GameManager gameManager;

    @Autowired
    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Object> createGame(@RequestBody String gameName) {
        // TODO: encapsulate this logic in a service,
        //  because exceptions are always 1:1 with HTTP status codes
        try {
            gameManager.createGame(gameName);
        } catch (GameAlreadyExistsException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{gameName}/join")
    public String joinGame(@PathVariable String gameName) {
        return null;
    }

    @PostMapping("/{gameName}/leave")
    public String leaveGame(@PathVariable String gameName) {
        return null;
    }
}
