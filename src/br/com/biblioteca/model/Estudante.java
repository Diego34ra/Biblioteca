package br.com.biblioteca.model;

public class Estudante extends Usuario{
	
    private String curso;

    public String getCurso() {
            return curso;
    }

    public void setCurso(String curso) {
            this.curso = curso;
    }


    public Estudante(int idade, String nome, String sexo, String telefone,  String curso, String senha, Integer matricula) {
            super(idade, nome, sexo, telefone, senha, matricula);
            this.curso = curso;
    }

    public Estudante(int idade, String nome, String sexo, String telefone,  String curso, String senha, Integer matricula, String tipo) {
        super(idade, nome, sexo, telefone, senha, matricula, tipo);
        this.curso = curso;
    }
    
    public Estudante() {
    }
	
	
	
}
