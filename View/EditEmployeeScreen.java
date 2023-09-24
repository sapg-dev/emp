package View;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Controller.UserController;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EditEmployeeScreen extends JPanel {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	/*
	 * Some variables declared here do not have the "Private" access modifier
	 * because they are needed in another package this however, does not defy the
	 * MVD architecture, as it is not the data the user input, but rather the data
	 * fromm a JTable. Consequently, the MVC architecture is not violated at its
	 * core purpose.
	 */
	public UserController theUserController = new UserController();
	public static DefaultTableModel model = new DefaultTableModel();

	private javax.swing.JButton ConductSearchButton;
	private javax.swing.JLabel EmployeeNameLabel;
	public javax.swing.JTextField EmployeeNumberInputField;
	private javax.swing.JLabel EmployeeNumberLabel;
	private javax.swing.JLabel FindEmployeeLabel;
	public javax.swing.JTextField FirstNameInputField;
	public javax.swing.JTextField LastNameInputField;
	private javax.swing.JLabel WelcomeLabel;
	public javax.swing.JTextField annualSalaryInput;
	private javax.swing.JLabel annualSalaryLabel;
	private javax.swing.JLabel employeeGenderLabel;
	private javax.swing.ButtonGroup employeeTypeButtonGroup;
	private javax.swing.JLabel employeeWorkLocationLabel;
	public javax.swing.JRadioButton femaleRadioButton;
	private javax.swing.JPanel fullTimeEmployeePanel;
	public javax.swing.JRadioButton fullTimeEmployeeRadioButton;
	private javax.swing.ButtonGroup genderButtonGroup;
	public javax.swing.JTextField hourlyWageInput;
	private javax.swing.JLabel hourlyWageLabel;
	public javax.swing.JTextField hoursPerWeekInput;
	private javax.swing.JLabel hoursPerWeekLabel;
	public javax.swing.JRadioButton maleRadioButton;
	private javax.swing.JPanel partTimeEmployeePanel;
	public javax.swing.JRadioButton partTimeEmployeeRadioButton;
	private javax.swing.JTextField weeksPerYearInput;
	private javax.swing.JLabel weeksPerYearLabel;
	public javax.swing.JTextField workLocationInput;

	public EditEmployeeScreen(UserController theUserController) {
		this.theUserController = theUserController;

		initializeGUI();

		fullTimeEmployeePanel.setVisible(false);
		partTimeEmployeePanel.setVisible(false);
	}

	private void initializeGUI() {
		setPreferredSize(new Dimension(805, 523));
		this.setBackground(new Color(248, 248, 255));

		employeeTypeButtonGroup = new javax.swing.ButtonGroup();
		genderButtonGroup = new javax.swing.ButtonGroup();
		FindEmployeeLabel = new javax.swing.JLabel();
		FindEmployeeLabel.setBounds(43, 25, 432, 29);
		WelcomeLabel = new javax.swing.JLabel();
		WelcomeLabel.setBounds(43, 64, 317, 17);
		partTimeEmployeeRadioButton = new javax.swing.JRadioButton();
		partTimeEmployeeRadioButton.setBounds(171, 232, 123, 23);
		fullTimeEmployeeRadioButton = new javax.swing.JRadioButton();
		fullTimeEmployeeRadioButton.setBounds(538, 231, 119, 23);
		EmployeeNumberLabel = new javax.swing.JLabel();
		EmployeeNumberLabel.setBounds(43, 91, 117, 17);
		EmployeeNumberInputField = new javax.swing.JTextField();
		EmployeeNumberInputField.setEnabled(false);
		EmployeeNumberInputField.setBounds(217, 82, 538, 29);
		EmployeeNameLabel = new javax.swing.JLabel();
		EmployeeNameLabel.setBounds(43, 116, 117, 17);
		FirstNameInputField = new javax.swing.JTextField();
		FirstNameInputField.setBounds(215, 116, 260, 23);
		LastNameInputField = new javax.swing.JTextField();
		LastNameInputField.setBounds(493, 115, 260, 24);
		ConductSearchButton = new javax.swing.JButton();
		ConductSearchButton.setBackground(new Color(30, 144, 255));
		ConductSearchButton.setBounds(326, 406, 201, 64);
		employeeGenderLabel = new javax.swing.JLabel();
		employeeGenderLabel.setBounds(43, 141, 117, 17);
		employeeWorkLocationLabel = new javax.swing.JLabel();
		employeeWorkLocationLabel.setBounds(43, 170, 156, 17);
		maleRadioButton = new javax.swing.JRadioButton();
		maleRadioButton.setBounds(212, 140, 148, 21);
		femaleRadioButton = new javax.swing.JRadioButton();
		femaleRadioButton.setBounds(451, 140, 140, 21);
		fullTimeEmployeePanel = new javax.swing.JPanel();
		fullTimeEmployeePanel.setBounds(471, 268, 318, 95);
		annualSalaryLabel = new javax.swing.JLabel();
		annualSalaryInput = new javax.swing.JTextField();
		partTimeEmployeePanel = new javax.swing.JPanel();
		partTimeEmployeePanel.setBounds(54, 257, 381, 106);
		hourlyWageLabel = new javax.swing.JLabel();
		hoursPerWeekLabel = new javax.swing.JLabel();
		weeksPerYearLabel = new javax.swing.JLabel();
		hourlyWageInput = new javax.swing.JTextField();
		hoursPerWeekInput = new javax.swing.JTextField();
		weeksPerYearInput = new javax.swing.JTextField();
		workLocationInput = new javax.swing.JTextField();
		workLocationInput.setBounds(217, 165, 536, 29);
		FindEmployeeLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
		FindEmployeeLabel.setText("Edit an Employee");
		WelcomeLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		WelcomeLabel.setText("Change the employee's information ");
		employeeTypeButtonGroup.add(partTimeEmployeeRadioButton);
		partTimeEmployeeRadioButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
		partTimeEmployeeRadioButton.setText("Part Time Employee");
		employeeTypeButtonGroup.add(fullTimeEmployeeRadioButton);
		fullTimeEmployeeRadioButton.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
		fullTimeEmployeeRadioButton.setText("Full Time Employee");
		fullTimeEmployeeRadioButton.setToolTipText("");
		EmployeeNumberLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		EmployeeNumberLabel.setText("Employee Number:");
		EmployeeNameLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		EmployeeNameLabel.setText("Employee Name:");
		ConductSearchButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		ConductSearchButton.setText("Save Changes");
		employeeGenderLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		employeeGenderLabel.setText("Employee Gender:");
		employeeWorkLocationLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		employeeWorkLocationLabel.setText("Employee Work Location: ");
		genderButtonGroup.add(maleRadioButton);
		maleRadioButton.setText("Male");
		genderButtonGroup.add(femaleRadioButton);
		femaleRadioButton.setText("Female");
		annualSalaryLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		annualSalaryLabel.setText("Annual Salary:");
		hourlyWageLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		hourlyWageLabel.setText("Hourly Wage:");

		hoursPerWeekLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		hoursPerWeekLabel.setText("Hours Per Week:");

		weeksPerYearLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		weeksPerYearLabel.setText("Weeks Per Year");

		setMinimumSize(new Dimension(900, 600));

		setSize(new Dimension(823, 549));

		ImageIcon image = new ImageIcon("Data\\ProgramPictures\\floppy-disk.png");// <a
																					// href="https://www.flaticon.com/free-icons/shift"
																					// title="shift icons">Shift icons
																					// created by Flat Icons -
																					// Flaticon</a>
		int width = 50;
		int height = 50;
		Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		ConductSearchButton.setIcon((scaledIcon));

		partTimeEmployeeRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				partTimeEmployeeRadioButtonActionPerformed(evt);
			}
		});

		fullTimeEmployeeRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fullTimeEmployeeRadioButtonActionPerformed(evt);
			}
		});
		EmployeeNumberInputField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EmployeeNumberInputFieldActionPerformed(evt);
			}
		});

		FirstNameInputField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				FirstNameInputFieldActionPerformed(evt);
			}
		});

		LastNameInputField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LastNameInputFieldActionPerformed(evt);
			}
		});

		ConductSearchButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ConductSearchButtonActionPerformed(evt);
			}
		});

		maleRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				maleRadioButtonActionPerformed(evt);
			}
		});

		hourlyWageInput.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				hourlyWageInputActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout fullTimeEmployeePanelLayout = new javax.swing.GroupLayout(fullTimeEmployeePanel);
		fullTimeEmployeePanel.setLayout(fullTimeEmployeePanelLayout);
		fullTimeEmployeePanelLayout.setHorizontalGroup(fullTimeEmployeePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(fullTimeEmployeePanelLayout.createSequentialGroup().addContainerGap()
						.addComponent(annualSalaryLabel).addGap(10, 10, 10)
						.addComponent(annualSalaryInput, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
						.addContainerGap()));
		fullTimeEmployeePanelLayout.setVerticalGroup(fullTimeEmployeePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(fullTimeEmployeePanelLayout.createSequentialGroup().addGap(19, 19, 19)
						.addGroup(fullTimeEmployeePanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(annualSalaryInput, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(annualSalaryLabel))
						.addContainerGap(57, Short.MAX_VALUE)));

		javax.swing.GroupLayout partTimeEmployeePanelLayout = new javax.swing.GroupLayout(partTimeEmployeePanel);
		partTimeEmployeePanel.setLayout(partTimeEmployeePanelLayout);
		partTimeEmployeePanelLayout.setHorizontalGroup(
				partTimeEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(partTimeEmployeePanelLayout.createSequentialGroup().addContainerGap()
								.addGroup(partTimeEmployeePanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(partTimeEmployeePanelLayout.createSequentialGroup()
												.addComponent(hourlyWageLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(hourlyWageInput, javax.swing.GroupLayout.PREFERRED_SIZE,
														185, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(partTimeEmployeePanelLayout.createSequentialGroup()
												.addComponent(weeksPerYearLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(weeksPerYearInput, javax.swing.GroupLayout.PREFERRED_SIZE,
														185, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(partTimeEmployeePanelLayout.createSequentialGroup()
												.addComponent(hoursPerWeekLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40,
														Short.MAX_VALUE)
												.addComponent(hoursPerWeekInput, javax.swing.GroupLayout.PREFERRED_SIZE,
														185, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(46, Short.MAX_VALUE)));
		partTimeEmployeePanelLayout.setVerticalGroup(partTimeEmployeePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(partTimeEmployeePanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(partTimeEmployeePanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(hourlyWageLabel).addComponent(hourlyWageInput,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(partTimeEmployeePanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(hoursPerWeekLabel)
								.addComponent(hoursPerWeekInput, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(7, 7, 7)
						.addGroup(partTimeEmployeePanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(weeksPerYearLabel).addComponent(weeksPerYearInput,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(26, Short.MAX_VALUE)));
		this.setLayout(null);
		this.add(WelcomeLabel);
		this.add(employeeWorkLocationLabel);
		this.add(workLocationInput);
		this.add(EmployeeNameLabel);
		this.add(employeeGenderLabel);
		this.add(EmployeeNumberLabel);
		this.add(EmployeeNumberInputField);
		this.add(FirstNameInputField);
		this.add(LastNameInputField);
		this.add(femaleRadioButton);
		this.add(maleRadioButton);
		this.add(partTimeEmployeeRadioButton);
		this.add(fullTimeEmployeeRadioButton);
		this.add(FindEmployeeLabel);
		this.add(partTimeEmployeePanel);
		this.add(fullTimeEmployeePanel);
		this.add(ConductSearchButton);

		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theUserController.showSeeEmployees();
			}
		});
		Cancel.setBounds(0, 0, 89, 23);
		add(Cancel);

	}

	// </editor-fold>//GEN-END:initComponents

	private void partTimeEmployeeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_partTimeEmployeeRadioButtonActionPerformed
		// TODO add your handling code here:
		fullTimeEmployeePanel.setVisible(false);
		partTimeEmployeePanel.setVisible(true);
	}// GEN-LAST:event_partTimeEmployeeRadioButtonActionPerformed

	private void fullTimeEmployeeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_fullTimeEmployeeRadioButtonActionPerformed
		// TODO add your handling code here:
		partTimeEmployeePanel.setVisible(false);
		fullTimeEmployeePanel.setVisible(true);

	}// GEN-LAST:event_fullTimeEmployeeRadioButtonActionPerformed

	private void FirstNameInputFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_FirstNameInputFieldActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_FirstNameInputFieldActionPerformed

	private void LastNameInputFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_LastNameInputFieldActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_LastNameInputFieldActionPerformed

	private void maleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_maleRadioButtonActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_maleRadioButtonActionPerformed

	private void ConductSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			char gender = 'N';
			boolean fullTime = false;

			if (maleRadioButton.isSelected() == true) {
				gender = 'M';

			}
			if (femaleRadioButton.isSelected() == true) {
				gender = 'F';
			}

			if (fullTimeEmployeeRadioButton.isSelected()) {
				fullTime = true;
			}

			if (partTimeEmployeeRadioButton.isSelected() == true) {
				fullTime = false;

			}

			JOptionPane.showMessageDialog(EditEmployeeScreen.this, "Changes have been saved", "Succesful",
					JOptionPane.PLAIN_MESSAGE);

			if (theUserController.saveChangesView(EmployeeNumberInputField, FirstNameInputField, LastNameInputField,
					workLocationInput, annualSalaryInput, gender, fullTime) == true)
				JOptionPane.showMessageDialog(EditEmployeeScreen.this, "Changes have been saved", "Succesful",
						JOptionPane.PLAIN_MESSAGE);
			;

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(EditEmployeeScreen.this, "There was an error, please try again");
			ex.printStackTrace();
			System.out.println(ex);
		}
	}// GEN-LAST:event_ConductSearchButtonActionPerformed

	private void hourlyWageInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_hourlyWageInputActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_hourlyWageInputActionPerformed

	@SuppressWarnings("unused")
	private void seeTableButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_seeTableButtonActionPerformed
		// TODO add your handling code here:

	}// GEN-LAST:event_seeTableButtonActionPerformed

	private void EmployeeNumberInputFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_EmployeeNumberInputFieldActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_EmployeeNumberInputFieldActionPerformed
}