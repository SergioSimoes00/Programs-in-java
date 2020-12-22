/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGames;

import java.util.Comparator;

/**
 *
 * @author Sérgio Simões
 */
public class CardSuitValueComparator implements Comparator<Card>{

    @Override
    public int compare(Card card1, Card card2) {
        return card1.getSuit().ordinal() - card2.getSuit().ordinal();
    }
    
    
}
