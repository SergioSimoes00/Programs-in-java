/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import elements.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * O Programa começa com este painel que pede ao utilizador um username e uma
 * password válida, mostrando um erro a vermelho caso não sejam compatíveis, tem
 * também um botão de criar conta que passa para o painel CreateAccountPane, se o
 * login corresponder a algum previamente criado, então passa para o painel MenuPane.
 * 
 * 
 * @author Sérgio Simões
 */
public class LoginPane extends BorderPane{
    private Label nameLabel;
    private TextField nameField;
    private Label passLabel;
    private TextField passField;
    private Button loginButton;
    private Hyperlink newAccountLink;
    private HBox nameHBox;
    private HBox passHBox;
    private VBox allVBox;
    
    public LoginPane(Stage primaryStage, User user){
        nameLabel = new Label("Username:");
        nameField = new TextField();
        passLabel = new Label("Password:");
        passField = new TextField();
        loginButton = new Button("Login");
        Label errorLabel = new Label("Dados não coincidem,\npode ter de criar uma conta");
        errorLabel.setTextFill(Paint.valueOf("#d22e2e"));
        loginButton.setOnAction(e -> {
            if(!nameField.getText().equals("") && user.loadUser(nameField.getText()).isPassEqual(passField.getText())){
                primaryStage.setScene(new Scene(new MenuPane(primaryStage, new User(nameField.getText(), passField.getText())), 250, 250));
                primaryStage.setTitle("Menu");
            }else{
                this.setBottom(errorLabel);
            }
        });
        newAccountLink = new Hyperlink("Criar conta");
        newAccountLink.setOnAction(e -> {
            primaryStage.setScene(new Scene(new CreateAccountPane(primaryStage, user), 300, 160));
            primaryStage.setTitle("Criar conta");
        });
        nameHBox = new HBox(10);
        passHBox = new HBox(10);
        allVBox = new VBox(10);
        allVBox.setAlignment(Pos.CENTER);
        nameHBox.getChildren().addAll(nameLabel, nameField);
        passHBox.getChildren().addAll(passLabel, passField);
        allVBox.getChildren().addAll(nameHBox, passHBox, loginButton, newAccountLink);
        this.setPadding(new Insets(20));
        this.setCenter(allVBox);
    }
}
