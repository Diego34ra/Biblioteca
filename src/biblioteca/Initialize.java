/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca;

import br.com.biblioteca.connection.ConnectionBD;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Diego
 */
public class Initialize {
    
    public void generateDB(){
        if(!verifyTable("obra")){
            createTable("CREATE TABLE `obra` (\n" +
                        "  `codObra` int(11) NOT NULL,\n" +
                        "  `nome` varchar(45) NOT NULL,\n" +
                        "  `tipo` varchar(45) NOT NULL,\n" +
                        "  `digital` tinyint(1) NOT NULL,\n" +
                        "  `emprestimo` tinyint(1) NOT NULL,\n" +
                        "  PRIMARY KEY (`codObra`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        if (!verifyTable("livro")) {
            createTable("CREATE TABLE `livro` (\n" +
                        "  `codObra` int(11) NOT NULL,\n" +
                        "  `autores` varchar(45) NOT NULL,\n" +
                        "  `editora` varchar(45) NOT NULL,\n" +
                        "  `ano` int(11) NOT NULL,\n" +
                        "  `edicao` int(11) NOT NULL,\n" +
                        "  `numFolhas` int(11) NOT NULL,\n" +
                        "  PRIMARY KEY (`codObra`),\n" +
                        "  CONSTRAINT `Obra_Livro` FOREIGN KEY (`codObra`) REFERENCES `obra` (`codObra`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        
        if(!verifyTable("fotografia")){
            createTable("CREATE TABLE `fotografia` (\n" +
                        "  `codObra` int(11) NOT NULL,\n" +
                        "  `area` varchar(45) NOT NULL,\n" +
                        "  `tamanho` varchar(45) NOT NULL,\n" +
                        "  PRIMARY KEY (`codObra`),\n" +
                        "  CONSTRAINT `Obra_Fotografia` FOREIGN KEY (`codObra`) REFERENCES `obra` (`codObra`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        if(!verifyTable("midia_audio")){
            createTable("CREATE TABLE `midia_audio` (\n" +
                        "  `codObra` int(11) NOT NULL,\n" +
                        "  `assunto` varchar(45) NOT NULL,\n" +
                        "  `tamanho` varchar(45) NOT NULL,\n" +
                        "  PRIMARY KEY (`codObra`),\n" +
                        "  CONSTRAINT `Obra_MidiaAudio` FOREIGN KEY (`codObra`) REFERENCES `obra` (`codObra`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        if(!verifyTable("usuario")){
            createTable("CREATE TABLE `usuario` (\n" +
                        "  `matricula` int(11) NOT NULL,\n" +
                        "  `nome` varchar(45) NOT NULL,\n" +
                        "  `sexo` varchar(45) NOT NULL,\n" +
                        "  `tipo` varchar(45) NOT NULL,\n" +
                        "  `telefone` varchar(45) NOT NULL,\n" +
                        "  `senha` varchar(45) NOT NULL,\n" +
                        "  PRIMARY KEY (`matricula`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        if(!verifyTable("funcionario")){
            createTable("CREATE TABLE `funcionario` (\n" +
                        "  `matricula` int(11) NOT NULL,\n" +
                        "  `salario` double(10,2) NOT NULL,\n" +
                        "  PRIMARY KEY (`matricula`),\n" +
                        "  CONSTRAINT `Usuario_Funcionario` FOREIGN KEY (`matricula`) REFERENCES `usuario` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        if(!verifyTable("estudante")){
            createTable("CREATE TABLE `estudante` (\n" +
                        "  `matricula` int(11) NOT NULL,\n" +
                        "  `curso` varchar(45) NOT NULL,\n" +
                        "  PRIMARY KEY (`matricula`),\n" +
                        "  CONSTRAINT `Usuario_Estudante` FOREIGN KEY (`matricula`) REFERENCES `usuario` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        if(!verifyTable("professor")){
            createTable("CREATE TABLE `professor` (\n" +
                        "  `matricula` int(11) NOT NULL,\n" +
                        "  `especialidade` varchar(45) NOT NULL,\n" +
                        "  PRIMARY KEY (`matricula`),\n" +
                        "  CONSTRAINT `Usuario_Professor` FOREIGN KEY (`matricula`) REFERENCES `usuario` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        if(!verifyTable("emprestimo")){
            createTable("CREATE TABLE `emprestimo` (\n" +
                        "  `codEmprestimo` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `matricula` int(11) NOT NULL,\n" +
                        "  `codObra` int(11) NOT NULL,\n" +
                        "  `dataEmprestimo` datetime NOT NULL,\n" +
                        "  PRIMARY KEY (`codEmprestimo`),\n" +
                        "  KEY `Usuario_Emprestimo` (`matricula`),\n" +
                        "  KEY `Obra_Emprestimo` (`codObra`),\n" +
                        "  CONSTRAINT `Obra_Emprestimo` FOREIGN KEY (`codObra`) REFERENCES `obra` (`codObra`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                        "  CONSTRAINT `Usuario_Emprestimo` FOREIGN KEY (`matricula`) REFERENCES `usuario` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
        
        if(!verifyTable("reserva")){
            createTable("CREATE TABLE `reserva` (\n" +
                        "  `codReserva` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `matricula` int(11) NOT NULL,\n" +
                        "  `codObra` int(11) NOT NULL,\n" +
                        "  `dataReserva` datetime NOT NULL,\n" +
                        "  PRIMARY KEY (`codReserva`),\n" +
                        "  KEY `Usuario_Reserva` (`matricula`),\n" +
                        "  KEY `Obra_Reserva` (`codObra`),\n" +
                        "  CONSTRAINT `Obra_Reserva` FOREIGN KEY (`codObra`) REFERENCES `obra` (`codObra`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                        "  CONSTRAINT `Usuario_Reserva` FOREIGN KEY (`matricula`) REFERENCES `usuario` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
        }
    }
    
    public void createTable(String sql){
        ConnectionBD con = new ConnectionBD();
        Statement stmt = null;
        try {
            stmt = con.getConnection().createStatement();
            stmt.execute(sql);    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public Boolean verifyTable(String table){
        String sql = "SELECT 1 AS RESULTADO \n" +
                     "  FROM information_schema.TABLES \n" +
                     "    WHERE table_schema = 'biblioteca'\n" +
                     "    AND table_name = '"+table+"'";
        Boolean valida = false;
        ConnectionBD con = new ConnectionBD();
        Statement stmt = null;
        ResultSet rs = null;
         try {
            stmt = con.getConnection().createStatement();
            rs = stmt.executeQuery(sql); 
            while(rs.next()) {
            	valida = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return valida;
    }
}
