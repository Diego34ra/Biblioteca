/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.services;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Reserva;
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
public class ReservaServices {
    
    
    public static Boolean create(Reserva reserva){
        Boolean valida = false;
        try {
                FileOutputStream file = new FileOutputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\reserva\\" + reserva.getCodigo());
                ObjectOutputStream stream = new ObjectOutputStream(file);
                stream.writeObject(reserva);
                stream.flush();
                valida = true;
        } catch (Exception erro) {
                System.out.println("Falha na gravação \n" + erro.toString());
                return valida;
        }
        return valida;
    }
    
    public static ArrayList<Reserva> findAll(){
        ArrayList<Reserva> reservas = new ArrayList<>();
        ArrayList<String> nameFiles = new ArrayList<>();
        File file = new File("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\reserva\\");
        File[] arquivos = file.listFiles();
        for (int i=0; i<file.listFiles().length; i++) {
            nameFiles.add(arquivos[i].getName());
        }
        for(String nameString : nameFiles) {
            try {             
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\reserva\\"+nameString);
                ObjectInputStream stream = new ObjectInputStream(fileInputStream);
                reservas.add((Reserva) stream.readObject());
            } catch (Exception erro) {
                System.out.println("Falha na leitura \n" + erro.toString());
                return null;
            }
        }
            return reservas;
    }
    
    public static Reserva findById(String id){
        Reserva reserva = new Reserva();

        try {             
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\reserva\\"+id);
            ObjectInputStream stream = new ObjectInputStream(fileInputStream);
            reserva = (Reserva) stream.readObject();
        } catch (Exception erro) {
            System.out.println("Falha na leitura \n" + erro.toString());
            return null;
        }
        return reserva;
    }
    
    public static void deleteById(long codigo) {
        Path path = Paths.get("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\reserva\\"+codigo); 
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("Emprestimo deletada com sucesso.");
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
