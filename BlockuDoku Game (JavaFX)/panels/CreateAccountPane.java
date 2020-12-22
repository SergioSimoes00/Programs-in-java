/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import elements.User;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Neste painel é pedido um username e uma password não vazios, se forem vazios
 * aparece uma mensagem de erro a avisar. Há dois botões, um para criar a conta
 * e outro para cancelar, se for criada a conta, volta para o painel de login,
 * se cancelar apenas volta para o painel de login sem fazer qualquer outra ação.
 * 
 * 
 * @author Sérgio Simões
 */
public class CreateAccountPane extends BorderPane{
    private Label nameLabel;
    private TextField nameField;
    private Label passLabel;
    private TextField passField;
    private Label errorLabel;
    private Button createAccountButton;
    private Button cancelButton;
    private HBox nameHBox;
    private HBox passHBox;
    private HBox buttonsHBox;
    private VBox dataVBox;
    
    public CreateAccountPane(Stage primaryStage, User user){
        nameLabel = new Label("Username:");
        nameField = new TextField();
        passLabel = new Label("Password:");
        passField = new TextField();
        errorLabel = new Label("Ambos os campos devem ser preenchidos");
        errorLabel.setTextFill(Paint.valueOf("#d22e2e"));
        createAccountButton = new Button("Criar conta");
        createAccountButton.setOnAction(e -> {
            if(nameField.getText().equals("") || passField.getText().equals("")){
                dataVBox.getChildren().add(errorLabel);
            }else{
                try {
                    user.setName(nameField.getText());
                    user.setPassword(passField.getText());
                    user.saveUser();
                    primaryStage.setScene(new Scene(new LoginPane(primaryStage, user), 260, 200));
                    primaryStage.setTitle("Criar conta");
                    
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(new LoginPane(primaryStage, user), 260, 200));
            primaryStage.setTitle("Criar conta");
        });
        nameHBox = new HBox(10);
        passHBox = new HBox(10);
        buttonsHBox = new HBox(10);
        dataVBox = new VBox(10);
        nameHBox.getChildren().addAll(nameLabel, nameField);
        passHBox.getChildren().addAll(passLabel, passField);
        dataVBox.getChildren().addAll(nameHBox, passHBox);
        buttonsHBox.getChildren().addAll(createAccountButton, cancelButton);
        buttonsHBox.setAlignment(Pos.BASELINE_RIGHT);
        
        this.setPadding(new Insets(20));
        this.setCenter(dataVBox);
        this.setBottom(buttonsHBox);
    }
}
