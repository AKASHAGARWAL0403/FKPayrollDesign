package Controllers;

import EmployeeTypes.Models.EmployeeType;
import EmployeeTypes.Models.FlatSalaryEmployee;
import Services.FlatSalaryEmployeeService;
import Services.WorkByHoursEmployeeService;
import Testing.DataLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class EmployeeController {
    private static FlatSalaryEmployeeService flatSalaryEmployeeService;
    private static DataLoader dataLoader;
    private static WorkByHoursEmployeeService workByHoursEmployeeService;

    public static DataLoader getDataLoader() {
        return dataLoader;
    }

    public static FlatSalaryEmployeeService getFlatSalaryEmployeeService() {
        return flatSalaryEmployeeService;
    }

    public static WorkByHoursEmployeeService getWorkByHoursEmployeeService() {
        return workByHoursEmployeeService;
    }

    public static void setDataLoader(DataLoader dataLoader) {
        EmployeeController.dataLoader = dataLoader;
    }

    public static void setFlatSalaryEmployeeService(FlatSalaryEmployeeService flatSalaryEmployeeService) {
        EmployeeController.flatSalaryEmployeeService = flatSalaryEmployeeService;
        FlatSalaryEmployeeController.setFlatSalaryEmployeeService(flatSalaryEmployeeService);
    }

    public static void setWorkByHoursEmployeeService(WorkByHoursEmployeeService workByHoursEmployeeService) {
        EmployeeController.workByHoursEmployeeService = workByHoursEmployeeService;
        WorkByHoursEmployeeController.setWorkByHoursEmployeeService(workByHoursEmployeeService);
    }

    public static void addWeeklyCharges(){
        FlatSalaryEmployeeController.addWeeklyCharges();
        WorkByHoursEmployeeController.addWeeklyCharges();
    }

    public static void loadEmployeeToJSON(){
        JSONObject jo = new JSONObject();
        JSONArray jaFlatSalary = FlatSalaryEmployeeController.addingFlatSalaryEmployeeToJSON();
        JSONArray jaWorkByHours = WorkByHoursEmployeeController.addingWorkByHoursEmployeeToJSON();
        jo.put("WorkByHoursEmployee" , jaWorkByHours);
        jo.put("FlatSalaryEmployee" , jaFlatSalary);
        PrintWriter pw;
        try {
            pw = new PrintWriter("JSONExample.json");
        }catch (FileNotFoundException e){
            return;
        }
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
        System.out.println("Loading Employee To JSON");
    }

    public static void loadEmployeeJSONToObjects(){
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader("JSONExample.json"));
        }catch (Exception e){
            return;
        }
        JSONObject jo = (JSONObject) obj;
        WorkByHoursEmployeeController.loadingWorkByHourJSONToObjects((JSONArray) jo.get("WorkByHoursEmployee"),EmployeeController.dataLoader);
        FlatSalaryEmployeeController.loadingFlatSalaryJSONToObjects((JSONArray) jo.get("FlatSalaryEmployee") ,EmployeeController.dataLoader);
        System.out.println("Loading JSON to Objects");
    }

    public static void addEmployee(Double Id , EmployeeType type){
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader("JSONExample.json"));
        }catch (Exception e){
            return;
        }
        JSONObject jo = (JSONObject) obj;
        JSONArray ja;
        if(type == EmployeeType.MONTHLY){
           ja  = FlatSalaryEmployeeController.addFlatSalaryEmployee(Id , (JSONArray) jo.get("FlatSalaryEmployee"));
           jo.put("FlatSalaryEmployee" , ja);
        }else{
           ja =  WorkByHoursEmployeeController.addWorkSalaryEmployee(Id , (JSONArray) jo.get("WorkByHoursEmployee"));
           jo.put("WorkByHoursEmployee" , ja);
        }

        PrintWriter pw;
        try {
            pw = new PrintWriter("JSONExample.json");
        }catch (FileNotFoundException e){
            return;
        }
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
    }

    public static void updateEmployee(Double Id , EmployeeType type){
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader("JSONExample.json"));
        }catch (Exception e){
            return;
        }
        JSONObject jo = (JSONObject) obj;
        JSONArray ja;
        if(type == EmployeeType.MONTHLY){
            ja  = FlatSalaryEmployeeController.updateFlatSalaryEmployee(Id , (JSONArray) jo.get("FlatSalaryEmployee"));
            jo.put("FlatSalaryEmployee" , ja);
        }else{
            ja =  WorkByHoursEmployeeController.addWorkSalaryEmployee(Id , (JSONArray) jo.get("WorkByHoursEmployee"));
            jo.put("WorkByHoursEmployee" , ja);
        }

        PrintWriter pw;
        try {
            pw = new PrintWriter("JSONExample.json");
        }catch (FileNotFoundException e){
            return;
        }
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
    }

    public static void deleteEmployee(Double Id , EmployeeType type){
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader("JSONExample.json"));
        }catch (Exception e){
            return;
        }
        JSONObject jo = (JSONObject) obj;
        JSONArray ja;
        if(type == EmployeeType.MONTHLY){
            ja  = FlatSalaryEmployeeController.deleteFlatSalaryEmployee(Id , (JSONArray) jo.get("FlatSalaryEmployee"));
            jo.put("FlatSalaryEmployee" , ja);
        }else{
            ja =  WorkByHoursEmployeeController.deleteWorkSalaryEmployee(Id , (JSONArray) jo.get("WorkByHoursEmployee"));
            jo.put("WorkByHoursEmployee" , ja);
        }

        PrintWriter pw;
        try {
            pw = new PrintWriter("JSONExample.json");
        }catch (FileNotFoundException e){
            return;
        }
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
    }
}
