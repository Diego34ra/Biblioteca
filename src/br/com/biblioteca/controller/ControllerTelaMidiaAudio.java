/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import biblioteca.Global;
import br.com.biblioteca.dao.EmprestimoDao;
import br.com.biblioteca.dao.MidiaAudioDao;
import br.com.biblioteca.dao.ObraDao;
import br.com.biblioteca.dao.ReservaDao;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Fotografia;
import br.com.biblioteca.model.MidiaAudio;
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
 * @author 2022101202010058
 */
public class ControllerTelaMidiaAudio implements Initializable{
    
    @FXML
    private TableView<MidiaAudio> tbMidiaAudio;
    private final TableColumn cellFotografiaId = new TableColumn("Código");
    private final TableColumn cellFotografiaNome = new TableColumn("Nome");
    private final TableColumn cellFotografiaAssunto = new TableColumn("Assunto");
    private final TableColumn cellFotografiaDuracao = new TableColumn("Duração");
    private final TableColumn cellFotografiaDigital = new TableColumn("Tipo");
    private final TableColumn cellFotografiaStatus = new TableColumn("Status");
    private final TableColumn<MidiaAudio,MidiaAudio> cellFotografiaEmprestar = new TableColumn("Emprestimo");
    private final TableColumn<MidiaAudio,MidiaAudio> cellFotografiaRenovar = new TableColumn("Renovar");
    
    private MidiaAudio midiaAudio = new MidiaAudio();
    private final MidiaAudioDao midiaAudioDao = new MidiaAudioDao();
    private final EmprestimoDao emprestimoDao = new EmprestimoDao();
    private final ReservaDao reservaDao = new ReservaDao();
    private final ObraDao obraDao = new ObraDao();
    
    private void carregaTabelaMidiaAudio(ObservableList<MidiaAudio> list){
        tbMidiaAudio.getColumns().clear();
        formataTabelaMidiaAudio();
        tbMidiaAudio.setItems(list);
        tbMidiaAudio.getColumns().addAll(cellFotografiaId,cellFotografiaNome, cellFotografiaAssunto, cellFotografiaDuracao,cellFotografiaDigital, cellFotografiaStatus, cellFotografiaEmprestar,cellFotografiaRenovar);
    }
    
    private void formataTabelaMidiaAudio(){
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
        
        cellFotografiaAssunto.setMinWidth(150);
        cellFotografiaAssunto.setPrefWidth(200);
        cellFotografiaAssunto.setResizable(false);
        cellFotografiaAssunto.setCellValueFactory (new PropertyValueFactory <> ( "assunto" ));
        cellFotografiaAssunto.setStyle("-fx-alignment: center;");
        
        cellFotografiaDuracao.setMinWidth(150);
        cellFotografiaDuracao.setPrefWidth(200);
        cellFotografiaDuracao.setResizable(false);
        cellFotografiaDuracao.setCellValueFactory (new PropertyValueFactory <> ( "duracao" ));
        cellFotografiaDuracao.setStyle("-fx-alignment: center;");
        
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
            TableCell<MidiaAudio, MidiaAudio> cell = new TableCell<MidiaAudio, MidiaAudio>() {
                @Override
                public void updateItem(MidiaAudio item, boolean empty) {
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
                                if (midiaAudio.getEmprestimo()) {
                                    Emprestimo emprestimo = new Emprestimo(midiaAudio,Global.usuario); 
                                    emprestimoDao.create(emprestimo);
                                    obraDao.updateEmprestimo(midiaAudio.getCodigo(), false);
                                    Alertas.alertaInformacao("Sucesso", "Emprestimo realizado com sucesso.");
                                    getTabela();
                                } else { 
                                    if (Alertas.confirmacao("Midia Áudio já emprestado!", "Deseja fazer reserva do Fotografia?") == 1) {
                                        Reserva reserva = new Reserva(midiaAudio,Global.usuario);
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
            TableCell<MidiaAudio, MidiaAudio> cell = new TableCell<MidiaAudio, MidiaAudio>() {
                @Override
                public void updateItem(MidiaAudio item, boolean empty) {
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
        List<MidiaAudio> midiaAudios = new ArrayList<>();
        midiaAudio = midiaAudioDao.findById(Global.obra.getCodigo());
        midiaAudios.add(midiaAudio);
        carregaTabelaMidiaAudio(FXCollections.observableArrayList(midiaAudios));
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTabela();
    }
    
}
