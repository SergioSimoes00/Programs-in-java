/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 * Esta classe serve para lançar uma exceção quando o utilizador quer colocar
 * um bloco no tabuleiro e algum dos quadrados que iria ser preenchido já foi
 * preenchido por outro bloco
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class FilledSquareException extends Exception{
    private String message;
    
    /**
     * @param message mensagem a ser imprimida
     */
    public FilledSquareException(String message) {
        this.message = message;
    }
    
    /**
     * Retorna a mensagem de erro
     * @return message, mensagem de erro
     */
    @Override
    public String toString(){
        return message;
    }
}