package tristtr.toepen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
        gameManager.createGame(gameName);

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
