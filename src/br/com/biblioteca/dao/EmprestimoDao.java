/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Emprestimo;
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
public class EmprestimoDao {
    
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final ObraDao obraDao = new ObraDao();
    
    
    public Boolean create(Emprestimo emprestimo){
        ConnectionBD con = new ConnectionBD();
        Boolean valida = false;
        String sql = "INSERT INTO emprestimo (matricula, codObra, dataEmprestimo) VALUES (?,?,?)";
        System.out.println("br.com.biblioteca.dao.EmprestimoDao.create()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, emprestimo.getUsuario().getMatricula());
            stmt.setInt(2, emprestimo.getObra().getCodigo());
            stmt.setDate(3, emprestimo.getDataEmprestimo());
            stmt.execute();
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
    
    public List<Emprestimo> findAll(){
        ConnectionBD con = new ConnectionBD();
        List<Emprestimo> emprestimos = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM emprestimo";
        System.out.println("br.com.biblioteca.dao.EmprestimoDao.findAll()");
        try {
            stmt = con.getConnection().createStatement();
            rs = stmt.executeQuery(sql); 
            while(rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
            	emprestimo.setCodigo(rs.getInt("codObra"));
            	emprestimo.setUsuario(usuarioDao.findById(rs.getInt("matricula")));
            	emprestimo.setObra(obraDao.findById(rs.getInt("codObra")));
                emprestimo.setDataEmprestimo(rs.getDate("dataEmprestimo"));
            	emprestimos.add(emprestimo);
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
        return emprestimos;
    }
    
    public Emprestimo findByIdObra(Integer codObra){
        ConnectionBD con = new ConnectionBD();
        Emprestimo emprestimo = new Emprestimo();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM emprestimo WHERE codObra = ?";
        System.out.println("br.com.biblioteca.dao.EmprestimoDao.findByIdObra()");
        try {
            stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, codObra);
            rs = stmt.executeQuery(); 
            while(rs.next()) {
            	emprestimo.setCodigo(rs.getInt("codObra"));
            	emprestimo.setUsuario(usuarioDao.findById(rs.getInt("matricula")));
            	emprestimo.setObra(obraDao.findById(rs.getInt("codObra")));
                emprestimo.setDataEmprestimo(rs.getDate("dataEmprestimo"));
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
        return emprestimo;
    }
    
    public Boolean deleteById(Integer codigo){
        ConnectionBD con = new ConnectionBD();
        PreparedStatement stmt;
        Boolean valida = false;
        String sql = "DELETE FROM emprestimo WHERE codObra = ?";
        System.out.println("codEmprestimo = "+codigo);
        System.out.println("br.com.biblioteca.dao.EmprestimoDao.deleteById()");
        try {
            stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute(); 
            valida = true;
        } catch (SQLException e) {
            System.out.println("Erro ao Executar o Delete = " + e);
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return valida;
    }
}
