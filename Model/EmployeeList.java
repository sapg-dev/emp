package Model;

import java.util.LinkedList;

/* Inheritance: Inheritance was used here for very specific purposes: 
 * 
 * 1)   Code reuse: Inheriting the LinkedList class allows you to reuse the existing implementation of linked list operations 
 *       such as insertion, deletion, and traversal, saving you the time and effort of writing these operations from scratch.
 *        
 * 2)  Improved efficiency: The LinkedList class is implemented in Java and is optimized for performance, so your custom linked list 
 *      class can benefit from these optimizations.
 *       
 * 3)  Extension purposes:  Inheriting the LinkedList class  makes it easier to integrate with other Java APIs that expect a 
 * 		LinkedList object as input, which will allow for future developers of this system not to have to redesign a large portion of the code
 * 
 * 4) Simplicity: Inheriting the LinkedList class can make it easier to implement a custom linked list class, especially 
 *         because I did not need to customize the basic linked list operations.
 * 
 */

public class EmployeeList extends LinkedList<Employee>

{
	public EmployeeNode head;
	private static final long serialVersionUID = 1L;

	/*
	 * This algorithm can easily be extended to allow for more sophisticated linked
	 * list operations. For example, it could be modified to insert a new node at a
	 * specific position in the list or to remove a node from the list. To do this,
	 * the algorithm would need to be updated to take in additional arguments, such
	 * as the position or node to insert or remove, and to handle these cases
	 * accordingly. The simplicity and modularity of this algorithm make it easy to
	 * adapt to a variety of use cases.
	 */

	/**
	 * 
	 * Adds an Employee object to the end of a linked list.
	 * 
	 * @param employee the Employee object to be added to the linked list
	 * 
	 */

	public void addToList(Employee employee) {
		// Create a new node with the employee information
		EmployeeNode newEmployee = new EmployeeNode(employee);

		// If the list is empty, set the head to the new node and return
		if (head == null) {
			head = newEmployee;
			return;
		}

		// Traverse the list to find the last node
		EmployeeNode lastNode = head;
		while (lastNode.getNextEmployeeNode() != null) {
			lastNode = lastNode.getNextEmployeeNode();
		}

		// Set the next pointer of the last node to the new node
		lastNode.setNextEmployeeNode(newEmployee);
	}

	/**
	 * 
	 * @param head of the linked list
	 * 
	 */
	public int getCountRec(EmployeeNode head) {

		// Base case
		if (head == null)
			return 0;

		// Count is this EmployeeNode plus rest of the list
		return 1 + getCountRec(head.getNextEmployeeNode());
	}

	public int getCount() {

		return getCountRec(head);
	}

	/*
	 * This custom method could not be achieved with the help of Java's linked list
	 * library. As the purpose of this method is very specific and not just
	 * searching an object, it had to be made custom. This method is quite simple
	 * and works recursively. It takes the head of a list as a parameter for
	 * recursive purposes and takes Employee x as a parameter also. Employee X is
	 * the employee we are looking for in an instance of EmployeeList. Comparatively
	 * to the default "search" method of the linked list; as every employee has a
	 * unique employee number, we can identify them using only their employee
	 * number, which is what a normal search algorithm would have done. This is
	 * extremely useful as it makes searching for an employee much faster, rather
	 * than comparing every variable of the employee we are looking for to the
	 * employee currently in the node, we can simply compare a primitive type of
	 * ints. This makes the program much more efficient.
	 */

	/**
	 * 
	 * @param head     of the linked list
	 * @param employee to search in the linked list
	 * 
	 */
	public boolean searchEmployee(EmployeeNode head, Employee employeeToSearch) {
		// Start at the head of the linked list
		EmployeeNode current = head;

		// Base case: if the current node is null, the employee is not found in the list
		if (current == null) {
			// Return false to indicate that the employee was not found
			return false;
		}

		// Check if the current node's employee matches the employee being searched for
		if (head.employee.getEmployeeNumber() == employeeToSearch.getEmployeeNumber()) {
			// If the employees match, return true to indicate that the employee was found
			return true;
		}

		// Recursive case: continue searching for the employee in the next node of the
		// linked list
		return searchEmployee(head.getNextEmployeeNode(), employeeToSearch);
	}

	public Employee returnEmployeeFromList(EmployeeNode head, Employee employee_To_Return) {
		// Start at the head of the linked list
		EmployeeNode current = head;

		// Base case: if the current node is null, the employee is not found in the list
		if (current == null) {
			// Return null to indicate that the employee was not found
			return null;
		}

		// Check if the current node's employee matches the employee being searched for
		if (head.get_Employee_Node_Data().getEmployeeNumber() == employee_To_Return.getEmployeeNumber()) {
			// If the employees match, return the employee object
			return head.get_Employee_Node_Data();
		}

		// Recursive case: continue searching for the employee in the next node of the
		// linked list
		return returnEmployeeFromList(head.getNextEmployeeNode(), employee_To_Return);
	}

	public EmployeeNode deleteEmployee(EmployeeNode head, int EmployeeNodeNumber) {

		if (head == null) // If linked list is empty, no operation can be done
			return null;

		if (EmployeeNodeNumber == 0) // if the index passed in the parameter is the first EmployeeNode(head), no
										// operations can be done
			return head;

		if (EmployeeNodeNumber == 1) // if index is 1
		{
			EmployeeNode employeeEmployeeNode = head.getNextEmployeeNode();
			return employeeEmployeeNode;
		}

		head.setNextEmployeeNode(deleteEmployee(head.getNextEmployeeNode(), EmployeeNodeNumber - 1));
		// EmployeeNode, and the index-1
		return head;
	}

	/*
	 * The method returns an integer value that represents the index of the first
	 * occurrence of the Employee object whose employee number matches the item
	 * argument. If there is no match, it returns -1.
	 */

	public int index_Of_Employee_In_EmployeeList(int index, EmployeeList employeeList) {

		for (int i = 0; i < employeeList.getCount(); i++) {
			if (employeeList.get(i).getEmployeeNumber() == index) {
				return i;
			}
		}
		return -1;
	}

}
