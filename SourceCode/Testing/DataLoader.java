package Testing;

import Controllers.EmployeeController;
import EmployeeTypes.Models.Employee;
import EmployeeTypes.Models.EmployeeType;
import EmployeeTypes.Models.FlatSalaryEmployee;
import EmployeeTypes.Models.WorkByHoursEmployee;
import Payment.PaymentTypes;
import Services.FlatSalaryEmployeeService;
import Services.Map.FlatSalaryEmployeeMapService;
import Services.Map.WorkByHoursEmployeeMapService;
import Services.WorkByHoursEmployeeService;

import java.util.Optional;
import java.util.Set;

public class DataLoader {
    private FlatSalaryEmployeeService flatSalaryEmployeeService;
    private WorkByHoursEmployeeService workByHoursEmployeeService;

    public DataLoader(FlatSalaryEmployeeService f , WorkByHoursEmployeeService w){
        flatSalaryEmployeeService = f;
        workByHoursEmployeeService = w;
    }

    public WorkByHoursEmployee addDataWorkHour(String name, String address,
                                               String contactNo, Long age,
                                               boolean unionMember, Double unionDueRate,
                                               EmployeeType employeeType, PaymentTypes paymentTypes, Double commissionRate , Double hourRate , Double UnionDueLeft , Double Id){

        WorkByHoursEmployee workByHoursEmployee = new WorkByHoursEmployee(name,address,contactNo,
                                    age , unionMember , unionDueRate , employeeType , paymentTypes,commissionRate , hourRate);
        workByHoursEmployee.setUnionDueLeft(UnionDueLeft);
        if(Id != null)
            workByHoursEmployee.setId(Id);
        workByHoursEmployee = workByHoursEmployeeService.save(workByHoursEmployee);
        EmployeeController.addEmployee(workByHoursEmployee.getId() , EmployeeType.HOURLY);
        return workByHoursEmployee;
    }

    public FlatSalaryEmployee addFlatSalary(String name, String address,
                                               String contactNo, Long age,
                                               boolean unionMember, Double unionDueRate,
                                               EmployeeType employeeType, PaymentTypes paymentTypes, Double commissionRate , Double salary , Double UnionDueLeft , Double Id){

        FlatSalaryEmployee flatSalaryEmployee = new FlatSalaryEmployee(name,address,contactNo,
                age , unionMember , unionDueRate , employeeType , paymentTypes,commissionRate , salary);
        if(Id != null)
            flatSalaryEmployee.setId(Id);
        flatSalaryEmployee.setUnionDueLeft(UnionDueLeft);
        flatSalaryEmployee = flatSalaryEmployeeService.save(flatSalaryEmployee);
        EmployeeController.addEmployee(flatSalaryEmployee.getId() , EmployeeType.MONTHLY);
        return flatSalaryEmployee;
    }

    public void callAddDataWorkHour(){
        WorkByHoursEmployee akashWork = addDataWorkHour("Akash" , "Jharia" , "9162728446" , 21L ,
                true , Double.valueOf(1000) , EmployeeType.HOURLY , PaymentTypes.PAYCHECK_DEPOSIT , 20D , 400D , 0D ,null);
        akashWork.addTimeCard(10L , "Monday");
        akashWork.addTimeCard(6L ,"Tuesday");
        akashWork.addSalesReport("20/2/2020" , 1000D);
        akashWork.addSalesReport("04/03/2020" , 2000D);
        EmployeeController.updateEmployee(akashWork.getId() , EmployeeType.HOURLY);

        WorkByHoursEmployee aritraWork = addDataWorkHour("Aritra" , "Moonidih" , "9123417253" , 21L ,
                false , Double.valueOf(1001) , EmployeeType.HOURLY , PaymentTypes.PAYCHECK_DEPOSIT , 10D , 300D , 0D ,null);
        aritraWork.addTimeCard(7L,"Monday");
        aritraWork.addTimeCard(6L,"Tuesday");
        aritraWork.addSalesReport("20/3/2020" , 1000D);
        aritraWork.addSalesReport("04/04/2020" , 2000D);
        EmployeeController.updateEmployee(aritraWork.getId() , EmployeeType.HOURLY);
    }

    public void callAddDataFlatSalary(){
        FlatSalaryEmployee sumit = addFlatSalary("sumit" , "Jharia" , "9162728446" , 21L ,
                true , Double.valueOf(1000) , EmployeeType.HOURLY , PaymentTypes.PAYCHECK_DEPOSIT , 20D , 40000D , 0D ,null);
        sumit.addSalesReport("20/2/2020" , 1000D);
        sumit.addSalesReport("04/03/2020" , 2000D);
        EmployeeController.updateEmployee(sumit.getId() , EmployeeType.MONTHLY);
    }

    public void deletWorkHour(Double id){
        workByHoursEmployeeService.deleteBYId(id);
        EmployeeController.deleteEmployee(id , EmployeeType.HOURLY);
    }

    public Set<WorkByHoursEmployee> getWorkHourData(){
        return workByHoursEmployeeService.findAll();
    }
    public Set<FlatSalaryEmployee> getFlatSalaryData() {
        return flatSalaryEmployeeService.findAll();
    }
}
