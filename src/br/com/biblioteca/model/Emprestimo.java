package br.com.biblioteca.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class Emprestimo implements Serializable {
	
    private Integer codigo;
    private Date dataEmprestimo;
    private Obra obra;
    private Usuario usuario;

    public Integer getCodigo() {
            return codigo;
    }
    public void setCodigo(Integer codigo) {
            this.codigo = codigo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
	
	public Obra getObra() {
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	void devolverLivro() {
		obra.setEmprestimo(true);
		System.out.println("Devolvendo o livro.");
	}
	
	public Emprestimo(Obra obra, Usuario usuario) {
            super();
            Random random = new Random();
            Integer codigo = random.nextInt(99999 - 1000 + 1) + 1000;
            this.dataEmprestimo = Date.valueOf(LocalDate.now());
            this.obra = obra;
            this.usuario = usuario;
	}
	
	public Emprestimo() {
		super();
	}
	
	
	
}
