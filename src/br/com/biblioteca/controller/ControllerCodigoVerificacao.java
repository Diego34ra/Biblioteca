package biblioteca;


import br.com.biblioteca.services.UsuarioServices;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerCodigoVerificacao implements Initializable {
	
    @FXML
    private AnchorPane painel;
    
    @FXML
    private Button txEnviar;

    @FXML
    private TextField txCodigo;

    @FXML
    private Button btSair;

    private String codigo;
    
    @FXML
    void enviarCodigo() {
        if(txCodigo.getText().equals(codigo)){
            UsuarioServices service = new UsuarioServices();
//            service.createUsuario(Global.usuario);
            Stage stage = (Stage) painel.getScene().getWindow();
            stage.close();
        } 
    }

    @FXML
    void btSair(ActionEvent event) {
        Stage stage = (Stage) painel.getScene().getWindow();
        stage.close();
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
