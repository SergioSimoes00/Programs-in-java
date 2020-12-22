/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 * Esta classe server para definir o modo dos blocos que vão ser usados durante
 * o jogo
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public enum Mode {
    HARD, EASY;
    
    /**
     * Retorna o modo em texto
     * 
     * @return "easy", se o modo for EASY;
     *         "hard", caso contrário
     */
    @Override
    public String toString() {
        switch(this){
            case HARD: return "hard";
            case EASY: return "easy";
            default: return "hard";
        }
    }
}