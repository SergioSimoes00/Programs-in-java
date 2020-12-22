/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGames;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sérgio Simões
 */
public class GameScores {
    private List<ValuedElement<Player>> scores;

    public GameScores() {
        scores = new LinkedList<>();
    }
    
    public void insertScore(Player player, int score){
        ValuedElement ve = new ValuedElement(player, score);
        scores.add(ve);
    }
    
    public void sortByScore(){
        Collections.sort(scores, new ComparatorValuedElement());
    }
    
    @Override
    public String toString(){
        sortByScore();
        String result = "------ Scores ------\n";
        for(int i = 0; i < 10; i++){
            if(i > scores.size() - 1){
                result += i + 1 + " - \n";
            }else{
                result += i + 1 + " - " + scores.get(i).getElement().getName()
                        + ":   " + scores.get(i).getValue() + "\n";
            }
        }
        return result;
    }
}
