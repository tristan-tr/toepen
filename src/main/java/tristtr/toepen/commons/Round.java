package tristtr.toepen.commons;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class Round {
    // TODO: score-keeping

    Deck deck;
    List<Trick> tricks;
    Map<Player, Set<Card>> hands;
}
