
package CardGames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Esta classe representa um baralho de cartas
 * 
 * @author POO 2019/2020
 * @version maio/2020
 */

public class Deck{

    private Stack<Card> cards;
    private Random random = new Random();

    public Deck() {
        cards = new Stack<>();
    }

    public Deck(List<Card> newCards) {
        this.cards = new Stack<>();
        newCards.forEach(card -> this.cards.push(card));
    }
    
    public Card topCard(){
        return cards.peek();
    }
    
    public Card drawCard(){
        return cards.pop();
    }
    
    public void putCard(Card card){
        cards.push(card);
    }

    public void addCard(Card card) {
        if (card != null) {
            cards.push(card);
        }
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    public void clear() {
        cards.clear();
    }

    public Card getRandomCard() {
        if (cards.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(cards.size() - 1);
        return cards.remove(randomIndex);
    }
    
    public List<Card> getCardsList(){
        return cards;
    }
    
    public void sortByValue(){
        Collections.sort(cards, new CardValueComparator());
    }
    
    public void sort(){
        Collections.sort(cards, new CardValueComparator());
        Collections.sort(cards, new CardSuitValueComparator());
    }
    
    public Iterator<Card> iterator(){
        return cards.iterator();
    }
    
    public String printWithValue(){
        String result = "";
        for(Card card : cards){
            result += "> " + card.getName() + " de " + card.getSuit() + " ("
                    + card.getValue() + ")\n";
        }
        return result;
    }
    
    public void shuffle(){
        Collections.shuffle(cards);
    }
    
    public List<Card> getCards(){
        List<Card> newCards = new ArrayList<>(cards);
        return newCards;
    }

    @Override
    public String toString() {
        String cardsList = "";

        for (Card card : cards) {
            cardsList += card + "\n";
        }

        return cardsList;
    }
}
