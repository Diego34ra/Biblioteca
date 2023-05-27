/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import biblioteca.Global;
import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Obra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Diego
 */
public class LivroDao {
    
    public Boolean create(Livro livro){
        ConnectionBD con = new ConnectionBD();
        Boolean valida = false;
        String sql = "INSERT INTO livro (codObra, autores, editora, ano, edicao, numFolhas) VALUES (?,?,?,?,?,?)";
        System.out.println("br.com.biblioteca.dao.LivroDao.create()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, livro.getCodigo());
            stmt.setString(2, livro.getAutores());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setInt(5, livro.getEdicao());
            stmt.setInt(6, livro.getNumFolhas());
            stmt.execute();
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
    
    public Livro findById(Integer codigo){
        ConnectionBD con = new ConnectionBD();
        Livro livro = new Livro();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM livro WHERE codObra = ?";
        System.out.println("br.com.biblioteca.dao.LivroDao.findById()");
        try {
            stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery(); 
            while(rs.next()) {
                livro = getObra(codigo);
                if(livro.getEmprestimo()){
                    livro.setStatus("Livre");
                } else {
                    livro.setStatus("Emprestado");
                }
            	livro.setCodigo(rs.getInt("codObra"));
            	livro.setAutores(rs.getString("autores"));
            	livro.setEditora(rs.getString("editora"));
            	livro.setAno(rs.getInt("ano"));
                livro.setEdicao(rs.getInt("edicao"));
                livro.setNumFolhas(rs.getInt("numFolhas"));
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
        return livro;
    }
    
    public Livro getObra(Integer codigo){
        ObraDao obraDao = new ObraDao();
        Obra obra = obraDao.findById(codigo);
        Livro livro = new Livro();
        livro.setNome(obra.getNome());
        livro.setTipo(obra.getTipo());
        livro.setDigital(obra.getDigital());
        livro.setEmprestimo(obra.getEmprestimo());
        
        return livro;
    }
}
