/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Sérgio Simões
 */
public class GamePane extends VBox{
    private Hangman hangman;
    private Game game;
    private ListView<String> alphabetListView;
    private ObservableList<String> alphabet;
    private HBox hboxButtons;
    
    
    public GamePane(Stage primaryStage){
        hangman = new Hangman();
        hangman.setTranslateX(320);
        game = new Game();
        alphabetListView = new ListView();
        alphabetListView.setOrientation(Orientation.HORIZONTAL);
        alphabetListView.setMaxHeight(60);
        alphabetListView.setPadding(new Insets(10));
        alphabetListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        alphabetListView.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            Node node = e.getPickResult().getIntersectedNode();
            while (node != null && node != alphabetListView && !(node instanceof ListCell))
                node = node.getParent();
            if (node instanceof ListCell) {
                e.consume();
                ListCell cell = (ListCell) node;
                alphabetListView.requestFocus();
                if (!cell.isSelected())
                alphabetListView.getSelectionModel().select(cell.getIndex());
            }
        });
        alphabet = FXCollections.observableArrayList();
        for (int i = 97; i <= 122; i++) {
            alphabet.add("" + (char) i);
        }
        alphabetListView.setItems(alphabet);
        alphabetListView.getSelectionModel().getSelectedItem();
        alphabetListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkEnd(newValue);
            }
        });
        alphabetListView.setOnKeyPressed(key -> {
            alphabet.stream().filter(letter -> key.getText().equals(letter))
            .forEach(letter -> {
                alphabetListView.getSelectionModel().select(alphabet.indexOf(letter));
                checkEnd(key.getText());
            });
        });
        Button backButton = new Button("Voltar");
        backButton.setFont(Font.font(null, FontWeight.BOLD, 16.0));
        backButton.setMinWidth(150);
        backButton.setMinHeight(50);
        backButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new StartPane(primaryStage), 500, 500));
            primaryStage.setTitle("Bem-vindo ao jogo da forca");
        });
        Button exitButton = new Button("Sair");
        exitButton.setFont(Font.font(null, FontWeight.BOLD, 16.0));
        exitButton.setMinWidth(150);
        exitButton.setMinHeight(50);
        exitButton.setOnAction(e -> Platform.exit());
        hboxButtons = new HBox(20);
        hboxButtons.getChildren().addAll(backButton, exitButton);
        hboxButtons.setAlignment(Pos.CENTER);
        hboxButtons.setPadding(new Insets(50));
        this.getChildren().addAll(hangman, game, alphabetListView, hboxButtons);
        this.setPadding(new Insets(20, 0, 0, 0));
        primaryStage.setX(400);
        primaryStage.setY(20);
    }
    
    private void checkEnd(String stringLetter){
        if(!game.testLetter(stringLetter)){
            hangman.next();
            if(hangman.isComplete()){
                lostGame();
            }
        }else{
            if(game.isComplete()){
                wonGame();
            }
        }
    }
    
    private void lostGame(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fim do Jogo");
        alert.setHeaderText("Não adivinhou...\nA palavra era: " + game.getHiddenWord());
        String s = "Pretende uma nova palavra?";
        alert.setContentText(s);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            hangman = new Hangman();
            game = new Game();
            alphabetListView.getSelectionModel().clearSelection();
            getChildren().clear();
            hangman.setTranslateX(320);
            getChildren().addAll(hangman, game, alphabetListView, hboxButtons);
        }else{ if(result.get() == ButtonType.CANCEL){
            Platform.exit();
        }
        }
    }
    
    private void wonGame(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fim do Jogo");
        alert.setHeaderText("Parabéns!\nAdivinhou a palavra em " + game.getNumberOfTries() + " tentativas.");
        String s = "Pretende uma nova palavra?";
        alert.setContentText(s);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            hangman = new Hangman();
            game = new Game();
            alphabetListView.getSelectionModel().clearSelection();
            getChildren().clear();
            hangman.setTranslateX(320);
            getChildren().addAll(hangman, game, alphabetListView, hboxButtons);
        }else{ if(result.get() == ButtonType.CANCEL){
            Platform.exit();
        }
        }
    }
}
