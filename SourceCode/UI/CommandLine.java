package UI;

import java.util.Arrays;
import java.util.Scanner;

public class CommandLine {
    private static String response = "/";
    private static Scanner sc = new Scanner(System.in);

    public static void Start(){
        while(!response.equals("exit")) {
            String[] resList = response.split("/");
            System.out.println(Arrays.toString(resList));
            if (resList.length == 0) {
                CommandLine.FirstView();
            }else if(resList[0].equalsIgnoreCase("list")){
                ListView.StartFunction(resList);
            }else if(resList[0].equalsIgnoreCase("add")){
                AddView.StartWith();
            }else if(resList[0].equalsIgnoreCase("update")){

            }else if(resList[0].equalsIgnoreCase("delete")){
                DeleteView.StartWith();
            }else if(resList[0].equalsIgnoreCase("exit")){
                break;
            }else{
                System.out.println("Enter The Correct Option Please");
            }
            System.out.println("________________________________");
            System.out.println("Press / to get back to main menu");
            System.out.println("Press exit to quit");
            response = sc.nextLine();
            //System.out.println(response);
        }
    }

    public static void FirstView(){
        System.out.println("Select An Operation To Perform");
        System.out.println("Press list for View List Of Employee");
        System.out.println("Press add for Adding an Employee");
        System.out.println("Press update for Updating an Employee");
        System.out.println("Press delete for Deleting an Employee");
    }
}
