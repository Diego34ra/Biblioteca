package br.com.biblioteca.model;

/**
 * Uma classe de professor que extende de funcionário
 * @author Diego
 *
 */
public class Professor extends Usuario{
	
	private String especialidade;
	
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
        
        
	public Professor(int idade, String nome, String sexo, String telefone, String especialidade, String senha, Integer matricula) {
		super(idade, nome, sexo, telefone, senha, matricula);
		this.especialidade = especialidade;
	}
        
        public Professor(int idade, String nome, String sexo, String telefone, String especialidade, String senha, Integer matricula, String tipo) {
		super(idade, nome, sexo, telefone, senha, matricula,tipo);
		this.especialidade = especialidade;
	}
	
		
}
