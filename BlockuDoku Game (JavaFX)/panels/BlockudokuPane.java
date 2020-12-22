/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import elements.*;
import javafx.scene.text.Font;
import java.io.IOException;
import java.util.Optional;
import javafx.animation.FadeTransition;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Este painel tem primeiramente a pontuação do jogador na parte superior do painel
 * seguido da representação do jogo e por último a linha onde é pedido que o utilizador
 * indique a próxima jogada podendo clicar no botão play, ou apenas clicando no botão
 * “enter” do teclado, na parte inferior do painel há 3 botões, um para guardar,
 * que aparece um diálogo de texto para o utilizador escrever o nome do ficheiro
 * que quer que o jogo seja guardado, podendo cancelar, Se o utilizador começou
 * um jogo já guardado anteriormente, quando for guardar outra vez, aparece
 * automaticamente neste caixa de texto o nome do ficheiro onde o jogo estava
 * guardado, substituindo o mesmo. Há ainda o botão de cancelar, que “esquece”
 * o jogo atual e volta ao menu. O último botão é o finish, pois o jogo não
 * verifica sozinho se já acabou ou não, este botão abre o painel FinishGamePane.
 * 
 * 
 * @author Sérgio Simões
 */
public class BlockudokuPane extends BorderPane{
    private Label scoreLabel;
    private VBox showVBox;
    private Label playLabel;
    private TextField playField;
    private Button playButton;
    private Label errorLabel;
    private Button saveButton;
    private Button finishButton;
    private Button cancelButton;
    private HBox playHBox;
    private VBox gameVBox;
    private HBox optionsHBox;
    
    public BlockudokuPane(Stage primaryStage, User user, Game game, String gameFile) throws CloneNotSupportedException, FilledSquareException{
        scoreLabel = new Label("Player " + user.getName() + " with " + game.getCurrentScore() + " points.");
        scoreLabel.setFont(Font.font("bold"));
        playLabel = new Label("Indique a próxima jogada (Bloco-ColunaLinha):");
        playField = new TextField();
        playField.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                play(user, game);
//                if(game.isFinished()){
//                    fileToSaveGame(primaryStage, game, user, gameFile);
//                }
            } catch (CloneNotSupportedException | FilledSquareException/* | IOException*/ ex) {
                errorLabel.setText("Bloco ou posição não foram bem escolhidos");
                showError();
            }
        }
        });
        playButton = new Button("Play");
        playButton.setOnAction(e -> {
            try {
                play(user, game);
//                if(game.isFinished()){
//                    fileToSaveGame(primaryStage, game, user, gameFile);
//                }
            } catch (CloneNotSupportedException | FilledSquareException/* | IOException*/ ex) {
                errorLabel.setText("Bloco ou posição não foram bem escolhidos");
                showError();
            }
        });
        playButton.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                play(user, game);
//                if(game.isFinished()){
//                    fileToSaveGame(primaryStage, game, user, gameFile);
//                }
            } catch (CloneNotSupportedException | FilledSquareException/* | IOException*/ ex) {
                errorLabel.setText("Bloco ou posição não foram bem escolhidos");
                showError();
            }
        }
        });
        errorLabel = new Label("");
        errorLabel.setTextFill(Color.RED);
        saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                fileToSaveGame(primaryStage, game, user, gameFile);
            } catch (IOException ex) {
                errorLabel.setText("Bloco ou posição não foram bem escolhidos");
                showError();
            }
        });
        saveButton.setMinWidth(50);
        finishButton = new Button("Finish");
        finishButton.setMinWidth(50);
        finishButton.setOnAction(e -> {
            GameScores gs = new GameScores();
            try {
                gs.insertScore(user, game.getCurrentScore());
                user.addScore(game.getCurrentScore());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            primaryStage.setScene(new Scene(new FinishGamePane(primaryStage, game, user)));
            primaryStage.setTitle("Jogo terminado");
            primaryStage.setX(primaryStage.getX() + 60);
            primaryStage.setY(60);
        });
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new MenuPane(primaryStage, user), 250, 250));
            primaryStage.setTitle("Menu");
        });
        cancelButton.setMinWidth(50);
        playHBox = new HBox(10);
        gameVBox = new VBox(10);
        optionsHBox = new HBox(20);
        optionsHBox.setAlignment(Pos.CENTER);
        optionsHBox.setPadding(new Insets(20));
        gameVBox.getChildren().add(scoreLabel);
        showVBox = setBoard(game);
        gameVBox.getChildren().add(showVBox);
        playHBox.getChildren().addAll(playLabel, playField, playButton);
        gameVBox.getChildren().addAll(playHBox, errorLabel);
        optionsHBox.getChildren().addAll(saveButton, finishButton, cancelButton);
        gameVBox.setPadding(new Insets(20, 0, 0, 20));
        this.setTop(gameVBox);
        this.setBottom(optionsHBox);
        
    }
    
    private void play(User user, Game game) throws CloneNotSupportedException, FilledSquareException{
        Board board = game.getBoard().clone();
        try{
            String block = playField.getText();
            Block chosenBlock;
            if (block.charAt(0) == 'A' || block.charAt(0) == 'a') {
                chosenBlock = game.getA();
                boolean play = game.playBlock(chosenBlock, block.toUpperCase());
                if(play == true){
                    game.setBlockA("");
                    errorLabel.setText("");
                }
            } else {if (block.charAt(0) == 'B' || block.charAt(0) == 'b') {
                    chosenBlock = game.getB();
                    boolean play = game.playBlock(chosenBlock, block.toUpperCase());
                    if(play == true){
                        game.setBlockB("");
                        errorLabel.setText("");
                    }
            } else {if ((block.charAt(0) == 'C' || block.charAt(0) == 'c')) {
                    chosenBlock = game.getC();
                    boolean play = game.playBlock(chosenBlock, block.toUpperCase());
                    if(play == true){
                        game.setBlockC("");
                        errorLabel.setText("");
                    }
            }}}
        } catch(StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e){
            game.setBoard(board);
            errorLabel.setText("Bloco ou posição não foram bem escolhidos");
            showError();
        }
        if(game.getAvailableBlocks() == 0){
            game.setRandomBlocks();
        }
        playField.setText("");
        scoreLabel.setText("Player " + user.getName() + " with " + game.getCurrentScore() + " points.");
        showVBox = setBoard(game);
        gameVBox.getChildren().set(1, showVBox);
    }
    
    private void fileToSaveGame(Stage primaryStage, Game game, User user, String gameFile) throws IOException{
        try{
            TextInputDialog dialog;
            Text messageState = new Text("");
            messageState.setFill(Color.FIREBRICK);
            dialog = new TextInputDialog(gameFile);
            dialog.setTitle("Guardar jogo");
            dialog.setHeaderText("Introduza um nome de ficheiro para guardar o jogo");
            Optional<String> result = dialog.showAndWait();
            String input = "";
            if(result.isPresent()){
                input = result.get();
                game.saveGame(input);
                primaryStage.setScene(new Scene(new MenuPane(primaryStage, user), 250, 250));
                primaryStage.setTitle("Menu");
            }
            messageState.setText("Texto introduzido: " + input);
        } catch (IOException e){
            errorLabel.setText(e.getMessage());
            showError();
        }
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
    
    private void showError(){
        FadeTransition transicao = new FadeTransition(Duration.millis(500.0), errorLabel);
        transicao.setFromValue(0.1);
        transicao.setToValue(1.0);
        transicao.setAutoReverse(false);
        transicao.play();
    }
}
