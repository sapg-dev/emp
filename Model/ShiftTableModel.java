package Model;

import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ShiftTableModel extends AbstractTableModel {

	/**
	 * 
	 * This class was inspired by :Stack Overflow. (n.d.). java - Automatically adjust Jtable Column to fit content. [online] Available at: https://stackoverflow.com/questions/17858132/automatically-adjust-jtable-column-to-fit-content
	 * Algorithmic thinking done bu a
	 */
	private static final long serialVersionUID = 1L;
	private int[][][] shifts;

	public ShiftTableModel(int[][][] shifts) {
		this.shifts = shifts;
	}

	@Override
	public int getRowCount() {
		return shifts.length;
	}

	@Override
	public int getColumnCount() {
		return shifts[0].length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return "Day " + (rowIndex + 1);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int employee : shifts[rowIndex][columnIndex - 1]) {
				sb.append(employee).append(" ");
			}
			return sb.toString();
		}
	}

	@Override
	public String getColumnName(int column) {
		return "Shift " + (column + 1);
	}

	public static JTable displayShifts(int[][][] shifts, int numDays, int numShifts, JTable table) {
		// Create a 2D array to hold the shift data
		String[][] data = new String[numDays][numShifts + 1];

		// Populate the data array with the shift information
		for (int i = 0; i < numDays; i++) {
			data[i][0] = "Day " + (i + 1);
			for (int j = 0; j < numShifts; j++) {
				StringBuilder sb = new StringBuilder();
				for (int employee : shifts[i][j]) {
					sb.append(employee).append(",");
				}
				data[i][j + 1] = sb.toString();
			}
		}
		int[] arra = new int[numShifts];
		for (int i = 1; i < numShifts; i++) {
			arra[i] = i;

		}
		Arrays.toString(arra);

		String[] arr = new String[numShifts];
		arr[0] = "Day";
		for (int j = 1; j < arra.length; j++) {
			arr[j] = String.valueOf(arra[j]);
		}

		// Create a table to display the shift data
		table = new JTable(data, arr);

		return table;

	}

	public static String[][] returnData(int numDays, int numShifts, int shifts[][][]) {

		String[][] data = new String[numDays][numShifts + 1];

		// Populate the data array with the shift information
		for (int i = 0; i < numDays; i++) {
			data[i][0] = "Day " + (i + 1);
			for (int j = 0; j < numShifts; j++) {
				StringBuilder sb = new StringBuilder();
				for (int employee : shifts[i][j]) {
					sb.append(employee).append(",");
				}
				data[i][j + 1] = sb.toString();

			}
		}

		return data;
	}

	public static String[] returnColumnNames(int numShifts, int numDays) {

		int[] arra = new int[numShifts];
		for (int i = 1; i < numShifts; i++) {
			arra[i] = i;

		}

		String[] arr = new String[numShifts];
		arr[0] = "Day";
		for (int j = 1; j < arra.length; j++) {
			arr[j] = String.valueOf(arra[j]);
		}

		return arr;
	}

}
