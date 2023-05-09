/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.services;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Obra;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class EmprestimoServices {
    
    
    public static Boolean createEmprestimo(Emprestimo emprestimo){
        Boolean valida = false;
        try {
                FileOutputStream file = new FileOutputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\emprestimo\\" + emprestimo.getCodigo());
                ObjectOutputStream stream = new ObjectOutputStream(file);
                stream.writeObject(emprestimo);
                stream.flush();
                valida = true;
        } catch (Exception erro) {
                System.out.println("Falha na gravação \n" + erro.toString());
                return valida;
        }
        return valida;
    }
    public static ArrayList<Emprestimo> findAll(){
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        ArrayList<String> nameFiles = new ArrayList<>();
        File file = new File("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\emprestimo\\");
        File[] arquivos = file.listFiles();
        for (int i=0; i<file.listFiles().length; i++) {
            nameFiles.add(arquivos[i].getName());
        }
        for(String nameString : nameFiles) {
            try {             
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\emprestimo\\"+nameString);
                ObjectInputStream stream = new ObjectInputStream(fileInputStream);
                emprestimos.add((Emprestimo) stream.readObject());
            } catch (Exception erro) {
                System.out.println("Falha na leitura \n" + erro.toString());
                return null;
            }
        }
            return emprestimos;
    }
    
    public static Emprestimo findById(String id){
        Emprestimo emprestimo = new Emprestimo();

        try {             
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\emprestimo\\"+id);
            ObjectInputStream stream = new ObjectInputStream(fileInputStream);
            emprestimo = (Emprestimo) stream.readObject();
        } catch (Exception erro) {
            System.out.println("Falha na leitura \n" + erro.toString());
            return null;
        }
        return emprestimo;
    }
}
