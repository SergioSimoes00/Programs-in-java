/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGames;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jose-MSI
 */
public class CardGames {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("=======================");
        
        List<Card> cards = new ArrayList<>();
        cards.add(new FaceCard(FaceName.JACK, Suit.CLUBS));
        cards.add(new NumberedCard(3, Suit.DIAMONDS));
        cards.add(new NumberedCard(1, Suit.CLUBS));
        cards.add(new FaceCard(FaceName.KING, Suit.HEARTS));
        cards.add(new FaceCard(FaceName.QUEEN, Suit.SPADES));
        
        System.out.println("Lista de Cartas soltas:\n");
        for (Card card : cards) {
            System.out.println(card);
        }
       
        System.out.println("\n\n==================");
        
        Deck deck1 = new Deck(cards);

        System.out.println("Baralho de cartas:\n");
        System.out.println(deck1);

        System.out.println("\n===========================");
        
        SuecaDeck deck2 = new SuecaDeck();
        
        System.out.println("Baralho de cartas da Sueca:\n");
        System.out.println(deck2);  
        
        
        System.out.println("\n==================");
        
        
        System.out.println("\n\nNível 1 ###############\n\n");
        Deck deck = new Deck(deck2.getCardsList());
        System.out.println(deck.toString());
        deck.sortByValue();
        System.out.println(deck.printWithValue());
        
        
        
        System.out.println("\n\nNível 2 ###############\n\n");
        deck.shuffle();
        System.out.println(deck.getCards());
        Iterator<Card> itCards = deck.iterator();
        while(itCards.hasNext()){
            Card card = itCards.next();
            if(card.getSuit() == Suit.DIAMONDS){
                itCards.remove();
            }
        }
        System.out.println(deck.printWithValue());
        
        
        
        System.out.println("\n\nNível 3 ###############\n\n");
        GameScores gs = new GameScores();
        Player manuel = new Player("MANUEL");
        Player joao = new Player("JOAO");
        Player ana = new Player("ANA");
        gs.insertScore(manuel, 18);
        gs.insertScore(joao, 15);
        gs.insertScore(ana, 12);
        System.out.println(gs.toString());
        
        
        
        System.out.println("\n\nNível 4 ###############\n\n");
        ArrayList<Card> cards4 = new ArrayList<>(deck1.getCardsList());
        System.out.println("Deck1 original:\n" + deck1.toString());
        deck1.clear();
        System.out.println("Deck1 clear:\n" + deck1.toString() + "\n\n");
        System.out.println("Deck1 stack:");
        for(Card card : cards4){
            deck1.putCard(card);
            System.out.println(deck1.topCard());
        }
        System.out.println("\n\nDeck1 draw cards:");
        
        while(!deck1.getCardsList().isEmpty()){
            System.out.println(deck1.drawCard());
        }
        System.out.println("\n\nDeck1 empty: " + deck1.toString());
        
        
        
        System.out.println("\n\nNível 5 ################\n\n");
        Deck deck5 = new SuecaDeck();
        System.out.println("Novo deck de sueca:\n");
        System.out.println(deck5.printWithValue());
        System.out.println("\n\nShuffle deck:\n");
        deck5.shuffle();
        System.out.println(deck5.printWithValue());
        System.out.println("\n\nSort deck:\n");
        deck5.sort();
        System.out.println(deck5.printWithValue());
    }

}
