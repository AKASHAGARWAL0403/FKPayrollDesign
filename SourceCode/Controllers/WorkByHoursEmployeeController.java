package Controllers;

import Commission.Models.CommissionBlock;
import EmployeeTypes.Models.EmployeeType;
import EmployeeTypes.Models.TimeCard;
import EmployeeTypes.Models.WorkByHoursEmployee;
import Payment.PaymentTypes;
import Services.WorkByHoursEmployeeService;
import Testing.DataLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.List;

public class WorkByHoursEmployeeController {
    private static WorkByHoursEmployeeService workByHoursEmployeeService;

    public static void setWorkByHoursEmployeeService(WorkByHoursEmployeeService workByHoursEmployeeService) {
        WorkByHoursEmployeeController.workByHoursEmployeeService = workByHoursEmployeeService;
    }

    public static void addWeeklyCharges(){
        workByHoursEmployeeService.findAll().forEach(item -> item.addUnionWeeklyTax());
    }

    public static JSONArray addingWorkByHoursEmployeeToJSON(){
        //JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        workByHoursEmployeeService.findAll().forEach(obj -> {
            JSONObject single = new JSONObject();
            single.put("Id", obj.getId());
            single.put("Name", obj.getName());
            single.put("Address", obj.getAddress());
            single.put("Contact", obj.getContactNo());
            single.put("Age", obj.getAge());
            single.put("Type", obj.getEmployeeType().toString());
            single.put("Union_Due_Rate", obj.getUnionDueRate());
            single.put("Union_Extra_Charges", obj.getUnionExtraCharges());
            single.put("Union_Due_Left", obj.getUnionDueLeft());
            single.put("Payment_Method", obj.getPaymentTypes().toString());
            single.put("Union_Member" , obj.isUnionMember());
            JSONObject commission = new JSONObject();
            commission.put("Id", obj.getCommissionList().getId());
            commission.put("Rate", obj.getCommissionList().getRate());
            JSONArray singleArray = new JSONArray();
            for (CommissionBlock entry : obj.getCommissionList().getCommissionBlockList()) {
                JSONObject commissionBlock = new JSONObject();
                commissionBlock.put("Id", entry.getId());
                commissionBlock.put("Date", entry.getDate());
                commissionBlock.put("Amount", entry.getAmount());
                commissionBlock.put("Paid", entry.isPaid());
                singleArray.add(commissionBlock);
            }
            commission.put("Commission_Blocks", singleArray);
            single.put("Commission", commission);
            JSONObject hourCard = new JSONObject();
            hourCard.put("Hour_Rate" , obj.getHourRate());
            JSONObject timeCards = new JSONObject();
            obj.getTimeCard().forEach((k , v) -> {
                JSONObject card =  new JSONObject();
                card.put("Hours" , v.getHours());
                card.put("Recieved" , v.getRecieved());
                timeCards.put(k , card);
            });
            hourCard.put("Time_Cards" , timeCards);
            single.put("Salary",hourCard);
            ja.add(single);
        });
        return ja;
    }

    public static void loadingWorkByHourJSONToObjects(JSONArray ja , DataLoader dataLoader){
        Iterator iter = ja.iterator();
        while (iter.hasNext()){
            JSONObject single = (JSONObject) iter.next();
            Double id = (Double) single.get("Id");
            String name = (String) single.get("Name");
            String address = (String) single.get("Address");
            String contact = (String) single.get("Contact");
            Long age = (Long) single.get("Age");
            boolean unionMember = (boolean) single.get("Union_Member");
            Double unionDueRate = (Double) single.get("Union_Due_Rate");
            Double unionExtraCharge = (Double) single.get("Union_Extra_Charges");
            Double unionDueLeft = (Double) single.get("Union_Due_Left");
            PaymentTypes paymentTypes = PaymentTypes.valueOf((String) single.get("Payment_Method"));
            EmployeeType employeeType = EmployeeType.valueOf((String) single.get("Type"));
            JSONObject salary = (JSONObject) single.get("Salary");
            Double hourRate = (Double) salary.get("Hour_Rate");
            JSONObject commission = (JSONObject) single.get("Commission");
            Double commissionId = (Double) commission.get("Id");
            Double commissionRate = (Double) commission.get("Rate");

            WorkByHoursEmployee workByHoursEmployee = dataLoader.addDataWorkHour(name,address,contact,age,unionMember,unionDueRate,employeeType,paymentTypes,commissionRate,hourRate,unionDueLeft,commissionId);
            workByHoursEmployee.getCommissionList().setId(commissionId);

            JSONArray commissionBlock = (JSONArray) commission.get("Commission_Blocks");
            Iterator commissionIter = commissionBlock.iterator();
            while (commissionIter.hasNext()){
                JSONObject block = (JSONObject) commissionIter.next();
                Double amount = (Double) block.get("Amount");
                String date = (String) block.get("Date");
                Double blockId = (Double) block.get("Id");
                boolean paid = (Boolean) block.get("Paid");
                CommissionBlock c = workByHoursEmployee.addSalesReport(date , amount);
                c.setId(blockId);
                c.setPaid(paid);
            }

            JSONObject timeCards = (JSONObject) salary.get("Time_Cards");
            List<String> keySet = List.of("Monday" , "Tuesday" , "Wednesday" ,
                    "Thursday" , "Friday" , "Saturday" , "Sunday");
            keySet.forEach(item ->{
                JSONObject singleTimeCard = (JSONObject) timeCards.get(item);
                Long hours = (Long) singleTimeCard.get("Hours");
                Boolean recieved = (Boolean) singleTimeCard.get("Recieved");
                TimeCard tc = workByHoursEmployee.addTimeCard(hours , item);
                tc.setRecieved(recieved);
            });
        }
    }

    public static JSONArray addWorkSalaryEmployee(Double Id , JSONArray ja){
        WorkByHoursEmployee obj = workByHoursEmployeeService.findById(Id);
        JSONObject single = new JSONObject();
        single.put("Id", obj.getId());
        single.put("Name", obj.getName());
        single.put("Address", obj.getAddress());
        single.put("Contact", obj.getContactNo());
        single.put("Age", obj.getAge());
        single.put("Type", obj.getEmployeeType().toString());
        single.put("Union_Due_Rate", obj.getUnionDueRate());
        single.put("Union_Extra_Charges", obj.getUnionExtraCharges());
        single.put("Union_Due_Left", obj.getUnionDueLeft());
        single.put("Payment_Method", obj.getPaymentTypes().toString());
        single.put("Union_Member" , obj.isUnionMember());
        JSONObject commission = new JSONObject();
        commission.put("Id", obj.getCommissionList().getId());
        commission.put("Rate", obj.getCommissionList().getRate());
        JSONArray singleArray = new JSONArray();
        for (CommissionBlock entry : obj.getCommissionList().getCommissionBlockList()) {
            JSONObject commissionBlock = new JSONObject();
            commissionBlock.put("Id", entry.getId());
            commissionBlock.put("Date", entry.getDate());
            commissionBlock.put("Amount", entry.getAmount());
            commissionBlock.put("Paid", entry.isPaid());
            singleArray.add(commissionBlock);
        }
        commission.put("Commission_Blocks", singleArray);
        single.put("Commission", commission);
        JSONObject hourCard = new JSONObject();
        hourCard.put("Hour_Rate" , obj.getHourRate());
        JSONObject timeCards = new JSONObject();
        obj.getTimeCard().forEach((k , v) -> {
            JSONObject card =  new JSONObject();
            card.put("Hours" , v.getHours());
            card.put("Recieved" , v.getRecieved());
            timeCards.put(k , card);
        });
        hourCard.put("Time_Cards" , timeCards);
        single.put("Salary",hourCard);
        ja.add(single);
        return  ja;
    }

    public static JSONArray updateWorkSalaryEmployee(Double Id , JSONArray ja){
        WorkByHoursEmployee obj = workByHoursEmployeeService.findById(Id);
        Iterator iter = ja.iterator();
        while (iter.hasNext()){
            JSONObject single = (JSONObject) iter.next();
            Double id = (Double) single.get("Id");
            if(Id.equals(id)){
                iter.remove();
                single = new JSONObject();
                single.put("Id", obj.getId());
                single.put("Name", obj.getName());
                single.put("Address", obj.getAddress());
                single.put("Contact", obj.getContactNo());
                single.put("Age", obj.getAge());
                single.put("Type", obj.getEmployeeType().toString());
                single.put("Union_Due_Rate", obj.getUnionDueRate());
                single.put("Union_Extra_Charges", obj.getUnionExtraCharges());
                single.put("Union_Due_Left", obj.getUnionDueLeft());
                single.put("Payment_Method", obj.getPaymentTypes().toString());
                single.put("Union_Member" , obj.isUnionMember());
                JSONObject commission = new JSONObject();
                commission.put("Id", obj.getCommissionList().getId());
                commission.put("Rate", obj.getCommissionList().getRate());
                JSONArray singleArray = new JSONArray();
                for (CommissionBlock entry : obj.getCommissionList().getCommissionBlockList()) {
                    JSONObject commissionBlock = new JSONObject();
                    commissionBlock.put("Id", entry.getId());
                    commissionBlock.put("Date", entry.getDate());
                    commissionBlock.put("Amount", entry.getAmount());
                    commissionBlock.put("Paid", entry.isPaid());
                    singleArray.add(commissionBlock);
                }
                commission.put("Commission_Blocks", singleArray);
                single.put("Commission", commission);
                JSONObject hourCard = new JSONObject();
                hourCard.put("Hour_Rate" , obj.getHourRate());
                JSONObject timeCards = new JSONObject();
                obj.getTimeCard().forEach((k , v) -> {
                    JSONObject card =  new JSONObject();
                    card.put("Hours" , v.getHours());
                    card.put("Recieved" , v.getRecieved());
                    timeCards.put(k , card);
                });
                hourCard.put("Time_Cards" , timeCards);
                single.put("Salary",hourCard);
                ja.add(single);
                break;
            }
        }
        return ja;
    }

    public static JSONArray deleteWorkSalaryEmployee(Double Id , JSONArray ja){
        WorkByHoursEmployee obj = workByHoursEmployeeService.findById(Id);
        Iterator iter = ja.iterator();
        while (iter.hasNext()){
            JSONObject single = (JSONObject) iter.next();
            Double id = (Double) single.get("Id");
            if(Id.equals(id)){
                iter.remove();
                break;
            }
        }
        return ja;
    }
}
