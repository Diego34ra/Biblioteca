package br.com.biblioteca.model;

public class MidiaAudio extends Obra{
    
    private String assunto;
    private String tamanho;

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public MidiaAudio(String assunto, String tamanho) {
        this.assunto = assunto;
        this.tamanho = tamanho;
    }

    public MidiaAudio() {
    }
    
    public void iniciar(){
        System.out.println("Começando o vídeo.");
    }
    
    public void pausar(){
        System.out.println("Pausando o vídeo.");
    }
}
