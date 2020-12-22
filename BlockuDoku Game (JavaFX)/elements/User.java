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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Esta classe define um user/player, vai ter um nome e uma password associada,
 * uma lista de scores e um highscore
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class User implements Serializable{
    private String name;
    private String password;
    private int highScore;
    private LinkedHashMap<Date, Integer> scores;
    
    /**
     * Construtor sem parametros para criar um user, num jogo, ainda sem nome e
     * password
     */
    public User(){
        name = "";
        password = "";
        highScore = 0;
        scores = new LinkedHashMap<>();
    }
    
    /**
     * Inicializa o nome e a password depois de se registar quando o programa é
     * corrido
     * 
     * @param name, nome do user
     * @param password, password associado ao user
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.highScore = 0;
        scores = new LinkedHashMap<>();
    }
    
    /**
     * 
     * @return name, nome do user
     */
    public String getName() {
        return name;
    }
    
    public String getPassword(){
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isPassEqual(String password){
        return this.password.equals(password);
    }
    
    /**
     * 
     * @return highScore, recorde do user
     */
    public int getHighScore() {
        return highScore;
    }
    
    private LinkedHashMap<Date, Integer> getScores(){
        return scores;
    }
    
    /**
     * Retorna uma String com o score history do user
     * 
     * @return result, score history
     */
    public String getScoreHistory(){
        String result = "";
            result += "-----Score History (" + name + ")-----";
        
            for(Map.Entry<Date, Integer> score : loadUser(this.name).getScores().entrySet()){
                result += "\nScore: " + score.getValue() + " (" + score.getKey() + ")";
            }
        return result;
    }
    
    private void addOldScore(Date date, int score){
        scores.put(date, score);
        if(score > highScore){
            highScore = score;
        }
    }
    
    /**
     * Adiciona o novo score à lista de scores, se for maior que o ultimo highscore,
     * atualiza o mesmo, guarda o score no ficheiro do score history
     * 
     * @param newScore, novo score a adicionar à lista de scores
     * @throws IOException
     */
    public void addScore(int newScore) throws IOException{
        Date newDate = new Date();
        User savedUser = loadUser(this.name);
        for(Map.Entry<Date, Integer> score : savedUser.getScores().entrySet()){
            this.addOldScore(score.getKey(), score.getValue());
        }
        scores.put(newDate, newScore);
        if(newScore > highScore){
            setHighScore(newScore);
        }
        saveUser();
    }
    
    private void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    
    /**
     * guarda os dados do score history deste user num ficheiro com o seu nome
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveUser() throws FileNotFoundException, IOException{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".dat"));
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Retorna todos os scores do user com o nome dado
     * 
     * @param name, nome do user
     * 
     * @return scores, LinkedHashMap com a informação dos scores do user
     */
    public User loadUser(String name){
        User user;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name + ".dat"));
            user = (User) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            user = new User();
        }
        return user;
    }
}