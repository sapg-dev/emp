package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controller.UserController;
import Model.Employee;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddEmployeeScreen extends JPanel implements Serializable {

	

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

    

	UserController theUserController;
	private javax.swing.JButton addEmployeeButton = new JButton();
	private JTextField annualSalaryInput = new  JTextField();
			javax.swing.JLabel annualSalaryLabel = new JLabel();
	private javax.swing.JLabel employeeNumberLabel = new JLabel();
	private javax.swing.ButtonGroup employeeTypeButtonGroup = new ButtonGroup();
	private javax.swing.JRadioButton femaleRadioButton = new JRadioButton();
	public javax.swing.JTextField firstNameInput = new JTextField();
	private javax.swing.JLabel firstNameLabel = new JLabel();
	private javax.swing.JRadioButton fullTimeEmployeeButton = new JRadioButton();
	private javax.swing.JLabel fullTimeEmployeeInfoLabel = new JLabel();
	private javax.swing.ButtonGroup genderButtonGroup = new ButtonGroup();
	private javax.swing.JLabel genderLabel = new JLabel();
	private javax.swing.JTextField hourlyWageInput = new JTextField();
	private javax.swing.JLabel hourlyWageLabel = new JLabel();
	protected javax.swing.JTextField lastNameInput = new JTextField();
	private javax.swing.JLabel lastNameLabel = new JLabel();
	private javax.swing.JRadioButton maleRadioButton = new JRadioButton();
	private javax.swing.JRadioButton partTimeEmployeeButton = new JRadioButton();
	private javax.swing.JLabel partTimeEmployeeInfoLabel = new JLabel();
	private javax.swing.JLabel titleLabel = new JLabel();
	private javax.swing.JLabel workLocationLabel = new JLabel();
	private JPasswordField passwordInput;
	private JComboBox<Object> roleof = new JComboBox<Object>();
	@SuppressWarnings("unused")
	private JFileChooser fileChooser = new JFileChooser();
	private JTextField employeeNumberInput;
	JLabel lblHoursPerWeeks;
	JButton empNumberGen;
	@SuppressWarnings("rawtypes")
	private JComboBox workLocationInput = new JComboBox();
	private JTextField hoursPerWeekText = new JTextField();
	private JSlider slider = new JSlider();
	private JLabel lblP = new JLabel();

	public AddEmployeeScreen(UserController controller) {
	
		this.theUserController = controller;


		initComponents();
	

	}
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {
		
		setPreferredSize( new Dimension(544, 742) );
		this.setBackground(new Color(248, 248, 255));
		// JFileChooser filechooser = new JFileChooser();
		// File f = new File("declarationFile");
	


		titleLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
		titleLabel.setText("Create a new Employee");

		employeeTypeButtonGroup.add(partTimeEmployeeButton);
		partTimeEmployeeButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
		partTimeEmployeeButton.setText("Part Time Employee");

		employeeTypeButtonGroup.add(fullTimeEmployeeButton);
		fullTimeEmployeeButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
		fullTimeEmployeeButton.setText("Full Time Employee");

		employeeNumberLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		employeeNumberLabel.setText("Employee Number: ");

		firstNameLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		firstNameLabel.setText("First Name: ");

		lastNameLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		lastNameLabel.setText("Last Name:");

		genderLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		genderLabel.setText("Gender: ");

		workLocationLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		workLocationLabel.setText("Work Location: ");

		annualSalaryLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		annualSalaryLabel.setText("Annual Salary:");

		fullTimeEmployeeInfoLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		fullTimeEmployeeInfoLabel.setText("Full Time Employee Information:");

		partTimeEmployeeInfoLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		partTimeEmployeeInfoLabel.setText("Part Time Employee Information:");

		hourlyWageLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		hourlyWageLabel.setText("Hourly Wage:");
		addEmployeeButton.setBackground(new Color(0, 128, 0));
		addEmployeeButton.setForeground(Color.BLACK);

		addEmployeeButton.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
		addEmployeeButton.setText("Add employee");

		genderButtonGroup.add(maleRadioButton);
		maleRadioButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
		maleRadioButton.setText("Male");
		maleRadioButton.setSelected(true);

		genderButtonGroup.add(femaleRadioButton);
		femaleRadioButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
		femaleRadioButton.setText("Female");

		passwordInput = new JPasswordField();

		lblP.setText("Password");
		lblP.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		roleof.setModel(new DefaultComboBoxModel<Object>(new String[] { "Admin", "Management", "Employee" }));
		roleof.setMaximumRowCount(2);
		roleof.setToolTipText("");
		roleof.setSelectedItem("Employee");
		passwordInput.setEnabled(false);

		employeeNumberInput = new JTextField();
		employeeNumberInput.setEditable(false);

		workLocationInput.setModel(new DefaultComboBoxModel(new String[] { "Jules", "Parfois", "Undiz", "Inglot" }));

		 empNumberGen = new JButton("Generate Number");

		slider.setToolTipText("Hours");
		hoursPerWeekText = new JTextField();
		hoursPerWeekText.setBackground(Color.LIGHT_GRAY);
		
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setValue(23);
		slider.setMinimum(18);
		slider.setMaximum(28);

		 lblHoursPerWeeks = new JLabel();
		lblHoursPerWeeks.setText("Hours Per Week");
		lblHoursPerWeeks.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		hoursPerWeekText.setColumns(10);
		
		ImageIcon image =  new ImageIcon("Data\\ProgramPictures\\add-users.png"); // <a href="https://www.flaticon.com/free-icons/shift" title="shift icons">Shift icons created by Flat Icons - Flaticon</a>
		int width =30;
		int height = 30;
		Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);

		addEmployeeButton.setIcon((scaledIcon));
		/*
		 * This sector is dedicated to the Event Listeners, consequently it included the
		 * employee number generation tool's interaction with the GUI, and the Add
		 * Eployee button
		 */

		empNumberGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				employeeNumberInput.setText(String.valueOf(theUserController.generateEmployeeNumber())); // Sets the
																											// texField
																											// of the
																											// employee
																											// number to
																											// the
																											// return
																											// value of
																											// the
																											// method

			}
		});

		roleof.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(roleof.getSelectedItem() == "Admin" && (partTimeEmployeeButton.isSelected() || fullTimeEmployeeButton.isSelected()) == true) {
					
					
					clearFullTimeInformation();
					clearPartTimeInformation();
					fullTimeEmployeeButton.setSelected(false);
					partTimeEmployeeButton.setSelected(false);
					employeeTypeButtonGroup.clearSelection();
					
					
					//fullTimeEmployeeButton.s
					//annualSalaryInput.setText("0");
				}

				/*
				 * This is for data validation purposes only : , if the current user adds an
				 * admin, theadmin will need a password and thus the field is turned on
				 * conversely, if the user adds an employee, an empployee does not have access
				 * to the system, rendering its need for a password trivial Thus the password
				 * fiedl is turned off
				 */
				if (roleof.getSelectedItem() == "Employee") {
					passwordInput.setEnabled(false);
				}
				if (roleof.getSelectedItem() == "Management" || roleof.getSelectedItem() == "Admin") {

					passwordInput.setEnabled(true);
				}
			}
		});

		partTimeEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				addPartTimeInformation();
				
				if((partTimeEmployeeButton.isSelected() == true)) {
					
					addPartTimeInformation();
					clearFullTimeInformation();
				}
				
	if(partTimeEmployeeButton.isSelected() == true && roleof.getSelectedItem() == "Admin") {
					
					clearPartTimeInformation();
					clearFullTimeInformation();
					employeeTypeButtonGroup.clearSelection()
;				
					
				}

				/*
				 * Data validation : again, a part time employee does not have an annual salary
				 * but a hourly wage, thus we turn off the fields dedicatied
				 */
			}
		});

		fullTimeEmployeeButton.addActionListener(new ActionListener() {

			/*
			 * Data validation : a full time employee only has an annual salary, so we turn
			 * off the other fields not needed
			 */

			public void actionPerformed(ActionEvent e) {
				if(fullTimeEmployeeButton.isSelected() == true && roleof.getSelectedItem() == "Admin") {
					
					clearPartTimeInformation();
					clearFullTimeInformation();
					employeeTypeButtonGroup.clearSelection()
;				
					
				}
				
				if (fullTimeEmployeeButton.isSelected() == true) {
					clearPartTimeInformation();
					addFullTimeInformation();
				
//					slider.setEnabled(false);
//					hoursPerWeekText.setText("0");
//					annualSalaryInput.setBackground(Color.WHITE);
//					annualSalaryInput.setText("");
//					hoursPerWeekText.setEditable(false);
//					annualSalaryInput.setEditable(true);

				}

			}
		});

		addEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				
				
		
					
					validateAndAddEmployee(roleof.getSelectedItem().toString());
			
					

					
				

				/*
				 * This is the most important part of this GUI : the creation of an employee We
				 * simply use the userController class's instance and then after data
				 * validation, we call the create user method
				 */

			
			}});

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				hoursPerWeekText.setText(String.valueOf(slider.getValue()));
			}
		});

		JButton backButton = new JButton("<--- back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				theUserController.back();

			}
		});
		
		clearFullTimeInformation();
		clearPartTimeInformation();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(48)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(employeeNumberLabel)
								.addComponent(firstNameLabel)
								.addComponent(lastNameLabel)
								.addComponent(genderLabel)
								.addComponent(workLocationLabel)
								.addComponent(lblP, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.addComponent(lastNameInput, 238, 238, 238)
								.addGroup(layout.createSequentialGroup()
									.addComponent(maleRadioButton)
									.addGap(34)
									.addComponent(femaleRadioButton))
								.addGroup(layout.createSequentialGroup()
									.addComponent(workLocationInput, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(roleof, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createParallelGroup(Alignment.TRAILING)
									.addComponent(annualSalaryInput, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
									.addComponent(hourlyWageInput, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(layout.createSequentialGroup()
										.addComponent(employeeNumberInput, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(empNumberGen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addComponent(firstNameInput, Alignment.LEADING, 238, 238, 238))))
						.addGroup(layout.createSequentialGroup()
							.addGap(19)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup()
											.addComponent(hourlyWageLabel)
											.addGap(318))
										.addGroup(layout.createSequentialGroup()
											.addComponent(lblHoursPerWeeks, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(hoursPerWeekText, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addGap(18))))
								.addComponent(fullTimeEmployeeInfoLabel)
								.addComponent(partTimeEmployeeInfoLabel)))
						.addGroup(layout.createSequentialGroup()
							.addGap(52)
							.addComponent(annualSalaryLabel)))
					.addContainerGap(108, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addComponent(backButton)
					.addContainerGap(491, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addGap(143)
					.addComponent(titleLabel)
					.addContainerGap(195, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addGap(115)
					.addComponent(addEmployeeButton, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(169, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addGap(191)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(partTimeEmployeeButton)
						.addComponent(fullTimeEmployeeButton))
					.addContainerGap(252, Short.MAX_VALUE))
		);
		annualSalaryInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			
			}
		});
		
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(backButton)
					.addGap(13)
					.addComponent(titleLabel)
					.addGap(29)
					.addComponent(fullTimeEmployeeButton)
					.addGap(18)
					.addComponent(partTimeEmployeeButton)
					.addGap(47)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(employeeNumberLabel)
						.addComponent(employeeNumberInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(empNumberGen))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstNameLabel)
						.addComponent(firstNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lastNameLabel)
						.addComponent(lastNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(genderLabel)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
							.addComponent(maleRadioButton)
							.addComponent(femaleRadioButton)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(workLocationLabel)
						.addComponent(workLocationInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(roleof, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblP, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(fullTimeEmployeeInfoLabel)
					.addGap(8)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(annualSalaryLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(annualSalaryInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(partTimeEmployeeInfoLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(hourlyWageLabel)
						.addComponent(hourlyWageInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHoursPerWeeks, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(hoursPerWeekText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(addEmployeeButton)
					.addGap(92))
		);
		this.setLayout(layout);

	}

	@SuppressWarnings("unused")
	private void addEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {// GEN-FIRST:event_addEmployeeButtonActionPerformed

	}

	@SuppressWarnings("unused")
	private void maleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_maleRadioButtonActionPerformed

	}

	public void setEmployeeNumberInput(JPasswordField employeeNumberInput) {
	}

	
	

	@SuppressWarnings("removal")
	public char getGender() {

		if (maleRadioButton.isSelected() == true) {
			return new Character('M');
		} else {
			return new Character('F');

		}

	}
	
	public void clearPartTimeInformation() {
		partTimeEmployeeInfoLabel.setVisible(false);
		hourlyWageLabel.setVisible(false);
		hourlyWageInput.setVisible(false);
		lblHoursPerWeeks.setVisible(false);
		slider.setVisible(false);
		hoursPerWeekText.setVisible(false);
		
		
	}
	
	public void clearFullTimeInformation() {
		
	
		annualSalaryLabel.setVisible(false);
		annualSalaryInput.setVisible(false);
	

		fullTimeEmployeeInfoLabel.setVisible(false);

		annualSalaryLabel.setVisible(false);
		annualSalaryInput.setVisible(false);

	
		
	}
	public void addFullTimeInformation() {
		employeeNumberLabel.setVisible(true);
		employeeNumberInput.setVisible(true);
		empNumberGen.setVisible(true);
		firstNameLabel.setVisible(true);
		firstNameInput.setVisible(true);
		lastNameLabel.setVisible(true);
		lastNameInput.setVisible(true);
		genderLabel.setVisible(true);
		maleRadioButton.setVisible(true);
		femaleRadioButton.setVisible(true);
		workLocationLabel.setVisible(true);
		workLocationInput.setVisible(true);
		roleof.setVisible(true);
		lblP.setVisible(true);
		passwordInput.setVisible(true);
		fullTimeEmployeeInfoLabel.setVisible(true);
		workLocationInput.setVisible(true);
		annualSalaryLabel.setVisible(true);
		annualSalaryInput.setVisible(true);
		addEmployeeButton.setVisible(true);
		
	}
	
	public void addPartTimeInformation( ) {
		
		
		
		
		
		
		
		partTimeEmployeeInfoLabel.setVisible(true);
		hourlyWageLabel.setVisible(true);
		hourlyWageInput.setVisible(true);
		lblHoursPerWeeks.setVisible(true);
		slider.setVisible(true);
		hoursPerWeekText.setVisible(true);
	}
	
	
	
	public void clearFields() {
		employeeNumberInput.setText(String.valueOf(theUserController.generateEmployeeNumber()));

		hourlyWageInput.setText("");
		
	
		hoursPerWeekText.setText("");
	

		annualSalaryInput.setText("");

		employeeNumberInput.setText("");
	
	
		firstNameInput.setText("");
		
		lastNameInput.setText("");

	
		
		
	
	
		passwordInput.setText("");
		fullTimeEmployeeInfoLabel.setText("");
		
	
		annualSalaryInput.setText("");
	


		hourlyWageInput.setText("");
	
	
	

		
		
	}
	
	public void validateAndAddEmployee(String role) {
		
		
		if((role == "Admin")) {
			
			Employee emp;
			
			annualSalaryInput.setText(new String("0"));

			emp = new Employee(firstNameInput.getText(), Integer.valueOf(employeeNumberInput.getText()),
					lastNameInput.getText(), String.valueOf(workLocationInput.getSelectedItem()),
					Integer.valueOf(annualSalaryInput.getText()), fullTimeEmployeeButton.isSelected(), getGender(), "Admin");
			
			
			
			
				
				emp.setPassword(passwordInput.getPassword());
			
				
			
				if (theUserController.createUserPass(emp) == true) {

					JOptionPane.showMessageDialog(this, "Sucess : Employee added");
					//clearFields();
					
				} else {
					JOptionPane.showMessageDialog(this, "An error occured, contact administrator");
					
				} 

	}
		
		
		if((role == "Employee")) {
			


			Employee emp;
			
			if(firstNameInput.getText().isEmpty() == false || employeeNumberInput.getText().isEmpty() == false || lastNameInput.getText().isEmpty() == false ||
					annualSalaryInput.getText().isEmpty() == false ) {
				
			
				

			emp = new Employee(firstNameInput.getText(), Integer.valueOf(employeeNumberInput.getText()),
					lastNameInput.getText(), String.valueOf(workLocationInput.getSelectedItem()),
					Integer.valueOf(annualSalaryInput.getText()), fullTimeEmployeeButton.isSelected(), getGender(), "Employee");
			
			
			
			
				
				emp.setPassword(new char [] {'n', 'u',  'l', 'l'});
			
				
			
				if (theUserController.createUserPass(emp) == true) {

					JOptionPane.showMessageDialog(this, "Sucess : Employee added");
					//clearFields();
					
				} else {
					JOptionPane.showMessageDialog(this, "An error occured, contact administrator");
					
				} 
			} else {
				JOptionPane.showMessageDialog(this, "Fields cannot be empty");
			}
			
			
			
		}
		
		clearFields();
			
		}
	

	}


