package DBOperations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Validating {

	private JFrame frame;
	private JTextField tf1;
	private JPasswordField tf2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Validating window = new Validating();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Validating() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(168, 21, 125, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lb1 = new JLabel("Name");
		lb1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lb1.setBounds(122, 82, 46, 14);
		frame.getContentPane().add(lb1);
		
		JLabel lb2 = new JLabel("Password");
		lb2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lb2.setBounds(122, 124, 80, 19);
		frame.getContentPane().add(lb2);
		
		tf1 = new JTextField();
		tf1.setBounds(231, 81, 86, 20);
		frame.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tf1.getText();
			    String password=tf2.getText();
			    try {
			    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","mrec");
			    	PreparedStatement ps=con.prepareStatement("select name,password from users where name=? and password=?");
			    	ps.setString(1,name);
			    	ps.setString(2,password);

			    	ResultSet rs=ps.executeQuery();
			    	if(rs.next())
			    	{
			    		JOptionPane.showMessageDialog(btnNewButton, "Valid user");
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(btnNewButton, "Invalid User");
			    	}
			    }
			    catch(SQLException e1) {
			    	e1.printStackTrace();
			    }
				}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(182, 197, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		tf2 = new JPasswordField();
		tf2.setBounds(231, 125, 86, 20);
		frame.getContentPane().add(tf2);
	}
}
