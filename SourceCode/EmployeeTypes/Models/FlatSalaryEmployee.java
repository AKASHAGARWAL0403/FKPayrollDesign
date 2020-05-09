package EmployeeTypes.Models;

import Payment.PaymentTypes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FlatSalaryEmployee extends Employee{
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private Double salary;
    private String lastSalaryRecieved;

    public FlatSalaryEmployee(String name, String address,
                              String contactNo, Integer age,
                              boolean unionMember, Double unionDueRate,
                              EmployeeType employeeType,
                              PaymentTypes paymentTypes,Double commissionRate , Double salary) {
        super(name, address, contactNo, age, unionMember, unionDueRate, employeeType, paymentTypes,commissionRate);
        this.salary = salary;
        this.lastSalaryRecieved = dateFormatter.format(new Date());
    }

    public FlatSalaryEmployee(Double salary){
        super();
        this.salary = salary;
        this.lastSalaryRecieved = dateFormatter.format(new Date());
    }

    public Double getSalary() {
        return salary;
    }

    public String getLastSalaryRecieved() {
        return lastSalaryRecieved;
    }

    Double salaryPayable(){
        Integer daysDue;
        try {
            daysDue = getDaysDifference(new Date(), dateFormatter.parse(lastSalaryRecieved));
        }catch (Exception e){
            return -1D;
        }
        Double due = (salary / 30) * daysDue;
        return due;
    }

    Double paySalary(){
        Double newSalary = salaryPayable();
        this.lastSalaryRecieved = dateFormatter.format(new Date());
        return  newSalary;
    }

    public Integer getDaysDifference(Date a , Date b){
        long diff = a.getTime() - b.getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        return Integer.valueOf(diffDays);
    }
}
