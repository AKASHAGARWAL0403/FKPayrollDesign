package Controllers;

import Commission.Models.CommissionBlock;
import EmployeeTypes.Models.EmployeeType;
import EmployeeTypes.Models.FlatSalaryEmployee;
import EmployeeTypes.Models.TimeCard;
import EmployeeTypes.Models.WorkByHoursEmployee;
import Payment.PaymentTypes;
import Services.FlatSalaryEmployeeService;
import Services.WorkByHoursEmployeeService;
import Testing.DataLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class FlatSalaryEmployeeController {
    private static FlatSalaryEmployeeService flatSalaryEmployeeService;

    public static void setFlatSalaryEmployeeService(FlatSalaryEmployeeService flatSalaryEmployeeService) {
        FlatSalaryEmployeeController.flatSalaryEmployeeService = flatSalaryEmployeeService;
    }

    public static void addWeeklyCharges(){
        flatSalaryEmployeeService.findAll().forEach(item -> item.addUnionWeeklyTax());
    }

    public static JSONArray addingFlatSalaryEmployeeToJSON(){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        flatSalaryEmployeeService.findAll().forEach(obj -> {
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
            single.put("Salary" , obj.getSalary());
            single.put("Last_Date_Paid" , obj.getLastSalaryRecieved());

            ja.add(single);
        });
        return ja;
    }

    public static void loadingFlatSalaryJSONToObjects(JSONArray ja,DataLoader dataLoader){
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
            Double salary = (Double) single.get("Salary");
            String lastSalaryDate = (String) single.get("Last_Date_Paid");
            JSONObject commission = (JSONObject) single.get("Commission");
            Double commissionId = (Double) commission.get("Id");
            Double commissionRate = (Double) commission.get("Rate");

            FlatSalaryEmployee flatSalaryEmployee = dataLoader.addFlatSalary(name,address,contact,age,unionMember,unionDueRate,employeeType,paymentTypes,commissionRate,salary,unionDueLeft,commissionId);
            flatSalaryEmployee.setLastSalaryRecieved(lastSalaryDate);
            flatSalaryEmployee.getCommissionList().setId(commissionId);

            JSONArray commissionBlock = (JSONArray) commission.get("Commission_Blocks");
            Iterator commissionIter = commissionBlock.iterator();
            while (commissionIter.hasNext()){
                JSONObject block = (JSONObject) commissionIter.next();
                Double amount = (Double) block.get("Amount");
                String date = (String) block.get("Date");
                Double blockId = (Double) block.get("Id");
                boolean paid = (Boolean) block.get("Paid");
                CommissionBlock c = flatSalaryEmployee.addSalesReport(date , amount);
                c.setId(blockId);
                c.setPaid(paid);
            }
        }
    }

    public static JSONArray addFlatSalaryEmployee(Double Id , JSONArray ja){
        FlatSalaryEmployee obj = flatSalaryEmployeeService.findById(Id);
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
        single.put("Salary" , obj.getSalary());
        single.put("Last_Date_Paid" , obj.getLastSalaryRecieved());
        ja.add(single);
        return ja;
    }

    public static JSONArray updateFlatSalaryEmployee(Double Id , JSONArray ja) {
        FlatSalaryEmployee obj = flatSalaryEmployeeService.findById(Id);
        Iterator iter = ja.iterator();
        while (iter.hasNext()) {
            JSONObject single = (JSONObject) iter.next();
            Double id = (Double) single.get("Id");
            if (Id.equals(id)) {
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
                single.put("Union_Member", obj.isUnionMember());
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
                single.put("Salary", obj.getSalary());
                single.put("Last_Date_Paid", obj.getLastSalaryRecieved());
                ja.add(single);
                break;
            }
        }
        return ja;
    }

    public static JSONArray deleteFlatSalaryEmployee(Double Id , JSONArray ja) {
        FlatSalaryEmployee obj = flatSalaryEmployeeService.findById(Id);
        Iterator iter = ja.iterator();
        while (iter.hasNext()) {
            JSONObject single = (JSONObject) iter.next();
            Double id = (Double) single.get("Id");
            if (Id.equals(id)) {
                iter.remove();
                break;
            }
        }
        return ja;
    }
}

