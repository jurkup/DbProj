package DataPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class DBManagmentApp {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBManagmentApp window = new DBManagmentApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con = null;
	
	/**
	 * Create the application.
	 */
	public DBManagmentApp() {
		initialize();
		con = DatenbankenConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 843, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nutzername");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(112, 115, 102, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Passwort");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(112, 176, 102, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(273, 115, 267, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(273, 176, 267, 34);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select * from nutzer where USRNAME=? and pw=? ";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(0, textField.getText());
					pst.setString(1, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count =0;
					while(rs.next()) {
						count=count++;
											
					}
					if(count==1) {
						JOptionPane.showMessageDialog(null, "Anmeldung erfolgreich");
					}
					else if(count > 1) {
						JOptionPane.showMessageDialog(null, "Nutzername und Passwort existieren doppelt, wenden Sie sich an den Admin");
					}
					else {
						JOptionPane.showMessageDialog(null, "Falsch! nochmal.");
					}
					
					rs.close();
					pst.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				}
				
			
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLogin.setBounds(324, 290, 115, 46);
		frame.getContentPane().add(btnLogin);
	}
}
