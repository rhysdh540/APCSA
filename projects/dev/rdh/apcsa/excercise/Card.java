package dev.rdh.apcsa.excercise;

/**
 * A class representing a playing card. Can represent all 52 cards in a standard deck, plus a joker.
 * Cards are immutable, and should be replaced with new cards instead of modified.
 */
public class Card implements Comparable<Card> {

    public enum Suit {
        JOKER,
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES;

        public String toIcon() {
            return switch (this) {
                case JOKER -> "J";
                case CLUBS -> "♣︎";
                case DIAMONDS -> "♦︎";
                case HEARTS -> "♥︎";
                case SPADES -> "♠︎";
            };
        }
        public String toString() {
            return switch (this) {
                case JOKER -> "Joker";
                case CLUBS -> "Clubs";
                case DIAMONDS -> "Diamonds";
                case HEARTS -> "Hearts";
                case SPADES -> "Spades";
            };
        }
        public static Suit[] getValues() {
            return new Suit[] {CLUBS, DIAMONDS, HEARTS, SPADES};
        }
    }
    public static final Card JOKER = new Card(14, Suit.JOKER);
    public static final String CARD_BACK_ICON = "\uD83C\uDCA0";

    private final Suit suit;
    /**
     * 1 = Ace<br>
     * 2-10 = 2-10<br>
     * 11 = Jack<br>
     * 12 = Queen<br>
     * 13 = King<br>
     * 14 = Joker
     */
    private final int value;

    /**
     * Public constructor for a card.
     * @param suit the suit of the card
     * @param value the value of the card
     */
    public Card(Suit suit, int value) {
        if(value < 1 || value > 13)
            throw new IllegalArgumentException("Value must be between 1 and 13 (inclusive)");
        if(suit == Suit.JOKER)
            throw new IllegalArgumentException("Suit cannot be JOKER. Use Card.JOKER instead");
        this.suit = suit;
        this.value = value;
    }
    /**
     * Public constructor for a card, duplicating another.
     * @param other the card to duplicate
     */
    public Card(Card other) {
        this(other.suit, other.value);
    }

    /**
     * Private fast-path constructor for a card. Doesn't use checks.
     * @param value the value of the card
     * @param suit the suit of the card
     */
    private Card(int value, Suit suit) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Returns the suit of the card.
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }
    /**
     * Returns the value of the card.
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }
    public String getValueName() {
        return switch (value) {
            case 1 -> "Ace";
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Joker";
            default -> String.valueOf(value);
        };
    }
    public String toString() {
        return (suit == Suit.JOKER) ? "Joker" : getValueName() + " of " + suit;
    }

    /**
     * Returns the card as a unicode icon. Not currently implemented.
     * @return the card as a unicode icon
     */
    public String toIcon() {
        return switch (suit) {
            case JOKER -> "\uD83C\uDCDF";
            case CLUBS, DIAMONDS, SPADES, HEARTS -> "not implemented";
        };
    }

    /**
     * Compares this card to another card.<p>
     * Override if you are extending this class for a game which has rankings for suits.
     * </p>
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the specified card.
     */
    public int compareTo(Card other) {
        return Integer.compare(value, other.value);
    }

    /**
     * Returns true if this card is equal to the other card.
     * @param other the object to be compared.
     * @return true if this card is equal to the other card
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Card otherCard)) return false;
        return value == otherCard.value && suit == otherCard.suit;
    }
}
