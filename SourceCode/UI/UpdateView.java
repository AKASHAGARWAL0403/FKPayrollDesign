package UI;

import Controllers.EmployeeController;
import EmployeeTypes.Models.Employee;
import EmployeeTypes.Models.EmployeeType;
import EmployeeTypes.Models.FlatSalaryEmployee;
import EmployeeTypes.Models.WorkByHoursEmployee;

import java.util.List;

public class UpdateView {

    public static void StartWith(){
        System.out.println("UPDATE VIEW");
        System.out.println("_______________________________");
        System.out.println("List of All Work Hourly Employee");
        System.out.println("________________________________");
        EmployeeController.getWorkByHoursEmployeeService().findAll().forEach(item ->{
            System.out.println("Employee ID: "+ item.getId() + " Name :" + item.getName());
            System.out.println("\n");
        });
        System.out.println("_______________________________");
        System.out.println("List of All Flat Salary Employee");
        System.out.println("________________________________");
        EmployeeController.getFlatSalaryEmployeeService().findAll().forEach(item ->{
            System.out.println("Employee ID: "+ item.getId() + " Name :" + item.getName());
            System.out.println("\n");
        });

        System.out.println("TO UPDATE ONE EMPLOYEE");
        System.out.println("To UPDATE Work By Hour Employee");
        System.out.println("_________________________________");
        System.out.println("Enter hourly/{Employee_id}");
        System.out.println("_________________________________");
        System.out.println("To UPDATE Flat Salary Employee");
        System.out.println("_________________________________");
        System.out.println("Enter monthly/{Employee_id}");
        System.out.println("_________________________________");
        System.out.println("Press / to get back to main menu");

        String res = CommandLine.sc.nextLine();
        String[] resList = res.split("/");
        while(true) {
            if (resList.length == 2) {
                if (resList[0].equalsIgnoreCase("hourly")) {
                    updateEmployee(Double.parseDouble(resList[1]) , EmployeeType.HOURLY);
                    break;
                } else if (resList[0].equalsIgnoreCase("monthly")) {
                    updateEmployee(Double.parseDouble(resList[1]) , EmployeeType.MONTHLY);
                    break;
                }
            } else if (resList.length == 0)
                break;
            System.out.println("_______________________");
            System.out.println("Please Enter Proper command");
            res = CommandLine.sc.nextLine();
            resList = res.split("/");
        }
    }

    public static void updateEmployee(Double id , EmployeeType e){
        System.out.println("Press 1 to Add Sales Receipt");
        System.out.println("Press 2 to Add Union Taxes");
        System.out.println("Press 3 to Update Employee Details");
        if(e == EmployeeType.HOURLY)
            System.out.println("Press 4 to post a Time Card");
        System.out.println("Press / to get back to main menu");
        System.out.println("__________________________________");

        String res = CommandLine.sc.nextLine();

        while(true) {
            if (res.equalsIgnoreCase("4")) {
                addTimeCard(id , e);
                break;
            } else if (res.equalsIgnoreCase("1")) {
                addSalesReceipt(id , e);
                break;
            } else if (res.equalsIgnoreCase("2")) {
                addUnionTaxes(id , e);
                break;
            } else if (res.equalsIgnoreCase("3")) {
                break;
            } else if (res.equalsIgnoreCase("/")) {
                break;
            } else {
                System.out.println("Please Enter the correct command");
            }
            res = CommandLine.sc.nextLine();
        }
    }

    public static void addTimeCard(Double id , EmployeeType e){
        System.out.println("Enter The No of Hours Worked(Less than 24)");
        String hours = CommandLine.sc.nextLine();
        Long hour = Long.parseLong(hours);
        while(hour >= 24L){
            System.out.println("Enter Legal Work Hours");
            hours = CommandLine.sc.nextLine();
            hour = Long.parseLong(hours);
        }
        System.out.println("Enter the Week Day name(Monday/Tuesday/Wednesday/Thursday/Friday/Saturday/Sunday");
        List<String> keySet = List.of("MONDAY" , "TUESDAY" , "WEDNESDAY" ,
                "THURSDAY" , "FRIDAY" , "SATURDAY" , "SUNDAY");
        String dayOfWeek = CommandLine.sc.nextLine();
        while(!keySet.contains(dayOfWeek.toUpperCase())){
            System.out.println("Please Enter the Data in required Format");
            dayOfWeek = CommandLine.sc.nextLine();
        }

        WorkByHoursEmployee obj = EmployeeController.getWorkByHoursEmployeeService().findById(id);
        obj.addTimeCard(hour , dayOfWeek);
        EmployeeController.updateEmployee(id , e);
        System.out.println("Time Card Successfully Added");
        System.out.println("_____________________________");
    }

    public static void addSalesReceipt(Double id , EmployeeType e){
        System.out.println("Enter The Amount of sale");
        String hours = CommandLine.sc.nextLine();
        Double hour = Double.parseDouble(hours);

        Employee obj = EmployeeController.getEmployeeService().findById(id);

        obj.addSalesReport(null , hour);
        EmployeeController.updateEmployee(id , e);

        System.out.println("Sales Report Successfully Added");
        System.out.println("_____________________________");
    }

    public static void addUnionTaxes(Double id , EmployeeType e){
        System.out.println("Enter The Amount of Charge To add");
        String chargeStr = CommandLine.sc.nextLine();
        Double charge = Double.parseDouble(chargeStr);

        Employee obj = EmployeeController.getEmployeeService().findById(id);

        if(!obj.isUnionMember()){
            System.out.println("Not A Union Member!!");
            return;
        }

        obj.setUnionExtraCharges(charge);
        EmployeeController.updateEmployee(id , e);

        System.out.println("Union Charge Successfully Added");
        System.out.println("_____________________________");
    }

    public static void updateOtherDetails(Double id , EmployeeType e){

    }
}
