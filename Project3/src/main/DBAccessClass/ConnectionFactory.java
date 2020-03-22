package DBAccessClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

//import com.mysql.cj.api.jdbc.Statement;

public class ConnectionFactory {
	private static final Logger LOGGER=Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/order_management?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String USER="root";
	private static final String PASSW="pass";
	private Connection connection;

	private static ConnectionFactory singleInstance=new ConnectionFactory();
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public Connection createConnection()
	{
		connection = null;
	        try {
	        	connection = DriverManager.getConnection(DBURL, USER, PASSW);
	        } catch (SQLException ex) {
	            System.err.println("SQLException: " + ex);
	            LOGGER.info("Eroare conectare");
	            System.exit(1);
	        }
			return connection;

	}	
	 public static Connection getConnection()
	 {
		 return singleInstance.createConnection();
	 }
	 public static void close(Connection connect)
	 {
		 try {
			 connect.close();
				}
				catch (SQLException ex) {
					System.err.println("Exception during connection close: " + ex);
					 LOGGER.info("Eroare deconectare connection");
				}
	 }
	 public static void close(PreparedStatement statement)
	 {
		 try {
			 statement.close();
				}
				catch (SQLException ex) {
					System.err.println("Exception during statemant close: " + ex);
					 LOGGER.info("Eroare deconectare statement");
				}
	 }
	 public static void close(ResultSet resultSet)
	 {
		 try {
			 resultSet.close();
				}
				catch (SQLException ex) {
					System.err.println("Exception during resultSet close: " + ex);
					 LOGGER.info("Eroare deconectare resultSet");
				}
	 }
	 public static void main(String[] args) {
		ConnectionFactory c=new ConnectionFactory();
		Connection con=c.createConnection();
		if(con!=null) System.out.println("succes");
		
	}
} 

