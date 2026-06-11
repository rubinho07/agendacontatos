package util;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/agenda_contatos?useTimezone=true&serverTimezone=UTC&useSSL=false",
                "root", 
                ""  
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar: " + e.getMessage(), e);
        }
    }
}
