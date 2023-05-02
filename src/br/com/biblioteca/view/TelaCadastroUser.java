package br.com.biblioteca.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TelaCadastroUser extends Application {

    public static Stage stage;
    public static Scene scene;
    
    public void start(Stage t) throws Exception {
        stage = new Stage();
        //para não esticar as laterais
        stage.setMaxWidth(546);
        stage.setMaxHeight(333);
        //valor padrao da tela
        stage.setWidth(546);
        stage.setHeight(333);
        //para não diminuir
        stage.setMinWidth(546);
        stage.setMinHeight(333);
        //desativando o botão maximixar e minimizar
        stage.setResizable(false);
        
        Parent painel = FXMLLoader.load(getClass().getResource("FXMLTelaCadastro.fxml"));
        scene = new Scene(painel);
        
        stage.setTitle("Cadastrar Livro");
//        stage.getIcons().add(new Image(TelaLogin.class.getResourceAsStream( "icon.png" ))); 
        
        stage.show();
        
        stage.setScene(scene);
        
        stage.setOnCloseRequest((WindowEvent t1) -> {
            t1.consume();
            stage.close();
        });
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage() {
        return stage;
    }
    
    public static Scene getScene(){
        return scene;
    }

}
