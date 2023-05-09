/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.services;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Obra;
import br.com.biblioteca.model.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ObraServices {
    
    public static Boolean createObra(Obra obra){
        Boolean valida = false;
        try {
            FileOutputStream file = new FileOutputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\obra\\" + obra.getCodigo());
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(obra);
            stream.flush();
            valida = true;
        } catch (Exception erro) {
            System.out.println("Falha na gravação \n" + erro.toString());
            return valida;
        }
        return valida;
    }
    
    public static ArrayList<Obra> findAll(){
        ArrayList<Obra> obras = new ArrayList<>();
        ArrayList<String> nameFiles = new ArrayList<>();
        File file = new File("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\obra\\");
        File[] arquivos = file.listFiles();
        System.out.println("nome files = "+ file.getName());
        System.out.println("tamanho da files = "+ file.listFiles().length);
        for (int i=0; i<file.listFiles().length; i++) {
            System.out.println("nome file = "+arquivos[i].getName());
            nameFiles.add(arquivos[i].getName());
        }
        for(String nameString : nameFiles) {
            System.out.println("pasta : "+ nameString);
            try {             
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\obra\\"+nameString);
                ObjectInputStream stream = new ObjectInputStream(fileInputStream);
                obras.add((Obra) stream.readObject());
            } catch (Exception erro) {
                System.out.println("Falha na leitura \n" + erro.toString());
                return null;
            }
        }
            return obras;
    }
    
    public static Obra findById(String id){
        Obra obra = new Obra();

        try {             
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\obra\\"+id);
            ObjectInputStream stream = new ObjectInputStream(fileInputStream);
            obra = (Obra) stream.readObject();
        } catch (Exception erro) {
            System.out.println("Falha na leitura \n" + erro.toString());
            return null;
        }
        return obra;
    }
    
    public static void deleteById(long codigo) {
        Path path = Paths.get("src/biblioteca/obra/"+codigo); 
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("Obra deletada com sucesso.");
            }
            else {
                System.out.println("Erro ao deletar usuario.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
