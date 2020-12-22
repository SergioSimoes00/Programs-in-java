/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Sérgio Simões
 */
public class Lab11 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        StartPane root = new StartPane(primaryStage);
        
        Scene scene = new Scene(root, 500, 500);
        
        primaryStage.setTitle("Bem-vindo ao jogo da forca");
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
