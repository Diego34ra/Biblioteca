package br.com.biblioteca.model;

import java.io.Serializable;

public class Fotografia extends Obra implements Serializable{
    
    private String tamanho;
    private String status;
    private String area;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Fotografia(String quantidade, String area) {
        this.tamanho = quantidade;
        this.area = area;
    }

    public Fotografia() {
    }

    public Fotografia(String tamanho, String area, String tipo, Boolean digital, String nome) {
        super(tipo, digital, nome);
        this.tamanho = tamanho;
        this.area = area;
    }
    
    
    
}
