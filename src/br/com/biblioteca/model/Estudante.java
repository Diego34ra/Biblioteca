package br.com.biblioteca.model;

public class Estudante extends Usuario{
	
	private String curso;
	
	public String getCurso() {
		return curso;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Estudante(int idade, String nome, String sexo, String telefone,  String curso) {
		super(idade, nome, sexo, telefone);
		this.curso = curso;
	}

	public Estudante() {
	}
	
	
	
}
