/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Fotografia;
import br.com.biblioteca.model.MidiaAudio;
import br.com.biblioteca.model.Obra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MidiaAudioDao {
    
    public Boolean create(MidiaAudio midiaAudio){
        ConnectionBD con = new ConnectionBD();
        Boolean valida = false;
        String sql = "INSERT INTO midia_audio (codObra, assunto, tamanho) VALUES (?,?,?)";
        System.out.println("br.com.biblioteca.dao.FotografiaDao.create()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, midiaAudio.getCodigo());
            stmt.setString(2, midiaAudio.getNome());
            stmt.setString(3, midiaAudio.getDuracao());
            stmt.execute();
            valida = true;
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao criar Mídia Áudio = " + e);
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                System.out.println("Erro ao fechar a conexão com o Banco de Dados.");
            }
        }
        return valida;
    }
    
    public MidiaAudio findById(Integer codigo){
        ConnectionBD con = new ConnectionBD();
        MidiaAudio midiaAudio = new MidiaAudio();
        PreparedStatement stmt;
        ResultSet rs;
        String sql = "SELECT * FROM midia_audio WHERE codObra = ?";
        System.out.println("br.com.biblioteca.dao.FotografiaDao.findById()");
        try {
            stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery(); 
            while(rs.next()) {
                midiaAudio = getObra(codigo);
                if(midiaAudio.getEmprestimo()){
                    midiaAudio.setStatus("Livre");
                } else {
                    midiaAudio.setStatus("Emprestado");
                }
            	midiaAudio.setCodigo(rs.getInt("codObra"));
            	midiaAudio.setDuracao(rs.getString("tamanho"));
            	midiaAudio.setAssunto(rs.getString("assunto"));
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
        return midiaAudio;
    }
    
    public MidiaAudio getObra(Integer codigo){
        ObraDao obraDao = new ObraDao();
        Obra obra = obraDao.findById(codigo);
        MidiaAudio midiaAudio = new MidiaAudio();
        midiaAudio.setNome(obra.getNome());
        midiaAudio.setTipo(obra.getTipo());
        midiaAudio.setDigital(obra.getDigital());
        midiaAudio.setEmprestimo(obra.getEmprestimo());
        
        return midiaAudio;
    }
    
}
