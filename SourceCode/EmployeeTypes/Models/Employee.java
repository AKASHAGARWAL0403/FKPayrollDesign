package EmployeeTypes.Models;

import Commission.Models.CommissionBlock;
import Commission.Models.CommissionList;
import Payment.PaymentTypes;


public class Employee {
    private Double id;
    private String name;
    private String address;
    private String contactNo;
    private Integer age;
    private boolean unionMember;
    private Double unionDueRate;
    private Double unionExtraCharges;
    private EmployeeType employeeType;
    private CommissionList commissionList;
    private PaymentTypes paymentTypes;

    public Employee() {
        id = null;
    }

    public Employee(String name, String address,
                    String contactNo, Integer age,
                    boolean unionMember, Double unionDueRate,
                    EmployeeType employeeType, PaymentTypes paymentTypes , Double commissionRate) {
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.age = age;
        this.unionMember = unionMember;
        this.unionDueRate = unionDueRate;
        this.unionExtraCharges = Double.valueOf(0);
        this.employeeType = employeeType;
        this.commissionList = new CommissionList(commissionRate);
        this.paymentTypes = paymentTypes;
    }

    public void setId(Double id){
        this.id = id;
    }

    public Double getId() {
        return id;
    }

    public PaymentTypes getPaymentTypes() {
        return paymentTypes;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public Integer getAge() {
        return age;
    }

    public boolean isUnionMember() {
        return unionMember;
    }

    public Double getUnionDueRate() {
        return unionDueRate;
    }

    public Double getUnionExtraCharges() {
        return unionExtraCharges;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public CommissionList getCommissionList() {
        return commissionList;
    }

    public CommissionBlock addSalesReport(String date , Double amount){
        return commissionList.addCommission(date , amount);
    }

    public Double salaryDeducted(){
        if(isUnionMember())
            return getUnionDueRate()+ getUnionExtraCharges();
        return 0D;
    }

    public Double commissionGenerated(){
        Double commission = 0D;
        for(CommissionBlock entry : commissionList.getCommissionBlockList()){
            if(!entry.isPaid()){
                commission += entry.getAmount()*(commissionList.getRate()/100);
            }
        }
        return commission;
    }

    public Double payCommissionGenerated(){
        Double commission = commissionGenerated();
        for(CommissionBlock entry : commissionList.getCommissionBlockList()){
            entry.setPaid(true);
        }
        return commission;
    }

}
