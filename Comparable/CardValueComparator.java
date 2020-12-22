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
public class CardValueComparator implements Comparator<Card>{
    
    @Override
    public int compare(Card card1, Card card2) {
        return card1.getValue() - card2.getValue();
    }
}
