/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author 2022101202010058
 */
public class Reserva implements DAO, Serializable{
    private long codigo;
    private LocalDateTime dataEmprestimo;
    private Livro livro;
    private Usuario usuario;
	
    public long getCodigo() {
            return codigo;
    }
    public void setCodigo(long codigo) {
            this.codigo = codigo;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public Livro getLivro() {
            return livro;
    }
    public void setLivro(Livro livro) {
            this.livro = livro;
    }
    public Usuario getUsuario() {
            return usuario;
    }
    public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
    }

    void devolverLivro() {
            livro.setEmprestimo(true);
            System.out.println("Devolvendo o livro.");
    }

    public Reserva(Livro livro, Usuario usuario) {
        super();
        Random random = new Random();
        this.codigo = (long) (random.nextDouble() * 10000000000L);
        this.dataEmprestimo = LocalDateTime.now();
        this.livro = livro;
        this.usuario = usuario;
    }

    public Reserva() {
            super();
    }
    @Override
    public String gravar() {
            String ret = "Emprestimo armazenado com sucesso!";
            try {
                    FileOutputStream file = new FileOutputStream("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Reserva\\" + this.getCodigo());
                    ObjectOutputStream stream = new ObjectOutputStream(file);
                    stream.writeObject(this);
                    stream.flush();
            } catch (Exception erro) {
                    ret = "Falha na gravação \n" + erro.toString();
            }
            return ret;
    }
    @Override
    public void excluir() {
            Path path = Paths.get("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Reserva\\"+this.getCodigo());
    try {
        boolean result = Files.deleteIfExists(path);
        if (result) {
            System.out.println("File is successfully deleted.");
        }
        else {
            System.out.println("File deletion failed.");
        }
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    }
    @Override
    public Reserva ler(long codigo) {
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Emprestimo\\" + codigo);
            ObjectInputStream stream = new ObjectInputStream(file);
            return((Reserva) stream.readObject());

        } catch (Exception erro) {
            System.out.println("Falha na leitura \n" + erro.toString());
            return null;
        }
    }
    @Override
    public String atualizar() {
        Reserva Reserva = ler(codigo);
        Reserva.setCodigo(codigo);
        Reserva.setDataEmprestimo(dataEmprestimo);
        Reserva.setLivro(livro);
        Reserva.setUsuario(usuario);
        Reserva.excluir();
        Reserva.gravar();
        return null;
    }
}
