package tristtr.toepen.commons;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Game {
    List<Round> rounds;
    Set<Player> players;
}
