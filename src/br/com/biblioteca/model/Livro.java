package br.com.biblioteca.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Livro extends Obra implements DAO{
	
	private String autores;
	private String titulo;
	private String area;
	private String editora;
	private int ano;
	private int edicao;
	private int numFolhas;
	private boolean emprestimo;
	
	public boolean isEmprestimo() {
		return emprestimo;
	}
	public void setEmprestimo(boolean emprestimo) {
		this.emprestimo = emprestimo;
	}
	public String getAutores() {
		return autores;
	}
	public void setAutores(String autores) {
		this.autores = autores;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getEdicao() {
		return edicao;
	}
	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
	public int getNumFolhas() {
		return numFolhas;
	}
	public void setNumFolhas(int numFolhas) {
		this.numFolhas = numFolhas;
	}
	
	
	public Livro(String tipo, String autores, String titulo, String area, String editora, int ano,
			int edicao, int numFolhas) {
		super(tipo);
		this.autores = autores;
		this.titulo = titulo;
		this.area = area;
		this.editora = editora;
		this.ano = ano;
		this.edicao = edicao;
		this.numFolhas = numFolhas;
		this.emprestimo = true;
	}
	
	
	public Livro() {
		
	}
	void abrirLivro() {
		System.out.println("Abrindo o livro!");
	}
	
	void fecharLivro() {
		System.out.println("Fechando o livro!");
	}
	@Override
	public String gravar() {
		String ret = "Livro armazenado com sucesso!";
		try {
			FileOutputStream file = new FileOutputStream("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Livro\\" + this.getCodigo());
			ObjectOutputStream stream = new ObjectOutputStream(file);
			stream.writeObject(this);
			stream.flush();
		} catch (Exception erro) {
			ret = "Falha na gravação \n" + erro.toString();
		}
		return ret;
	}
	@Override
	public void excluir() {
		Path path = Paths.get("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Livro\\"+this.getCodigo());
		 
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("File is successfully deleted.");
            }
            else {
                System.out.println("File deletion failed.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	@Override
	public Livro ler(long codigo) {
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Livro\\" + codigo);
			ObjectInputStream stream = new ObjectInputStream(file);
			return((Livro) stream.readObject());
			
		} catch (Exception erro) {
			System.out.println("Falha na leitura \n" + erro.toString());
			return null;
		}
	}
	@Override
	public String atualizar() {
		Livro livro = ler(this.getCodigo());
		livro.setAutores(autores);
		livro.setAno(ano);
		livro.setArea(area);
		livro.setEdicao(edicao);
		livro.setEditora(editora);
		livro.setEmprestimo(emprestimo);
		livro.setNumFolhas(numFolhas);
		livro.setTipo(this.getTipo());
		livro.setTitulo(titulo);
		livro.excluir();
		livro.gravar();
		return null;
	}
	
	
}
