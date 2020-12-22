/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Esta classe vai guardar uma lista de blocos, dependendo do modo, que vai ser
 * utilizada no jogo, pode ainda obter um bloco aleatoriamente para usar no jogo
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class Blocks implements Serializable{
    private ArrayList<Block> blocks;
    private Mode mode;
    private int numBlocks;
    private Random random;
    
    /**
     * Construtor utilizado para criar um jogo sem parametros, o modo começa no
     * easy, e a lista de blocos ainda vazia
     */
    public Blocks(){
        blocks = new ArrayList<>();
        mode = Mode.EASY;
        numBlocks = 0;
        random = new Random();
    }
    
    /**
     * altera o modo de acordo com o dado, adiciona os blocos de acordo com o modo
     * 
     * @param mode, modo do blocos
     */
    public Blocks(Mode mode) {
        random = new Random();
        blocks = new ArrayList<>();
        this.mode = Mode.EASY;
        numBlocks = 19;
        blocks.add(new Block("i4"));
        blocks.add(new Block("i4-"));
        blocks.add(new Block("q"));
        blocks.add(new Block("t"));
        blocks.add(new Block("t<"));
        blocks.add(new Block("t>"));
        blocks.add(new Block("t-"));
        blocks.add(new Block("l"));
        blocks.add(new Block("l<"));
        blocks.add(new Block("l>"));
        blocks.add(new Block("l-"));
        blocks.add(new Block("j"));
        blocks.add(new Block("j<"));
        blocks.add(new Block("j>"));
        blocks.add(new Block("j-"));
        blocks.add(new Block("s"));
        blocks.add(new Block("s"));
        blocks.add(new Block("z"));
        blocks.add(new Block("z"));
        if(mode != Mode.EASY){
            this.mode = Mode.HARD;
            numBlocks = 37;
            blocks.add(new Block("i1"));
            blocks.add(new Block("i2"));
            blocks.add(new Block("i2-"));
            blocks.add(new Block("i3"));
            blocks.add(new Block("i3-"));
            blocks.add(new Block("lmin"));
            blocks.add(new Block("lmin<"));
            blocks.add(new Block("lmin>"));
            blocks.add(new Block("lmin-"));
            blocks.add(new Block("lmax"));
            blocks.add(new Block("lmax<"));
            blocks.add(new Block("lmax>"));
            blocks.add(new Block("lmax-"));
            blocks.add(new Block("T"));
            blocks.add(new Block("T<"));
            blocks.add(new Block("T>"));
            blocks.add(new Block("T-"));
            blocks.add(new Block("Q"));
        }
    }
    
    /**
     * 
     * @return this.mode, modo dos blocos
     */
    public Mode getMode(){
        return this.mode;
    }
    
    /**
     * 
     * @return bloco, bloco aleatório da lista de blocos
     */
    public Block getRandomBlock(){
        return blocks.get(random.nextInt(numBlocks));
    }
}