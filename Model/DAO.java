package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Controller.IO;
import Controller.UserController;

public class DAO {

	private static Connection theConnection;
	private IO theIO = new IO();
	UserController theUserController = new UserController();
	EmployeeList list;

	public DAO(EmployeeList list) {
		this.list = list;
		DAO.theConnection = Database.getDB().getConnection();
	}

	/*
	 * The generateShifts method is a Java function that generates a schedule for
	 * shifts based on certain inputs. The method takes in several parameters,
	 * including the number of employees, number of shifts, number of days, and
	 * minimum and maximum number of employees per shift, as well as arrays
	 * representing preferred and unavailable employees. The method begins by
	 * creating an empty three-dimensional array to store the shifts. It then
	 * creates a list of employee numbers and randomly assigns employees to shifts
	 * for each day and shift. It does this by first creating a copy of the list of
	 * employee numbers and then determining the number of employees needed for each
	 * shift by generating a random number between the minimum and maximum number of
	 * employees per shift. The method then creates a list of eligible employees for
	 * the shift by checking which employees are available and preferred. It assigns
	 * eligible employees to the shift until the required number of employees is
	 * reached. If there are not enough eligible employees, it randomly assigns
	 * available employees to the shift until the required number is reached. To
	 * determine if an employee is unavailable or preferred, the method uses two
	 * helper functions: isUnavailable and isPreferred. The method uses Java's
	 * ArrayList class to store and manipulate the lists of employee numbers and
	 * eligible employees. It also uses the Random class to generate random numbers.
	 * The method uses a for-loop to iterate over the days and shifts and to assign
	 * employees to shifts. By using a for-loop instead of a while-loop, the code
	 * becomes more readable and less prone to errors. In terms of memory usage, the
	 * method creates a three-dimensional array to store the shifts, which can
	 * consume a significant amount of memory depending on the number of days,
	 * shifts, and employees. The use of ArrayLists can also increase memory usage
	 * compared to arrays, as ArrayLists require more overhead. However, the use of
	 * ArrayLists provides more flexibility and ease of use compared to arrays, as
	 * ArrayLists can dynamically grow and shrink in size as needed. Overall, the
	 * generateShifts method provides a simple and effective way to generate
	 * schedules for shifts in Java.
	 */

	public static int[][][] generateShifts(int numEmployees, int numShifts, int numDays, int minEmployeesPerShift,
			int maxEmployeesPerShift, int[] preferredEmployees, int[] unavailableEmployees) {
		// Create an empty 3D array to store the shifts
		int[][][] shifts = new int[numDays][numShifts][maxEmployeesPerShift];

		// Create a list of employee numbers
		ArrayList<Integer> employeeNumbers = new ArrayList<>();
		ArrayList<Integer> eligibleEmployees = new ArrayList<>();
		Random random = new Random();

		for (int i = 1; i <= numEmployees; i++) {
			employeeNumbers.add(i);
		}

		// Randomly assign employees to shifts

		for (int i = 0; i < numDays; i++) {
			// Create a copy of the employee numbers list for the current day
			ArrayList<Integer> availableEmployeeNumbers = new ArrayList<>(employeeNumbers);

			for (int j = 0; j < numShifts; j++) {
				// Determine the number of employees needed for the shift
				int numEmployeesNeeded = random.nextInt(maxEmployeesPerShift - minEmployeesPerShift + 1)
						+ minEmployeesPerShift;

				for (int k = 0; k < availableEmployeeNumbers.size(); k++) {

					int employeeNumber = availableEmployeeNumbers.get(k);
					if (!isUnavailable(employeeNumber, unavailableEmployees, i)
							&& isPreferred(employeeNumber, preferredEmployees)) {
						eligibleEmployees.add(employeeNumber);
					}
				}

				// Assign the eligible employees to the shift
				int employeeIndex = 0;
				for (int k = 0; k < numEmployeesNeeded && k < eligibleEmployees.size(); k++) {
					int employeeNumber = eligibleEmployees.get(random.nextInt(eligibleEmployees.size()));

					// Assign the employee to the shift
					shifts[i][j][k] = employeeNumber;
					employeeIndex++;
				}

				// If there aren't enough eligible employees, fill the remaining spots with any
				// available employee
				for (int k = employeeIndex; k < numEmployeesNeeded && k < availableEmployeeNumbers.size(); k++) {
					int employeeNumber = availableEmployeeNumbers.get(random.nextInt(availableEmployeeNumbers.size()));

					// Assign the employee to the shift
					shifts[i][j][k] = employeeNumber;
				}
			}

			/*
			 * Fisher-Yates algorithm from
			 * :https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-
			 * shuffle-algorithm/
			 */

			int n = employeeNumbers.size(); // Get the size of the list

			// Iterate over the list in reverse order
			for (int k = n - 1; k > 0; k--) {
				int j = random.nextInt(k + 1); // Generate a random integer between 0 and i (inclusive)
				// Swap the elements at index i and j
				int temp = employeeNumbers.get(k);
				employeeNumbers.set(i, employeeNumbers.get(j));
				employeeNumbers.set(j, temp);
			}

		}

		// Return the generated shifts
		return shifts;
	}

