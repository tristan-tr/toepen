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
        try {
            gameManager.createGame(gameName);
        }
//        catch (GameAlreadyExistsException e){
//            return ResponseEntity.status(409).body(e.getMessage());
//        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
