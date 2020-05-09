import Controllers.UnionWeeklyTax;
import Services.FlatSalaryEmployeeService;
import Services.Map.FlatSalaryEmployeeMapService;
import Services.Map.WorkByHoursEmployeeMapService;
import Services.WorkByHoursEmployeeService;
import Testing.DataLoader;


public class MainClass {
    private static FlatSalaryEmployeeService flatSalaryEmployeeService = new FlatSalaryEmployeeMapService();
    private static WorkByHoursEmployeeService workByHoursEmployeeService = new WorkByHoursEmployeeMapService();
    private static UnionWeeklyTax unionWeeklyTax;

    public static void main(String args[]){
        DataLoader dataLoader = new DataLoader(flatSalaryEmployeeService , workByHoursEmployeeService);
        //dataLoader.callAddDataWorkHour();
        unionWeeklyTax = new UnionWeeklyTax(flatSalaryEmployeeService , workByHoursEmployeeService);
        unionWeeklyTax.loadEmployeeJSONToObjects(dataLoader);
        //unionWeeklyTax.addWeeklyCharges();
        unionWeeklyTax.loadEmployeeToJSON();
        dataLoader.getData().forEach(System.out::println);
    }
}