	/*
	 * This section is dedicated to the creation, retrieval and deletion of
	 * employees/users, so it involves the creation of SHA-256 passwords, writing in
	 * the database new users, fetching users based on user specified data etc...
	 */

	public boolean createUser(Employee theUser) {
		try {
			PreparedStatement theStatement = theConnection
					.prepareStatement("insert into employee_info (employeeNumber, firstName, lastName, workLocation, "
							+ "" + "annualSalary, fullTime, gender, role, password ) values (?,?, ?, ?, ?,?, ?,?,?)");

			theStatement.setString(2, theUser.getFirstName());
			theStatement.setInt(1, theUser.getEmployeeNumber());
			theStatement.setString(3, theUser.getLastName());
			theStatement.setString(4, theUser.getWorkLocation());
			theStatement.setInt(5, theUser.getAnnualSalary());
			theStatement.setBoolean(6, theUser.isFullTime());
			theStatement.setString(7, String.valueOf(theUser.getGender()));
			theStatement.setString(8, theUser.getRole());
			theStatement.setString(9, String.valueOf(theUser.getPassword()));

			theStatement.execute();

			theUserController.employeeList.addToList(theUser);

			return true;

		} catch (Exception ex) {

			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occurred while creating a new user", ex);
			return false;

		}
	}

	public boolean getUser(Employee theUser) throws Exception {

		try {

			PreparedStatement theStatement = theConnection
					.prepareStatement("select * from employee_info where firstName = ? and password = ?");
			theStatement.setString(1, theUser.getFirstName());
			theStatement.setString(2, new String(theUser.getPassword()));
			ResultSet queryResult = theStatement.executeQuery();

			if (queryResult.next()) {

				return true;

			} else {

				return false;
			}
		} catch (Exception ex) {

			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occurred while creating a new user", ex);

			return false;
		}

	}

	public void deleteEmployee(JTable table, DefaultTableModel model) {

		try {

			Statement theStatement = theConnection.createStatement();

			int row = table.getSelectedRow();
			String cell = table.getModel().getValueAt(row, 0).toString();

			Employee deleted = new Employee(Integer.valueOf(cell));
			/* Deleting data from serialized file */

			theIO.deleteEmployee(deleted, this.list);

			/* Deleting data from serialized database */
			theStatement.executeUpdate("DELETE FROM employee_info where employeeNumber=" + cell);

			if (row != -1) {

				// remove selected row from the model

				model.removeRow(table.getSelectedRow());

			}

			JOptionPane.showMessageDialog(theUserController.screen, "Success");

		} catch (Exception ex) {

			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occurred while contacting the DB", ex);

			JOptionPane.showMessageDialog(theUserController.screen, "An error occured in contacting the database");

		}
	}

