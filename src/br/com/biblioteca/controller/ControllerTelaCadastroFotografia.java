/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import br.com.biblioteca.dao.ObraDao;
import br.com.biblioteca.model.Fotografia;
import br.com.biblioteca.model.Obra;
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
 * @author Diego
 */
public class ControllerTelaCadastroFotografia implements Initializable{
    
    @FXML
    private CheckBox cbFisica;

    @FXML
    private CheckBox cbDigital;
    @FXML
    private AnchorPane Pane;

    @FXML
    private TextField txArea;
    
    @FXML
    private TextField txTamanho;

    @FXML
    private TextField txNome;
    
    private final ObraDao obraDao = new ObraDao();

    @FXML
    void Salvar( ) {
        Boolean digital = false;
        if(cbDigital.isSelected()){
            digital = true;
        }
        
        Fotografia fotografia = new Fotografia(txTamanho.getText(),txArea.getText(),
                                               "Fotografia",digital,txNome.getText());
        Obra obra = (Obra) fotografia;
        
        if(obraDao.create(obra)){
            Alertas.alertaInformacao("Sucesso!", "Fotografia cadastrado com sucesso!");
            //Fecha a tela atual
            Stage stage = (Stage) Pane.getScene().getWindow();
            stage.close();
        } else{
            Alertas.alertaInformacao("Erro!", "Erro ao cadastrar o Fotografia!");
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
