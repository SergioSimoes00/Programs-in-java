/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.Comparator;

/**
 * Esta classe emplementa a interface Comparator, para comparar os values de dois
 * elementos da classe ValuedElement
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class ValuedElementComparator implements Comparator<ValuedElement>{
    
    /**
     * Retorna o inteiro resultante da subtração dos values do elemento do
     * segundo parametro dado pelo elemento do primeiro
     * 
     * @param ve1, elemento da classe ValuedElement
     * @param ve2, elemento da classe ValuedElement
     * @return inteiro positivo, se ve2 maior que ve1;
     *         inteiro negativo, se ve2 menor que ve1;
     *         0, se ve2 igual a ve1
     */
    @Override
    public int compare(ValuedElement ve1, ValuedElement ve2) {
        return ve2.getValue() - ve1.getValue();
    }
}