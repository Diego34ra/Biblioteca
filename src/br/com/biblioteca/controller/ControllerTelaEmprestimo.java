/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Global;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Obra;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.services.EmprestimoServices;
import br.com.biblioteca.services.ObraServices;
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
public class ControllerTelaEmprestimo implements Initializable{
    
    @FXML
    private TableView<Emprestimo> tbEmprestimo;
    private final TableColumn cellEmprestimoId = new TableColumn("Código");
    private final TableColumn<Emprestimo,Usuario> cellEmprestimoUsuario = new TableColumn("Usuario");
    private final TableColumn<Emprestimo,Livro> cellEmprestimoLivro = new TableColumn("Livro");

    @FXML
    private TextField txConsulta;

    @FXML
    private ComboBox<String> cbConsulta;

    @FXML
    void getEmprestimo() {
        ObservableList<Emprestimo> obj = null;
        switch (cbConsulta.getSelectionModel().getSelectedItem()) {
            case "Todos":
                obj = FXCollections.observableArrayList(EmprestimoServices.findAll());
                break;
            case "Código":
                obj = FXCollections.observableArrayList(EmprestimoServices.findById(txConsulta.getText()));
                break;
            default:
                throw new AssertionError();
        }
        carregaTabelaAcervo(obj);
    }
    
    private void carregaTabelaAcervo(ObservableList<Emprestimo> list){
        tbEmprestimo.getColumns().clear();
        formataTabelaAcervo();
        tbEmprestimo.setItems(list);
        if(Global.usuario instanceof Funcionario){
           tbEmprestimo.getColumns().addAll(cellEmprestimoId,cellEmprestimoUsuario,cellEmprestimoLivro); 
        } else {
            tbEmprestimo.getColumns().addAll(cellEmprestimoId,cellEmprestimoUsuario,cellEmprestimoLivro); 
        }
    }
    
    private void formataTabelaAcervo(){
        cellEmprestimoId.setMinWidth(100);
        cellEmprestimoId.setPrefWidth(120);
        cellEmprestimoId.setResizable(false);
        cellEmprestimoId.setCellValueFactory (new PropertyValueFactory <> ( "codigo" ));
        cellEmprestimoId.setCellFactory( cell -> {              
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
        cellEmprestimoId.setStyle("-fx-alignment: center;");
        
        cellEmprestimoUsuario.setMinWidth(200);
        cellEmprestimoUsuario.setPrefWidth(350);
        cellEmprestimoUsuario.setResizable(false);
        cellEmprestimoUsuario.setCellValueFactory (new PropertyValueFactory <> ( "usuario" ));
        cellEmprestimoUsuario.setCellFactory( col -> {              
            return new TableCell<Emprestimo, Usuario>() {
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
        cellEmprestimoUsuario.setStyle("-fx-alignment: center;");
        
        
        cellEmprestimoLivro.setMinWidth(200);
        cellEmprestimoLivro.setPrefWidth(350);
        cellEmprestimoLivro.setResizable(false);
        cellEmprestimoLivro.setCellValueFactory (new PropertyValueFactory <> ( "livro" ));
        cellEmprestimoLivro.setCellFactory( col -> {              
            return new TableCell<Emprestimo, Livro>() {
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
        cellEmprestimoLivro.setStyle("-fx-alignment: center;");
        
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
