/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;


import biblioteca.Alertas;
import biblioteca.Global;
import br.com.biblioteca.dao.UsuarioDao;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.view.TelaInformacoes;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TextField txMatricula;

    @FXML
    private PasswordField txSenha;

    @FXML
    private Button btTelaLogin;

    @FXML
    private Button btSair;
    
    private UsuarioDao usuarioDao = new UsuarioDao();
    
    @FXML
    void logar(){
//        Usuario usuario  = UsuarioServices.findById(txMatricula.getText());
        Usuario usuario  = usuarioDao.findById(Integer.valueOf(txMatricula.getText()));
        if(txMatricula.getText().equals(usuario.getMatricula().toString())){
            if (usuario.getSenha().equals(txSenha.getText())) {
                Global.usuario = usuario;
                TelaInformacoes tela = new TelaInformacoes();
                try {
                    tela.start(new Stage());
                    TelaInformacoes.getStage().show();
                } catch (Exception ex) {
                    System.out.println("Exception ao entrar no menu principal\n"+ex);
                } 
                //Fecha a tela atual
                Stage stage = (Stage) Pane.getScene().getWindow();
                stage.close();
            } else {
                Alertas.alertaInformacao("Senha incorreta!", "A senha digitada está incorreta.");
            }
        } else {
            Alertas.alertaInformacao("Matrícula inválida!", "Não existe usuário cadastrado com essa matrícula.");
        }
    }

	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}