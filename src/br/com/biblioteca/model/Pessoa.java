package br.com.biblioteca.model;

public abstract class Pessoa {
	private int idade;
	private String nome;

	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Pessoa(int idade, String nome) {
		super();
		this.idade = idade;
		this.nome = nome;
	}
	public Pessoa() {
		super();
	}
	
	
}
