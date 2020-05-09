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

    public void addDataWorkHour(String name, String address,
                                String contactNo, Integer age,
                                boolean unionMember, Double unionDueRate,
                                EmployeeType employeeType, PaymentTypes paymentTypes){

        WorkByHoursEmployee workByHoursEmployee = new WorkByHoursEmployee(name,address,contactNo,
                                    age , unionMember , unionDueRate , employeeType , paymentTypes);
        workByHoursEmployeeService.save(workByHoursEmployee);
    }

    public void callAddDataWorkHour(){
        addDataWorkHour("Akash" , "Jharia" , "9162728446" , Integer.valueOf(21) ,
                true , Double.valueOf(1000) , EmployeeType.HOURLY , PaymentTypes.PAYCHECK_DEPOSIT);
        addDataWorkHour("Aritra" , "Moonidih" , "9123417253" , Integer.valueOf(21) ,
                false , Double.valueOf(1001) , EmployeeType.HOURLY , PaymentTypes.PAYCHECK_DEPOSIT);
    }


    public Set<WorkByHoursEmployee> getData(){
        return workByHoursEmployeeService.findAll();
    }
}
