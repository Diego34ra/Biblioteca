package br.com.biblioteca.model;

public class Funcionario extends Usuario{
	
	private double salario;

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
        
	public Funcionario(int idade, String nome, String sexo, String telefone, double salario, String senha) {
		super(idade, nome, sexo, telefone, senha);
		this.salario = salario;
	}

	public Funcionario() {
	}

	
}
