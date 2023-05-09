package br.com.biblioteca.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Random;

public class Emprestimo implements DAO, Serializable {
	
	private long codigo;
	private LocalDateTime dataEmprestimo;
	private Livro livro;
	private Usuario usuario;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	void devolverLivro() {
		livro.setEmprestimo(true);
		System.out.println("Devolvendo o livro.");
	}
	
	public Emprestimo(Livro livro, Usuario usuario) {
            super();
            Random random = new Random();
            this.codigo = (long) (random.nextDouble() * 10000000000L);
            this.dataEmprestimo = LocalDateTime.now();
            this.livro = livro;
            this.usuario = usuario;
	}
	
	public Emprestimo() {
		super();
	}
	@Override
	public String gravar() {
		String ret = "Emprestimo armazenado com sucesso!";
		try {
			FileOutputStream file = new FileOutputStream("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Emprestimo\\" + this.getCodigo());
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
		Path path = Paths.get("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Emprestimo\\"+this.getCodigo());
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
	public Emprestimo ler(long codigo) {
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\2022101202010058\\Desktop\\Biblioteca\\Emprestimo\\" + codigo);
			ObjectInputStream stream = new ObjectInputStream(file);
			return((Emprestimo) stream.readObject());
			
		} catch (Exception erro) {
			System.out.println("Falha na leitura \n" + erro.toString());
			return null;
		}
	}
	@Override
	public String atualizar() {
            Emprestimo emprestimo = ler(codigo);
            emprestimo.setCodigo(codigo);
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setLivro(livro);
            emprestimo.setUsuario(usuario);
            emprestimo.excluir();
            emprestimo.gravar();
            return null;
	}
	
	
	
}
