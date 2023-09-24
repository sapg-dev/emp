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

public class PreferedEmployees extends JPanel {

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
	static int[] preferedEmployees;

	public PreferedEmployees(UserController theUserController) {

		this.theUserController = theUserController;

		initComponents();
	}

	private void initComponents() {
		setPreferredSize(new Dimension(550, 531));

		theUserController.updateViewJTable(model, table);

		FindEmployeeLabel = new javax.swing.JLabel();
		FindEmployeeLabel.setBounds(10, 34, 293, 29);
		WelcomeLabel = new javax.swing.JLabel();
		WelcomeLabel.setBounds(20, 61, 520, 17);
		FindEmployeeLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
		FindEmployeeLabel.setText("Prefered Employees");
		WelcomeLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
		WelcomeLabel.setText("Choose specific employees you would like to see in the generated shifts");

		setSize(new Dimension(578, 571));

		this.setLayout(null);
		this.add(WelcomeLabel);
		this.add(FindEmployeeLabel);

		scrollPane = new JScrollPane();

		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// int a = (int) table.getValueAt(table.getSelectedRow(), 0);
				// System.out.println(a);
			}
		});

		scrollPane.setBounds(20, 100, 491, 300);
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

		btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ShiftGenerator.arrayToPut = getPrefferedEmployees();
				JOptionPane.showMessageDialog(theUserController.screen, "Preferred Employees added : Success")

				;
			}
		});
		btnNewButton_2.setBackground(Color.GREEN);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBounds(147, 459, 231, 23);
		this.add(btnNewButton_2);

	}

	public int[] getPrefferedEmployees() {

		int[] selectedRows = table.getSelectedRows();
		preferedEmployees = new int[selectedRows.length];

		for (int i = 0; i < selectedRows.length; i++) {

			preferedEmployees[i] = Integer.valueOf(((String) table.getValueAt(selectedRows[i], 0)));

		}

		return preferedEmployees;
	}

}
