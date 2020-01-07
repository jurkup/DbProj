package DataPack;

import java.sql.*;

import javax.swing.JOptionPane;
public class DatenbankenConnection {
	
	public static Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection= DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "egal");
			JOptionPane.showMessageDialog(null, "Connection Successful");

	        return connection;
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
	}

}
