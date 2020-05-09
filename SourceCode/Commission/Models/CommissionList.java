package Commission.Models;

import java.util.ArrayList;
import java.util.List;

public class CommissionList {
    private static Double lastID = 0D;

    private Double id;
    private Double rate;
    private List<CommissionBlock> commissionBlockList = new ArrayList<CommissionBlock>();

    public CommissionList(Double rate){
        this.rate = rate;
        this.id = lastID+1;
        lastID++;
    }

    public void setId(Double id) {
        this.id = id;
        lastID = Math.max(lastID , id)+1;
    }

    public Double getId() {
        return id;
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

    public String toString(){
        String res = "";
        res += "Commission Id " + id + "\n";
        res += "Commission Rate is " + rate + " \n";
        for(CommissionBlock item : commissionBlockList){
            res += item + "\n";
        }
        return res;
    }
}
