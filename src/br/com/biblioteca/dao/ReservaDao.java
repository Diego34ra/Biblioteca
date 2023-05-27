/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Reserva;
import java.sql.PreparedStatement;

/**
 *
 * @author Diego
 */
public class ReservaDao {
    
    public Boolean create(Reserva reserva){
        ConnectionBD con = new ConnectionBD();
        Boolean valida = false;
        String sql = "INSERT INTO reserva (matricula, codObra, dataReserva) VALUES (?,?,?)";
        System.out.println("br.com.biblioteca.dao.ReservaDao.create()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, reserva.getUsuario().getMatricula());
            stmt.setInt(2, reserva.getObra().getCodigo());
            stmt.setDate(3, reserva.getDataReserva());
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
}
