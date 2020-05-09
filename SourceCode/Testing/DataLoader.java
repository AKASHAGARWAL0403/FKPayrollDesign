package Testing;

import EmployeeTypes.Models.Employee;
import EmployeeTypes.Models.EmployeeType;
import EmployeeTypes.Models.WorkByHoursEmployee;
import Payment.PaymentTypes;
import Services.FlatSalaryEmployeeService;
import Services.Map.FlatSalaryEmployeeMapService;
import Services.Map.WorkByHoursEmployeeMapService;
import Services.WorkByHoursEmployeeService;

import java.util.Set;

public class DataLoader {
    private FlatSalaryEmployeeService flatSalaryEmployeeService;
    private WorkByHoursEmployeeService workByHoursEmployeeService;

    public DataLoader(){
        flatSalaryEmployeeService = new FlatSalaryEmployeeMapService();
        workByHoursEmployeeService = new WorkByHoursEmployeeMapService();
    }

    public WorkByHoursEmployee addDataWorkHour(String name, String address,
                                String contactNo, Integer age,
                                boolean unionMember, Double unionDueRate,
                                EmployeeType employeeType, PaymentTypes paymentTypes,Double commissionRate , Double hourRate){

        WorkByHoursEmployee workByHoursEmployee = new WorkByHoursEmployee(name,address,contactNo,
                                    age , unionMember , unionDueRate , employeeType , paymentTypes,commissionRate , hourRate);
        workByHoursEmployeeService.save(workByHoursEmployee);
        return workByHoursEmployee;
    }

    public void callAddDataWorkHour(){
        WorkByHoursEmployee akashWork = addDataWorkHour("Akash" , "Jharia" , "9162728446" , Integer.valueOf(21) ,
                true , Double.valueOf(1000) , EmployeeType.HOURLY , PaymentTypes.PAYCHECK_DEPOSIT , 20D , 400D);
        akashWork.addTimeCard(10 , "Monday");
        akashWork.addTimeCard(6,"Tuesday");
        akashWork.addSalesReport("20/2/2020" , 1000D);
        akashWork.addSalesReport("04/03/2020" , 2000D);

        WorkByHoursEmployee aritraWork = addDataWorkHour("Aritra" , "Moonidih" , "9123417253" , Integer.valueOf(21) ,
                false , Double.valueOf(1001) , EmployeeType.HOURLY , PaymentTypes.PAYCHECK_DEPOSIT , 10D , 300D);
        aritraWork.addTimeCard(7,"Monday");
        aritraWork.addTimeCard(6,"Tuesday");
        aritraWork.addSalesReport("20/3/2020" , 1000D);
        aritraWork.addSalesReport("04/04/2020" , 2000D);
    }


    public Set<WorkByHoursEmployee> getData(){
        return workByHoursEmployeeService.findAll();
    }
}
