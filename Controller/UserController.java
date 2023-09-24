package Controller;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.DocumentException;

import Model.EmployeeList;
import Model.ShiftTableModel;
import Model.DAO;
import Model.Employee;

import View.*;

/*The UserController class is responsible for acting as a mediator between the View and the Model in the Employee Management System. 
 * It enables communication between the interface that the user interacts with (the View) and the data and functionality of the application (the Model). 
 * The UserController class is responsible for showing different screens of the interface, such as the login screen, home screen, and screens for adding and editing employees. 
 * The class also communicates with the DAO (Data Access Object) class to perform actions such as adding, editing, and deleting employees, as well as searching for and displaying employees in tables.
 *  By having a separate class for controlling communication between the View and the Model, it improves the structure and organization of the code. 
 *  Additionally, this separation of concerns makes it easier to make changes to the application in the future since changes made to one part of the code will not affect the other.
 */

public class UserController {


	private LoginFrame loginFrame;
	private MainFrame homeScreen;
	private AddEmployeeScreen addEmployeeScreen;
	private SeeEmployees seeEmployeesScreen;
	private EditEmployeeScreen editEmployeeScreen;
	private ShiftGenerator shiftGenerator;
	public UnavailableEmployees unavailableEmployeeScreen;
	public PreferedEmployees preferedEmployeesScreen;
	private PayEmployees payEmployeesScreen;
	public JFrame screen;

	public EmployeeList employeeList = new EmployeeList();
	private IO theIO = new IO();
	private DAO theDAO;

