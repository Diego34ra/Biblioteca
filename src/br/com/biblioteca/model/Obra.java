package br.com.biblioteca.model;

import java.util.Random;

public abstract class Obra {
	
	private long codigo;
	private String tipo;
        private Boolean digital;

    public Boolean getDigital() {
        return digital;
    }

    public void setDigital(Boolean digital) {
        this.digital = digital;
    }

    public long getCodigo() {
            return codigo;
    }
    public void setCodigo(long codigo) {
            this.codigo = codigo;
    }
    public String getTipo() {
            return tipo;
    }
    public void setTipo(String tipo) {
            this.tipo = tipo;
    }
    
    public Obra(String tipo, Boolean digital) {
            super();
            Random random = new Random();
            long codigo = (long) (random.nextDouble() * 10000000000L);
            this.codigo = codigo;
            this.digital = digital;
            this.tipo = tipo;
    }
    public Obra() {
    }
	
	
	
	
}
