package tristtr.toepen.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tristtr.toepen.commons.UserInfo;

@RestController
public class SessionController {

    private UserInfo userInfo;

    @Autowired
    public SessionController(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/setUserInfo")
    public String setUserInfo(@PathParam("gameName") String gameName, @PathParam("playerName") String playerName) {
        userInfo.setGameName(gameName);
        userInfo.setPlayerName(playerName);
        return getUserInfo();
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo() {
        return userInfo.toString();
    }
}
