import Controllers.EmployeeController;
import EmployeeTypes.Models.Employee;
import Services.EmployeeService;
import Services.FlatSalaryEmployeeService;
import Services.Map.EmployeeMapService;
import Services.Map.FlatSalaryEmployeeMapService;
import Services.Map.WorkByHoursEmployeeMapService;
import Services.WorkByHoursEmployeeService;
import Testing.DataLoader;
import UI.CommandLine;


public class MainClass {
    private static FlatSalaryEmployeeService flatSalaryEmployeeService = new FlatSalaryEmployeeMapService();
    private static WorkByHoursEmployeeService workByHoursEmployeeService = new WorkByHoursEmployeeMapService();
    private static EmployeeService employeeService = new EmployeeMapService();
    private static DataLoader dataLoader;

    public static void main(String args[]){
        dataLoader = new DataLoader(flatSalaryEmployeeService , workByHoursEmployeeService);

        /*
        * Setting Up The Controller
        */
        EmployeeController.setFlatSalaryEmployeeService(flatSalaryEmployeeService);
        EmployeeController.setWorkByHoursEmployeeService(workByHoursEmployeeService);
        EmployeeController.setEmployeeService(employeeService);
        EmployeeController.setDataLoader(dataLoader);

        EmployeeController.loadEmployeeJSONToObjects();

//        dataLoader.callAddDataWorkHour();
//        dataLoader.callAddDataFlatSalary();
        CommandLine.Start();

        EmployeeController.loadEmployeeToJSON();
        //dataLoader.getWorkHourData().forEach(System.out::println);
        //dataLoader.getFlatSalaryData().forEach(System.out::println);
    }
}
