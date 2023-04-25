/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.services.UsuarioServices;
import br.com.faculdade.projetopoo.view.TelaCadastroUser;
import br.com.faculdade.projetopoo.view.TelaInformacoes;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Diego
 */
public class ControllerTelaPrincipal implements Initializable {    
   
    @FXML
    private AnchorPane Pane;
   
    @FXML
    private Button btSair;

    @FXML
    private Button btTelaCadastro;

    @FXML
    private Button btTelaLogin;

    @FXML
    private PasswordField txSenha;

    @FXML
    private TextField txMatricula;

    @FXML
    private Label txRecuperaSenha;

    @FXML
    void recuperarSenha() {
        System.out.println("recuperar senha");
    }
    
    @FXML
    void logar() throws NoSuchAlgorithmException {
        Usuario usuario  = UsuarioServices.findById(txMatricula.getText());
        if(txMatricula.getText().equals(usuario.getMatricula())){
            if (usuario.getSenha().equals(txSenha.getText())) {
                TelaInformacoes tela = new TelaInformacoes();
                try {
                    tela.start(new Stage());
                    TelaInformacoes.getStage().show();
                } catch (Exception ex) {
                    System.out.println("Exception ao entrar no menu principal\n"+ex);
                } 
            } else {
                Alertas.alertaInformacao("Senha incorreta!", "A senha digitada está incorreta.");
            }
        } else {
            Alertas.alertaInformacao("Matrícula inválida!", "Não existe usuário cadastrado com essa matrícula.");
        }
    }
    
    @FXML
    void cadastrarUsuario() {
        TelaCadastroUser tela = new TelaCadastroUser();
        try {
            tela.start(new Stage());
            TelaCadastroUser.getStage().show();
        } catch (Exception ex) {
            System.out.println("Exception ao criar a tela de cadastro\n"+ex);
        }       
    }
    
    @FXML
    void btSair(ActionEvent event) {
    	System.exit(0);
    }
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}