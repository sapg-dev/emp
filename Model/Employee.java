package Model;

import java.io.Serializable;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4942384993089307570L;

	private String firstName;
	private int employeeNumber;
	private String lastName;
	private String workLocation;
	private int annualSalary;
	private boolean fullTime;
	private char gender;
	private String role;
	private char[] password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public int getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(int annualSalary) {
		this.annualSalary = annualSalary;
	}

	public boolean isFullTime() {
		return fullTime;
	}

	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	/*
	 * Polymorphism : Here polymorphism was used to ease the development of this
	 * program. Occasionlly, dummy employees had to be created with the aim of using
	 * the recursive method "returnEmployeeFromList" from the EmployeeList class,
	 * which would return a full and "tangible" employee with all the variables such
	 * as the name and last name present, with whom calculations and their data is
	 * used. We can see the use of this constructor for example in the
	 * deleteEmployee() method used in the DAO, in which we use the constructor to
	 * delete an employee. This is incredibly useful, as no "dummy" values have to
	 * be input in order for a "dummy" employee to be generated, and we can
	 * efficiently and quickly create an employee wihtout the need for the normal
	 * constructor.
	 */
	public Employee(String firstName, int employeeNumber, String lastName, String workLocation, int annualSalary,
			boolean fullTime, char gender, String role) {
		this.firstName = firstName;
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.workLocation = workLocation;
		this.annualSalary = annualSalary;
		this.fullTime = fullTime;
		this.gender = gender;
		this.role = role;

	}

	public Employee(int employeeNumber) {
		this.employeeNumber = employeeNumber;

	}

}