	/*
	 * The searchUser method takes in a DefaultTableModel object, along with three
	 * String parameters: name, lastName, and workLocation. It searches for
	 * employees in a MySQL database based on these parameters and adds the results
	 * to the given DefaultTableModel.
	 * 
	 * First, the method clears the existing rows in the model by setting its row
	 * count to zero.
	 * 
	 * Next, the method initializes a ResultSet and a PreparedStatement to null.
	 * 
	 * The method then attempts to connect to a MySQL database using the
	 * DriverManager class and the connection details provided. The connection
	 * details include the database URL, the username, and the password.
	 * 
	 * Depending on the values of the name and lastName parameters, the method sets
	 * up a SQL query to search for employees in the employee_info table. If the
	 * lastName parameter is empty, the query searches for employees whose first
	 * name matches the name parameter. If the name parameter is empty, the query
	 * searches for employees whose last name matches the lastName parameter. If
	 * both parameters have values, the query searches for employees whose first
	 * name matches the name parameter and whose last name matches the lastName
	 * parameter.
	 * 
	 * The prepared statement is then executed, and the resulting ResultSet is
	 * stored in the rs variable.
	 * 
	 * The method then loops through the rows of the ResultSet using a while loop,
	 * adding each row to the DefaultTableModel by calling the addRow method on it.
	 * The addRow method takes an array of objects, with each object representing a
	 * cell in the row.
	 * 
	 * After the loop completes, the ResultSet and PreparedStatement are closed,
	 * along with the database connection.
	 * 
	 * If any exception occurs during the method execution, a message dialog is
	 * shown to display the error message, and a java.util.logging.Logger object
	 * logs the error message along with the exception stack trace.
	 */
	public void searchUser(DefaultTableModel model, String name, String lastName, String workLocation) {

		model.setRowCount(0);

		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");

			if (lastName.isEmpty()) {
				ps = theConnection.prepareStatement("SELECT * FROM employee_info WHERE firstName LIKE ?");
				ps.setString(1, name + "%");
			} else if (name.isEmpty()) {
				ps = theConnection.prepareStatement("SELECT * FROM employee_info WHERE lastName LIKE ?");
				ps.setString(1, lastName + "%");
			} else {
				ps = theConnection
						.prepareStatement("SELECT * FROM employee_info WHERE firstName LIKE ? AND lastName LIKE ?");
				ps.setString(1, name + "%");
				ps.setString(2, lastName + "%");
			}

			rs = ps.executeQuery();

			while (rs.next()) {

				model.addRow(new Object[] { rs.getString("employeeNumber"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("workLocation"), rs.getString("annualSalary"),
						rs.getBoolean("fullTime"), rs.getString("gender"), rs.getString("role"),

				});

			}

			rs.close();
			ps.close();
			con.close();

		}

		catch (Exception ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occurred while searching a user", ex);

		}

	}

