/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Fotografia;
import br.com.biblioteca.model.Obra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class FotografiaDao {
    
    public Boolean create(Fotografia fotografia){
        ConnectionBD con = new ConnectionBD();
        Boolean valida = false;
        String sql = "INSERT INTO fotografia (codObra, area, quantidade) VALUES (?,?,?)";
        System.out.println("br.com.biblioteca.dao.FotografiaDao.create()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, fotografia.getCodigo());
            stmt.setString(2, fotografia.getNome());
            stmt.setString(3, fotografia.getTamanho());
            stmt.execute();
            valida = true;
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao criar Fotografia = " + e);
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                System.out.println("Erro ao fechar a conexão com o Banco de Dados.");
            }
        }
        return valida;
    }
    
    public Fotografia findById(Integer codigo){
        ConnectionBD con = new ConnectionBD();
        Fotografia fotografia = new Fotografia();
        PreparedStatement stmt;
        ResultSet rs;
        String sql = "SELECT * FROM fotografia WHERE codObra = ?";
        System.out.println("br.com.biblioteca.dao.FotografiaDao.findById()");
        try {
            stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery(); 
            while(rs.next()) {
                fotografia = getObra(codigo);
                if(fotografia.getEmprestimo()){
                    fotografia.setStatus("Livre");
                } else {
                    fotografia.setStatus("Emprestado");
                }
            	fotografia.setCodigo(rs.getInt("codObra"));
            	fotografia.setTamanho(rs.getString("tamanho"));
            	fotografia.setArea(rs.getString("area"));
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
        return fotografia;
    }
    
    public Fotografia getObra(Integer codigo){
        ObraDao obraDao = new ObraDao();
        Obra obra = obraDao.findById(codigo);
        Fotografia fotografia = new Fotografia();
        fotografia.setNome(obra.getNome());
        fotografia.setTipo(obra.getTipo());
        fotografia.setDigital(obra.getDigital());
        fotografia.setEmprestimo(obra.getEmprestimo());
        
        return fotografia;
    }
    
}
