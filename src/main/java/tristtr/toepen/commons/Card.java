package tristtr.toepen.commons;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Embeddable
public class Card {
    @Id
    private Suit suit;
    @Id
    private Rank rank;

    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    public enum Rank {
        JACK, QUEEN, KING, ACE, SEVEN, EIGHT, NINE, TEN
    }
}
