package tristtr.toepen.commons;

import lombok.Data;

import java.util.List;

@Data
public class Trick {
    // TODO: not all players have to participate

    List<Card> cardsPlayed;
    Player turn;
    Player winner;
}
