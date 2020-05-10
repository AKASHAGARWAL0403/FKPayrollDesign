package UI;

import Controllers.EmployeeController;

public class ListView {

    public static void ListViewService(){
        System.out.println("LIST VIEW");
        System.out.println("________________________________");
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

        System.out.println("Open One Employee Details");
        System.out.println("To open Work By Work Employee Details");
        System.out.println("_________________________________");
        System.out.println("Enter list/hourly/{Employee_id}");
        System.out.println("_________________________________");
        System.out.println("To open Work By Work Employee Details");
        System.out.println("_________________________________");
        System.out.println("Enter list/monthly/{Employee_id}");

    }
    public static void StartFunction(String[] resList){
        if(resList.length == 1){
            ListView.ListViewService();
        }else if(resList.length == 3){
            SingleView.StartFunction(resList);
        }
    }
}
