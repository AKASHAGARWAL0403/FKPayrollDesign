package UI;

import Controllers.EmployeeController;
import EmployeeTypes.Models.EmployeeType;

import java.util.Scanner;

public class DeleteView {
    public static void StartWith(){
        Scanner sc = new Scanner(System.in);

        System.out.println("DELETE VIEW");
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

        System.out.println("Delete One Employee");
        System.out.println("To Delete Work By Hour Employee");
        System.out.println("_________________________________");
        System.out.println("Enter hourly/{Employee_id}");
        System.out.println("_________________________________");
        System.out.println("To delete Flat Salary Employee");
        System.out.println("_________________________________");
        System.out.println("Enter monthly/{Employee_id}");
        System.out.println("_________________________________");
        System.out.println("Press / to get back to main menu");


        String res = sc.nextLine();
        String[] resList = res.split("/");
        while(true) {
            if (resList.length == 2) {
                if (resList[0].equalsIgnoreCase("hourly")) {
                    deleteHourEmployeeView(Double.parseDouble(resList[1]));
                    break;
                } else if (resList[0].equalsIgnoreCase("monthly")) {
                    deleteFlatEmployeeView(Double.parseDouble(resList[1]));
                    break;
                }
            } else if (resList.length == 0)
                break;
            System.out.println("_______________________");
            System.out.println("Please Enter Proper command");
            res = sc.nextLine();
            resList = res.split("/");
        }
    }

    public static void deleteHourEmployeeView(Double id){
        EmployeeController.getWorkByHoursEmployeeService().deleteBYId(id);
        EmployeeController.deleteEmployee(id , EmployeeType.HOURLY);
        System.out.println("Employee Has Been Deleted Successfully");
    }

    public static void deleteFlatEmployeeView(Double id){
        EmployeeController.getFlatSalaryEmployeeService().deleteBYId(id);
        EmployeeController.deleteEmployee(id , EmployeeType.MONTHLY);
        System.out.println("Employee Has Been Deleted Successfully");
    }
}
