package br.com.biblioteca.model;

import java.io.Serializable;
import java.util.Random;

public class Obra implements Serializable{
	
	private Integer codigo;
        private String nome;
	private String tipo;
        private Boolean digital;
        private Boolean emprestimo;

    public Boolean getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Boolean emprestimo) {
        this.emprestimo = emprestimo;
    }
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
        
    public Boolean getDigital() {
        return digital;
    }

    public void setDigital(Boolean digital) {
        this.digital = digital;
    }

    public Integer getCodigo() {
            return codigo;
    }
    public void setCodigo(Integer codigo) {
            this.codigo = codigo;
    }
    public String getTipo() {
            return tipo;
    }
    public void setTipo(String tipo) {
            this.tipo = tipo;
    }
    
    public Obra(String tipo, Boolean digital, String nome) {
            super();
            Random random = new Random();
            Integer codigo = random.nextInt(99999 - 1000 + 1) + 1000;
            this.codigo = codigo;
            this.digital = digital;
            this.tipo = tipo;
            this.nome = nome;
            this.emprestimo = true;
    }
    public Obra() {
    }
	
	
	
	
}
