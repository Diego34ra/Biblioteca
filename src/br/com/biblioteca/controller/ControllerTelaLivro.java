/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import biblioteca.Global;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Obra;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.services.EmprestimoServices;
import br.com.biblioteca.services.ObraServices;
import br.com.biblioteca.services.ReservaServices;
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
public class ControllerTelaLivro implements Initializable{
    @FXML
    private TableView<Livro> tbLivro;
    private final TableColumn cellLivroId = new TableColumn("Código");
    private final TableColumn cellLivroNome = new TableColumn("Nome");
    private final TableColumn cellLivroDigital = new TableColumn("Tipo");
    private final TableColumn cellLivroAutor = new TableColumn("Autor");
    private final TableColumn cellLivroEditora = new TableColumn("Editora");
    private final TableColumn cellLivroEdicao = new TableColumn("Edição");
    private final TableColumn cellLivroAno = new TableColumn("Ano");
    private final TableColumn cellLivroNumFolha = new TableColumn("Páginas");
    private final TableColumn cellLivroStatus = new TableColumn("Status");
    private final TableColumn<Livro,Livro> cellLivroEmprestar = new TableColumn("Emprestimo");
    private final TableColumn<Livro,Livro> cellLivroRenovar = new TableColumn("Renovar");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Livro> livros = new ArrayList<>();
        if (Global.livro.isEmprestimo()) {
            Global.livro.setStatus("Livre");
        } else {
            Global.livro.setStatus("Emprestado");
        }
        livros.add(Global.livro);
        carregaTabelaAcervo(FXCollections.observableArrayList(livros));
        
    }
    
    private void carregaTabelaAcervo(ObservableList<Livro> list){
        tbLivro.getColumns().clear();
        formataTabelaLivro();
        tbLivro.setItems(list);
        tbLivro.getColumns().addAll(cellLivroId,cellLivroNome,cellLivroDigital,cellLivroAutor,cellLivroEditora,cellLivroEdicao,cellLivroAno, cellLivroNumFolha, cellLivroStatus, cellLivroEmprestar,cellLivroRenovar);
    }
    
    private void formataTabelaLivro(){
        cellLivroId.setMinWidth(100);
        cellLivroId.setPrefWidth(120);
        cellLivroId.setResizable(false);
        cellLivroId.setCellValueFactory (new PropertyValueFactory <> ( "codigo" ));
        cellLivroId.setCellFactory( cell -> {              
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
        cellLivroId.setStyle("-fx-alignment: center;");
        
        cellLivroNome.setMinWidth(200);
        cellLivroNome.setPrefWidth(250);
        cellLivroNome.setResizable(false);
        cellLivroNome.setCellValueFactory (new PropertyValueFactory <> ( "nome" ));
        cellLivroNome.setStyle("-fx-alignment: center;");
        
        cellLivroDigital.setMinWidth(200);
        cellLivroDigital.setPrefWidth(250);
        cellLivroDigital.setResizable(false);
        cellLivroDigital.setCellValueFactory (new PropertyValueFactory <> ( "tipo" ));
        cellLivroDigital.setStyle("-fx-alignment: center;");
        
        cellLivroAutor.setMinWidth(200);
        cellLivroAutor.setPrefWidth(250);
        cellLivroAutor.setResizable(false);
        cellLivroAutor.setCellValueFactory (new PropertyValueFactory <> ( "autores" ));
        cellLivroAutor.setStyle("-fx-alignment: center;");
        
        cellLivroEditora.setMinWidth(200);
        cellLivroEditora.setPrefWidth(250);
        cellLivroEditora.setResizable(false);
        cellLivroEditora.setCellValueFactory (new PropertyValueFactory <> ( "autores" ));
        cellLivroEditora.setStyle("-fx-alignment: center;");
        
        cellLivroEdicao.setMinWidth(80);
        cellLivroEdicao.setPrefWidth(100);
        cellLivroEdicao.setResizable(false);
        cellLivroEdicao.setCellValueFactory (new PropertyValueFactory <> ( "edicao" ));
        cellLivroEdicao.setStyle("-fx-alignment: center;");
        
        cellLivroAno.setMinWidth(80);
        cellLivroAno.setPrefWidth(100);
        cellLivroAno.setResizable(false);
        cellLivroAno.setCellValueFactory (new PropertyValueFactory <> ( "ano" ));
        cellLivroAno.setStyle("-fx-alignment: center;");
        
        cellLivroNumFolha.setMinWidth(80);
        cellLivroNumFolha.setPrefWidth(100);
        cellLivroNumFolha.setResizable(false);
        cellLivroNumFolha.setCellValueFactory (new PropertyValueFactory <> ( "numFolhas" ));
        cellLivroNumFolha.setStyle("-fx-alignment: center;");
        
        cellLivroStatus.setMinWidth(80);
        cellLivroStatus.setPrefWidth(100);
        cellLivroStatus.setResizable(false);
        cellLivroStatus.setCellValueFactory (new PropertyValueFactory <> ( "status" ));
        cellLivroStatus.setStyle("-fx-alignment: center;");
        
        cellLivroEmprestar.setMinWidth(50);
        cellLivroEmprestar.setPrefWidth(80);
        cellLivroEmprestar.setResizable(false);
        cellLivroEmprestar.setStyle("-fx-alignment: center;");
        cellLivroEmprestar.setCellFactory(col -> {
            TableCell<Livro, Livro> cell = new TableCell<Livro, Livro>() {
                @Override
                public void updateItem(Livro item, boolean empty) {
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
                                if (Global.livro.isEmprestimo()) {
                                    Emprestimo emprestimo = new Emprestimo(Global.livro,Global.usuario); 
                                    EmprestimoServices.createEmprestimo(emprestimo);
                                    Global.livro.setEmprestimo(false);
                                    ObraServices.deleteById(Global.livro.getCodigo());
                                    ObraServices.createObra(Global.livro);
                                    Alertas.alertaInformacao("Sucesso", "Emprestimo realizado com sucesso.");
                                } else { 
                                    if (Alertas.confirmacao("Livro já emprestado!", "Deseja fazer reserva do livro?") == 1) {
                                        Reserva reserva = new Reserva(Global.livro,Global.usuario);
                                        ReservaServices.create(reserva);
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
        
        cellLivroRenovar.setMinWidth(50);
        cellLivroRenovar.setPrefWidth(80);
        cellLivroRenovar.setResizable(false);
        cellLivroRenovar.setStyle("-fx-alignment: center;");
        cellLivroRenovar.setCellFactory(col -> {
            TableCell<Livro, Livro> cell = new TableCell<Livro, Livro>() {
                @Override
                public void updateItem(Livro item, boolean empty) {
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
                                if (!Global.livro.isEmprestimo()) {
                                    Global.livro.setEmprestimo(true);
                                    ObraServices.deleteById(Global.livro.getCodigo());
                                    ObraServices.createObra(Global.livro);
                                    Alertas.alertaInformacao("Sucesso", "Renovação realizada com sucesso.");
                                } 
                            }
                        );
                        setGraphic(botao);
                    }
                }
            };
            return cell ;
        });
            
    }
}
