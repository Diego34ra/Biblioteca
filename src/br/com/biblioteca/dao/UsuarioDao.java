/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.dao;

import br.com.biblioteca.connection.ConnectionBD;
import br.com.biblioteca.model.Estudante;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Professor;
import br.com.biblioteca.model.Usuario;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class UsuarioDao {
    
    public Boolean create(Usuario usuario){
        ConnectionBD con = new ConnectionBD();
        Boolean valida = false;
        String sql = "INSERT INTO usuario (matricula, nome, sexo, tipo, telefone, senha) VALUES (?,?,?,?,?,?)";
        System.out.println("br.com.faculdade.projetopoo.services.UsuarioService.createUsuario()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setLong(1, usuario.getMatricula());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getTipo());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getSenha());
            stmt.execute();
            createEspecializacao(usuario);
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
    
    public static List<Usuario> findAll(){
        ConnectionBD con = new ConnectionBD();
        List<Usuario> usuarios = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuario";
        System.out.println("br.com.faculdade.biblioteca.dao.UsuarioDao()");
        try {
            stmt = con.getConnection().createStatement();
            rs = stmt.executeQuery(sql); 
            while(rs.next()) {
                Usuario usuario = new Usuario();
            	usuario.setMatricula(rs.getInt("matricula"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setSexo(rs.getString("sexo"));
            	usuario.setTipo(rs.getString("tipo"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSenha(rs.getString("senha"));
            	usuarios.add(usuario);
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
        return usuarios;
    }
    
    public static List<Usuario> findByName(String consulta){
        ConnectionBD con = new ConnectionBD();
        List<Usuario> usuarios = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
        System.out.println("br.com.faculdade.projetopoo.services.ProjetoService.findAll()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, "%"+consulta+"%");
            rs = stmt.executeQuery(); 
            while(rs.next()) {
                Usuario usuario = new Usuario();
            	usuario.setMatricula(rs.getInt("matricula"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setSexo(rs.getString("sexo"));
            	usuario.setTipo(rs.getString("tipo"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
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
        return usuarios;
    }
    
    public Usuario findById(Integer codigo){
        ConnectionBD con = new ConnectionBD();
        Usuario usuario = new Usuario();
        usuario.setMatricula(0);
        ResultSet rs;
        String sql = "SELECT * FROM usuario WHERE matricula = ?";
        System.out.println("br.com.faculdade.projetopoo.services.ProjetoService.findAll()");
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery(); 
            while(rs.next()) {
            	usuario.setMatricula(rs.getInt("matricula"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setSexo(rs.getString("sexo"));
            	usuario.setTipo(rs.getString("tipo"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSenha(rs.getString("senha"));
            	
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
        return usuario;
    }
    
    public static void createEspecializacao(Usuario usuario){
        if(usuario instanceof Professor){
            ProfessorDao professorDao = new ProfessorDao();
            professorDao.create((Professor) usuario);
        } else if(usuario instanceof Estudante){
            EstudanteDao estudanteDao = new EstudanteDao();
            estudanteDao.create((Estudante) usuario);
        } else if(usuario instanceof Funcionario){
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            funcionarioDao.create((Funcionario) usuario);
        }
    }
}