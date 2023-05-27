/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Professor;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;

/**
 *
 * @author Diego
 */
public class ProfessorDao {
    
    public void create(Professor professor) {
        ConnectionBD con = new ConnectionBD();
        String sql = "INSERT INTO professor (matricula, especialidade) VALUES (?,?)";
        System.out.println("br.com.biblioteca.dao.ProfessorDao.create()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setLong(1, professor.getMatricula());
            stmt.setString(2, professor.getEspecialidade());
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
}
