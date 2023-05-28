/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import biblioteca.Global;
import static biblioteca.Global.livro;
import br.com.biblioteca.dao.EmprestimoDao;
import br.com.biblioteca.dao.FotografiaDao;
import br.com.biblioteca.dao.ObraDao;
import br.com.biblioteca.dao.ReservaDao;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Fotografia;
import br.com.biblioteca.model.Reserva;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Diego
 */
public class ControllerTelaFotografia implements Initializable{
    
    @FXML
    private TableView<Fotografia> tbFotografia;
    private final TableColumn cellFotografiaId = new TableColumn("Código");
    private final TableColumn cellFotografiaNome = new TableColumn("Nome");
    private final TableColumn cellFotografiaArea = new TableColumn("Area");
    private final TableColumn cellFotografiaTamanho = new TableColumn("Tamanho");
    private final TableColumn cellFotografiaDigital = new TableColumn("Tipo");
    private final TableColumn cellFotografiaStatus = new TableColumn("Status");
    private final TableColumn<Fotografia,Fotografia> cellFotografiaEmprestar = new TableColumn("Emprestimo");
    private final TableColumn<Fotografia,Fotografia> cellFotografiaRenovar = new TableColumn("Renovar");
    
    private final FotografiaDao fotografiaDao = new FotografiaDao();
    private final ObraDao obraDao = new ObraDao();
    private final EmprestimoDao emprestimoDao = new EmprestimoDao();
    private final ReservaDao reservaDao = new ReservaDao();
    private Fotografia fotografia = new Fotografia();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTabela();
    }
    
    private void carregaTabelaFotografia(ObservableList<Fotografia> list){
        tbFotografia.getColumns().clear();
        formataTabelaFotografia();
        tbFotografia.setItems(list);
        tbFotografia.getColumns().addAll(cellFotografiaId,cellFotografiaNome, cellFotografiaArea, cellFotografiaTamanho,cellFotografiaDigital, cellFotografiaStatus, cellFotografiaEmprestar,cellFotografiaRenovar);
    }
    
    private void formataTabelaFotografia(){
        cellFotografiaId.setMinWidth(100);
        cellFotografiaId.setPrefWidth(120);
        cellFotografiaId.setResizable(false);
        cellFotografiaId.setCellValueFactory (new PropertyValueFactory <> ( "codigo" ));
        cellFotografiaId.setCellFactory( cell -> {              
            return new TableCell<AbstractMethodError, Integer>() {
                @Override
                protected void updateItem( Integer item, boolean empty) {
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
        cellFotografiaId.setStyle("-fx-alignment: center;");
        
        cellFotografiaNome.setMinWidth(150);
        cellFotografiaNome.setPrefWidth(200);
        cellFotografiaNome.setResizable(false);
        cellFotografiaNome.setCellValueFactory (new PropertyValueFactory <> ( "nome" ));
        cellFotografiaNome.setStyle("-fx-alignment: center;");
        
        cellFotografiaDigital.setMinWidth(200);
        cellFotografiaDigital.setPrefWidth(250);
        cellFotografiaDigital.setResizable(false);
        cellFotografiaDigital.setCellValueFactory (new PropertyValueFactory <> ( "tipo" ));
        cellFotografiaDigital.setStyle("-fx-alignment: center;");
        
        cellFotografiaArea.setMinWidth(150);
        cellFotografiaArea.setPrefWidth(200);
        cellFotografiaArea.setResizable(false);
        cellFotografiaArea.setCellValueFactory (new PropertyValueFactory <> ( "area" ));
        cellFotografiaArea.setStyle("-fx-alignment: center;");
        
        cellFotografiaTamanho.setMinWidth(150);
        cellFotografiaTamanho.setPrefWidth(200);
        cellFotografiaTamanho.setResizable(false);
        cellFotografiaTamanho.setCellValueFactory (new PropertyValueFactory <> ( "tamanho" ));
        cellFotografiaTamanho.setStyle("-fx-alignment: center;");
        
        cellFotografiaStatus.setMinWidth(80);
        cellFotografiaStatus.setPrefWidth(100);
        cellFotografiaStatus.setResizable(false);
        cellFotografiaStatus.setCellValueFactory (new PropertyValueFactory <> ( "status" ));
        cellFotografiaStatus.setStyle("-fx-alignment: center;");
        
        cellFotografiaEmprestar.setMinWidth(50);
        cellFotografiaEmprestar.setPrefWidth(80);
        cellFotografiaEmprestar.setResizable(false);
        cellFotografiaEmprestar.setStyle("-fx-alignment: center;");
        cellFotografiaEmprestar.setCellFactory(col -> {
            TableCell<Fotografia, Fotografia> cell = new TableCell<Fotografia, Fotografia>() {
                @Override
                public void updateItem(Fotografia item, boolean empty) {
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
                                if (fotografia.getEmprestimo()) {
                                    Emprestimo emprestimo = new Emprestimo(fotografia,Global.usuario); 
                                    emprestimoDao.create(emprestimo);
                                    obraDao.updateEmprestimo(fotografia.getCodigo(), false);
                                    Alertas.alertaInformacao("Sucesso", "Emprestimo realizado com sucesso.");
                                    getTabela();
                                } else { 
                                    if (Alertas.confirmacao("Fotografia já emprestado!", "Deseja fazer reserva do Fotografia?") == 1) {
                                        Reserva reserva = new Reserva(fotografia,Global.usuario);
                                        reservaDao.create(reserva);
                                        Alertas.alertaInformacao("Sucesso", "Reserva realizada com sucesso.");
                                    }
                                }
                            }
                        );
                        setGraphic(botao);
                    }
                }
            };
            return cell ;
        });
        
        cellFotografiaRenovar.setMinWidth(50);
        cellFotografiaRenovar.setPrefWidth(80);
        cellFotografiaRenovar.setResizable(false);
        cellFotografiaRenovar.setStyle("-fx-alignment: center;");
        cellFotografiaRenovar.setCellFactory(col -> {
            TableCell<Fotografia, Fotografia> cell = new TableCell<Fotografia, Fotografia>() {
                @Override
                public void updateItem(Fotografia item, boolean empty) {
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
    
    public void getTabela(){
        List<Fotografia> fotografias = new ArrayList<>();
        fotografia = fotografiaDao.findById(Global.obra.getCodigo());
        fotografias.add(fotografia);
        carregaTabelaFotografia(FXCollections.observableArrayList(fotografias));
    }

}
