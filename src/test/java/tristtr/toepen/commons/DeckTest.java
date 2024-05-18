package tristtr.toepen.commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;

    static <T, S> boolean equals(Iterator<T> a, Iterator<S> b) {
        while (a.hasNext() && b.hasNext()) {
            if (!a.next().equals(b.next())) {
                return false;
            }
        }
        // maybe one of the iterators has more elements than the other
        return !a.hasNext() && !b.hasNext();
    }

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void testConstructor() {
        assertEquals(32, deck.size()); // make sure we have all possible cards (32)
        // Make sure that the deck is shuffled (this will fail 1 in 32 factorial times)
        LinkedHashSet<Card> cards = deck.drawCards(32);
        deck.initializeDeck(); // reset the deck to pre-shuffled state
        LinkedHashSet<Card> cards2 = deck.drawCards(32);
        assertFalse(equals(cards.iterator(), cards2.iterator())); // we cannot use equals() because the set equals() doesnt care about order
    }

    @Test
    void testDrawCard() {
        for (int cardsLeft = 32; cardsLeft > 0; cardsLeft--) {
            assertEquals(cardsLeft, deck.size());
            Card card = deck.drawCard();
            assertFalse(deck.contains(card));
        }

        assertEquals(0, deck.size());

        // deck is empty, so we should get an assertion error when we try to draw a card
        assertThrows(AssertionError.class, () -> deck.drawCard());
    }

    @Test
    void testDrawCards() {
        // precondition checks
        assertThrows(AssertionError.class, () -> deck.drawCards(-1));
        assertThrows(AssertionError.class, () -> deck.drawCards(33));

        LinkedHashSet<Card> cards = deck.drawCards(5);
        assertEquals(27, deck.size());
        for (Card card : cards) {
            assertFalse(deck.contains(card));
        }

        deck.clear();

        // drawing 0 cards should return an empty set
        assertEquals(new LinkedHashSet<>(), deck.drawCards(0));

        // deck is empty, so we should get an assertion error when we try to draw a card
        assertThrows(AssertionError.class, () -> deck.drawCards(1));
    }

    @Test
    void testPeekCard() {
        assertEquals(deck.peekCard(), deck.drawCard());

        deck.clear();
        assertThrows(AssertionError.class, () -> deck.peekCard());
    }

    @Test
    void testPeekCards() {
        // precondition checks
        assertThrows(AssertionError.class, () -> deck.peekCards(-1));
        assertThrows(AssertionError.class, () -> deck.peekCards(33));

        assertEquals(deck.peekCards(5), deck.drawCards(5));

        deck.clear();
        assertEquals(new LinkedHashSet<>(), deck.peekCards(0));
        assertThrows(AssertionError.class, () -> deck.peekCards(1));
    }

    @Test
    void testShuffle() {
        LinkedHashSet<Card> cards = deck.peekCards(32);
        deck.shuffle();
        LinkedHashSet<Card> shuffledCards = deck.peekCards(32);
        assertFalse(equals(cards.iterator(), shuffledCards.iterator()));
    }

    @Test
    void testSize() {
        assertEquals(32, deck.size());
        deck.drawCard();
        assertEquals(31, deck.size());
        deck.clear();
        assertEquals(0, deck.size());
    }

    @Test
    void testContains() {
        Card card = deck.peekCard();
        assertTrue(deck.contains(card));
        deck.drawCard();
        assertFalse(deck.contains(card));
    }

    @Test
    void testClear() {
        deck.clear();
        assertEquals(0, deck.size());
    }

    @Test
    void testInitializeDeck() {
        deck.drawCards(16);
        deck.initializeDeck();
        assertEquals(32, deck.size()); // make sure that the deck is reset to its initial state
    }
}