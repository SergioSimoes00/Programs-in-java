/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import elements.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Neste painel aparece no topo o estado do tabuleiro e o score quando o jogo foi
 * terminado e em baixo do lado esquerdo o score history do utilizador e do lado
 * direito o ranking do top 10. Há ainda um botão para voltar para o menu principal.
 * 
 * 
 * @author Sérgio Simões
 */
public class FinishGamePane extends BorderPane{
    private VBox boardLabel;
    private Label endedLabel;
    private Label historyLabel;
    private Label rankingLabel;
    private Button backButton;
    private HBox scoresHBox;
    private VBox allVBox;
    
    public FinishGamePane(Stage primaryStage, Game game, User user){
        boardLabel = setBoard(game);
        endedLabel = new Label("Jogo terminado! Score: " + game.getCurrentScore());
        historyLabel = new Label(user.getScoreHistory());
        rankingLabel = new Label(new GameScores().loadGameScores().toString());
        backButton = new Button("Voltar");
        backButton.setAlignment(Pos.CENTER);
        backButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                primaryStage.setScene(new Scene(new MenuPane(primaryStage, user), 250, 250));
                primaryStage.setTitle("Menu");
            }
        });
        backButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new MenuPane(primaryStage, user), 250, 250));
            primaryStage.setTitle("Menu");
        });
        scoresHBox = new HBox(10);
        scoresHBox.getChildren().addAll(historyLabel, rankingLabel);
        allVBox = new VBox(10);
        allVBox.getChildren().addAll(boardLabel, endedLabel, scoresHBox);
        this.setAlignment(backButton, Pos.CENTER);
        this.setMargin(backButton, new Insets(12,12,12,12));
        this.setBottom(backButton);
        this.setCenter(allVBox);
        this.setBottom(backButton);
        this.setPadding(new Insets(10));
    }
    
    private VBox setBoard(Game game){
        VBox vBox = new VBox(0);
        HBox colsHBox = new HBox(0);
        Label label1 = new Label(" ");
        label1.setMinWidth(12);
        colsHBox.getChildren().add(label1);
        for(int i = 0; i < game.getBoard().getLenght()*2; i+=2){
            Label label = new Label(game.getBoard().toString().substring(i+1, i+3));
            label.setMinWidth(12);
            colsHBox.getChildren().add(label);
        }
        vBox.getChildren().add(colsHBox);
        for(int i = 0; i < game.getBoard().getHeight(); i++){
            HBox hBox = new HBox(0);
            Label label = new Label("" + (i+1));
            label.setMinWidth(12);
            hBox.getChildren().add(label);
            for(int j = 0; j < game.getBoard().getLenght(); j++){
                Label posLabel = new Label("|" + game.getBoard().get(j, i));
                posLabel.setMinWidth(12);
                hBox.getChildren().add(posLabel);
            }
            vBox.getChildren().add(hBox);
        }
        Label blocksLabel = new Label(game.getBlockA() + game.getBlockB() + game.getBlockC());
        vBox.getChildren().add(blocksLabel);
        return vBox;
    }
}
