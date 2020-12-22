/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import panels.*;
import elements.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe Responsável por correr o programa, começando pelo painel do login (LoginPane).
 * ´
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class Program extends Application {
    private User user;
    
    public Program(){
        user = new User();
    }
    
    @Override
    public void start(Stage primaryStage) {
        LoginPane root = new LoginPane(primaryStage, user);
        Scene scene = new Scene(root, 260, 200);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
