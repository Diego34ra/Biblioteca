/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import br.com.biblioteca.dao.ObraDao;
import br.com.biblioteca.model.MidiaAudio;
import br.com.biblioteca.model.Obra;
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
 * @author 2022101202010058
 */
public class ControllerTelaCadastroMidiaAudio implements Initializable{
    
    @FXML
    private AnchorPane Pane;

    @FXML
    private TextField txNome;

    @FXML
    private TextField txAssunto;

    @FXML
    private TextField txDuracao;

    @FXML
    private CheckBox cbFisica;

    @FXML
    private CheckBox cbDigital;

    @FXML
    private Button btSalvar;
    
    private final ObraDao obraDao = new ObraDao();

    @FXML
    void Salvar() {
        Boolean digital = false;
        if(cbDigital.isSelected()){
            digital = true;
        }
        
        MidiaAudio midiaAudio = new MidiaAudio(txAssunto.getText(), txDuracao.getText(), "Mídia Áudio",
                digital, txNome.getText());
        
        Obra obra = (Obra) midiaAudio;
        if(obraDao.create(obra)){
            Alertas.alertaInformacao("Sucesso!", "Mídia Áudio cadastrado com sucesso!");
            //Fecha a tela atual
            Stage stage = (Stage) Pane.getScene().getWindow();
            stage.close();
        } else{
            Alertas.alertaInformacao("Erro!", "Erro ao cadastrar o Mídia Áudio!");
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
