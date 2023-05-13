package br.com.biblioteca.model;

import java.io.Serializable;

public class Fotografia implements Serializable{
    
    private int quantidade;
    private String area;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Fotografia(int quantidade, String area) {
        this.quantidade = quantidade;
        this.area = area;
    }

    public Fotografia() {
    }
    
    
}
