/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import biblioteca.Global;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Obra;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.model.Usuario;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Diego
 */
public class ControllerTelaReserva implements Initializable{
    
    @FXML
    private TableView<Reserva> tbReserva;
    private final TableColumn cellReservaId = new TableColumn("Código");
    private final TableColumn<Reserva,Usuario> cellReservaUsuario = new TableColumn("Usuario");
    private final TableColumn<Reserva,Obra> cellReservaObra = new TableColumn("Obra");
    private final TableColumn<Reserva,Reserva> cellReservaDevolver = new TableColumn("Deletar");

    @FXML
    private TextField txConsulta;

    @FXML
    private ComboBox<String> cbConsulta;

    @FXML
    void getReserva() {
        ObservableList<Reserva> obj = null;
        switch (cbConsulta.getSelectionModel().getSelectedItem()) {
            case "Todos":
//                obj = FXCollections.observableArrayList(ReservaServices.findAll());
                break;
            case "Código":
//                obj = FXCollections.observableArrayList(ReservaServices.findById(txConsulta.getText()));
                break;
            default:
                throw new AssertionError();
        }
        carregaTabelaAcervo(obj);
    }
    
    private void carregaTabelaAcervo(ObservableList<Reserva> list){
        tbReserva.getColumns().clear();
        formataTabelaAcervo();
        tbReserva.setItems(list);
        if(Global.usuario instanceof Funcionario){
           tbReserva.getColumns().addAll(cellReservaId,cellReservaUsuario,cellReservaObra,cellReservaDevolver); 
        } else {
            tbReserva.getColumns().addAll(cellReservaId,cellReservaUsuario,cellReservaObra,cellReservaDevolver); 
        }
    }
    
    private void formataTabelaAcervo(){
        cellReservaId.setMinWidth(100);
        cellReservaId.setPrefWidth(120);
        cellReservaId.setResizable(false);
        cellReservaId.setCellValueFactory (new PropertyValueFactory <> ( "codigo" ));
        cellReservaId.setCellFactory( cell -> {              
            return new TableCell<AbstractMethodError, Long>() {
                @Override
                protected void updateItem( Long item, boolean empty) {
                   super.updateItem(item, empty);
                   if(item == null|| empty) {
                       setText("");
                       setGraphic(null);
                   }else {
                        setText(item.toString());
                   }
                }
            };
         });
        cellReservaId.setStyle("-fx-alignment: center;");
        
        cellReservaUsuario.setMinWidth(200);
        cellReservaUsuario.setPrefWidth(340);
        cellReservaUsuario.setResizable(false);
        cellReservaUsuario.setCellValueFactory (new PropertyValueFactory <> ( "usuario" ));
        cellReservaUsuario.setCellFactory( col -> {              
            return new TableCell<Reserva, Usuario>() {
                @Override
                protected void updateItem( Usuario item, boolean empty) {
                   super.updateItem(item, empty);
                   if(item == null|| empty) {
                       setText("");
                       setGraphic(null);
                   }else {
                       setText(item.getNome());
                   }
                }
            };
         });
        cellReservaUsuario.setStyle("-fx-alignment: center;");
        
        
        cellReservaObra.setMinWidth(200);
        cellReservaObra.setPrefWidth(340);
        cellReservaObra.setResizable(false);
        cellReservaObra.setCellValueFactory (new PropertyValueFactory <> ( "obra" ));
        cellReservaObra.setCellFactory( col -> {              
            return new TableCell<Reserva, Obra>() {
                @Override
                protected void updateItem( Obra item, boolean empty) {
                   super.updateItem(item, empty);
                   if(item == null|| empty) {
                       setText("");
                       setGraphic(null);
                   }else {
                       setText(item.getNome());
                   }
                }
            };
         });
        cellReservaObra.setStyle("-fx-alignment: center;");
        
        cellReservaDevolver.setMinWidth(50);
        cellReservaDevolver.setPrefWidth(90);
        cellReservaDevolver.setResizable(false);
        cellReservaDevolver.setStyle("-fx-alignment: center;");
        cellReservaDevolver.setCellFactory(col -> {
            TableCell<Reserva, Reserva> cell = new TableCell<Reserva, Reserva>() {
                @Override
                public void updateItem(Reserva item, boolean empty) {
                    final Tooltip infAjuda = new Tooltip();
                    infAjuda.setText("Buscar detalhe da obra");
                    Button botao = new Button();
                    File file = new File("C:/Users/Developer/Documents/GitHub/Biblioteca/img/realizar.png");
                    Image imagem = new Image(file.toURI().toString());
                    ImageView imv = new ImageView();
                    {
                        imv.setFitHeight(20l);
                        imv.setFitWidth(20l);
                    }
                    imv.setImage(imagem);
                    botao.setPickOnBounds(true);
                    botao.setGraphic(imv);
                    botao.setAlignment(Pos.CENTER);
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        botao.setOnAction(event -> 
                            { 
                                
                            }
                        );
                        setGraphic(botao);
                    }
                }
            };
            return cell ;
        });
            
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbConsulta.getItems().addAll(Global.tipoConsulta("acervo"));
        
        cbConsulta.getSelectionModel().selectFirst();
    }
}
