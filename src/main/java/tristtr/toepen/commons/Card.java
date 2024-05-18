package tristtr.toepen.commons;

import lombok.Value;

@Value
public class Card {
    Suit suit;
    Rank rank;


    /**
     * The suit of a card.
     */
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    /**
     * The rank of a card. These are ordered from lowest to highest by the rules of Toepen.
     */
    public enum Rank {
        JACK, QUEEN, KING, ACE, SEVEN, EIGHT, NINE, TEN
    }
}
