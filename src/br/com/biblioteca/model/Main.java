package br.com.biblioteca.model;

import br.com.biblioteca.services.MenuServices;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	 public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Livro> livros = new ArrayList<>();
		MenuServices menuServices = new MenuServices();
		System.out.println("------- Menu de operacoes --------");
                System.out.println("1- Cadastrar Livro");
                System.out.println("2- Cadastrar Usuário");
                System.out.println("3- Realizar Emprestimo");
                System.out.println("4- Realizar Devolução");
                System.out.println("5- Listar todos os empréstimos");
		System.out.print("Escolha: ");
		int escolha = scanner.nextInt();
		switch (escolha) {
		case 1: 
			livros = menuServices.cadastrarLivro(livros);
			break;
		case 2:
			usuarios = menuServices.cadastrarUsuario(usuarios);
			break;
		case 3:
			menuServices.realizarEmprestismo(livros, usuarios.get(0));
			break;
		case 4:
			menuServices.devolverLivro(usuarios.get(0));
			break;
		case 5:
			menuServices.listarEmprestimo(usuarios.get(0));
			break;
		}
	}
	
}
