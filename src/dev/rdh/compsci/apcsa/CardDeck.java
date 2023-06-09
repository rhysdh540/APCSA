package dev.rdh.compsci.apcsa;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class CardDeck implements Iterable<Card> {

    /**
     * Standard French deck of 52 cards. Don't use double brace initialization, kids.
     */
    public static final CardDeck STANDARD_DECK = new CardDeck();
    static {
        for(Card.Suit suit : Card.Suit.getValues())
            for(int i = 1; i <= 13; i++)
                STANDARD_DECK.add(new Card(suit, i));
        STANDARD_DECK.locked = true;
    }

    /**
     * The cards in the deck.
     */
    private List<Card> cards;
    /**
     * Whether or not the deck is locked. If it is, you can't add or remove cards. Only for {@code STANDARD_DECK}
     */
    private boolean locked = false;

    public CardDeck() {
        cards = new ArrayList<>();
    }
    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }
    public CardDeck(CardDeck deck) {
        this.cards = new ArrayList<>(deck.cards);
    }
    public CardDeck(Card... cards) {
        this.cards = new ArrayList<>(Arrays.asList(cards));
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public boolean contains(Object o) {
        return cards.contains((Card)o);
    }

    @NotNull @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public void forEach(Consumer<? super Card> action) {
        cards.forEach(action);
    }

    @NotNull
    public Card[] toArray() {
        return cards.toArray(new Card[0]);
    }

    public boolean add(Card... cards) {
        if(locked) return false;
        return this.cards.addAll(Arrays.asList(cards));
    }

    public boolean removeIf(Predicate<? super Card> filter) {
        if(locked) return false;
        return cards.removeIf(filter);
    }

    public void replaceAll(UnaryOperator<Card> operator) {
        if(locked) return;
        cards.replaceAll(operator);
    }

    public void clear() {
        if(locked) return;
        cards.clear();
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public Card set(int index, Card element) {
        if(locked) return null;
        return cards.set(index, element);
    }

    public void add(int index, Card element) {
        if(locked) return;
        cards.add(index, element);
    }

    public Card remove(int index) {
        if(locked) return null;
        return cards.remove(index);
    }

    public int indexOf(Object o) {
        return cards.indexOf((Card)o);
    }

    public int lastIndexOf(Object o) {
        return cards.lastIndexOf((Card)o);
    }

    public Spliterator<Card> spliterator() {
        return cards.spliterator();
    }

    public void shuffle() {
        if(locked) return;
        Collections.shuffle(cards);
    }

    public void removeDuplicates(){
        if(locked) return;
        for(int i = 0; i < cards.size(); i++)
            for(int j = i+1; j < cards.size(); j++)
                if(cards.get(i).equals(cards.get(j))){
                    cards.remove(j);
                    j--;
                }
    }
}
