package banco_conect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciador_biblioteca", "root", "");

            return conexao;
        } catch (SQLException exception) {
            throw new Error(exception.getMessage());
        }
    }
}