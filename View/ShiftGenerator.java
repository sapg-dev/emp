package View;

import Controller.IO;
import Controller.UserController;
import Model.DAO;
import Model.Employee;
import Model.ShiftTableModel;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;


public class ShiftGenerator extends JPanel {
	
	
	
	
	/*Declaration of variables*/
	
	/**
	@author kct347

	*/

	private static final long serialVersionUID = 1L;
	public  JTable table = new JTable();
	public   DefaultTableModel model = new DefaultTableModel();


	
	private JTextField numberOfShifts;
	private JTextField numberOfDays;
	private IO inputOutput  = new IO();
	private JTextField minimumEmployees;
	private JTextField maximumEmployees;
	private IO theIO = new IO();
	
	UserController theUserController;
	 static int [] arrayToExclude;
	 static int [] arrayToPut;

	
	
	public ShiftGenerator(UserController theUserController) {
		setBackground(new Color(255, 255, 255));
		
		java.util.List<Employee> list =  theIO.readEmployeesFromFile();
		theUserController.employeeList.addAll(list);
		
		this.theUserController = theUserController;
		this.theUserController.employeeList = theUserController.employeeList;
		initComponents();
		
	}
	
	
	public void initComponents() {
		
		this.setBackground(new Color(248, 248, 255));
		/* This constructor is used for the GUI only */
		
		setLocation(0, -21);
		setPreferredSize(new Dimension(926, 444));
		this.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(290, 132, 626, 269);
		this.add(scrollPane);
		table.setToolTipText("");
		
	
		scrollPane.setViewportView(table);
		

		
	
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
						"Once generated the shfts will appear here"
        	}
        ));

        
        JLabel welcomelable = new JLabel("Employee Shift Automatic Tool");
        welcomelable.setFont(new Font("Tahoma", Font.PLAIN, 19));
        welcomelable.setBounds(293, 11, 323, 23);
        this.add(welcomelable);
        
        numberOfShifts = new JTextField();
        numberOfShifts.setBounds(177, 136, 86, 29);
        this.add(numberOfShifts);
        numberOfShifts.setColumns(10);
        
        numberOfDays = new JTextField();
        numberOfDays.setBounds(177, 166, 86, 29);
        this.add(numberOfDays);
        numberOfDays.setColumns(10);
        
        JButton generateShiftButton = new JButton("Generate Shift");
        generateShiftButton.setBackground(Color.GREEN);
        generateShiftButton.setForeground(Color.BLACK);
       
        generateShiftButton.setBounds(10, 344, 253, 57);
        this.add(generateShiftButton);
        
        JLabel lblNewLabel = new JLabel("Number of Shifts per Day :");
        lblNewLabel.setBounds(10, 143, 157, 14);
        this.add(lblNewLabel);
        
        JLabel lblNumberOfDaystypically = new JLabel("Number of Days(max 14):");
        lblNumberOfDaystypically.setBounds(10, 173, 157, 14);
        this.add(lblNumberOfDaystypically);
        
        JButton exportToPDFButton = new JButton("Export to PDF");
      
        exportToPDFButton.setBackground(Color.CYAN);
        exportToPDFButton.setForeground(Color.BLACK);
        exportToPDFButton.setBounds(10, 412, 253, 23);
        this.add(exportToPDFButton);
        
        JLabel lblNewLabel_1 = new JLabel("This tool allows you to generate shifts schedules for employees randomly");
        lblNewLabel_1.setBounds(259, 40, 745, 14);
        this.add(lblNewLabel_1);
        
        JButton backButton = new JButton("<----back");
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		theUserController.showHomeScreen();
        		
        	}
        });
        backButton.setBounds(0, 0, 89, 23);
        this.add(backButton);
        
        minimumEmployees = new JTextField();
        minimumEmployees.setColumns(10);
        minimumEmployees.setBounds(177, 197, 86, 29);
        this.add(minimumEmployees);
        
        JLabel lblMinimumEmployeesPer = new JLabel("Minimum Employees Shift");
        lblMinimumEmployeesPer.setBounds(10, 200, 157, 14);
        this.add(lblMinimumEmployeesPer);
        
        JLabel lblMaximumEmployeesPer = new JLabel("Maximum Employees  Shift:");
        lblMaximumEmployeesPer.setBounds(10, 225, 157, 31);
        this.add(lblMaximumEmployeesPer);
        
        maximumEmployees = new JTextField();
        maximumEmployees.setColumns(10);
        maximumEmployees.setBounds(177, 228, 86, 29);
        this.add(maximumEmployees);
        
        JLabel lblPreferredEmployees = new JLabel("Employees to Include: ");
        lblPreferredEmployees.setBounds(10, 267, 157, 14);
        this.add(lblPreferredEmployees);
        
        JLabel lblUnavailableEmployees = new JLabel("Unavailable Employees : ");
        lblUnavailableEmployees.setBounds(10, 301, 157, 14);
        this.add(lblUnavailableEmployees);
        
        JButton preferedEmmployeeButton = new JButton("Choose");
        preferedEmmployeeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		theUserController.ShowpreferedEmployeesScreen();
        	}
        });
        preferedEmmployeeButton.setBounds(174, 263, 89, 23);
        this.add(preferedEmmployeeButton);
        
        JButton unavailableEmployeesButton = new JButton("Choose");
        unavailableEmployeesButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	theUserController.ShowunavailableEmployeeScreen();
        	}
        });
        unavailableEmployeesButton.setBounds(174, 297, 89, 23);
        this.add(unavailableEmployeesButton);
        
        
        
       /* This section of the constructor is dedicated to all the eventListeners present in the window*/
        
        generateShiftButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		
        		if(Integer.valueOf(minimumEmployees.getText()) >= Integer.valueOf(maximumEmployees.getText())) {
        			JOptionPane.showMessageDialog(theUserController.screen, "Minimum amount of employees cannot be equal or higher than the maximum amount of employees");
        		} else {
        			
        		
        		
        		if(doArraysContainSimilarEmployee() == true) {
        			JOptionPane.showMessageDialog(theUserController.screen, "The exclusion list and preferred list have similar employees : Rectify before you can continue" );
        		} else {
        			
        		
        		
        		if(numberOfShifts.getText().isEmpty() == true || numberOfDays.getText().isEmpty() == true ) { // Checking for the validity of the data
        		JOptionPane.showMessageDialog(theUserController.screen, "The number of shifts and the number of days cannot be empty"); //Outputs a message in the case data hasn't been validated
        		}  else {	
        			
        			
        		model.setRowCount(0); //Resets the table blank
        		model.setColumnCount(0); //Resets the table blank
        		
        		
        		/* This method is slightly complex, it combines multiple methods together
        		 * Its purpose is to add the generated shift to the JTable, to make it easily readable to the user
        		 * To do so, we use the addGene...(Object [][] array) method that simply loops through all the rows in the array.
        		 * The array prior to being used by the addColumn method of the model object, is converted into an array of Strings, as the method only takes in objects
        		 * To do this the method transformShift...(int [][] array) is used by the UserController, and then the new transformed array passed down in the in the addColumn method
        		 
        		 */
        		

        		try {
        			
        		
        		
        		  int[][][] shifts = DAO.generateShifts(theUserController.employeeList.size(), Integer.valueOf(numberOfShifts.getText()), Integer.valueOf(numberOfDays.getText()),
        				  
        				  Integer.valueOf(minimumEmployees.getText()), Integer.valueOf(maximumEmployees.getText()), arrayToPut, arrayToExclude);
        	
        	       JTable table1 = 
        	    		   new JTable(ShiftTableModel.returnData(Integer.valueOf(numberOfDays.getText()), 
        	    				   Integer.valueOf(numberOfShifts.getText()), shifts ), 
        	    				   ShiftTableModel.returnColumnNames(Integer.valueOf(numberOfShifts.getText()), 
        	    						   Integer.valueOf(numberOfDays.getText())));
        	       table.setModel(table1.getModel());
        	       table.setPreferredScrollableViewportSize(table.getPreferredSize());
        	       
     //This is to adjust the size of the table to be adjusted to the generation of the shifts
        	       
        	       table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        	   
        	       for (int column = 0; column < table.getColumnCount(); column++)
        	       {
        	           TableColumn tableColumn = table.getColumnModel().getColumn(column);
        	           int preferredWidth = tableColumn.getMinWidth();
        	           int maxWidth = tableColumn.getMaxWidth();

        	           for (int row = 0; row < table.getRowCount(); row++)
        	           {
        	               TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
        	               Component c = table.prepareRenderer(cellRenderer, row, column);
        	               int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
        	               preferredWidth = Math.max(preferredWidth, width);

        	               //  We've exceeded the maximum width, no need to check other rows

        	               if (preferredWidth >= maxWidth)
        	               {
        	                   preferredWidth = maxWidth;
        	                   break;
        	               }
        	           }
        	           
        	       

        	           tableColumn.setPreferredWidth( preferredWidth );
        	           
        	           
        	           //This is to adjust the cell size based on its contents so all the employee number can be seen
        	           
        	           for(int i = 0; i < table.getColumnCount(); i++) {
        	        	   
        	           
        	           TableColumn column1 = table.getColumnModel().getColumn(i);

        	        // Get the preferred width of the column based on its contents
        	        int width = 0;
        	        for (int row = 0; row < table.getRowCount(); row++) {
        	          TableCellRenderer renderer = table.getCellRenderer(row, i);
        	          width = Math.max(width, renderer.getTableCellRendererComponent(table, table.getValueAt(row, i), false, false, row, i).getPreferredSize().width);
        	        }

        	        // Add some extra padding to the width
        	        width += 2 * table.getIntercellSpacing().width;

        	        // Set the column width to the preferred width
        	        column1.setPreferredWidth(width);
        	       
        	           } 
        	           
        	          
        		
        	       }
        	       
        	      } catch (Exception ex) {
        	        	   JOptionPane.showMessageDialog(theUserController.screen, "There was an error : cause :" + ex.getCause());
        	        	   
        	        	   
        	        	   }}}
        	           }
        	}});
        
        
        exportToPDFButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		inputOutput.generateShiftPDF(table);
        		
        	}
        });
     

		
	}
	


public boolean doArraysContainSimilarEmployee() {
	
	for(int i = 0; i<arrayToPut.length; i++) {
		for(int j = 0; j<arrayToExclude.length; j++) {
			
			if((arrayToPut[i] == arrayToExclude[j])) {
				System.out.println(arrayToPut[i]);
				System.out.println(i + "this is i");
				return true;
			}
			
		}

	}
	return false;
	
}
}



    



