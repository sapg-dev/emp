package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.UserController;

public class UnavailableEmployees extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table = new JTable();
	private DefaultTableModel model = new DefaultTableModel();

	UserController theUserController;

	private javax.swing.JLabel FindEmployeeLabel;
	private javax.swing.JLabel WelcomeLabel;
	private JScrollPane scrollPane;

	private JButton btnNewButton_2;
	private int arrayToExclude[];

	public UnavailableEmployees(UserController theUserController) {

		this.theUserController = theUserController;

		initComponents();
	}

	private void initComponents() {

		setPreferredSize(new Dimension(540, 507));
		theUserController.updateViewJTable(model, table);

		new javax.swing.ButtonGroup();
		new javax.swing.ButtonGroup();
		FindEmployeeLabel = new javax.swing.JLabel();
		FindEmployeeLabel.setBounds(10, 34, 293, 29);
		WelcomeLabel = new javax.swing.JLabel();
		WelcomeLabel.setBounds(20, 62, 317, 17);
		FindEmployeeLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
		FindEmployeeLabel.setText("Exclude Employee");
		WelcomeLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		WelcomeLabel.setText("Choose employees to exclude from the shift generation");

		setSize(new Dimension(550, 580));

		this.setLayout(null);
		this.add(WelcomeLabel);
		this.add(FindEmployeeLabel);

		scrollPane = new JScrollPane();

	

		scrollPane.setBounds(20, 110, 491, 300);
		this.add(scrollPane);

		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);

		JButton btnNewButton_1 = new JButton("<----Save changes and go back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				theUserController.ShowshiftGenerator();

			}
		});
		btnNewButton_1.setBounds(0, 0, 261, 23);
		this.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Exclude ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ShiftGenerator.arrayToExclude = unavailableEmployees();
				JOptionPane.showMessageDialog(theUserController.screen, "Employee(s) have been excluded : Success");

			}
		});
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBounds(20, 421, 491, 23);
		this.add(btnNewButton_2);

	}

	public int[] unavailableEmployees() {

		int[] selectedRows = table.getSelectedRows();
		arrayToExclude = new int[selectedRows.length];

		for (int i = 0; i < selectedRows.length; i++) {

			arrayToExclude[i] = (Integer.valueOf((String) table.getValueAt(selectedRows[i], 0)));

		}

		return arrayToExclude;

	}

}
