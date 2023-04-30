/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.services.UsuarioServices;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class teste {
    public static void main(String[] args) {
        
        List<Item> list = new ArrayList<>();
        BigDecimal x = BigDecimal.valueOf(2.0);
        
        BigDecimal y = BigDecimal.valueOf(5.0);
        

        System.out.println("valor = " + x.add(y));
        
        Item item1 = new Item();
        item1.setCod(2L);
        item1.setQuant(3);
        list.add(item1);
        
        Item item2 = new Item();
        item2.setCod(2L);
        item2.setQuant(6);
        for(Item item : list){
            if(item.getCod() == item2.getCod()){
                item.setQuant(item.getQuant()+item2.getQuant());
            }
        
        }
        System.out.println("a");
        for(Item item : list){
            System.out.println("quant = "+ item.getQuant());
        
        }
        list.remove(1);
        
        Usuario usur = new Usuario();
        usur.setIdade(10);
        usur.setMatricula(101010L);
        usur.setNome("Diego");
        usur.setSenha("123");
        usur.setSexo("M");
        UsuarioServices.create(usur);
    }
}
