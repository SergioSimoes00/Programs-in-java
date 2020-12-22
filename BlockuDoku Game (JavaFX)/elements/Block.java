/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.io.Serializable;

/**
 * Esta classe define o nome de cada bloco, e, a forma como vão ser escritas na
 * consola
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class Block implements Serializable{
    private String name;
    
    /**
     * Atribui o nome ao bloco
     * 
     * @param name, nome do bloco
     */
    public Block(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @return name, nome do bloco
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return String, dependendo do nome do bloco, vai imprimir a forma do mesmo
     */
    @Override
    public String toString() {
        switch(getName()){
            case "i4": return "#\n#\n#\n#\n";
            case "i4-": return "####\n";
            case "q": return "##\n##\n";
            case "t": return "###\n  #\n";
            case "t<": return "  #\n##\n  #\n";
            case "t>": return "#\n##\n#\n";
            case "t-": return "  #\n###\n";
            case "l": return "#\n#\n##\n";
            case "l<": return "###\n#\n";
            case "l>": return "     #\n###\n";
            case "l-": return "##\n  #\n  #\n";
            case "j": return "  #\n  #\n##\n";
            case "j<": return "#\n###\n";
            case "j>": return "###\n    #\n";
            case "j-": return "##\n#\n#\n";
            case "s": return "  ##\n##\n";
            case "s-": return "#\n##\n  #\n";
            case "z": return "##\n  ##\n";
            case "z-": return "  #\n##\n#\n";
            case "i1": return "#\n";
            case "i2": return "#\n#\n";
            case "i2-": return "##\n";
            case "i3": return "#\n#\n#\n";
            case "i3-": return "###\n";
            case "lmin": return "#\n##\n";
            case "lmin<": return "##\n#\n";
            case "lmin>": return "##\n  #\n";
            case "lmin-": return "  #\n##\n";
            case "lmax": return "#\n#\n###\n";
            case "lmax<": return "###\n#\n#\n";
            case "lmax>": return "###\n    #\n    #\n";
            case "lmax-": return "    #\n    #\n###\n";
            case "T": return "###\n  #\n  #\n";
            case "T<": return "    #\n###\n    #\n";
            case "T>": return "#\n###\n#\n";
            case "T-": return "  #\n  #\n###\n";
            case "Q": return "###\n###\n###\n";
            default: return "";
        }
    }
}