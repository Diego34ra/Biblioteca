package br.com.biblioteca.controller;

import br.com.biblioteca.view.TelaCadastroLivro;
import br.com.biblioteca.view.TelaCadastroUser;
import br.com.biblioteca.view.TelaInformacoes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ControllerTelaInformacoes implements Initializable {

    @FXML
    private AnchorPane Pane;
    
    @FXML
    private MenuItem menuUsuario;

    @FXML
    void cadastrarUsuario() throws IOException {
        TelaCadastroUser tela = new TelaCadastroUser();
        try {
            tela.start(new Stage());
            TelaCadastroUser.getStage().show();
        } catch (Exception ex) {
            System.out.println("Exception ao entrar no menu principal\n"+ex);
        } 
    }
    
    @FXML
    void getTelaAcervo() throws IOException {
        Parent menuRecebimento = FXMLLoader.load(getClass().getResource("/br/com/biblioteca/view/FXMLTelaAcervo.fxml"));
        AnchorPane.setTopAnchor(menuRecebimento, 72.0);
        AnchorPane.setBottomAnchor(menuRecebimento, 23.0);
        AnchorPane.setLeftAnchor(menuRecebimento, 23.0);
        AnchorPane.setRightAnchor(menuRecebimento, 23.0);
        Pane.getChildren().setAll(menuRecebimento);
    }
    
    @FXML
    void cadastrarLivro() {
        TelaCadastroLivro tela = new TelaCadastroLivro();
        try {
            tela.start(new Stage());
            TelaInformacoes.getStage().show();
        } catch (Exception ex) {
            System.out.println("Exception ao entrar no menu principal\n"+ex);
        } 
    }
		
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
