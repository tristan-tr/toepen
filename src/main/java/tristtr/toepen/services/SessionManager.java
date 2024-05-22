package tristtr.toepen.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tristtr.toepen.commons.UserInfo;

@Getter
@Setter
@Service
public class SessionManager {

    private UserInfo userInfo;

    @Autowired
    public SessionManager(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setUserInfo(String gameName, String playerName) {
        userInfo.setGameName(gameName);
        userInfo.setPlayerName(playerName);
    }

    public String getGameName() {
        return userInfo.getGameName();
    }

    public void setGameName(String gameName) {
        userInfo.setGameName(gameName);
    }

    public String getPlayerName() {
        return userInfo.getPlayerName();
    }

    public void setPlayerName(String playerName) {
        userInfo.setPlayerName(playerName);
    }
}
