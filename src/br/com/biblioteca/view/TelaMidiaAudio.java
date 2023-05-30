/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.view;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Diego
 */
public class TelaMidiaAudio {
    public static Stage stage;
    public static Scene scene;
    
    public void start(Stage t) throws Exception {
        stage = new Stage();
        //para não esticar as laterais
        stage.setMaxWidth(1000);
        stage.setMaxHeight(288);
        //valor padrao da tela
        stage.setWidth(1000);
        stage.setHeight(288);
        //para não diminuir
        stage.setMinWidth(1000);
        stage.setMinHeight(288);
        //desativando o botão maximixar e minimizar
        stage.setResizable(false);
        
        Parent painel = FXMLLoader.load(getClass().getResource("FXMLTelaMidiaAudio.fxml"));
        scene = new Scene(painel);
        
        stage.setTitle("Mídia Áudio");
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
