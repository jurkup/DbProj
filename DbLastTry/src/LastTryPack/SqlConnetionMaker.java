package LastTryPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SqlConnetionMaker {
	

	public static Connection getConnection() throws ClassNotFoundException {
		try {
			
			Connection connection= DriverManager.getConnection("jdbc:mariadb://localhost:3306/modsimbank", "root", "egal");
			

	        return connection;
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
	}

}
