package tristtr.toepen.commons;

import lombok.Data;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
public class Game {
    String name;
    List<Round> rounds;
    Set<Player> players;

    public Game(String name) {
        this.name = name;
        this.rounds = new LinkedList<>();
        this.players = new HashSet<>();
    }
}
