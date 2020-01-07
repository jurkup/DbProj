package LastTryPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class FimalDbManagment {

	private JFrame frame;
	private JTextField loginTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FimalDbManagment window = new FimalDbManagment();
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
	 * @throws ClassNotFoundException 
	 */
	public FimalDbManagment() throws ClassNotFoundException {
		
		initialize();
		con = SqlConnetionMaker.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 760, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton loginBtn = new JButton("New button");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select * from nutzer where USRNAME=? and pw=? ";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(0, loginTextField.getText());
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
		loginBtn.setBounds(378, 258, 100, 37);
		frame.getContentPane().add(loginBtn);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(185, 129, 76, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(185, 177, 85, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		loginTextField = new JTextField();
		loginTextField.setBounds(294, 129, 297, 37);
		frame.getContentPane().add(loginTextField);
		loginTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(294, 186, 297, 28);
		frame.getContentPane().add(passwordField);
	}

}
