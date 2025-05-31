package estoque_av2_projeto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String HOST = "localhost";
	    private static final int PORT = 3306;
	    private static final String DATABASE = "Projeto_Estoque";
	    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
	    private static final String USER = "root";
	    private static final String PASSWORD = "root";

	    public static Connection conectar() throws SQLException {
	        try {
	            Class.forName(DRIVER);
	        } catch (ClassNotFoundException e) {
	            System.out.println("Driver não encontrado!");
	            e.printStackTrace();
	        }
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}
