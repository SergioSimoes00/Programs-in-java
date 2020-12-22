/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import elements.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Este painel tem um botão para iniciar novo jogo, que aparece um diálogo de opções
 * a pedir o modo de jogo tendo as opções de básico e avançado, tem os botões para
 * iniciar o jogo e outro para voltar para o menu. O menu um outro botão para abrir
 * um jogo guardado que abre um outro diálogo, neste caso de texto, que pede para
 * inserir o nome do ficheiro correspondente ao jogo que o utilizador pretende abrir,
 * deixando apenas abrir o jogo se o utilizador for realmente o mesmo do jogo guardado,
 * tem o botão para abrir o jogo e outro para voltar para o menu. O menu tem ainda
 * um botão que permite ao utilizador ver o seu score history a partir de um painel
 * “HistoryScorePane” que tem um scroll bar caso o conteúdo seja maior do que o
 * painel, tem um botão para poder voltar para o menu. Há mais um quarto botão
 * no menu que permite ao utilizador ver o ranking do top 10 de todos os utilizadores
 * num painel “RankingPane” que tem uma lista enumerada de 1 a 10 com os 10 melhores
 * scores de todos os utilizadores, tem também um botão para poder voltar para o menu.
 * O último botão do menu serve para sair do programa.
 * 
 * 
 * @author Sérgio Simões
 */
public class MenuPane extends BorderPane{
    private Game game;
    private Label optionsLabel;
    private Button newGameButton;
    private Button openGameButton;
    private Button scoreHistoryButton;
    private Button rankingButton;
    private Button exitButton;
    private VBox allVBox;
    
    public MenuPane(Stage primaryStage, User user){
        primaryStage.setX(550);
        primaryStage.setY(200);
        optionsLabel = new Label("Escolha uma opção:");
        optionsLabel.setMinWidth(200);
        optionsLabel.setAlignment(Pos.CENTER);
        optionsLabel.setFont(Font.font(null, FontWeight.MEDIUM, 16.0));
        newGameButton = new Button("Iniciar novo jogo");
        newGameButton.setMinWidth(200);
        newGameButton.setOnAction(e -> {
            try {
                newGameDialog(primaryStage, game, user);
            } catch (CloneNotSupportedException | FilledSquareException ex) {
                System.out.println(ex.getMessage());
            }
        });
        openGameButton = new Button("Abrir jogo");
        openGameButton.setMinWidth(200);
        openGameButton.setOnAction(e -> {
            try {
                openGameDialog(primaryStage, game, user);
            } catch (CloneNotSupportedException | FilledSquareException ex) {
                System.out.println(ex.getMessage());
            }
        });
        scoreHistoryButton = new Button("Mostrar pontuações pessoais");
        scoreHistoryButton.setMinWidth(200);
        scoreHistoryButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new HistoryScorePane(primaryStage, user), 300, 500));
            primaryStage.setTitle("Pontuações pessoais");
            primaryStage.setX(550);
            primaryStage.setY(50);
        });
        rankingButton = new Button("Ranking(Top 10)");
        rankingButton.setMinWidth(200);
        rankingButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new RankingPane(primaryStage, user), 270, 300));
            primaryStage.setTitle("Rannking (Top 10)");
        });
        exitButton = new Button("Sair");
        exitButton.setOnAction(e -> Platform.exit());
        exitButton.setMinWidth(200);
        allVBox = new VBox(10);
        allVBox.setPadding(new Insets(20));
        allVBox.getChildren().addAll(optionsLabel, newGameButton, openGameButton,
                                     scoreHistoryButton, rankingButton, exitButton);
        this.setCenter(allVBox);
    }
    
    private void newGameDialog(Stage primaryStage, Game game, User user) throws CloneNotSupportedException, FilledSquareException{
        String[] dataArray = {"Modo básico", "Modo avançado"};
        List<String> dialogData = Arrays.asList(dataArray);
        Text state = new Text("");
        ChoiceDialog<String> dialog = new ChoiceDialog(dialogData.get(0), dialogData);
        dialog.setTitle("Iniciar novo jogo");
        dialog.setHeaderText("Escolha um modo de jogo");
        Optional<String> result = dialog.showAndWait();
        String selected = "canceled.";
        if(result.isPresent()){
            selected = result.get();
            if(selected.equals("Modo básico")){
                game = new Game(user.getName(), user.getPassword(), Mode.EASY);
            }else{
                game = new Game(user.getName(), user.getPassword(), Mode.HARD);
            }
            primaryStage.setScene(new Scene(new BlockudokuPane(primaryStage, user, game, ""), 600, 600));
            primaryStage.setTitle("Blockudoku");
            primaryStage.setX(400);
            primaryStage.setY(40);
        }
        state.setText("Seleção: " + selected);
    }
    
    private void openGameDialog(Stage primaryStage, Game game, User user) throws CloneNotSupportedException, FilledSquareException{
        game = new Game();
        TextInputDialog dialog;
        Text messageState = new Text("");
        messageState.setFill(Color.FIREBRICK);
        dialog = new TextInputDialog();
        dialog.setTitle("Abrir jogo");
        dialog.setHeaderText("Introduza o nome de um ficheiro com o jogo guardado pretendido");
        Optional<String> result = dialog.showAndWait();
        String input = "";
        if(result.isPresent()){
            input = result.get();
            
            if(game.loadGame(input).getUserName().equals(user.getName())){
                primaryStage.setScene(new Scene(new BlockudokuPane(primaryStage, user, game.loadGame(input), input), 600, 600));
                primaryStage.setTitle("Blockudoku");
                primaryStage.setX(400);
                primaryStage.setY(40);
            }
        }
        messageState.setText("Texto introduzido: " + input);
    }
}