package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Controller.UserController;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MainFrame extends JPanel {

	private static final long serialVersionUID = 1L;

	UserController theUserController;

	private JButton btnVieweditEmployees;

	public MainFrame(UserController theUserController) {
		setBackground(new Color(248, 248, 255));

		this.theUserController = theUserController;
		initComponents();

	}

	public void initComponents() {

		setPreferredSize(new Dimension(724, 459));

		this.setLayout(null);

		JButton adddEmployeeButton = new JButton("Add Employee");

		/* href="https://www.flaticon.com/free-icons/shift" */

		ImageIcon image = new ImageIcon("C:\\Users\\sacha\\Downloads\\add-user.png");

		int width = 50;
		int height = 50;
		Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		adddEmployeeButton.setIcon((scaledIcon));

		adddEmployeeButton.setBackground(new Color(255, 250, 250));
		adddEmployeeButton.setForeground(Color.BLACK);
		adddEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				theUserController.showAddEmployeeScreen();
			}
		});
		adddEmployeeButton.setBounds(100, 100, 247, 108);
		this.add(adddEmployeeButton);

		JButton SalariesButton = new JButton("Salaries");
		SalariesButton.setBackground(new Color(248, 248, 255));
		/* href="https://www.flaticon.com/free-icons/shift" */
		ImageIcon image2 = new ImageIcon("C:\\Users\\sacha\\IA\\officialIB-IA\\Data\\ProgramPictures\\salary.png");

		Image scaledImage2 = image2.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
		SalariesButton.setIcon((scaledIcon2));

		SalariesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {theUserController.showPayEmployees();}});
		SalariesButton.setForeground(Color.DARK_GRAY);
		SalariesButton.setBounds(100, 300, 247, 108);
		this.add(SalariesButton);

		btnVieweditEmployees = new JButton("View/Edit Employees");
		btnVieweditEmployees.setBackground(new Color(255, 255, 255));
		btnVieweditEmployees.setForeground(Color.DARK_GRAY);

		/* Picture taken from https://www.flaticon.com/free-icons/shift */

		ImageIcon image1 = new ImageIcon("C:\\Users\\sacha\\IA\\officialIB-IA\\Data\\ProgramPictures\\team-work.png");
		int width1 = 50;
		int height1 = 50;
		Image scaledImage1 = image1.getImage().getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
		btnVieweditEmployees.setIcon((scaledIcon1));
		btnVieweditEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				theUserController.showSeeEmployees();
				SeeEmployees.seeAllButton.doClick();

			}
		});
		btnVieweditEmployees.setBounds(400, 100, 247, 108);
		this.add(btnVieweditEmployees);

		JButton btnNewButton_2_1 = new JButton("Shifts");
		btnNewButton_2_1.setBackground(new Color(245, 255, 250));
		btnNewButton_2_1.setForeground(Color.DARK_GRAY);

		ImageIcon image3 = new ImageIcon(
				"C:\\Users\\sacha\\IA\\officialIB-IA\\Data\\ProgramPictures\\work-schedule.png");// <a
																									// href="https://www.flaticon.com/free-icons/shift"
																									// title="shift
																									// icons">Shift
																									// icons created by
																									// Flat Icons -
																									// Flaticon</a>

		Image scaledImage3 = image3.getImage().getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
		btnNewButton_2_1.setIcon((scaledIcon3));

		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theUserController.ShowshiftGenerator();

			}

		});
		btnNewButton_2_1.setBounds(400, 300, 247, 108);
		this.add(btnNewButton_2_1);

		JLabel lblNewLabel = new JLabel("Welcome to EMP");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(290, 11, 494, 47);
		this.add(lblNewLabel);
	}
}
