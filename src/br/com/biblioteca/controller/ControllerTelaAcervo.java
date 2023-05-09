/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Global;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Obra;
import br.com.biblioteca.services.ObraServices;
import br.com.biblioteca.view.TelaCadastroUser;
import br.com.biblioteca.view.TelaLivro;
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
public class ControllerTelaAcervo implements Initializable{
    
    @FXML
    private TextField txConsulta;
    
    @FXML
    private ComboBox<String> cbConsulta;
    
    @FXML
    private TableView<Obra> tbAcervo;
    private final TableColumn cellAcervoId = new TableColumn("Código");
    private final TableColumn cellAcervoNome = new TableColumn("Nome");
    private final TableColumn cellAcervotipo = new TableColumn("Tipo");
    private final TableColumn<Obra,Obra> cellAcervoDetalhes = new TableColumn("Detalhes");
    private final TableColumn<Obra,Obra> cellAcervoDelete = new TableColumn("Deletar");
    
    private void carregaTabelaAcervo(ObservableList<Obra> list){
        tbAcervo.getColumns().clear();
        formataTabelaAcervo();
        tbAcervo.setItems(list);
        if(Global.usuario instanceof Funcionario){
           tbAcervo.getColumns().addAll(cellAcervoId,cellAcervoNome,cellAcervotipo,cellAcervoDetalhes, cellAcervoDelete); 
        } else {
            tbAcervo.getColumns().addAll(cellAcervoId,cellAcervoNome,cellAcervotipo,cellAcervoDetalhes); 
        }
    }

            
    @FXML
    void getAcervo() {
        ObservableList<Obra> obj = null;
        switch (cbConsulta.getSelectionModel().getSelectedItem()) {
            case "Todos":
                obj = FXCollections.observableArrayList(ObraServices.findAll());
                break;
            case "Código":
                obj = FXCollections.observableArrayList(ObraServices.findById(txConsulta.getText()));
                break;
            default:
                throw new AssertionError();
        }
        carregaTabelaAcervo(obj);
    }
    
    
    private void formataTabelaAcervo(){
        cellAcervoId.setMinWidth(100);
        cellAcervoId.setPrefWidth(120);
        cellAcervoId.setResizable(false);
        cellAcervoId.setCellValueFactory (new PropertyValueFactory <> ( "codigo" ));
        cellAcervoId.setCellFactory( cell -> {              
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
        cellAcervoId.setStyle("-fx-alignment: center;");
        
        cellAcervoNome.setMinWidth(200);
        cellAcervoNome.setPrefWidth(350);
        cellAcervoNome.setResizable(false);
        cellAcervoNome.setCellValueFactory (new PropertyValueFactory <> ( "nome" ));
        cellAcervoNome.setStyle("-fx-alignment: center;");
        
        cellAcervotipo.setMinWidth(200);
        cellAcervotipo.setPrefWidth(350);
        cellAcervotipo.setResizable(false);
        cellAcervotipo.setCellValueFactory (new PropertyValueFactory <> ( "tipo" ));
        cellAcervotipo.setStyle("-fx-alignment: center;");
        
        cellAcervoDetalhes.setMinWidth(50);
        cellAcervoDetalhes.setPrefWidth(80);
        cellAcervoDetalhes.setResizable(false);
        cellAcervoDetalhes.setStyle("-fx-alignment: center;");
        cellAcervoDetalhes.setCellFactory(col -> {
            TableCell<Obra, Obra> cell = new TableCell<Obra, Obra>() {
                @Override
                public void updateItem(Obra item, boolean empty) {
                    final Tooltip infAjuda = new Tooltip();
                    infAjuda.setText("Buscar detalhe da obra");
                    Button botao = new Button();
                    File file = new File("C:/Users/Developer/Documents/GitHub/Biblioteca/img/detalhe.png");
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
                                Obra obra = getTableView().getItems().get(getIndex());
                                switch (getTableView().getItems().get(getIndex()).getTipo()) {
                                    case "Livro":
                                        Global.livro = (Livro) obra;
                                        TelaLivro tela = new TelaLivro();
                                        try {
                                            tela.start(new Stage());
                                            TelaLivro.getStage().show();
                                        } catch (Exception ex) {
                                            System.out.println("Exception ao entrar na tela de detalhes\n"+ex);
                                        } 
                                        break;
                                    case "Mídia Áudio":
                                        break;
                                    case "Fotografia":
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                            }
                        );
                        setGraphic(botao);
                    }
                }
            };
            return cell ;
        });
        
        cellAcervoDelete.setMinWidth(50);
        cellAcervoDelete.setPrefWidth(80);
        cellAcervoDelete.setResizable(false);
        cellAcervoDelete.setStyle("-fx-alignment: center;");
        cellAcervoDelete.setCellFactory(col -> {
            TableCell<Obra, Obra> cell = new TableCell<Obra, Obra>() {
                @Override
                public void updateItem(Obra item, boolean empty) {
                    final Tooltip infAjuda = new Tooltip();
                    infAjuda.setText("Deletar obra");
                    Button botao = new Button();
                    File file = new File("C:/Users/Developer/Documents/GitHub/Biblioteca/img/delete.png");
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
                                Obra obra = getTableView().getItems().get(getIndex());
                                ObraServices.deleteById(obra.getCodigo());
                                ObservableList<Obra> obj = FXCollections.observableArrayList(ObraServices.findAll());
                                carregaTabelaAcervo(obj);
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
