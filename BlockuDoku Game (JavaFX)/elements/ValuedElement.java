/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.io.Serializable;

/**
 * Esta classe vai ter como elemento um user, e, vai comparar os scores de dois users
 * 
 * @param <E>, vai ser usado como User
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class ValuedElement<E> implements Comparable<ValuedElement<E>>, Serializable{
    private E element;
    private int value;
    
    /**
     * Recebe o user e o score associado a esse user
     * 
     * @param element, user
     * @param value, score do user
     */
    public ValuedElement(E element, int value) {
        this.element = element;
        this.value = value;
    }
    
    /**
     * 
     * @return element, user
     */
    public E getElement() {
        return element;
    }
    
    /**
     * 
     * @return value, score do user
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Verifica se o score de um user é maior, menor ou igual ao score do user
     * referente a esta classe
     * 
     * @param ve, um elemento desta mesma classe
     * @return inteiro positivo, se o score do user de ve for maior;
     *                 negativo, se o score do user de ve for menor;
     *                 0, se o score do user de ve for igual.
     */
    @Override
    public int compareTo(ValuedElement<E> ve) {
        return ve.getValue() - this.value;
    }    
}