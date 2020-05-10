package EmployeeTypes.Models;

import Payment.PaymentTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkByHoursEmployee extends Employee{
    private Double hourRate;
    private Map<String, TimeCard> timeCard;
    public WorkByHoursEmployee(String name, String address,
                               String contactNo, Long age, boolean unionMember,
                               Double unionDueRate, EmployeeType employeeType,
                               PaymentTypes paymentTypes,Double commissionRate , Double hourRate) {
        super(name, address, contactNo, age, unionMember, unionDueRate, employeeType, paymentTypes , commissionRate);
        this.hourRate = hourRate;
        timeCard = new HashMap<>();
        initializeTimeCard();
    }

    public Double getHourRate() {
        return hourRate;
    }

    public Map<String, TimeCard> getTimeCard() {
        return timeCard;
    }

    private void initializeTimeCard(){
        List<String> keySet = List.of("MONDAY" , "TUESDAY" , "WEDNESDAY" ,
                "THURSDAY" , "FRIDAY" , "SATURDAY" , "SUNDAY");
        for(String day : keySet){
            timeCard.put(day , new TimeCard());
        }
    }

    public TimeCard addTimeCard(Long hours , String dayOfWeek){
        TimeCard timeCard = new TimeCard(hours);
        this.timeCard.put(dayOfWeek , timeCard);
        return timeCard;
    }

    public Double salaryPayable(){
        Double netPayable = 0D;
        for(TimeCard t : timeCard.values()){
            if(t.getRecieved())
                continue;
            Long hours = t.getHours();
            Long overTime = Math.max(0 , hours - 8);
            netPayable += Math.min(8 , hours)*this.hourRate + overTime*this.hourRate*1.15;
        }
        return netPayable;
    }

    public Double paySalary(){
        Double netSalary = salaryPayable();
        initializeTimeCard();
        return netSalary;
    }

    public String toString(){
        String res = "";
        res += "Employee Id is " + super.getId() + " \n";
        res += "Employee Name is " + super.getName() + " \n";
        res += "Address is " + super.getAddress() + " \n";
        res += "Age is " + super.getAge() + " \n";
        res += "Contact is " + super.getContactNo() + " \n";
        res += "Union Member " + super.isUnionMember() + "\n";
        if(super.isUnionMember()){
            res += "Union Tax Rate is " + super.getUnionDueRate() + "/week \n";
            res += "Union Extra Tax is " + super.getUnionExtraCharges() + " \n";
            res += "Union Tax Due Left is " + super.getUnionDueLeft() + " \n";
        }
        res += "Payment type is " + super.getPaymentTypes() + "\n";
        res += "Employee Type is " + super.getEmployeeType() + "\n";
        res += "Salary \n";
        res += "Rate is " + hourRate + "/hour \n";
        res += "Employee Salary Due is " + this.salaryPayable() + "\n";
        res += "Commission due is " + super.commissionGenerated() +"\n";
        res += "Total Deduction(Union Tax Due + Union Extra Tax) is " + super.salaryDeducted() +" \n";
        res += "Commission Info \n";
        res += super.getCommissionList();

        return  res;
    }
}
