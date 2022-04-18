package com.example;

import java.util.Objects;

public class StudyRecord {
	public static void main(String[] args) {
		var jack = new EmployeeRecord("1", "jack bauer", "tr1", 100_000);
		var kate = new EmployeeRecord();
		System.out.println(jack);
		jack = jack.increaseSalary(0.5);
		System.out.println(jack);
		System.out.println(kate);
	}
}

// immutable
final record EmployeeRecord(String identity, String fullName, String iban, double salary) {
	public EmployeeRecord() {
		this("2", "jack shephard", "tr2", 200_000); // Full Argument Constructor
	}

	public EmployeeRecord increaseSalary(double rate) {
		return new EmployeeRecord(this.identity(), this.fullName(), this.iban(), this.salary() * (1.0 + rate));
	}

	@Override
	public String toString() {
		return "EmployeeRecord [identity=" + identity + ", fullName=" + fullName + ", iban=" + iban + "]";
	}

}

final class Employee {
	private final String identity;
	private final String fullName;
	private final String iban;
	private final double salary;

	public Employee(String identity, String fullName, String iban, double salary) {
		this.identity = identity;
		this.fullName = fullName;
		this.iban = iban;
		this.salary = salary;
	}

	public String getIdentity() {
		return identity;
	}

	public String getFullName() {
		return fullName;
	}

	public String getIban() {
		return iban;
	}

	public double getSalary() {
		return salary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fullName, iban, identity, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(fullName, other.fullName) && Objects.equals(iban, other.iban)
				&& Objects.equals(identity, other.identity)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullName=" + fullName + ", iban=" + iban + ", salary=" + salary
				+ "]";
	}

}