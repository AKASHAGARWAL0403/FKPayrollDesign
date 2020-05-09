package Commission.Models;

import java.util.ArrayList;
import java.util.List;

public class CommissionList {
    private static Double lastID = Double.valueOf(0);
    private Double id;
    private Double rate;
    private List<CommissionBlock> commissionBlockList = new ArrayList<CommissionBlock>();

    public CommissionList(Double rate){
        this.rate = rate;
        this.id = lastID+1;
        lastID++;
    }

    public Double getRate() {
        return rate;
    }

    public CommissionBlock addCommission(String date , Double amount){
        commissionBlockList.add(new CommissionBlock(date , amount));
        return commissionBlockList.get(commissionBlockList.size()-1);
    }

    public List<CommissionBlock> getCommissionBlockList() {
        return commissionBlockList;
    }
}
