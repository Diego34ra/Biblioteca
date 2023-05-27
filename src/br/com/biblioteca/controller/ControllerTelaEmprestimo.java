/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.controller;

import biblioteca.Alertas;
import biblioteca.Global;
import br.com.biblioteca.dao.EmprestimoDao;
import br.com.biblioteca.dao.ObraDao;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Obra;
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

/**
 *
 * @author Diego
 */
public class ControllerTelaEmprestimo implements Initializable{
    
    @FXML
    private TableView<Emprestimo> tbEmprestimo;
    private final TableColumn cellEmprestimoId = new TableColumn("Código");
    private final TableColumn<Emprestimo,Usuario> cellEmprestimoUsuario = new TableColumn("Usuario");
    private final TableColumn<Emprestimo,Obra> cellEmprestimoObra = new TableColumn("Obra");
    private final TableColumn<Emprestimo,Emprestimo> cellEmprestimoDevolver = new TableColumn("Devolver");

    @FXML
    private TextField txConsulta;

    @FXML
    private ComboBox<String> cbConsulta;
    
    private ObservableList<Emprestimo> obj = null;
    private EmprestimoDao emprestimoDao = new EmprestimoDao();
    private ObraDao obraDao = new ObraDao();

    @FXML
    void getEmprestimo() {
        switch (cbConsulta.getSelectionModel().getSelectedItem()) {
            case "Todos":
                obj = FXCollections.observableArrayList(emprestimoDao.findAll());
                break;
            case "Código da Obra":
                obj = FXCollections.observableArrayList(emprestimoDao.findByIdObra(Integer.valueOf(txConsulta.getText())));
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
        if(Global.usuario.getTipo().equals("Funcionario")){
           tbEmprestimo.getColumns().addAll(cellEmprestimoId,cellEmprestimoUsuario,cellEmprestimoObra,cellEmprestimoDevolver); 
        } else {
            tbEmprestimo.getColumns().addAll(cellEmprestimoId,cellEmprestimoUsuario,cellEmprestimoObra,cellEmprestimoDevolver); 
        }
    }
    
    private void formataTabelaAcervo(){
        cellEmprestimoId.setMinWidth(100);
        cellEmprestimoId.setPrefWidth(120);
        cellEmprestimoId.setResizable(false);
        cellEmprestimoId.setCellValueFactory (new PropertyValueFactory <> ( "codigo" ));
        cellEmprestimoId.setCellFactory( cell -> {              
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
        cellEmprestimoId.setStyle("-fx-alignment: center;");
        
        cellEmprestimoUsuario.setMinWidth(200);
        cellEmprestimoUsuario.setPrefWidth(340);
        cellEmprestimoUsuario.setResizable(false);
        cellEmprestimoUsuario.setCellValueFactory (new PropertyValueFactory <> ( "usuario" ));
        cellEmprestimoUsuario.setCellFactory( col -> {              
            return new TableCell<Emprestimo, Usuario>() {
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
        cellEmprestimoUsuario.setStyle("-fx-alignment: center;");
        
        
        cellEmprestimoObra.setMinWidth(200);
        cellEmprestimoObra.setPrefWidth(340);
        cellEmprestimoObra.setResizable(false);
        cellEmprestimoObra.setCellValueFactory (new PropertyValueFactory <> ( "obra" ));
        cellEmprestimoObra.setCellFactory( col -> {              
            return new TableCell<Emprestimo, Obra>() {
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
        cellEmprestimoObra.setStyle("-fx-alignment: center;");
        
        cellEmprestimoDevolver.setMinWidth(50);
        cellEmprestimoDevolver.setPrefWidth(90);
        cellEmprestimoDevolver.setResizable(false);
        cellEmprestimoDevolver.setStyle("-fx-alignment: center;");
        cellEmprestimoDevolver.setCellFactory(col -> {
            TableCell<Emprestimo, Emprestimo> cell = new TableCell<Emprestimo, Emprestimo>() {
                @Override
                public void updateItem(Emprestimo item, boolean empty) {
                    final Tooltip infAjuda = new Tooltip();
                    infAjuda.setText("Devolver Obra");
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
                                Emprestimo emprestimo = getTableView().getItems().get(getIndex());
                                emprestimoDao.deleteById(emprestimo.getCodigo());
                                obraDao.updateEmprestimo(emprestimo.getCodigo(), true);
                                Alertas.alertaInformacao("Sucesso", "Devolução realizada com sucesso.");
                                obj.remove(emprestimo);
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
        cbConsulta.getItems().addAll(Global.tipoConsulta("emprestimo"));
        
        cbConsulta.getSelectionModel().selectFirst();
    }
}
