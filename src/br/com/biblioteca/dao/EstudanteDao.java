/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Estudante;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Diego
 */
public class EstudanteDao {
    
    public void create(Estudante estudante) {
        ConnectionBD con = new ConnectionBD();
        String sql = "INSERT INTO funcionario (matricula, curso) VALUES (?,?)";
        System.out.println("br.com.faculdade.projetopoo.services.UsuarioService.createUsuario()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setLong(1, estudante.getMatricula());
            stmt.setString(2, estudante.getCurso());
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
    }
    
    public Estudante findById(Long codigo){
        ConnectionBD con = new ConnectionBD();
        Estudante estudante = new Estudante();
        estudante.setMatricula(0);
        ResultSet rs = null;
        String sql = "SELECT * FROM funcionario WHERE matricula = ?";
        System.out.println("br.com.biblioteca.dao.FuncionarioDao.findById()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setLong(1, codigo);
            rs = stmt.executeQuery(); 
            while(rs.next()) {
            	estudante.setMatricula(rs.getInt("matricula"));
            	estudante.setCurso(rs.getString("curso"));
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
        return estudante;
    }
}
