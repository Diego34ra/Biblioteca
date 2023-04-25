package br.com.biblioteca.model;

/**
 * Uma classe de professor que extende de funcionário
 * @author Diego
 *
 */
public class Professor extends Funcionario{
	
	private String especialidade;
	
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	public Professor(int idade, String nome, String sexo, String telefone, double salario, String especialidade) {
		super(idade, nome, sexo, telefone, salario);
		this.especialidade = especialidade;
	}
	public Professor() {
		super();
	}
	
	
}
