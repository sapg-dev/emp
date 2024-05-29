package Model;

import java.util.Comparator;

public class EmployeeNumberComparator implements Comparator<Employee> {
	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.getEmployeeNumber() - e2.getEmployeeNumber();

	}
}
