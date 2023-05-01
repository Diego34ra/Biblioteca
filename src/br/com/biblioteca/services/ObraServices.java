/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.biblioteca.services;

import br.com.biblioteca.model.Livro;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Diego
 */
public class ObraServices {
    
    public static Boolean createLivro(Livro livro){
        Boolean valida = false;
        try {
                FileOutputStream file = new FileOutputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\obra\\" + livro.getCodigo());
                ObjectOutputStream stream = new ObjectOutputStream(file);
                stream.writeObject(livro);
                stream.flush();
                valida = true;
        } catch (Exception erro) {
                System.out.println("Falha na gravação \n" + erro.toString());
                return valida;
        }
        return valida;
    }
}
