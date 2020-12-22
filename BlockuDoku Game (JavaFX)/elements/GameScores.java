/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta classe Guarda todos os scores de todos os jogos num ficheiro, o toString
 * retorna os 10 melhores scores
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class GameScores implements Serializable{
    private List<ValuedElement<User>> scores;
    
    /**
     * Inicializa a lista de scores sem elementos
     */
    public GameScores() {
        scores = new LinkedList<>();
    }
    
    public GameScores(List newScores){
        scores = new LinkedList<>(newScores);
    }
    
    private List<ValuedElement<User>> getScores() {
        return scores;
    }
    
    private void insertOldScore(User user, int score){
        ValuedElement ve = new ValuedElement(user, score);
        scores.add(ve);
    }
    
    /**
     * Insere um elemento da classe ValuedElement, com um dado user e um dado score,
     * na lista de scores e guarda o score num ficheiro
     * 
     * @param user, da classe User
     * @param score, score do user
     * @throws IOException 
     */
    public void insertScore(User user, int score) throws IOException{
        ValuedElement ve = new ValuedElement(user, score);
        GameScores gs = new GameScores(loadGameScores().getScores());
        for(ValuedElement<User> oldScore : gs.getScores()){
            this.insertOldScore(oldScore.getElement(), oldScore.getValue());
        }
        scores.add(ve);
        saveGameScores();
    }
    
    private void sortByScore(){
        Collections.sort(scores, new ValuedElementComparator());
    }
    
    /**
     * guarda todos os scores de todos os jogos no ficheiro "GameScores.dat"
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveGameScores() throws FileNotFoundException, IOException{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("GameScores.dat"));
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Retorna todos os scores de todos os jogos guardados no ficheiro "GameScores.dat"
     * 
     * @return scores, LinkedHashMap com a informação dos scores do user
     */
    public GameScores loadGameScores(){
        GameScores gameScores;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("GameScores.dat"));
            gameScores = (GameScores) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            gameScores = new GameScores();
        }
        return gameScores;
    }
    
    /**
     * Ordena os scores por ordem decrescente e retorna uma String com os 10 melhores
     * 
     * @return String com os 10 melhores scores
     */
    @Override
    public String toString(){
        GameScores gs = loadGameScores();
        gs.sortByScore();
        String result = "------ Ranking (Top 10) ------";
        for(int i = 0; i < 10; i++){
            if(i > gs.getScores().size() - 1){
                result += "\n" + (i + 1) + " - ";
            }else{
                result += "\n" + (i + 1) + " - " + gs.getScores().get(i).getElement().getName()
                       + ":   " + gs.getScores().get(i).getValue();
            }
        }
        return result;
    }
}