package br.com.biblioteca.model;

public class MidiaAudio extends Obra{
    
    private String assunto;
    private String duracao;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public MidiaAudio(String assunto, String duracao) {
        this.assunto = assunto;
        this.duracao = duracao;
    }

    public MidiaAudio(String assunto, String duracao, String tipo, Boolean digital, String nome) {
        super(tipo, digital, nome);
        this.assunto = assunto;
        this.duracao = duracao;
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
