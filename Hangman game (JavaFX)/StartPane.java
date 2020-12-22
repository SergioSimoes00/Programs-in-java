/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author Sérgio Simões
 */
public class StartPane extends BorderPane{
    private static final double TAMANHO_FONTE = 30.0;
    private static final double WIDTH_BUTTON = 300.0;
    private Button playButton;
    private Button instructionsButton;
    private Button exitButton;
    private VBox allVBox;
    
    public StartPane(Stage primaryStage){
        playButton = new Button("Jogar");
        playButton.setFont(Font.font(null, FontWeight.BOLD, TAMANHO_FONTE));
        playButton.setMinWidth(WIDTH_BUTTON);
        playButton.setMinHeight(150.0);
        playButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new GamePane(primaryStage), 800, 650));
            primaryStage.setTitle("Jogo da forca");
        });
        instructionsButton = new Button("Instruções");
        instructionsButton.setFont(Font.font(null, FontWeight.BOLD, TAMANHO_FONTE));
        instructionsButton.setMinWidth(WIDTH_BUTTON);
        instructionsButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new InstructionsPane(primaryStage), 600, 600));
        });
        exitButton = new Button("Sair");
        exitButton.setFont(Font.font(null, FontWeight.BOLD, TAMANHO_FONTE));
        exitButton.setMinWidth(WIDTH_BUTTON);
        exitButton.setOnAction(e -> Platform.exit());
        allVBox = new VBox(20);
        allVBox.getChildren().addAll(playButton, instructionsButton, exitButton);
        allVBox.setAlignment(Pos.CENTER);
        this.setCenter(allVBox);
        this.setPadding(new Insets(20));
    }
}
