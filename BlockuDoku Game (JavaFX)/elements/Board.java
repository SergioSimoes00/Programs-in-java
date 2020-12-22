/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Esta classe define um tabuleiro a partir de um array com uma certa altura e
 * um certo comprimento
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class Board implements Serializable{
    private String[] tabuleiro;
    private int lenght;
    private int height;
    
    /**
     * Inicializa o tabuleiro com comprimento e altura de 9, utiliza
     * o método setup()
     */
    public Board(){
        this.tabuleiro = new String[9 * 9];
        this.lenght = 9;
        this.height = 9;
        this.setup();
    }
    
    /**
     * Inicializa o tabuleiro com lenght de comprimento e height de altura,
     * utiliza o método setup()
     * 
     * @param lenght, comprimento do tabuleiro
     * @param height, altura do tabuleiro
     */
    public Board(int lenght, int height) {
        this.tabuleiro = new String[lenght * height];
        this.lenght = lenght;
        this.height = height;
        this.setup();
    }
    
    /**
     * Permite clonar um board, desta forma é mais facil substituir o tabuleiro
     * se um bloco adicionado se sobrepor a outro, basta criar um board a partir
     * deste método, para depois substituir por este, caso isso aconteça
     * 
     * @return Board
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Board clone() throws CloneNotSupportedException{
        Board returner = new Board(lenght, height);
        returner.tabuleiro = Arrays.copyOf(tabuleiro, tabuleiro.length);
        return returner;
    }
    
    /**
     * Retorna a altura do tabuleiro do tabuleiro
     * 
     * @return height, altura do tabuleiro
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Retorna o comprimento do tabuleiro
     * 
     * @return lenght, comprimento do tabuleiro
     */
    public int getLenght(){
        return lenght;
    }
    
    /**
     * Altera um quadrado de uma dada posição para um dado valor, lança um excessão
     * do tipo FilledSquareException se a posição no tabuleiro já estiver preenchida
     * com o valor dado
     * 
     * @param x, Letra correspondente à coluna do tabuleiro
     * @param y, Número correspondente à linha do tabuleiro
     * @param valor, String a ser colocada no quadrado
     * @throws FilledSquareException, se o quadrado já estiver preenchido com o valor dado
    */
    public void set(char x, int y, String valor) throws FilledSquareException{
        int xF = (int)x-65;
        if(!get(xF, y-1).equals(valor)){
            tabuleiro[(y - 1) * lenght + xF] = valor;
        }else{
            throw new FilledSquareException("Quadrado já preenchido");
        }
    }
    
    /**
     * Retorna o valor de um quadrado de uma dada posição
     * 
     * @param x, Coluna do tabuleiro
     * @param y, Linha do tabuleiro
     * @return Valor correspondente à posição dada
     */
    public String get(int x, int y) {
        return tabuleiro[y * lenght + x];
    }
    
    private void setup(){
        for (int i = 0; i < tabuleiro.length; i++) {
            tabuleiro[i] = ".";
        }
    }
    
    /**
     * Altera o valor de um quadrado de uma dada posição para um dado valor
     * 
     * @param pos, Posição no tabuleiro (Letra/número) Ex: G4
     * @param valor, Valor que será adicionado ao quadrado
     * 
     * @throws FilledSquareException
     */
    public void set(String pos, String valor) throws FilledSquareException{
        set(pos.charAt(0), (pos.charAt(1))-'0', valor);
    }
    
    /**
     * Imprime o tabuleiro com o formato desejado, primeira coluna com os números
     * que definem as linhas, primeira coluna com as letras que definem as colunas,
     * todos os outros elementos do array têm o valor que corresponde ao valor do
     * elemento do array nessa posição. Todos estes elementos estão separados por
     * uma barra vertical
     * 
     * @return String result com o tabuleiro no formato desejado
     */
    @Override
    public String toString() {
        String result = " ";
        char a = 'A';

        for (int i = 0; i < this.lenght; i++) {
            result+=("|" + a++);
        }
        
        result += "\n";
        
        for (int i = 0; i < this.height; i++) {
            result += i + 1;
            for (int j = 0; j < this.lenght; j++) {
                result+="|" + get(j,i);
            }
            result+="\n";
        }
        
        return result;
    }
}