	public void setUp() {
		System.out.println("test");
		List<Employee> a = theIO.readEmployeesFromFile();
		
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
			employeeList.addToList(a.get(i));}
		try {UI.uiDesign();} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| UnsupportedLookAndFeelException e) {}

		theDAO = new DAO(this.employeeList);
		theIO = new IO();
		loginFrame = new LoginFrame(this);
		homeScreen = new MainFrame(this);
		addEmployeeScreen = new AddEmployeeScreen(this);
		seeEmployeesScreen = new SeeEmployees(this);
		editEmployeeScreen = new EditEmployeeScreen(this);
		shiftGenerator = new ShiftGenerator(this);
		unavailableEmployeeScreen = new UnavailableEmployees(this);
		preferedEmployeesScreen = new PreferedEmployees(this);
		payEmployeesScreen = new PayEmployees(this);
		screen = new JFrame("Employee Management System");
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showScreen(loginFrame);

	}

	public void showScreen(Object screen) {

		this.screen.getContentPane().removeAll();
		this.screen.getContentPane().add((Component) screen);
		this.screen.pack();
		this.screen.setResizable(false);
		this.screen.setVisible(true);

	}

	public void showAddEmployeeScreen() {
		showScreen(addEmployeeScreen);

	}

	public void showEditEmployeeScreen(String name, String lastName, String location, int number, String gender,
			boolean role, String salary, String hourly, String hoursPWeek) {

		showScreen(editEmployeeScreen);
		editEmployeeScreen.FirstNameInputField.setText(name);
		editEmployeeScreen.LastNameInputField.setText(lastName);
		editEmployeeScreen.workLocationInput.setText(location);
		editEmployeeScreen.FirstNameInputField.setText(name);
		editEmployeeScreen.EmployeeNumberInputField.setText(String.valueOf(number));

		if (gender.equals("M")) {
			editEmployeeScreen.maleRadioButton.setSelected(true);

		} else {
			if (gender.equals("F")) {

				editEmployeeScreen.femaleRadioButton.setSelected(true);

			}
		}

		if (role == true) {
			editEmployeeScreen.fullTimeEmployeeRadioButton.setSelected(true);
			editEmployeeScreen.fullTimeEmployeeRadioButton.doClick();
			editEmployeeScreen.annualSalaryInput.setText(salary);

		}

		if (role == false) {
			editEmployeeScreen.partTimeEmployeeRadioButton.setSelected(true);
			editEmployeeScreen.partTimeEmployeeRadioButton.doClick();
			editEmployeeScreen.hourlyWageInput.setText(hourly);
			editEmployeeScreen.hoursPerWeekInput.setText(hoursPWeek);
		}

	}

	public void showHomeScreen() {
		showScreen(homeScreen);

	}

	public void showSeeEmployees() {
		showScreen(seeEmployeesScreen);

	}

	public void ShowshiftGenerator() {
		showScreen(shiftGenerator);

	}

	public void ShowpreferedEmployeesScreen() {
		showScreen(preferedEmployeesScreen);

	}

	public void ShowunavailableEmployeeScreen() {
		showScreen(unavailableEmployeeScreen);

	}

	public void showPayEmployees() {

		showScreen(payEmployeesScreen);

	}

	public void back() {
		showScreen(homeScreen);
	}

	public boolean createUserPass(Employee theUser) {

		List<Employee> employees = theIO.readEmployeesFromFile();

		String encryptedPassword = theDAO.encryptPass(new String(theUser.getPassword()));
		theUser.setPassword(encryptedPassword.toCharArray());
		employees.add(theUser);
		theIO.writeEmployeesToFile(employees);
		return theDAO.createUser(theUser);
	}

	public boolean encryptLogin(Employee theUser) throws Exception {

		String encryptedPassword = theDAO.encryptPass(new String(theUser.getPassword()));
		theUser.setPassword(encryptedPassword.toCharArray());

		return this.theDAO.getUser(theUser);

	}

	public void removeEmployee(JTable table, DefaultTableModel model) {
		try {

			theDAO.deleteEmployee(table, model);

		} catch (Exception ex) {

			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);
		}
	}

	public void searchEmployee(DefaultTableModel model, String name, String lastName, String workLocation) {

		try {

			theDAO.searchUser(model, name, lastName, workLocation);
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);
		}
	}

	public void updateViewJTable(DefaultTableModel model, JTable table) {

		try {

			theDAO.update_JTable_with_DB_Data(model, table);
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);
		}
	}

	public boolean saveChangesView(JTextField EmployeeNumberInputField, JTextField FirstNameInputField,
			JTextField LastNameInputField, JTextField workLocationInput, JTextField annualSalaryInput, char gender,
			boolean type) {
		try {

			if (theDAO.save_changes_of_employee(EmployeeNumberInputField, FirstNameInputField, LastNameInputField,
					workLocationInput, annualSalaryInput, gender, type))
				return true;

		} catch (Exception ex) {

			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);
		}

		return false;
	}

	public void salariesJTable(DefaultTableModel model, JTable table) {

		theDAO.salariesJTable(model, table);
	}

	public void generateSalariesPDF(int empNumberRows, DateFormat dateFormat, Calendar cal,
			int employeeNumberToSearchAndReturn, int upper_bound, int upper_bound_percentage, int lower_bound,
			int lower_bound_percentage, String currency) {

		theDAO.generatePaySlipPDF(empNumberRows, dateFormat, cal, employeeNumberToSearchAndReturn, upper_bound,
				upper_bound_percentage, lower_bound, lower_bound_percentage, currency);
	}

	public int generateEmployeeNumber() {
		return theDAO.generateEmployeeNumber();
	}

	public JTable controller_generateShift(int numShifts, int numDays, int minEmployeesPerShift,
			int maxEmployeesPerShift, int[] preferredEmployees, int[] unavailableEmployees, JTable table) {

		int[][][] shifts = DAO.generateShifts(employeeList.getCount(), numShifts, numDays, minEmployeesPerShift,
				maxEmployeesPerShift, preferredEmployees, unavailableEmployees);
		ShiftTableModel.displayShifts(shifts, numDays, numShifts, table);

		return table;
	}

	public String[][] returnData(int numDays, int numShifts, int minEmployeesPerShift, int maxEmployeesPerShift,
			int[] preferredEmployees, int[] unavailableEmployees) {
		return ShiftTableModel.returnData(numDays, numShifts, DAO.generateShifts(employeeList.getCount(), numShifts,
				numDays, minEmployeesPerShift, maxEmployeesPerShift, preferredEmployees, unavailableEmployees));

	}

	public String[] returnColumnName(int numShifts, int numDays) {
		return ShiftTableModel.returnColumnNames(numShifts, numDays);
	}

	public void generatePDFForEmmplotees(JTable table) throws DocumentException, FileNotFoundException {
		theDAO.generatePDFForAllEmployees(table);
	} public static void main(String[] args) {
		UserController user = new UserController();
		user.setUp();
		
	}

}
