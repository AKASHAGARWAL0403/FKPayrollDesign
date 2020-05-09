package EmployeeTypes.Models;

import Payment.PaymentTypes;

public class FlatSalaryEmployee extends Employee{
    public FlatSalaryEmployee(String name, String address,
                              String contactNo, Integer age,
                              boolean unionMember, Double unionDueRate,
                              EmployeeType employeeType,
                              PaymentTypes paymentTypes) {
        super(name, address, contactNo, age, unionMember, unionDueRate, employeeType, paymentTypes);
    }
}
