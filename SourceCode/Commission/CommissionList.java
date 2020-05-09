package Commission;

import EmployeeTypes.Employee;

import java.util.ArrayList;

public class CommissionList {
    private static Double lastID = Double.valueOf(0);
    private Double id;
    private ArrayList<CommissionBlock> commissionBlockArrayList = new ArrayList<CommissionBlock>();

    public CommissionList(){
        this.id = lastID+1;
        lastID++;
    }

    public CommissionBlock addCommission(String date , Double amount){
        commissionBlockArrayList.add(new CommissionBlock(date , amount));
        return commissionBlockArrayList.get(commissionBlockArrayList.size()-1);
    }

    public ArrayList<CommissionBlock> getCommissionBlockArrayList() {
        return commissionBlockArrayList;
    }
}
