/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGames;

/**
 *
 * @author Sérgio Simões
 */
public class ValuedElement<E> implements Comparable<ValuedElement<E>>{
    private E element;
    private int value;

    public ValuedElement(E element, int value) {
        this.element = element;
        this.value = value;
    }

    public E getElement() {
        return element;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(ValuedElement<E> ve) {
        return ve.getValue() - this.value;
    }
}
