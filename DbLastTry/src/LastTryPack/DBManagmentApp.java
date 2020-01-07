package LastTryPack;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;



import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DBManagmentApp {

	private JFrame frame;
	private JTextField LoginTextField;
	private JPasswordField passwordField;
	
	Connection con = null;

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

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public DBManagmentApp() throws ClassNotFoundException {
		initialize();
		con = DatenbankConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 827, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Nutzername");
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBounds(198, 64, 102, 34);
		frame.getContentPane().add(label);
		
		LoginTextField = new JTextField();
		LoginTextField.setColumns(10);
		LoginTextField.setBounds(359, 64, 267, 34);
		frame.getContentPane().add(LoginTextField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(359, 125, 267, 34);
		frame.getContentPane().add(passwordField);
		
		JLabel label_1 = new JLabel("Passwort");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(198, 125, 102, 34);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select * from nutzer where USRNAME=? and pw=? ";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(0, LoginTextField.getText());
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
		button.setFont(new Font("Tahoma", Font.PLAIN, 17));
		button.setBounds(410, 239, 115, 46);
		frame.getContentPane().add(button);
	}

}
