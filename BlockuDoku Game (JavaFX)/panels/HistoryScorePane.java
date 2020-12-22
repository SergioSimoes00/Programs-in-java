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
 *
 * @author Sérgio Simões
 */
public class HistoryScorePane extends BorderPane{
    private Label scoresLabel;
    private Button backButton;
    private ScrollBar scrollBar;
    
    public HistoryScorePane(Stage primaryStage, User user){
        scoresLabel = new Label(user.getScoreHistory());
        backButton = new Button("Voltar");
        backButton.setAlignment(Pos.CENTER);
        backButton.setMinWidth(100);
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
        VBox vbox = new VBox();
        vbox.getChildren().add(scoresLabel);
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPrefSize(0, 400);
        this.setTop(scrollPane);
        this.setCenter(backButton);
        this.setPadding(new Insets(10));
    }
}
