package br.com.biblioteca.services;

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

public class UsuarioServices {
	
	public ArrayList<Usuario> findAll(){
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<String> nameFiles = new ArrayList<>();
		File file = new File("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\usuario\\");
	    File[] arquivos = file.listFiles();
	    for (int i=0; i<file.length(); i++) {
	        nameFiles.add(arquivos[i].getName());
	    }
	    for(String nameString : nameFiles) {
	    	try {             
                    FileInputStream fileInputStream = new FileInputStream(nameString);
                    ObjectInputStream stream = new ObjectInputStream(fileInputStream);
                    usuarios.add((Usuario) stream.readObject());
                } catch (Exception erro) {
                    System.out.println("Falha na leitura \n" + erro.toString());
                    return null;
                }
	    }
		return usuarios;
	}
	
	public static Usuario findById(String codigo) {
            try {
                FileInputStream file = new FileInputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\usuario\\" + codigo);
                ObjectInputStream stream = new ObjectInputStream(file);
                return((Usuario) stream.readObject());

            } catch (Exception erro) {
                System.out.println("Falha na leitura \n" + erro.toString());
                return null;
            }
	}
	
	public static Boolean create(Usuario usuario) {
            Boolean valida = false;
            try {
                    FileOutputStream file = new FileOutputStream("C:\\Users\\Developer\\Documents\\GitHub\\Biblioteca\\src\\biblioteca\\usuario\\" + usuario.getMatricula());
                    ObjectOutputStream stream = new ObjectOutputStream(file);
                    stream.writeObject(usuario);
                    stream.flush();
                    valida = true;
            } catch (Exception erro) {
                    System.out.println("Falha na gravação \n" + erro.toString());
                    return valida;
            }
            return valida;
	}
	
	public void deleteById(long codigo) {
            Path path = Paths.get("biblioteca/usuario"+codigo); 
            try {
                boolean result = Files.deleteIfExists(path);
                if (result) {
                    System.out.println("Usuario deletado com sucesso.");
                }
                else {
                    System.out.println("Erro ao deletar usuario.");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
	}
	
	public Boolean update(Usuario usuarioUpdate, long codigo) {
		Usuario usuario = findById(String.valueOf(codigo));
		usuario.setIdade(usuario.getIdade());
		usuario.setLivros(usuarioUpdate.getLivros());
		usuario.setNome(usuarioUpdate.getNome());
		usuario.setSexo(usuarioUpdate.getSexo());
		usuario.setTelefone(usuarioUpdate.getTelefone());
		
		deleteById(codigo);
		return create(usuario);
		
	}
}
