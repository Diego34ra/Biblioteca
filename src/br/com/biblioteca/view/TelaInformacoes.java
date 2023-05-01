package br.com.biblioteca.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class TelaInformacoes extends Application {
public static Stage stage;
    public static Scene scene;
    
    @Override
    public void start(Stage t) throws Exception {
        stage =  new Stage();
        
        stage.setWidth(1000);
        stage.setHeight(800);
        
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        
        stage.setResizable(true);
        
        Parent painel = FXMLLoader.load(getClass().getResource("FXMLTelaInicial.fxml"));
        
        scene = new Scene(painel);
        
        stage.setTitle("Biblioteca");
//        stage.getIcons().add(new Image(TelaInicial.class.getResourceAsStream( "icon.png" ))); 
        
        stage.setScene(scene);
        
        stage.setOnCloseRequest((WindowEvent t1) -> {
            t1.consume();
            stage.close();
            Platform.exit();
            System.exit(0);
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
