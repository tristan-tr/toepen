package tristtr.toepen.commons;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Game {
    String name;
    List<Round> rounds;
    List<Player> players;

    public Game(String name) {
        this.name = name;
        this.rounds = new LinkedList<>();
        this.players = new LinkedList<>();
    }
}
