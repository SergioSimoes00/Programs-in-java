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
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Sérgio Simões
 */
public class RankingPane extends BorderPane{
    private Label rankingLabel;
    private Button backButton;
    
    public RankingPane(Stage primaryStage, User user){
        rankingLabel = new Label(new GameScores().toString());
        rankingLabel.setPadding(new Insets(0, 0, 20, 0));
        backButton = new Button("Voltar");
        backButton.setMinWidth(100);
        backButton.setAlignment(Pos.CENTER);
        backButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new MenuPane(primaryStage, user), 250, 250));
            primaryStage.setTitle("Menu");
        });
        backButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                primaryStage.setScene(new Scene(new MenuPane(primaryStage, user), 250, 250));
                primaryStage.setTitle("Menu");
            }
        });
        this.setTop(rankingLabel);
        this.setCenter(backButton);
        this.setPadding(new Insets(20));
    }
}
