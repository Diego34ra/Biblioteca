/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author 2022101202010058
 */
public class Reserva implements Serializable{
    private Integer codigo;
    private Date dataReserva;
    private Obra obra;
    private Usuario usuario;
	
    public Integer getCodigo() {
            return codigo;
    }
    public void setCodigo(Integer codigo) {
            this.codigo = codigo;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
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
            System.out.println("Devolvendo o Obra.");
    }

    public Reserva(Obra obra, Usuario usuario) {
        super();
        Random random = new Random();
        this.codigo = random.nextInt(99999 - 1000 + 1) + 1000;
        this.dataReserva = Date.valueOf(LocalDate.now());
        this.obra = obra;
        this.usuario = usuario;
    }

    public Reserva() {
            super();
    }
}
