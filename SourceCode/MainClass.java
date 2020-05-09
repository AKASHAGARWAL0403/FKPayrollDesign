import Controllers.EmployeeController;
import Services.FlatSalaryEmployeeService;
import Services.Map.FlatSalaryEmployeeMapService;
import Services.Map.WorkByHoursEmployeeMapService;
import Services.WorkByHoursEmployeeService;
import Testing.DataLoader;


public class MainClass {
    private static FlatSalaryEmployeeService flatSalaryEmployeeService = new FlatSalaryEmployeeMapService();
    private static WorkByHoursEmployeeService workByHoursEmployeeService = new WorkByHoursEmployeeMapService();
    private static DataLoader dataLoader;

    public static void main(String args[]){
        dataLoader = new DataLoader(flatSalaryEmployeeService , workByHoursEmployeeService);
        EmployeeController.setFlatSalaryEmployeeService(flatSalaryEmployeeService);
        EmployeeController.setWorkByHoursEmployeeService(workByHoursEmployeeService);
        EmployeeController.loadEmployeeJSONToObjects(dataLoader);

        //dataLoader.callAddDataFlatSalary();
        //dataLoader.callAddDataWorkHour();
        //dataLoader.deletWorkHour(2D);

        //EmployeeController.loadEmployeeToJSON();
        dataLoader.getWorkHourData().forEach(System.out::println);
        dataLoader.getFlatSalaryData().forEach(System.out::println);
    }
}
