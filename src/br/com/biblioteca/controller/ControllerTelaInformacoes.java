package br.com.biblioteca.controller;

import biblioteca.Global;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.view.TelaCadastroFotografia;
import br.com.biblioteca.view.TelaCadastroFuncionario;
import br.com.biblioteca.view.TelaCadastroLivro;
import br.com.biblioteca.view.TelaCadastroProfessor;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ControllerTelaInformacoes implements Initializable {

    @FXML
    private AnchorPane Pane;
    
    @FXML
    private MenuItem menuUsuario;
    
    @FXML
    private Menu menuCadastrar;

    @FXML
    void cadastrarUsuario() throws IOException {
        TelaCadastroUser tela = new TelaCadastroUser();
        try {
            tela.start(new Stage());
            TelaCadastroUser.getStage().show();
        } catch (Exception ex) {
            System.out.println("Exception ao entrar na tela de cadastro\n"+ex);
        } 
    }
    
    @FXML
    void cadastrarProfessor(ActionEvent event) {
        TelaCadastroProfessor tela = new TelaCadastroProfessor();
        try {
            tela.start(new Stage());
            TelaCadastroProfessor.getStage().show();
        } catch (Exception ex) {
            System.out.println("Exception ao entrar na tela de cadastro de professor\n"+ex);
        } 
    }
    
    @FXML
    void getTelaEmprestimo(ActionEvent event) throws IOException {
        Parent menuRecebimento = FXMLLoader.load(getClass().getResource("/br/com/biblioteca/view/FXMLTelaEmprestimo.fxml"));
        AnchorPane.setTopAnchor(menuRecebimento, 72.0);
        AnchorPane.setBottomAnchor(menuRecebimento, 23.0);
        AnchorPane.setLeftAnchor(menuRecebimento, 23.0);
        AnchorPane.setRightAnchor(menuRecebimento, 23.0);
        Pane.getChildren().setAll(menuRecebimento);
    }
    
    @FXML
    void getReserva() throws IOException {
        Parent menuRecebimento = FXMLLoader.load(getClass().getResource("/br/com/biblioteca/view/FXMLTelaReserva.fxml"));
        AnchorPane.setTopAnchor(menuRecebimento, 72.0);
        AnchorPane.setBottomAnchor(menuRecebimento, 23.0);
        AnchorPane.setLeftAnchor(menuRecebimento, 23.0);
        AnchorPane.setRightAnchor(menuRecebimento, 23.0);
        Pane.getChildren().setAll(menuRecebimento);
    }

    
    @FXML
    void cadastrarFuncionario() {
        TelaCadastroFuncionario tela = new TelaCadastroFuncionario();
        try {
            tela.start(new Stage());
            TelaCadastroFuncionario.getStage().show();
        } catch (Exception ex) {
            System.out.println("Exception ao entrar na tela de cadastro de professor\n"+ex);
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
    
    @FXML
    void cadastrarFotografia() {
        TelaCadastroFotografia tela = new TelaCadastroFotografia();
        try {
            tela.start(new Stage());
            TelaCadastroFotografia.getStage().show();
        } catch (Exception ex) {
            System.out.println("Exception ao entrar na tela de cadastrar fotografia \n"+ex);
        } 
    }
		
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuCadastrar.setVisible(false);
        if (Global.usuario.getTipo().equals("Funcionario") ) {
            menuCadastrar.setVisible(true);
        }
    }

}
