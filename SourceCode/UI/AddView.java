package UI;

import Commission.Models.CommissionList;
import Controllers.EmployeeController;
import EmployeeTypes.Models.EmployeeType;
import Payment.PaymentTypes;
import Testing.DataChecker;

import java.util.Map;
import java.util.Scanner;

public class AddView {
    public static void StartWith(){
        Scanner sc = new Scanner(System.in);

        System.out.println("ADD EMPLOYEE");
        System.out.println("_______________________________");
        System.out.println("Enter Employee Name : ");
        String name = sc.nextLine();
        System.out.println("Enter Employee Address");
        String address = sc.nextLine();
        System.out.println("Enter Employee Age");
        String ageString = sc.nextLine();
        Long age = Long.parseLong(ageString);
        System.out.println("Enter Employee Contact Number");
        String contactNo = sc.nextLine();
        System.out.println("Is he a union Member(yes/no)");
        String unionMember = sc.nextLine();
        boolean isUnion;
        if(unionMember.equalsIgnoreCase("yes"))
            isUnion = true;
        else
            isUnion = false;
        Double unionDueRate = 0D;
        if(isUnion) {
            System.out.println("Enter the union Weekly Rate for employee");
            String unionRate = sc.nextLine();
            unionDueRate = Double.parseDouble(unionRate);
        }
        System.out.println("Enter the Employee Commission Rate");
        String commissionStr = sc.nextLine();
        Double commissionRate = Double.parseDouble(commissionStr);
        System.out.println("Enter the Employee Payment Type(PAYCHECK_MAILED/PAYCHECK_PICKUP/PAYCHECK_DEPOSIT)");
        String payment = sc.nextLine();
        PaymentTypes paymentTypes = PaymentTypes.valueOf(payment.toUpperCase());
        System.out.println("Enter the Employee Type(HOURLY/MONTHLY)");
        String employee = sc.nextLine();
        EmployeeType employeeType = EmployeeType.valueOf(employee.toUpperCase());
        if(employeeType == EmployeeType.HOURLY){
            System.out.println("Enter the Employee Rate Rate");
            String hourStr = sc.nextLine();
            Double hourRate = Double.parseDouble(hourStr);
            boolean valid = DataChecker.StartWith(null,commissionRate,contactNo);
            if(valid){
                EmployeeController.getDataLoader().addDataWorkHour(name,address,contactNo,age,isUnion,unionDueRate,employeeType,paymentTypes,commissionRate,hourRate,0D,null);
                System.out.println("Employee Has been Added");
            }
        }else if(employeeType == EmployeeType.MONTHLY){
            System.out.println("Enter the Employee Salary");
            String salaryStr = sc.nextLine();
            Double salary = Double.parseDouble(salaryStr);
            boolean valid = DataChecker.StartWith(null,commissionRate,contactNo);
            if(valid) {
                EmployeeController.getDataLoader().addFlatSalary(name, address, contactNo, age, isUnion, unionDueRate, employeeType, paymentTypes, commissionRate, salary, 0D, null);
                System.out.println("Employee Has Been Added");
            }
        }
    }
}