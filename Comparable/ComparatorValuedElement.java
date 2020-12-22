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
public class ComparatorValuedElement implements Comparator<ValuedElement>{

    @Override
    public int compare(ValuedElement ve1, ValuedElement ve2) {
        return ve2.getValue() - ve1.getValue();
    }
}
