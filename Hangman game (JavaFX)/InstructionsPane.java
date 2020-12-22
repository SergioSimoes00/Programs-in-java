/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author Sérgio Simões
 */
public class InstructionsPane extends BorderPane{
    private static final double TAMANHO_TITLE = 18.0;
    private static final double TAMANHO_TEXT = 14.0;
    private Label whatLabel;
    private Label whatTextLabel;
    private Label howLabel;
    private Label howTextLabel;
    private Button backButton;
    private VBox allVBox;
    
    public InstructionsPane(Stage primaryStage){
        whatLabel = new Label("O que é");
        whatLabel.setFont(Font.font(null, FontWeight.BOLD, TAMANHO_TITLE));
        whatTextLabel = new Label("O jogo da forca é um jogo em que o jogador tem de adivinhar a palavra proposta,\n" +
                    "sabendo apenas o número de letras e o tema ligado à palavra. No nosso jogo o tema\n" +
                    "é keywords de Java para todas as palavras.\n\n" +
                    "A cada letra errada, é desenhada uma parte da forca (a forca inclui o corpo do\n" +
                    "enforcado).\n\n" +
                    "O jogo termina com sucesso quando a palavra é adivinhada antes da forca estar\n" +
                    "completa.");
        whatTextLabel.setStyle("-fx-background-color: white");
        whatTextLabel.setFont(Font.font(null, FontWeight.NORMAL, TAMANHO_TEXT));
        howLabel = new Label("Como jogar");
        howLabel.setFont(Font.font(null, FontWeight.BOLD, TAMANHO_TITLE));
        howTextLabel = new Label("O jogador pode utilizar o teclado ou o rato para selecionar uma letra. Depois,\n" +
                    "letra a letra, o jogador tenta adivinhar a palavra.");
        howTextLabel.setStyle("-fx-background-color: white");
        howTextLabel.setFont(Font.font(null, FontWeight.NORMAL, TAMANHO_TEXT));
        backButton = new Button("Voltar");
        backButton.setAlignment(Pos.CENTER);
        backButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new StartPane(primaryStage), 500, 500));
            primaryStage.setTitle("Bem-vindo ao jogo da forca");
        });
        allVBox = new VBox(10);
        allVBox.getChildren().addAll(whatLabel, whatTextLabel, howLabel, howTextLabel, backButton);
        allVBox.setAlignment(Pos.CENTER);
        this.setCenter(allVBox);
        this.setPadding(new Insets(20));
    }
}
