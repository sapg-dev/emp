package View;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Controller.UI;
import Controller.UserController;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import java.awt.Image;

public class PayEmployees extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table = new JTable();
	private DefaultTableModel model = new DefaultTableModel();

	private JScrollPane scrollPane;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar cal = Calendar.getInstance();
	UserController theUserController;

	public PayEmployees(UserController theUserController) {
		this.theUserController = theUserController;

		initComponents();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {

		this.setBackground(new Color(248, 248, 255));

		setPreferredSize(new Dimension(1144, 519));

		try {
			UI.uiDesign();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		theUserController.salariesJTable(model, table); // This method populates the JTable with the information that
														// neeeds to be displayed

		/* This section handles the correct creation of the GUI */

		setMinimumSize(new java.awt.Dimension(829, 659));
		setSize(new Dimension(1129, 800));
		this.setLayout(null);

		scrollPane = new JScrollPane();

		scrollPane.setBounds(20, 72, 575, 348);
		this.add(scrollPane);

		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		JTextField abovesalariesInput = new JTextField();
		abovesalariesInput.setBounds(754, 139, 86, 35);
		this.add(abovesalariesInput);
		abovesalariesInput.setColumns(10);

		JTextField salariesBelowInput = new JTextField();
		salariesBelowInput.setColumns(10);
		salariesBelowInput.setBounds(754, 185, 86, 33);
		this.add(salariesBelowInput);

		JTextField above_tax_rate_input = new JTextField();
		above_tax_rate_input.setColumns(10);
		above_tax_rate_input.setBounds(907, 139, 86, 35);
		this.add(above_tax_rate_input);

		JTextField below_tax_rate = new JTextField();
		below_tax_rate.setColumns(10);
		below_tax_rate.setBounds(907, 185, 86, 33);
		this.add(below_tax_rate);

		JLabel lblNewLabel_1 = new JLabel("For salaries above : ");
		lblNewLabel_1.setBounds(625, 142, 115, 14);
		this.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("For salaries below: ");
		lblNewLabel_1_1.setBounds(625, 188, 115, 14);
		this.add(lblNewLabel_1_1);

		JLabel above_tax_rate = new JLabel("tax rate is :");
		above_tax_rate.setBounds(846, 142, 115, 14);
		this.add(above_tax_rate);

		JLabel lblNewLabel_1_2_1 = new JLabel("%");
		lblNewLabel_1_2_1.setBounds(1003, 142, 115, 14);
		this.add(lblNewLabel_1_2_1);

		JLabel above_tax_rate_1 = new JLabel("tax rate is :");
		above_tax_rate_1.setBounds(846, 188, 115, 14);
		this.add(above_tax_rate_1);

		JLabel FinancialLabel = new JLabel("Financial and Tax Rate settings");
		FinancialLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
		FinancialLabel.setBounds(709, 72, 336, 14);
		this.add(FinancialLabel);

		JComboBox<String> currency = new JComboBox<String>();
		currency.setModel(new DefaultComboBoxModel(new String[] { "Mauritian Rupee(Rs)", "United States Dollar($)",
				"Euro(\u20AC)", "Indian Rupee(\u20B9)", "South African Rand(R)" }));
		currency.setBounds(736, 229, 265, 22);
		this.add(currency);

		JButton generatePDF = new JButton("Generate PDF");
		generatePDF.setBounds(642, 262, 368, 45);
		this.add(generatePDF);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("%");
		lblNewLabel_1_2_1_1.setBounds(1003, 188, 115, 14);
		this.add(lblNewLabel_1_2_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Choose currency : ");
		lblNewLabel_1_1_1.setBounds(605, 233, 115, 14);
		this.add(lblNewLabel_1_1_1);

		JLabel lblEmployeesFinancials = new JLabel("Employee's Financials");
		lblEmployeesFinancials.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmployeesFinancials.setBounds(506, 4, 336, 14);
		this.add(lblEmployeesFinancials);

		JLabel lblThisToolAllows = new JLabel(
				"This tool allows you to generate pay slips for employhees as a PDF, and also adjust the tax rates, and currencies used");
		lblThisToolAllows.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThisToolAllows.setBounds(253, 29, 790, 14);
		this.add(lblThisToolAllows);

		JButton btnNewButton = new JButton("");

		ImageIcon image1 = new ImageIcon("Data\\ProgramPictures\\arrow.png");// <a
																				// href="https://www.flaticon.com/free-icons/shift"
																				// title="shift icons">Shift icons
																				// created by Flat Icons - Flaticon</a>
		int width = 30;
		int height = 30;
		Image scaledImage1 = image1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
		btnNewButton.setIcon((scaledIcon1));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theUserController.showHomeScreen();
			}
		});
		btnNewButton.setBounds(0, 2, 89, 23);
		this.add(btnNewButton);

		JButton refresh = new JButton("Refresh Table");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				theUserController.updateViewJTable(model, table);
			}
		});
		refresh.setBounds(10, 461, 143, 34);
		add(refresh);

		/*
		 * This section is dedicated to the event listeners of this GUI, so it involved
		 * the reaction to the generate PDF button being clicked
		 */

		ImageIcon image = new ImageIcon("Data\\ProgramPictures\\pdf.png");// <a
																			// href="https://www.flaticon.com/free-icons/shift"
																			// title="shift icons">Shift icons created
																			// by Flat Icons - Flaticon</a>

		Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		generatePDF.setIcon((scaledIcon));

		generatePDF.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				/* Validation of Data using If statement and OR gates */

				if (abovesalariesInput.getText().isEmpty() == false || above_tax_rate_input.getText().isEmpty() == false
						|| salariesBelowInput.getText().isEmpty() == false
						|| below_tax_rate.getText().isEmpty() == false
						|| abovesalariesInput.getText().equals(salariesBelowInput.getText()) == true) {

					/*
					 * Here all necessary variables to ALLOW correct calling of the method are
					 * declared
					 */

					int upper_bound = Integer.valueOf(abovesalariesInput.getText());
					int upper_bound_percentage = Integer.valueOf(above_tax_rate_input.getText());
					int lower_bound = Integer.valueOf(salariesBelowInput.getText());
					int lower_bound_percentage = Integer.valueOf(below_tax_rate.getText());
					String currenyText = currency.getSelectedItem().toString().substring(
							currency.getSelectedItem().toString().indexOf("(") + 1,
							currency.getSelectedItem().toString().indexOf(")"));
					for (int i = 0; i < model.getRowCount(); i++) {
						/* Calling the method */theUserController.generateSalariesPDF(model.getRowCount(), dateFormat,
								cal, Integer.valueOf((String) model.getValueAt(i, 0)), upper_bound,
								upper_bound_percentage, lower_bound, lower_bound_percentage, currenyText);

					}
				} else {

					JOptionPane.showMessageDialog(null, "Financial and Tax Informations cannot be empty");
				}
			}
		});

	}

}