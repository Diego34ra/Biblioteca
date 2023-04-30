package br.com.biblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerTelaInformacoes implements Initializable {

    @FXML
    private BorderPane BorderPane;
    
    @FXML
    private MenuItem menuUsuario;

    @FXML
    void cadastrarUsuario(ActionEvent event) throws IOException {
//        BorderPane BorderPane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        Parent menuRecebimento = FXMLLoader.load(getClass().getResource("/br/com/biblioteca/view/FXMLTeste.fxml"));
        BorderPane.setCenter(menuRecebimento);
    }
		
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
