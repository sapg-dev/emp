

package Controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Model.Employee;
import Model.EmployeeList;


/**
@author __redacted__


*/

public class IO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void createDirectory() {

		File[] directory = { new File("Data"), new File("Data/PDF"), new File("Data/Ser"),
				new File("Data/Ser/User.ser"), new File("Data/Ser/Employees.ser"), new File("Data/Ser/Salaries.ser") };

		for (File element : directory) {
			if (!element.exists()) {
				if (element.getName().contains(".")) {
					try {
						FileOutputStream file = new FileOutputStream(element);
						ObjectOutputStream output = new ObjectOutputStream(file);

						output.close();
						file.close();

					} catch (Exception ex) {
						
						Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
					}
				} else {
					makeDir(element);
				}
			}
		}

	}

	private static void makeDir(File file) {
		if (!file.exists()) {
			try {
				file.mkdir();
			} catch (Exception e) {
				Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	}

	public void deleteEmployee(Employee emp, EmployeeList list) {

		List<Employee> employees = readEmployeesFromFile();

		// Find the employee to delete
		Employee employeeToDelete = null;
		for (Employee employee : employees) {
			if (employee.getEmployeeNumber() == emp.getEmployeeNumber()) {
				employeeToDelete = employee;
				break;
			}
		}

		// Remove the employee from the list

		employees.remove(employeeToDelete);
		list.remove(list.index_Of_Employee_In_EmployeeList((employeeToDelete.getEmployeeNumber()), list));

		// Write the updated list of employees back to the file
		writeEmployeesToFile(employees);
	}

	@SuppressWarnings("unchecked")

	public List<Employee> readEmployeesFromFile() {
		List<Employee> employees = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream("Data\\Ser\\Employees.ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			employees = (List<Employee>) ois.readObject();
			for (int i = 0; i < employees.size(); i++) {
				System.out.println(employees.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	public void writeEmployeesToFile(List<Employee> employees) {
		try (FileOutputStream fos = new FileOutputStream("Data\\Ser\\Employees.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(employees);
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void generatePDF(int numDays, int rowCount, int columnCount, DefaultTableModel model) {

		Document document = new Document();

		try {

			// Set the output file and open the document
			PdfWriter.getInstance(document, new FileOutputStream("Data\\PDF\\employees_data.pdf"));
			document.open();
			// document.addHeader("Employees", "Automatic Shift Tool");

			// Add a table to the document with 2 columns
			PdfPTable pdfTable = new PdfPTable(numDays);

			// Add the table headers

			for (int j = 0; j < numDays; j++) {
				pdfTable.addCell("Day" + (j + 1));

			}

			// Add the employee names and salaries from the JTable to the PDF table
			for (int i = 0; i < rowCount; i++) {
				for (int z = 0; z < columnCount; z++) {

					pdfTable.addCell(String.valueOf(model.getValueAt(i, z)));

				}
			}

			// Add the PDF table to the document
			document.add(pdfTable);

		} catch (Exception ex) {
			Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			// Close the document
			document.close();
		}

	}

	public static void generatePDFShift(int[][][] shifts, int numDays, int numShifts, int maxEmployeesPerShift)
			throws FileNotFoundException, DocumentException {

		Document document = new Document(PageSize.A4);
		document.setMargins(20, 20, 20, 20);
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayAsString = today.format(formatter);
		String dateFormat = today.format(formatter);
		Paragraph date = new Paragraph("Generated on: " + dateFormat.formatted(new Date()));
		date.setAlignment(Element.ALIGN_CENTER);
		document.add(date);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("shifts" + todayAsString + ".pdf"));
		document.open();

		PdfPTable table = new PdfPTable(numShifts);

		for (int i = 0; i < numDays; i++) {
			for (int j = 0; j < numShifts; j++) {
				for (int k = 0; k < maxEmployeesPerShift; k++) {
					table.addCell(String.valueOf(shifts[i][j][k]));

				}
			}
		}

		document.add(table);
		document.close();

	}

	public void generateShiftPDF(JTable table) {
		// Set page size and margins
		Document document = new Document();
		document.setPageSize(PageSize.A3);
		document.setMargins(20, 20, 20, 20);

		try {
			// Create PdfWriter
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Data\\PDF\\Shifts.pdf"));

			// Open document
			document.open();

			// Create PdfPTable

			Paragraph title = new Paragraph("Shift Generation");
			title.setAlignment(Element.ALIGN_CENTER);

			document.add(title);

			// Add date to document
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Paragraph date = new Paragraph("Generated on: " + dateFormat.format(new Date()));

			date.setAlignment(Element.ALIGN_CENTER);
			document.add(date);
			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

			// Iterate over the rows and cells of the JTable and add them to the PdfPTable
			for (int i = 0; i < table.getRowCount(); i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
					pdfTable.addCell(table.getValueAt(i, j).toString());
				}
			}

			// Add PdfPTable to document
			pdfTable.setSpacingBefore(10f);
			document.add(pdfTable);
			pdfTable.setSpacingBefore(10f);
			// Close document
			document.close();

			// Close PdfWriter
			writer.close();
		} catch (DocumentException | IOException e) {
			Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