	// This method updates the JTable with data from the database.
	public void update_JTable_with_DB_Data(DefaultTableModel model, JTable table) {

		// Clear the existing rows in the table model
		model.setRowCount(0);

		// If the columns don't exist, create them and set the table model
		if (model.findColumn("Employee Number") == -1) {
			model.addColumn("Employee Number");
			model.addColumn("First Name");
			model.addColumn("Last Name");
			model.addColumn("Shop");
			model.addColumn("Annual Salary");
			model.addColumn("Is fulltime ?");
			model.addColumn("Gender");
			model.addColumn("Role in the company");

			table.setModel(model);
		}

		try {
			// Select all the rows from the employee_info table
			String query = "SELECT * FROM employee_info";

			// Create a statement object to execute the query
			Statement statement = theConnection.createStatement();

			// Execute the query and get the result set
			ResultSet resultSet = statement.executeQuery(query);

			// Loop through each row in the result set
			while (resultSet.next()) {
				// Add the row data to the table model
				model.addRow(new Object[] { resultSet.getString("employeeNumber"), resultSet.getString("firstName"),
						resultSet.getString("lastName"), resultSet.getString("workLocation"),
						resultSet.getString("annualSalary"), resultSet.getBoolean("fullTime"),
						resultSet.getString("gender"), resultSet.getString("role") });
			}

			// Close the result set and statement objects
			resultSet.close();
			statement.close();

		} catch (Exception ex) {
			// If an error occurs, log the error
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "An error occurred", ex);
		}
	}

	/*
	 * The update_JTable_with_DB_Data method takes in a DefaultTableModel object and
	 * a JTable object. It updates the JTable with data from a MySQL database based
	 * on a SQL query.
	 * 
	 * First, the method clears the existing rows in the model by setting its row
	 * count to zero.
	 * 
	 * Next, the method checks if the columns exist in the table. If they don't
	 * exist, the method adds the columns to the table model with the respective
	 * column names. Then, it sets the table model to the given JTable object.
	 * 
	 * The method then tries to execute a SQL query to select all rows from the
	 * employee_info table.
	 * 
	 * A Statement object is created to execute the query. The query is executed
	 * using the executeQuery method of the Statement object, and the resulting
	 * ResultSet object is stored in the resultSet variable.
	 * 
	 * The method then loops through the rows of the ResultSet using a while loop,
	 * adding each row to the DefaultTableModel by calling the addRow method on it.
	 * The addRow method takes an array of objects, with each object representing a
	 * cell in the row.
	 * 
	 * After the loop completes, the ResultSet and Statement objects are closed.
	 * 
	 * If any exception occurs during the method execution, a
	 * java.util.logging.Logger object logs the error message along with the
	 * exception stack trace.
	 * 
	 */
	public boolean save_changes_of_employee(JTextField EmployeeNumberInputField, JTextField FirstNameInputField,
			JTextField LastNameInputField, JTextField workLocationInput, JTextField annualSalaryInput, char gender,
			boolean type) {

		try {

			Connection theConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");
			PreparedStatement theStatement = theConnection.prepareStatement(
					"update  employee_info SET firstName = ?, lastName =? , workLocation = ?, annualSalary =?,  gender = ?, fullTime = ?  where employeeNumber = "
							+ EmployeeNumberInputField.getText());
			theStatement.setString(1, FirstNameInputField.getText());
			theStatement.setString(2, LastNameInputField.getText());
			theStatement.setString(3, workLocationInput.getText());
			theStatement.setInt(4, Integer.valueOf(annualSalaryInput.getText()));

			theStatement.setString(5, String.valueOf(gender));
			theStatement.setBoolean(6, type);
			if (theStatement.execute() == true)
				return true;

		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);

		}
		return false;

	}

	public void salariesJTable(DefaultTableModel model, JTable table) {
		model.setRowCount(0);

		if (model.findColumn("Employee Number") == -1) {

			model.addColumn("Employee Number");
			model.addColumn("First Name");
			model.addColumn("Last Name");
			model.addColumn("Shop");
			model.addColumn("Annual Salary");
			model.addColumn("Full Time");
			model.addColumn("Gender");
			model.addColumn("Role");

			table.setModel(model);
		} else {

		}

		try {

			String query = "SELECT * FROM employee_info";
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				model.addRow(new Object[] {

						rs.getString("employeeNumber"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("workLocation"), rs.getString("annualSalary"), rs.getBoolean("fullTime"),
						rs.getString("gender"),

						rs.getString("role"),

				});
			}

			rs.close();
			st.close();
			con.close();

			// table.getColumnModel().getColumn(0).setPreferredWidth(40);

		} catch (Exception ex) {

			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);

		}
	}

	/*
	 * This method takes a plain text password as input and returns its SHA-256
	 * encrypted version as a string. SHA-256 is a cryptographic hashing algorithm
	 * that takes an input and produces a fixed-size output, which is a unique
	 * representation of the input.
	 * 
	 * First, the method retrieves an instance of the MessageDigest class, which is
	 * used to perform the SHA-256 encryption. The password is then converted to
	 * bytes using the UTF-16LE character encoding and passed to the MessageDigest
	 * instance to perform the encryption. The resulting encrypted hash is then
	 * represented as a string of hexadecimal digits using the StringBuilder class.
	 * 
	 * The for loop iterates through each byte of the encrypted hash, converting it
	 * to a two-digit hexadecimal string representation. If the hexadecimal string
	 * has only one digit, a "0" is added in front to ensure that each byte is
	 * represented by two digits. The resulting string of hexadecimal digits is
	 * returned as the encrypted value of the password.
	 * 
	 * If any exception occurs during the encryption process, the method logs the
	 * error message and returns the error message as a string.
	 */

	public String encryptPass(String password) { /*
													 * Reference :Stack Overflow. (n.d.). cryptography - How to hash
													 * some String with SHA-256 in Java? [online] Available at:
													 * https://stackoverflow.com/questions/5531455/how-to-hash-some-
													 * string-with-sha-256-in-java .
													 */
		try {

			// retrieve instance of the encryptor of SHA-256
			MessageDigest digestor = MessageDigest.getInstance("SHA-256");
			// retrieve bytes to encrypt
			byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_16LE));
			StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);
			// perform encryption
			for (byte element : encodedhash) {
				String hexVal = Integer.toHexString(0xff & element);
				if (hexVal.length() == 1) {
					encryptionValue.append('0');
				}
				encryptionValue.append(hexVal);
			}
			// return encrypted value
			return encryptionValue.toString();
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);
			return ex.getMessage();
		}
	}

	/*
	 * The generatePaySlipPDF method is used to generate pay slips in PDF format for
	 * a given employee. It takes in several parameters including the number of
	 * times to loop, the date format to use, a calendar object, upper and lower
	 * bounds for pay calculations, and a currency string. Within the method, a for
	 * loop is used to iterate through the number of times specified by the
	 * rowCountSubstitute parameter. On each iteration, an Employee object is
	 * retrieved for the specified employee number, and the exportToPDFForPay method
	 * is called to create a PDF pay slip for that employee using the specified
	 * parameters. Any errors that occur during execution are logged using the
	 * Logger class. Overall, this method provides a convenient way to generate pay
	 * slips for multiple employees using a standardized format and parameters.
	 */
	public void generatePaySlipPDF(int rowCountSubstitute, DateFormat dateFormat, Calendar cal,
			int employeeNumberToSearchAndReturn, int upper_bound, int upper_bound_percentage, int lower_bound,
			int lower_bound_percentage, String currency) {

		try {

			for (int i = 0; i < rowCountSubstitute; i++) {

				Employee employeeInTable = list.returnEmployeeFromList(list.head,
						new Employee(employeeNumberToSearchAndReturn));

				exportToPDFForPay(dateFormat.format(cal.getTime()), employeeInTable, dateFormat, cal, upper_bound,
						upper_bound_percentage, lower_bound, lower_bound_percentage, currency);

			}

		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);
		}

	}

	/*
	 * The method "exportToPDFForPay" generates a pay slip for a specific employee
	 * and exports it to a PDF file. The method takes in several input parameters
	 * such as the date, the employee object for whom the pay slip needs to be
	 * generated, upper and lower bounds for the tax rate, and the currency. The
	 * method calculates the tax rate based on the employee's annual salary and the
	 * given upper and lower bounds for the tax rate. The generated pay slip
	 * includes information such as the employer's name and location, the employee's
	 * full name, the base income, tax rate imposed, total deductions, and total
	 * amount. The method uses the iText library to create a PDF file and sets
	 * various attributes for formatting the pay slip.
	 */

	public void exportToPDFForPay(String date, Employee generatingPaySlipForThisEmployee, DateFormat dateFormat,
			Calendar cal, int upper_bound, int upper_bound_percentage, int lower_bound, int lower_bound_percentage,
			String currency) {

		int tax_rate_for_employee = 0;

		try {

			if (generatingPaySlipForThisEmployee.getAnnualSalary() > upper_bound) {
				tax_rate_for_employee = upper_bound_percentage;
			}

			if (generatingPaySlipForThisEmployee.getAnnualSalary() < upper_bound) {

				tax_rate_for_employee = lower_bound_percentage;

			}

			String path = "Data\\PDF\\PaySlips\\" + date + " " + generatingPaySlipForThisEmployee.getFirstName() + " "
					+ generatingPaySlipForThisEmployee.getLastName() + ".pdf";

			Font titleFont = new Font(FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.BLACK);
			Font bold = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
			Font normal = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
			Document document = new Document();

			PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			document.setMargins(5, 5, 1, 5);
			document.setPageSize(PageSize.A6);

			float[] columnWidths = { 150F, 150F, 150F };

			// dateFormat.format(cal.getTime()

			Paragraph test3 = new Paragraph("Date: " + dateFormat.format(cal.getTime()));
			test3.setAlignment(Element.ALIGN_RIGHT);

			Paragraph name1 = new Paragraph(
					"Employer : NG NEXT LTD " + " : " + generatingPaySlipForThisEmployee.getWorkLocation());
			name1.setFont(bold);
			Paragraph test = new Paragraph("Employee's Full Name : " + generatingPaySlipForThisEmployee.getFirstName()
					+ " " + generatingPaySlipForThisEmployee.getLastName());

			document.add(test3);
			document.add(name1);
			document.add(test);

			PdfPTable table = new PdfPTable(columnWidths);
			table.setWidthPercentage(110);

			table.addCell(new PdfPCell(new Phrase("Payments", bold)));
			table.addCell(new PdfPCell(new Phrase("Deductions", bold)));
			table.addCell(new PdfPCell(new Phrase("Total", bold)));
			table.addCell(new PdfPCell(new Phrase(
					"Base income : " + generatingPaySlipForThisEmployee.getAnnualSalary() + currency, normal)));
			table.addCell(new PdfPCell(new Phrase("Tax Rate imposed  : " + tax_rate_for_employee + "%", normal)));
			table.addCell(new PdfPCell(new Phrase("-", normal)));
			table.addCell(new PdfPCell(new Phrase("-", normal)));
			table.addCell(new PdfPCell(new Phrase("Total Deductions : "
					+ generatingPaySlipForThisEmployee.getAnnualSalary() * (tax_rate_for_employee) / 100 + currency,
					normal)));
			table.addCell(new PdfPCell(new Phrase("-", normal)));
			table.addCell(
					new PdfPCell(new Phrase("-------------------------------------------------------- ", normal)));
			table.addCell(
					new PdfPCell(new Phrase("-------------------------------------------------------- ", normal)));
			table.addCell(new PdfPCell(new Phrase("Total Amount: " + " "
					+ (generatingPaySlipForThisEmployee.getAnnualSalary()
							- (generatingPaySlipForThisEmployee.getAnnualSalary() * (tax_rate_for_employee) / 100))
					+ currency, bold)));

			Paragraph title = new Paragraph("Payment Information Sheet ");
			title.setFont(titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(5);
			title.setSpacingBefore(100);
			document.add(title);

			table.setWidthPercentage(110);
			document.add(table);
			document.close();

		} catch (Exception e) {

			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	public int generateEmployeeNumber() {

		if (this.list.getCount() != 0) {

			return list.getCount() + 1;
			

		} else {
			return 1;

		}
	}

	// method for the generateShift() method
	private static boolean isPreferred(int employeeNumber, int[] preferredEmployees) {
		for (int preferredEmployee : preferredEmployees) {
			if (employeeNumber == preferredEmployee) {
				return true;
			}
		}
		return false;
	}

	// method for the generateShift() method
	private static boolean isUnavailable(int employeeNumber, int[] unavailableEmployees, int day) {
		for (int i = 0; i < unavailableEmployees.length; i++) {
			if (employeeNumber == unavailableEmployees[i] && i == day) {
				return true;
			}
		}
		return false;
	}

	public void generatePDFForAllEmployees(JTable table)  {
		Document document = new Document();
		document.setPageSize(PageSize.A2);
		try {
			// Check if the file with the name "employees.pdf" already exists
			File file = new File("Data\\PDF\\EmployeePDF\\employees.pdf");
			String filename = file.getName();
			String filePath = file.getParent();
			Pattern pattern = Pattern.compile("(.*)(\\.)(.*)");
			Matcher matcher = pattern.matcher(filename);
			String fileNameWithoutExt = filename;
			String fileExt = "";
			if (matcher.matches()) {
				fileNameWithoutExt = matcher.group(1);
				fileExt = matcher.group(3);
			}
			int count = 1;
			while (file.exists()) {
				filename = fileNameWithoutExt + "(" + count + ")" + "." + fileExt;
				file = new File(filePath, filename);
				count++;
			}

			// Create a PdfWriter with the new filename
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			// Add a table to the document with 8 columns
			PdfPTable pdfTable = new PdfPTable(8);

			// Add the table headers
			pdfTable.addCell("Employee Number");
			pdfTable.addCell("First Name");
			pdfTable.addCell("Last Name");
			pdfTable.addCell("Work Location");
			pdfTable.addCell("Annual Salary");
			pdfTable.addCell("Gender");
			pdfTable.addCell("Role");
			pdfTable.addCell("Fulltime");
			pdfTable.setWidthPercentage(100);

			// Add the employee names and salaries from the JTable to the PDF table
			for (int i = 0; i < table.getRowCount(); i++) {
				String employeeNumber = String.valueOf(table.getValueAt(i, 0));
				pdfTable.addCell(String.valueOf(employeeNumber));
				String firstname = (String) table.getValueAt(i, 1);
				pdfTable.addCell(String.valueOf(firstname));
				String lastName = (String) table.getValueAt(i, 2);
				pdfTable.addCell(String.valueOf(lastName));
				String workLocation = (String) table.getValueAt(i, 3);
				pdfTable.addCell(String.valueOf(workLocation));
				String annualSalary = (String) table.getValueAt(i, 4);
				pdfTable.addCell(String.valueOf(annualSalary));
				String fullTime = String.valueOf(table.getValueAt(i, 5));
				pdfTable.addCell(String.valueOf(fullTime));
				String gender = (String) table.getValueAt(i, 6);
				pdfTable.addCell(String.valueOf(gender));
				String role = (String) table.getValueAt(i, 7);
				pdfTable.addCell(String.valueOf(role));
			}

			// Add the PDF table to the document
			document.add(pdfTable);
		} catch (Exception ex) {

			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured", ex);
		}

	}

}