package tristtr.toepen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tristtr.toepen.services.GameManager;

@RestController
@RequestMapping("/game")
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
