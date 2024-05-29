package Model;

/*
This class is called EmployeeNode and represents a single node in a linked list of Employee objects. 
Each EmployeeNode contains an Employee object and a reference to the next EmployeeNode in the list.
*/


/*
 * Having these methods in the EmployeeNode class instead of directly accessing
 * the next field from the EmployeeList class provides several advantages,
 * including:
 * 
 * Encapsulation: The setNextEmployeeNode method allows us to change the
 * reference to the next EmployeeNode in a controlled and safe manner, as we can
 * add additional logic or checks before setting the next field.
 * 
 * Abstraction: By providing the getNextEmployeeNode method, we can abstract
 * away the implementation details of the EmployeeList class, making it easier
 * to use and understand. This means that we can change the internal
 * implementation of the EmployeeList class without affecting the rest of the
 * program.
 * 
 * Code maintainability: By encapsulating the next field and providing these
 * methods, we can ensure that the code is easier to maintain and debug, since
 * we can ensure that the next EmployeeNode is always accessed in a consistent
 * and correct manner.
 * 
 * Encourages good coding practices: By using these methods, we can ensure that
 * the EmployeeList class follows good coding practices, such as encapsulation
 * and abstraction, which makes the code more readable, understandable, and
 * maintainable
 */


public class EmployeeNode {

	/* The class has the following fields: */
	Employee employee; // This is an instance of the Employee class that will be stored in the node
	EmployeeNode nextEmployee; // This is a reference to the next EmployeeNode in the linked list

	/*
	 * The class has the following constructor:
	 */
	EmployeeNode(Employee employeeToBeAdded) { // Takes an Employee object as a parameter
		employee = employeeToBeAdded; // Sets the Employee object field to the parameter
		nextEmployee = null; // Sets the next EmployeeNode reference to null
	}

	public void setNextEmployeeNode(EmployeeNode nextNode) { // Sets the next EmployeeNode reference to the provided
																// EmployeeNode parameter
		this.nextEmployee = nextNode; // Assigns the parameter to the next EmployeeNode field
	}

	public EmployeeNode getNextEmployeeNode() { // Returns the next EmployeeNode in the linked list
		return nextEmployee; // Returns the next EmployeeNode field
	}

	public Employee get_Employee_Node_Data() { // Returns the Employee object stored in the node
		return employee; // Returns the Employee object field
	}
}

