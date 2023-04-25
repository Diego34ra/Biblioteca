package br.com.biblioteca.model;

import java.util.Random;

public abstract class Obra {
	
	private long codigo;
	private String tipo;
	
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
	public Obra(String tipo) {
		super();
		Random random = new Random();
		long codigo = (long) (random.nextDouble() * 10000000000L);
		this.codigo = codigo;
		this.tipo = tipo;
	}
	public Obra() {
	}
	
	
	
	
}
