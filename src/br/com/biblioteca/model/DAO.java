package br.com.biblioteca.model;

public interface DAO {
	public String gravar();
	public void excluir();
	public Object ler(long codigo);
	public String atualizar();
}
