package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import Controller.UserController;
import Model.Employee;

@SuppressWarnings("unused")

public class LoginFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	UserController theUserController;

	public LoginFrame(UserController theUserController) {

		this.theUserController = theUserController;

		initComponents();

	}

	public void initComponents() {

		setPreferredSize(new Dimension(713, 414));
		this.setLayout(null);

		textField = new JTextField();
		textField.setBounds(266, 198, 337, 27);
		this.add(textField);
		textField.setColumns(10);

		JLabel userName = new JLabel("USERNAME:");
		userName.setFont(new Font("Open Sans", userName.getFont().getStyle(), 13));
		userName.setForeground(Color.BLACK);
		userName.setBackground(Color.WHITE);
		userName.setBounds(115, 205, 141, 14);
		this.add(userName);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setBounds(266, 284, 134, 23);
		this.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (chckbxNewCheckBox.isSelected() == true) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('°');
				}
			}
		});
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Open Sans", btnNewButton.getFont().getStyle(), 13));
		btnNewButton.addActionListener(new ActionListener() {

			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {

				try {

					char[] charArray = { 'a', 'd', 'm', 'i', 'n' };
					Employee p = new Employee(textField.getText(), 1, "0", "0", 1, true, '0', "0");
					p.setPassword(passwordField.getPassword());
					if (theUserController.encryptLogin(p) == true) {

						theUserController.showHomeScreen();
					} else {
						JOptionPane.showMessageDialog(theUserController.screen, "Username or password is incorect",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(492, 291, 113, 27);
		this.add(btnNewButton);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		passwordField.setBounds(266, 250, 337, 27);
		this.add(passwordField);

		JLabel lblUsername = new JLabel("PASSWORD");
		lblUsername.setFont(new Font("Open Sans", lblUsername.getFont().getStyle(), 13));
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBackground(Color.WHITE);
		lblUsername.setBounds(115, 261, 141, 14);
		this.add(lblUsername);

		chckbxNewCheckBox.setBounds(266, 284, 134, 23);
		this.add(chckbxNewCheckBox);

		ImageIcon image = new ImageIcon("Data\\ProgramPictures\\hr.png"); // <a
																			// href="https://www.flaticon.com/free-icons/shift"
																			// title="shift icons">Shift icons created
																			// by Flat Icons - Flaticon</a>
		int width = 30;
		int height = 30;
		Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		JLabel lblNewLabel = new JLabel("Welcome to Next: Employee Management System");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		lblNewLabel.setBounds(115, 74, 538, 27);
		add(lblNewLabel);
		lblNewLabel.setIcon((scaledIcon));

	}
}
