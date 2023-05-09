package br.com.biblioteca.services;


import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.model.Professor;
import br.com.biblioteca.model.Estudante;
import br.com.biblioteca.model.Funcionario;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class MenuServices {
	
    public ArrayList<Livro> cadastrarLivro(ArrayList<Livro> livros) {
        int i;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Qual o título do livro? ");
            String titulo = scanner.nextLine();
            System.out.println("Qual o tipo da obra do livro? ");
            String tipo = scanner.nextLine();
            System.out.println("Quais são os autores do livro? ");
            String autores = scanner.nextLine();
//            System.out.println("Sobre qual area e o livro? ");
//            String area = scanner.nextLine();
            System.out.println("Qual e a editora do livro? ");
            String editora = scanner.nextLine();
            System.out.println("Qual é o ano do livro? ");
            int ano = scanner.nextInt();
            System.out.println("Qual a edição do livro?");
            int edicao = scanner.nextInt();
            System.out.println("Qual o numero de folhas do livro? ");
            int numFolhas = scanner.nextInt();
            System.out.println("O livro é digital? ");
            String digit = scanner.nextLine();
            Boolean digital = false;
            if(digit.equals("S")){
                digital = true;
            }
            Livro livro = new Livro(tipo,autores,titulo,titulo,editora,ano,edicao,numFolhas,digital);
            livros.add(livro);
            i = JOptionPane.showConfirmDialog(
                    null, 
                    "Deseja cadastrar mais um livro?"
                    );
        } while (i == JOptionPane.YES_OPTION);
        return livros;
    }

    public ArrayList<Usuario> cadastrarUsuario(ArrayList<Usuario> usuarios) {
            Scanner scanner = new Scanner(System.in);
            int i ;
            do {
                    Double salario;
                    System.out.println("Qual o nome do usuario? ");
                    String nome = scanner.nextLine();
                    System.out.println("Qual a idade ? ");
                    int idade = scanner.nextInt();
                    System.out.println("Qual o sexo? ");
                    String sexo = scanner.nextLine();
                    System.out.println("Qual o telefone? ");
                    String telefone = scanner.nextLine();
                    System.out.println("Qual a senha? ");
                    String senha = scanner.nextLine();
                    System.out.println("Qual a matricula? ");
                    Long matricula = scanner.nextLong();
                    System.out.println("----- Tipo de usuario -----");
                    System.out.println("1 - Professor");
                    System.out.println("2 - Estudante");
                    System.out.println("3 - Funcionario");
                    System.out.println("---------------------------");
                    int resposta = scanner.nextInt();
                    switch (resposta) {
                    case 1: 
                            System.out.println("Qual a especialidade do professor?");
                            String especialidade = scanner.nextLine();
                            System.out.println("Qual o salario do professor?");
                            salario = scanner.nextDouble();
                            usuarios.add(new Professor(idade,nome,sexo,telefone,especialidade,senha,matricula));
                            break;
                    case 2:
                            System.out.println("Qual o curso do Estudante?");
                            String curso = scanner.nextLine();
                            usuarios.add(new Estudante(idade,nome,sexo,telefone,curso,senha,matricula));
                            break;
                    case 3:
                            System.out.println("Qual o salario do funcionario?");
                            salario = scanner.nextDouble(); 
                            usuarios.add(new Funcionario(idade,nome,sexo,telefone,salario,senha,matricula));
                    default:
                            System.out.println("Valor invalido : " + resposta);
                    }
                    i = JOptionPane.showConfirmDialog(
                            null, 
                            "Deseja cadastrar mais um Usuario?"
                            );
            } while (i == JOptionPane.YES_OPTION);

            return usuarios;
    }

    public void devolverLivro(Usuario usuario) {
            Scanner scanner = new Scanner(System.in);
            if(usuario.getLivros().size() == 0) {
                    System.out.println("Nenhum livro para devolver.");
            } else {
                    System.out.println("-------- Lista de livros para devolucao ----------");
                    int contador = 1;
                    for(Livro livro : usuario.getLivros()) {
                            System.out.println(contador + " - " + livro.getTitulo());
                    }
                    System.out.println("Qual livro deseja devolver ? ");
                    int resposta = scanner.nextInt();
                    usuario.getLivros().remove(resposta-1);
            }
    }

    public void listarEmprestimo(Usuario usuario) {
            System.out.println("--- Emprestimos ---");
            for(Livro livro: usuario.getLivros()) {
                    System.out.println(livro.getCodigo() + " - " + livro.getTitulo());
            }
    }

    public void realizarEmprestismo(ArrayList<Livro> livros, Usuario usuario) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("--- Lista de livros disponíveis ---");
            int contador = 1;
            for(Livro livro: livros) {
                    if(livro.isEmprestimo()) {
                            System.out.println(livro.getCodigo() + " - " + livro.getTitulo());
                    }
                    contador++;
            }
            System.out.println("Qual o codigo do livro que deseja pegar?");
            long resposta = scanner.nextLong();

            for(Livro livro : livros) {
                    if (livro.getCodigo() == resposta) {
                            System.out.println("Emprestimo feito com sucesso.");
                            livro.setEmprestimo(false);
                            if (usuario instanceof Estudante) {
                                if (usuario.getLivros().size() <= 3) {
                                        usuario.getLivros().add(livro);
                                } else {
                                        System.out.println(" O Estudante ja atingiu a quantidade maxima de emprestimo");
                                }
                            } else if (usuario instanceof Funcionario) {
                                if (usuario.getLivros().size() <= 6) {
                                        usuario.getLivros().add(livro);
                                } else {
                                        System.out.println(" O Funcionario já atingiu a quantidade maxima de emprestimo");
                                }
                            } else {
                                if (usuario.getLivros().size() <= 9) {
                                        usuario.getLivros().add(livro);
                                } else {
                                        System.out.println(" O Professor já atingiu a quantidade maxima de emprestimo");
                                }
                            }

                    }
            }
    }
}
