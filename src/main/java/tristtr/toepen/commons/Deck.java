package tristtr.toepen.commons;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Represents a deck of cards.
 * The deck is initialized with all 32 cards of a Piquet deck shuffled in a random order.
 */
@Data
public class Deck {
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    LinkedHashSet<Card> cards;


    /**
     * Creates a new deck and initializes it with all 32 cards of a standard Piquet deck.
     * The deck is then shuffled.
     * @see #initializeDeck()
     * @see #shuffle()
     */
    public Deck() {
        cards = new LinkedHashSet<>();
        initializeDeck();
        shuffle();
    }

    /**
     * Replaces the cards in the deck with all 32 cards of a standard Piquet deck.
     */
    public void initializeDeck() {
        cards.clear();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Removes the first card from the 'bottom' of the deck and returns it.
     * @throws AssertionError if the deck is empty
     * @return the first card from the 'bottom' of the deck
     */
    public Card drawCard() {
        assert !cards.isEmpty(): "Tried to draw card from empty deck.";

        return cards.removeFirst();
    }

    /**
     * Removes the first {@code amount} cards from the 'bottom' of the deck and returns them.
     * The cards are returned in the order they were drawn.
     * @throws AssertionError if the deck has less than {@code amount} cards
     * @throws AssertionError if {@code amount} is negative
     * @param amount the amount of cards to draw
     * @return the first {@code amount} cards from the 'bottom' of the deck in the order they were drawn.
     */
    public LinkedHashSet<Card> drawCards(int amount) {
        assert cards.size() >= amount : "Tried to draw more cards than are left in the deck.";
        assert amount >= 0 : "Tried to draw a negative amount of cards.";

        LinkedHashSet<Card> drawnCards = new LinkedHashSet<>();
        for (int i = 0; i < amount; i++) {
            drawnCards.add(drawCard());
        }
        return drawnCards;
    }

    public Card peekCard() {
        assert !cards.isEmpty() : "Tried to peek card from empty deck.";

        return cards.getFirst();
    }

    public LinkedHashSet<Card> peekCards(int amount) {
        assert cards.size() >= amount : "Tried to peek more cards than are left in the deck.";
        assert amount >= 0 : "Tried to peek a negative amount of cards.";

        LinkedHashSet<Card> drawnCards = new LinkedHashSet<>();
        Iterator<Card> iterator = cards.iterator();
        for (int i = 0; i < amount; i++) {
            drawnCards.add(iterator.next());
        }
        return drawnCards;
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        List<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        cards = new LinkedHashSet<>(shuffledCards);
    }

    /**
     * Returns the amount of cards left in the deck.
     * @return the amount of cards left in the deck
     */
    public int size() {
        return cards.size();
    }

    /**
     * Checks if the deck contains a specific card.
     *
     * @param card the card to check for
     * @return true if the deck contains the card, false otherwise
     */
    public boolean contains(Card card) {
        return cards.contains(card);
    }

    /**
     * Clears the deck.
     */
    public void clear() {
        cards.clear();
    }
}
