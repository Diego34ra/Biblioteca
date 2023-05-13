/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import biblioteca.Global;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.services.ReservaServices;
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
    private final TableColumn<Reserva,Livro> cellReservaLivro = new TableColumn("Livro");
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
                obj = FXCollections.observableArrayList(ReservaServices.findAll());
                break;
            case "Código":
                obj = FXCollections.observableArrayList(ReservaServices.findById(txConsulta.getText()));
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
           tbReserva.getColumns().addAll(cellReservaId,cellReservaUsuario,cellReservaLivro,cellReservaDevolver); 
        } else {
            tbReserva.getColumns().addAll(cellReservaId,cellReservaUsuario,cellReservaLivro,cellReservaDevolver); 
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
//                    System.out.println("nome = "+ item.getNome());
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
        
        
        cellReservaLivro.setMinWidth(200);
        cellReservaLivro.setPrefWidth(340);
        cellReservaLivro.setResizable(false);
        cellReservaLivro.setCellValueFactory (new PropertyValueFactory <> ( "livro" ));
        cellReservaLivro.setCellFactory( col -> {              
            return new TableCell<Reserva, Livro>() {
                @Override
                protected void updateItem( Livro item, boolean empty) {
                   super.updateItem(item, empty);
                   if(item == null|| empty) {
                       setText("");
                       setGraphic(null);
                   }else {
                       setText(item.getTitulo());
                   }
                }
            };
         });
        cellReservaLivro.setStyle("-fx-alignment: center;");
        
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
                                ReservaServices.deleteById(getTableView().getItems().get(getIndex()).getCodigo());
                                Alertas.alertaInformacao("Sucesso", "Renovação realizada com sucesso.");
                            }
                        );
                        setGraphic(botao);
                    }
                }
            };
            return cell ;
        });
        
//        cellAcervoDetalhes.setMinWidth(50);
//        cellAcervoDetalhes.setPrefWidth(80);
//        cellAcervoDetalhes.setResizable(false);
//        cellAcervoDetalhes.setStyle("-fx-alignment: center;");
//        cellAcervoDetalhes.setCellFactory(col -> {
//            TableCell<Obra, Obra> cell = new TableCell<Obra, Obra>() {
//                @Override
//                public void updateItem(Obra item, boolean empty) {
//                    final Tooltip infAjuda = new Tooltip();
//                    infAjuda.setText("Buscar detalhe da obra");
//                    Button botao = new Button();
//                    File file = new File("C:/Users/Developer/Documents/GitHub/Biblioteca/img/detalhe.png");
//                    Image imagem = new Image(file.toURI().toString());
//                    ImageView imv = new ImageView();
//                    {
//                        imv.setFitHeight(20l);
//                        imv.setFitWidth(20l);
//                    }
//                    imv.setImage(imagem);
//                    botao.setPickOnBounds(true);
//                    botao.setGraphic(imv);
//                    botao.setAlignment(Pos.CENTER);
//                    super.updateItem(item, empty);
//                    if (empty) {
//                        setGraphic(null);
//                    } else {
//                        botao.setOnAction(event -> 
//                            { 
//                                Obra obra = getTableView().getItems().get(getIndex());
//                                switch (getTableView().getItems().get(getIndex()).getTipo()) {
//                                    case "Livro":
//                                        Global.livro = (Livro) obra;
//                                        TelaLivro tela = new TelaLivro();
//                                        try {
//                                            tela.start(new Stage());
//                                            TelaLivro.getStage().show();
//                                        } catch (Exception ex) {
//                                            System.out.println("Exception ao entrar na tela de detalhes\n"+ex);
//                                        } 
//                                        break;
//                                    case "Mídia Áudio":
//                                        break;
//                                    case "Fotografia":
//                                        break;
//                                    default:
//                                        throw new AssertionError();
//                                }
//                            }
//                        );
//                        setGraphic(botao);
//                    }
//                }
//            };
//            return cell ;
//        });
        
//        cellAcervoDelete.setMinWidth(50);
//        cellAcervoDelete.setPrefWidth(80);
//        cellAcervoDelete.setResizable(false);
//        cellAcervoDelete.setStyle("-fx-alignment: center;");
//        cellAcervoDelete.setCellFactory(col -> {
//            TableCell<Obra, Obra> cell = new TableCell<Obra, Obra>() {
//                @Override
//                public void updateItem(Obra item, boolean empty) {
//                    final Tooltip infAjuda = new Tooltip();
//                    infAjuda.setText("Deletar obra");
//                    Button botao = new Button();
//                    File file = new File("C:/Users/Developer/Documents/GitHub/Biblioteca/img/delete.png");
//                    Image imagem = new Image(file.toURI().toString());
//                    ImageView imv = new ImageView();
//                    {
//                        imv.setFitHeight(20l);
//                        imv.setFitWidth(20l);
//                    }
//                    imv.setImage(imagem);
//                    botao.setPickOnBounds(true);
//                    botao.setGraphic(imv);
//                    botao.setAlignment(Pos.CENTER);
//                    super.updateItem(item, empty);
//                    if (empty) {
//                        setGraphic(null);
//                    } else {
//                        botao.setOnAction(event -> 
//                            { 
//                                Obra obra = getTableView().getItems().get(getIndex());
//                                ObraServices.deleteById(obra.getCodigo());
//                                ObservableList<Obra> obj = FXCollections.observableArrayList(ObraServices.findAll());
//                                carregaTabelaAcervo(obj);
//                            }
//                        );
//                        setGraphic(botao);
//                    }
//                }
//            };
//            return cell ;
//        });
            
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbConsulta.getItems().addAll(Global.tipoConsulta("acervo"));
        
        cbConsulta.getSelectionModel().selectFirst();
    }
}
