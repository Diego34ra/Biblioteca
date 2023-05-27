package br.com.biblioteca.model;

public class Funcionario extends Usuario{
	
    private double salario;

    public double getSalario() {
            return salario;
    }

    public void setSalario(double salario) {
            this.salario = salario;
    }

    public Funcionario(int idade, String nome, String sexo, String telefone, double salario, String senha, Integer matricula) {
            super(idade, nome, sexo, telefone, senha, matricula);
            this.salario = salario;
    }

    public Funcionario(int idade, String nome, String sexo, String telefone, double salario, String senha, Integer matricula, String tipo) {
        super(idade, nome, sexo, telefone, senha, matricula, tipo);
        this.salario = salario;
    }
        
    public Funcionario() {
    }

	
}
