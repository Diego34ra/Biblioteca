package br.com.biblioteca.model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{
	private int idade;

	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Pessoa(int idade) {
		super();
		this.idade = idade;
	}
	public Pessoa() {
		super();
	}
	
	
}
