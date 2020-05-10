package Testing;

import java.util.HashMap;
import java.util.Map;

public class DataChecker {
    public static boolean StartWith(Double workHour , Double commissionRate , String contact){
        Map<String,String> map = new HashMap<>();
        boolean valid = true;
        if(workHour != null && !checkWorkHours(workHour)) {
            System.out.println("Please Enter Correct Work Hours");
            valid = false;
        }
        if(commissionRate != null && !checkCommissionRate(commissionRate)){
            System.out.println("Please Enter Correct Commission rate");
            valid = false;
        }
        if(contact != null && !checkContact(contact)) {
            System.out.println("Please Enter Correct Contact Number");
            valid = false;
        }
        return valid;
    }

    public static boolean checkWorkHours(Double workHour){
        if(workHour >= 24D)
            return false;
        return true;
    }

    public static boolean checkCommissionRate(Double commissionRate){
        if (commissionRate > 100)
            return false;
        return true;
    }

    public static boolean checkContact(String contact){
        if(contact.length() != 10)
            return false;
        return true;
    }
}
