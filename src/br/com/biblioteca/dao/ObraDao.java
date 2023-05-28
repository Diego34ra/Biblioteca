/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Fotografia;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Obra;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ObraDao {
    
    public Boolean create(Obra obra){
        ConnectionBD con = new ConnectionBD();
        Boolean valida = false;
        String sql = "INSERT INTO obra (codObra, nome, tipo, digital, emprestimo) VALUES (?,?,?,?,?)";
        System.out.println("br.com.faculdade.projetopoo.services.UsuarioService.createUsuario()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, obra.getCodigo());
            stmt.setString(2, obra.getNome());
            stmt.setString(3, obra.getTipo());
            stmt.setBoolean(4, obra.getDigital());
            stmt.setBoolean(5, obra.getEmprestimo());
            stmt.execute();
            createEspecializacao(obra);
            valida = true;
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return valida;
    }
    
    public List<Obra> findAll(){
        ConnectionBD con = new ConnectionBD();
        List<Obra> obras = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM obra";
        System.out.println("br.com.faculdade.biblioteca.dao.UsuarioDao()");
        try {
            stmt = con.getConnection().createStatement();
            rs = stmt.executeQuery(sql); 
            while(rs.next()) {
                Obra obra = new Obra();
            	obra.setCodigo(rs.getInt("codObra"));
            	obra.setNome(rs.getString("nome"));
            	obra.setDigital(rs.getBoolean("digital"));
            	obra.setTipo(rs.getString("tipo"));
                obra.setEmprestimo(rs.getBoolean("emprestimo"));
            	obras.add(obra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obras;
    }
    
    public Obra findById(Integer codigo){
        ConnectionBD con = new ConnectionBD();
        Obra obra = new Obra();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM obra WHERE codObra = ?";
        System.out.println("br.com.biblioteca.dao.ObraDao.findById()");
        try {
            stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery(); 
            while(rs.next()) {
            	obra.setCodigo(rs.getInt("codObra"));
            	obra.setNome(rs.getString("nome"));
            	obra.setDigital(rs.getBoolean("digital"));
            	obra.setTipo(rs.getString("tipo"));
                obra.setEmprestimo(rs.getBoolean("emprestimo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obra;
    }
    
    public Obra updateEmprestimo(Integer codigo, Boolean emprestimo){
        ConnectionBD con = new ConnectionBD();
        Obra obra = new Obra();
        PreparedStatement stmt;
        String sql = "UPDATE obra SET emprestimo = ? WHERE codObra = ?";
        System.out.println("br.com.biblioteca.dao.ObraDao.updateEmprestimo()");
        try {
            stmt = con.getConnection().prepareStatement(sql);
            stmt.setBoolean(1, emprestimo);
            stmt.setInt(2, codigo);
            stmt.execute(); 
        } catch (SQLException e) {
            System.out.println("Erro ao Executar o Update = " + e);
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return obra;
    }
    
    public static void deleteById(long codigo) {
        Path path = Paths.get("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\obra\\"+codigo); 
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("Obra deletada com sucesso.");
            }
            else {
                System.out.println("Erro ao deletar usuario.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void createEspecializacao(Obra obra){
        if(obra instanceof Livro){
            LivroDao livroDao = new LivroDao();
            livroDao.create((Livro) obra);
        } 
        else if(obra instanceof Fotografia){
            FotografiaDao fotografiaDao = new FotografiaDao();
            fotografiaDao.create((Fotografia) obra);
        } 
//        else if(obra instanceof Fotografia){
//            FuncionarioDao funcionarioDao = new FuncionarioDao();
//            funcionarioDao.create((Funcionario) usuario);
//        }
    }
}
