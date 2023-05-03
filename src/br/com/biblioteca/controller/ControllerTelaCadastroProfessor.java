/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import br.com.biblioteca.model.Estudante;
import br.com.biblioteca.model.Professor;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.services.UsuarioServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author 2022101202010058
 */
public class ControllerTelaCadastroProfessor implements Initializable {
    
    @FXML
    private AnchorPane Pane;
    
    @FXML
    private TextField txSenha;

    @FXML
    private TextField txTelefone;

    @FXML
    private CheckBox cbFeminino;

    @FXML
    private CheckBox cbMasculino;

    @FXML
    private TextField txNome;

    @FXML
    private TextField txIdade;

    @FXML
    private TextField txMatriculo;

    @FXML
    private TextField txEspecialidade;

    @FXML
    void setFeminino( ) {
        cbMasculino.setSelected(false);
        if(!cbFeminino.isSelected()){
            cbFeminino.setSelected(true);
        }
    }

    @FXML
    void setMasculino( ) {
        cbFeminino.setSelected(false);
        if(!cbMasculino.isSelected()){
            cbMasculino.setSelected(true);
        }
    }
    
    @FXML
    void salvar() {
        String sexo = "";
        if(cbMasculino.isSelected()){
            sexo = "Masculino";
        } else{
            sexo = "Feminino";
        }
 
        Usuario usuario = new Professor(Integer.parseInt(txIdade.getText()), txNome.getText(),
                                             sexo, txTelefone.getText(), txEspecialidade.getText(),
                                        txSenha.getText(), Long.parseLong(txMatriculo.getText()));
        
        if(UsuarioServices.create(usuario)){
            Alertas.alertaInformacao("Sucesso!", "Usuário cadastrado com sucesso!");
            //Fecha a tela atual
            Stage stage = (Stage) Pane.getScene().getWindow();
            stage.close();
        } else{
            Alertas.alertaInformacao("Erro!", "Erro ao cadastrar o Usuário!");
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbMasculino.setSelected(true);
    }

}


