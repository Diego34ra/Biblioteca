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
import java.util.ArrayList;

public class Usuario extends Pessoa implements DAO, Serializable{
	
	private Long matricula;
	private String sexo;
        private String nome;
	private String telefone;
	private ArrayList<Livro> livros;
        private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
    public ArrayList<Livro> getLivros() {
            return livros;
    }
    
    public void setLivros(ArrayList<Livro> livros) {
            this.livros = livros;
    }
    
    public String getSexo() {
            return sexo;
    }
    
    public void setSexo(String sexo) {
            this.sexo = sexo;
    }
    public String getTelefone() {
            return telefone;
    }

    public Long getMatricula() {
            return matricula;
    }
    public void setMatricula(Long matricula) {
            this.matricula = matricula;
    }
    public void setTelefone(String telefone) {
            this.telefone = telefone;
    }

    void lerLivro() {
            System.out.println("Lendo o livro.");
    }

    public Usuario(int idade, String nome, String sexo, String telefone, String senha, Long matricula) {
            super(idade);
            this.matricula = matricula;
            this.nome = nome;
            this.sexo = sexo;
            this.telefone = telefone;
            this.senha = senha;
    }
    public Usuario() {
    }
    @Override
    public String gravar() {
            String ret = "Usuario armazenado com sucesso!";
            try {
                    FileOutputStream file = new FileOutputStream("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Usuario\\" + this.getMatricula());
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
            Path path = Paths.get("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Usuario\\"+this.getMatricula());

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
    public Usuario ler(long codigo) {
            try {
                    FileInputStream file = new FileInputStream("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Usuario\\" + codigo);
                    ObjectInputStream stream = new ObjectInputStream(file);
                    return((Usuario) stream.readObject());

            } catch (Exception erro) {
                    System.out.println("Falha na leitura \n" + erro.toString());
                    return null;
            }
    }
    @Override
    public String atualizar() {
            Usuario usuario = ler(this.getMatricula());
            usuario.setIdade(getIdade());
            usuario.setMatricula(matricula);
            usuario.setSenha(getSenha());
            usuario.setNome(getNome());
            usuario.setTelefone(getTelefone());
            usuario.setSexo(getSexo());
            usuario.excluir();
            usuario.gravar();
            return null;
    }
}