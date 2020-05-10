package UI;

import Controllers.EmployeeController;
import Controllers.WorkByHoursEmployeeController;
import EmployeeTypes.Models.FlatSalaryEmployee;
import EmployeeTypes.Models.WorkByHoursEmployee;

public class SingleView {

    public static void StartFunction(String[] resList){
        if(resList[1].equalsIgnoreCase("hourly"))
            WorkByHourEmployeeView(Double.parseDouble(resList[2]));
        else if(resList[1].equalsIgnoreCase("monthly"))
            FlatSalaryEmployeeView(Double.parseDouble(resList[2]));
    }

    public static void WorkByHourEmployeeView(Double id){
        WorkByHoursEmployee obj = EmployeeController.getWorkByHoursEmployeeService().findById(id);
        System.out.println(obj);
        System.out.println("_____________________________");
        System.out.println("Press list to get back to previous menu");
    }
    public static void FlatSalaryEmployeeView(Double id){
        FlatSalaryEmployee obj = EmployeeController.getFlatSalaryEmployeeService().findById(id);
        System.out.println(obj);
        System.out.println("_____________________________");
        System.out.println("Press list to get back to previous menu");
    }
}
