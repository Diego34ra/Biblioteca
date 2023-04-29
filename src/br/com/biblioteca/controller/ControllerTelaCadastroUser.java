package br.com.biblioteca.controller;

import br.com.biblioteca.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerTelaCadastroUser implements Initializable {

        @FXML
        private TextField txNome;

        @FXML
        private TextField txCpf;

        @FXML
        private TextField txEmail;

        @FXML
        private PasswordField psSenha;

        @FXML
        private Button btCadastrarUser;

        private Usuario usuario;

        @FXML
        void cadastrar() {
            boolean valida = true;
//            if(!UsuarioService.findByEmail(txEmail.getText()).getEmail().equals("")){
//                System.out.println("E-mail já cadastrado");
//                valida = false;
//            }  else if(!UsuarioService.findByCpf(txCpf.getText()).getCpf().equals("")){
//                System.out.println("Cpf já cadastrado");
//                valida = false;
//            }
//            if (valida) {
//                Global.email = txEmail.getText();
//                TelaVerificacao tela = new TelaVerificacao();
//                try {
//                    tela.start(new Stage());
//                    TelaVerificacao.getStage().show();
//                    Global.usuario = new Usuario( txCpf.getText(),txEmail.getText(),
//                                                  txNome.getText(), psSenha.getText());
//                } catch (Exception ex) {
//                    System.out.println("Exception ao criar a tela de cadastro\n"+ex);
//                }
//            }
            
        }
        
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub	
	}

}
