package br.com.biblioteca.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario extends Pessoa implements Serializable{
	
	private Integer matricula;
	private String sexo;
        private String nome;
        private String tipo;
	private String telefone;
	private ArrayList<Livro> livros;
        private String senha;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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

    public Integer getMatricula() {
            return matricula;
    }
    public void setMatricula(Integer matricula) {
            this.matricula = matricula;
    }
    public void setTelefone(String telefone) {
            this.telefone = telefone;
    }

    void lerLivro() {
            System.out.println("Lendo o livro.");
    }

    public Usuario(int idade, String nome, String sexo, String telefone, String senha, Integer matricula) {
            super(idade);
            this.matricula = matricula;
            this.nome = nome;
            this.sexo = sexo;
            this.telefone = telefone;
            this.senha = senha;
    }
    
    public Usuario(int idade, String nome, String sexo, String telefone, String senha, Integer matricula, String tipo) {
            super(idade);
            this.matricula = matricula;
            this.nome = nome;
            this.sexo = sexo;
            this.telefone = telefone;
            this.senha = senha;
            this.tipo = tipo;
    }
    public Usuario() {
    }
}