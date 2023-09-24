package View;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.DocumentException;

import Controller.UserController;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import javax.swing.JToggleButton;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class SeeEmployees extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	UserController theUserController;

	// public SeeEmployees sp = new SeeEmployees(theUserController);
	private JComboBox<Object> workLocationInput = new JComboBox<Object>();

	private javax.swing.JButton ConductSearchButton = new JButton();
	private javax.swing.JLabel EmployeeNameLabel = new JLabel();
	private javax.swing.JLabel FindEmployeeLabel = new JLabel();
	private javax.swing.JTextField FirstNameInputField = new JTextField();
	private javax.swing.JTextField LastNameInputField = new JTextField();
	private javax.swing.JButton RemoveEmployeeButton = new JButton();
	private javax.swing.JLabel WelcomeLabel = new JLabel();

	static javax.swing.JButton seeAllButton = new JButton();
	private JScrollPane scrollPane = new JScrollPane();
	int width = 30;
	int height = 30;
	String firstName;
	String lastName;

	public SeeEmployees(UserController theUserController) {

		this.theUserController = theUserController;

		initComponents();
	}

	private void initComponents() {

		setPreferredSize(new Dimension(1062, 686));
		theUserController.updateViewJTable(model, table);
		seeAllButton.doClick();


		FindEmployeeLabel = new javax.swing.JLabel();
		FindEmployeeLabel.setBounds(10, 34, 294, 29);
		WelcomeLabel = new javax.swing.JLabel();
		WelcomeLabel.setBounds(20, 62, 317, 17);
		EmployeeNameLabel = new javax.swing.JLabel();
		EmployeeNameLabel.setBounds(43, 116, 117, 17);
		FirstNameInputField = new javax.swing.JTextField();
		FirstNameInputField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				model.setRowCount(0);// GEN-FIRST:event_LastNameInputFieldActionPerformed
				theUserController.searchEmployee(model, FirstNameInputField.getText(), LastNameInputField.getText(),
						String.valueOf(workLocationInput.getSelectedItem()));
			}
		});
		FirstNameInputField.setBounds(215, 106, 260, 29);
		LastNameInputField = new javax.swing.JTextField();
		LastNameInputField.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				model.setRowCount(0);// GEN-FIRST:event_LastNameInputFieldActionPerformed
				theUserController.searchEmployee(model, FirstNameInputField.getText(), LastNameInputField.getText(),
						String.valueOf(workLocationInput.getSelectedItem()));
			}
		});
		LastNameInputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				model.setRowCount(0);// GEN-FIRST:event_LastNameInputFieldActionPerformed
				theUserController.searchEmployee(model, FirstNameInputField.getText(), LastNameInputField.getText(),
						String.valueOf(workLocationInput.getSelectedItem()));
			}
		});
		LastNameInputField.setBounds(215, 159, 260, 29);
		ConductSearchButton = new javax.swing.JButton();
		ConductSearchButton.setForeground(Color.BLACK);
		ConductSearchButton.setBackground(Color.GREEN);
		ConductSearchButton.setBounds(199, 571, 664, 104);
		seeAllButton = new javax.swing.JButton();

		ImageIcon imagere = new ImageIcon("Data\\ProgramPictures\\refresh.png"); // <a
																					// href="https://www.flaticon.com/free-icons/shift"
																					// title="shift icons">Shift icons
																					// created by Flat Icons -
																					// Flaticon</a>
		int widthre = 20;
		int heightre = 20;
		Image scaledImagere = imagere.getImage().getScaledInstance(widthre, heightre, Image.SCALE_SMOOTH);
		ImageIcon scaledIconre = new ImageIcon(scaledImagere);

		seeAllButton.setIcon((scaledIconre));
		seeAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theUserController.updateViewJTable(model, table);
			}
		});
		seeAllButton.setText("Refresh Table ");
		seeAllButton.setBounds(10, 206, 169, 35);
		RemoveEmployeeButton = new javax.swing.JButton();
		RemoveEmployeeButton.setBackground(Color.RED);
		RemoveEmployeeButton.setBounds(10, 571, 179, 104);
		FindEmployeeLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
		FindEmployeeLabel.setText("Find Employee");
		WelcomeLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		WelcomeLabel.setText("Please input some Employee Identification Information");
		EmployeeNameLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		EmployeeNameLabel.setText("Employee Name:");
		ConductSearchButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		ConductSearchButton.setText("Find Match");
		RemoveEmployeeButton.setText("Remove ");

		sortJTable();
		ImageIcon image = new ImageIcon("Data\\ProgramPictures\\employee.png");// <a
																				// href="https://www.flaticon.com/free-icons/shift"
																				// title="shift icons">Shift icons
																				// created by Flat Icons - Flaticon</a>
		Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		ConductSearchButton.setIcon((scaledIcon));

		setMinimumSize(new java.awt.Dimension(829, 659));

		setSize(new Dimension(1029, 659));

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

		ImageIcon removeImage = new ImageIcon("Data\\ProgramPictures\\remove.png");// <a
																					// href="https://www.flaticon.com/free-icons/shift"
																					// title="shift icons">Shift icons
																					// created by Flat Icons -
																					// Flaticon</a>
		Image removescaledImage = removeImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon removescaledIcon = new ImageIcon(removescaledImage);
		RemoveEmployeeButton.setIcon((removescaledIcon));

		RemoveEmployeeButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {

					RemoveEmployeeButtonActionPerformed(evt);

				} catch (SQLException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		this.setLayout(null);
		this.add(WelcomeLabel);
		this.add(EmployeeNameLabel);
		this.add(FirstNameInputField);
		this.add(LastNameInputField);
		this.add(FindEmployeeLabel);
		this.add(ConductSearchButton);
		this.add(RemoveEmployeeButton);
		this.add(seeAllButton);

		scrollPane = new JScrollPane();

		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// int a = (int) table.getValueAt(table.getSelectedRow(), 0);
				// System.out.println(a);
			}
		});

		JButton btnEditEmployee = new JButton();

		btnEditEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				theUserController.showEditEmployeeScreen((String) table.getValueAt(table.getSelectedRow(), 1),
						(String) String.valueOf(table.getValueAt(table.getSelectedRow(), 2)),
						(String) String.valueOf(table.getValueAt(table.getSelectedRow(), 3)),
						Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0))),
						((String) table.getValueAt(table.getSelectedRow(), 6)).toString(),
						Boolean.valueOf((boolean) table.getValueAt(table.getSelectedRow(), 5)),

						String.valueOf(table.getValueAt(table.getSelectedRow(), 4)),
						String.valueOf(table.getValueAt(table.getSelectedRow(), 4)),
						String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
			}

		});
		btnEditEmployee.setEnabled(false);

		scrollPane.setBounds(10, 242, 1042, 319);
		this.add(scrollPane);

		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);

		ImageIcon editImage = new ImageIcon("Data\\ProgramPictures\\settings.png");// <a
																					// href="https://www.flaticon.com/free-icons/shift"
																					// title="shift icons">Shift icons
																					// created by Flat Icons -
																					// Flaticon</a>
		Image editscaledImage = editImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon editscaledIcon = new ImageIcon(editscaledImage);
		btnEditEmployee.setIcon((editscaledIcon));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (table.getSelectionModel().isSelectionEmpty() == false) {
					btnEditEmployee.setEnabled(true);

				} else {
					btnEditEmployee.setEnabled(false);
				}

			}
		});
		scrollPane.setViewportView(table);

		btnEditEmployee.setText("Edit Employee");
		btnEditEmployee.setBounds(896, 171, 156, 61);
		this.add(btnEditEmployee);

		JButton btnNewButton_1 = new JButton("<---- back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				theUserController.back();

			}
		});
		btnNewButton_1.setBounds(0, 0, 89, 23);
		this.add(btnNewButton_1);

		JButton ExportPDF = new JButton("Export to PDF");
		ExportPDF.setBackground(new Color(0, 206, 209));

		ImageIcon pdfimage = new ImageIcon("Data\\ProgramPictures\\pdf.png");// <a
																				// href="https://www.flaticon.com/free-icons/shift"
																				// title="shift icons">Shift icons
																				// created by Flat Icons - Flaticon</a>
		int width = 30;
		int height = 30;
		Image pdfscaledImage = pdfimage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon pdfscaledIcon = new ImageIcon(pdfscaledImage);
		ExportPDF.setIcon((pdfscaledIcon));
		ExportPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getRowCount() != 0) {

					try {
						theUserController.generatePDFForEmmplotees(table);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					showMessage("Success : PDF generated in Data\\PDF");

				} else {
					JOptionPane.showMessageDialog(null,
							"There was an error in the generation of the PDF, try again. Click on See All Employees to refresh");

				}

			}
		});
		ExportPDF.setBounds(873, 572, 179, 104);
		this.add(ExportPDF);

		JLabel lblEmployeeLastName = new JLabel();
		lblEmployeeLastName.setText("Employee Last Name:");
		lblEmployeeLastName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmployeeLastName.setBounds(43, 166, 146, 17);
		add(lblEmployeeLastName);

	}

	public void showMessage(String message) {

		JOptionPane.showMessageDialog(this, message);

	}

	private void FirstNameInputFieldActionPerformed(java.awt.event.ActionEvent evt) {

		// GEN-FIRST:event_FirstNameInputFieldActionPerformed

	}

	private void LastNameInputFieldActionPerformed(java.awt.event.ActionEvent evt) {
		model.setRowCount(0);// GEN-FIRST:event_LastNameInputFieldActionPerformed
		theUserController.searchEmployee(model, FirstNameInputField.getText(), LastNameInputField.getText(),
				String.valueOf(workLocationInput.getSelectedItem()));
	}

	private void ConductSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			theUserController.searchEmployee(model, FirstNameInputField.getText(), LastNameInputField.getText(),
					String.valueOf(workLocationInput.getSelectedItem()));
			clear();
			// PayEmployees a = new PayEmployees();

		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	public void clear() {
		FirstNameInputField.setText("");

		LastNameInputField.setText("");
		workLocationInput.setSelectedItem("");

	}

	private void RemoveEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
		if (JOptionPane.showConfirmDialog(SeeEmployees.this,
				"This action cannot be undone. Are you sure you would like to delete this employee permanently ?") == 0)
			theUserController.removeEmployee(table, model);

	}

	public JTable returnTable() {
		return table;

	}

	public DefaultTableModel returnModel() {

		return model;

	}

	public void sortJTable() {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		// Get the index of the column containing the employeeNumber values
		int employeeNumberColumnIndex = table.getColumnModel().getColumnIndex("Employee Number");

		// Set the sorter to sort the rows based on the employeeNumber column
		sorter.setComparator(employeeNumberColumnIndex, (Comparator<?>) new Comparator<String>() {

			public int compare(String s1, String s2) {
				Integer i1 = Integer.parseInt(s1);
				Integer i2 = Integer.parseInt(s2);
				return i1.compareTo(i2);
			}
		});
		sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(employeeNumberColumnIndex, SortOrder.ASCENDING)));
	}
}