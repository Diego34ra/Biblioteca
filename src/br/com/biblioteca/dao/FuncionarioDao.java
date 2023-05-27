/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Usuario;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Diego
 */
public class FuncionarioDao {
    
    public void create(Funcionario funcionario) {
        ConnectionBD con = new ConnectionBD();
        String sql = "INSERT INTO funcionario (matricula, salario) VALUES (?,?)";
        System.out.println("br.com.faculdade.projetopoo.services.UsuarioService.createUsuario()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setLong(1, funcionario.getMatricula());
            stmt.setDouble(2, funcionario.getSalario());
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
    
     public static Funcionario findById(Long codigo){
        ConnectionBD con = new ConnectionBD();
        Funcionario funcionario = new Funcionario();
        funcionario.setMatricula(0);
        ResultSet rs = null;
        String sql = "SELECT * FROM funcionario WHERE matricula = ?";
        System.out.println("br.com.biblioteca.dao.FuncionarioDao.findById()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setLong(1, codigo);
            rs = stmt.executeQuery(); 
            while(rs.next()) {
            	funcionario.setMatricula(rs.getInt("matricula"));
            	funcionario.setSalario(rs.getDouble("salario"));
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
        return funcionario;
    }
}
