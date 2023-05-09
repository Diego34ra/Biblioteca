/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Obra;
import br.com.biblioteca.services.ObraServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Diego
 */
public class ControllerTelaCadastroLivro implements Initializable{
    
    @FXML
    private AnchorPane Pane;
    
    @FXML
    private CheckBox cbFisica;

    @FXML
    private CheckBox cbDigital;

    @FXML
    private Button btSalvar;
    
    @FXML
    private TextField txNumFolhas;

    @FXML
    private TextField txTitulo;

    @FXML
    private TextField txAno;

    @FXML
    private TextField txAutores;

    @FXML
    private TextField txEditora;

    @FXML
    private TextField txEdicao;

    @FXML
    void Salvar() {
        Boolean digital = false;
        if(cbDigital.isSelected()){
            digital = true;
        }
        Livro livro = new Livro("Livro", txAutores.getText(), txTitulo.getText(), txTitulo.getText(),
                                 txEditora.getText(), Integer.parseInt(txAno.getText()),
                                 Integer.parseInt(txEdicao.getText()), Integer.parseInt(txNumFolhas.getText()),
                                 digital);
        System.out.println("titulo livro = "+ livro.getTitulo());
        Obra obra = (Obra) livro;
        if(ObraServices.createObra(obra)){
            Alertas.alertaInformacao("Sucesso!", "Livro cadastrado com sucesso!");
            //Fecha a tela atual
            Stage stage = (Stage) Pane.getScene().getWindow();
            stage.close();
        } else{
            Alertas.alertaInformacao("Erro!", "Erro ao cadastrar o livro!");
        }
    }
    
    @FXML
    void setFisica() {
        cbDigital.setSelected(false);
        if(!cbFisica.isSelected()){
            cbFisica.setSelected(true);
        }
        
    }

    @FXML
    void setDigital( ) {
        cbFisica.setSelected(false);
        if(!cbDigital.isSelected()){
            cbDigital.setSelected(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbFisica.setSelected(true);
    }

}
