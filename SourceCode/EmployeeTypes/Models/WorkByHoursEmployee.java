package EmployeeTypes.Models;

import Payment.PaymentTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TimeCard{
    private Integer hours;
    private Boolean recieved;
    public TimeCard(){
        hours = 0;
        recieved = false;
    }

    public TimeCard(Integer hours){
        this.hours = hours;
        recieved = false;
    }

    public Integer getHours() {
        return hours;
    }

    public Boolean getRecieved() {
        return recieved;
    }
}

public class WorkByHoursEmployee extends Employee{
    private Double hourRate;
    private Map<String, TimeCard> timeCard;
    public WorkByHoursEmployee(String name, String address,
                               String contactNo, Integer age, boolean unionMember,
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

    private void initializeTimeCard(){
        List<String> keySet = List.of("Monday" , "Tuesday" , "Wednesday" ,
                "Thursday" , "Friday" , "Saturday" , "Sunday");
        for(String day : keySet){
            timeCard.put(day , new TimeCard());
        }
    }

    public void addTimeCard(Integer hours , String dayOfWeek){
        TimeCard timeCard = new TimeCard(hours);
        this.timeCard.put(dayOfWeek , timeCard);
    }

    public Double salaryPayable(){
        Double netPayable = 0D;
        for(TimeCard t : timeCard.values()){
            if(t.getRecieved())
                continue;
            Integer hours = t.getHours();
            Integer overTime = Math.max(0 , hours - 8);
            netPayable += Math.min(8 , hours)*this.hourRate + overTime*this.hourRate*1.15;
        }
        return netPayable;
    }

    public Double paySalary(){
        Double netSalary = salaryPayable();
        initializeTimeCard();
        return netSalary;
    }
}
