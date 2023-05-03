/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import br.com.biblioteca.model.Obra;
import br.com.biblioteca.services.ObraServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Diego
 */
public class ControllerTelaAcervo {
    
    @FXML
    private TextField txConsulta;
    
    @FXML
    private TableView<Obra> tbAcervo;
    private final TableColumn cellAcervoId = new TableColumn("Código");
    private final TableColumn cellAcervoNome = new TableColumn("Nome");
    private final TableColumn cellAcervotipo = new TableColumn("Tipo");
    private final TableColumn<Obra,Obra> cellAcervoDetalhes = new TableColumn("Detalhes");
    
//    private final TableColumn<ClienteBean,ClienteBean> cellCliIntegerar = new TableColumn("Integra");
    
    private void carregaTabelaProjeto(ObservableList<Obra> list){
        tbAcervo.getColumns().clear();
        formataTabelaProjeto();
        tbAcervo.setItems(list);
        tbAcervo.getColumns().addAll(cellAcervoId,cellAcervoNome,cellAcervotipo,cellAcervoDetalhes);
    }
    
//    switch (cbTipoConsultaClienteGeral.getSelectionModel().getSelectedItem()){
//                    case "Nome":
//                        sql = "SELECT C.NAME AS NOME, \n" +
//                            "C.DOCUMENTNR AS CGC, \n" +
//                            "C.INTEGRADO,C.CUSTOMERID,\n" +
//                            "C.INTEGRADORA AS IDINTEGRADORA\n" +
//                            "FROM CLIENTE C WHERE C.INTEGRADORA = "+conf.getId()+
//                            "\n AND C.NAME LIKE '%"+txClienteGeral.getText()+"%'";
//                        
//                        carregaTabelaClienteGeral(ClienteIntegradoDao.getListCliente(sql));
//                        ObservableList<ClienteBean> obj = FXCollections.observableArrayList(lista);
//                        break;
//    }
            
    @FXML
    void getAcervo() {
        System.out.println("Aqui");
        ObservableList<Obra> obj = FXCollections.observableArrayList(ObraServices.findAll());
        carregaTabelaProjeto(obj);
    }
    
    
    private void formataTabelaProjeto(){
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
        cellAcervoNome.setStyle("-fx-alignment: center-left;");
        
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
                    infAjuda.setText("Exclui o cliente da integradora.");
                    Button botao = new Button();
//                    File file = new File(caminho);
//                    Image imagem = new Image(file.toURI().toString());
//                    ImageView imv = new ImageView();
//                    {
//                        imv.setFitHeight(20l);
//                        imv.setFitWidth(20l);
//                    }
//                    imv.setImage(imagem);
                    botao.setPickOnBounds(true);
//                    botao.setGraphic(imv);
                    botao.setAlignment(Pos.CENTER);
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        botao.setOnAction(event -> 
                            { 
                                System.out.println("Testando");
//                                if(Alertas.confirmacao("Confirma a exclusão do cliente?", "", "Sim", "Não") == 1){
//                                    deletaClienteGeral(getTableView().getItems().get(getIndex()));
//                                    Alertas.alertaInformacao("Processo finalizado!","");
//                                    consultaClienteGeral();
//                                } else
//                                    Alertas.alertaAtencao("Ok!", "Nada foi alterado!");
                            }
                        );
                        setGraphic(botao);
                    }
                }
            };
            return cell ;
        });
        
//        cellCliIntegerar.setMinWidth(50);
//        cellCliIntegerar.setPrefWidth(80);
//        cellCliIntegerar.setResizable(false);
//        cellCliIntegerar.setStyle("-fx-alignment: center;");
//        cellCliIntegerar.setCellFactory(col -> {
//            TableCell<ClienteBean, ClienteBean> cell = new TableCell<ClienteBean, ClienteBean>() {
//                @Override
//                public void updateItem(ClienteBean item, boolean empty) {
//                    final Tooltip infAjuda = new Tooltip();
//                    infAjuda.setText("Tenta integrar o cliente com o ERP.");
//                    Button botao = new Button();
//                    String caminho = "icon/integrar.png";
//                    File file = new File(caminho);
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
//                                if(Alertas.confirmacao("Confirma o processamento manual?", "", "Sim", "Não") == 1){
//                                    integraCliente(getTableView().getItems().get(getIndex()));
//                                    Alertas.alertaInformacao("Processo finalizado!","");
//                                    consultaClienteGeral();
//                                } else
//                                    Alertas.alertaAtencao("Ok!", "Nada foi alterado!");
//                            }
//                        );
//                        
//                        setGraphic(botao);
//                    }
//                }
//            };
//            return cell ;
//        });
        
    }
}
