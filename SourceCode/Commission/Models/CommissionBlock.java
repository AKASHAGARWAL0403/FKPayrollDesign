package Commission.Models;

public class CommissionBlock {
    private static Double lastID = 0D;
    private Double id;
    private String date;
    private Double amount;
    private boolean paid;

    public CommissionBlock(String date, Double amount) {
        this.date = date;
        this.amount = amount;
        this.paid = false;
        this.id = lastID + 1;
        lastID++;
    }

    public void setId(Double id){
        this.id = id;
        lastID = Math.max(id , lastID) + 1;
    }

    public Double getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String toString(){
        String res = "Commission ID is " + id + " for a amount of " + amount + " on date " +
                date;
        if(paid)
            res += "The Commission Amount has been paid to the Employee";
        else
            res += "The Commission Amount is not yet paid to the Employee";
        return res;
    }
}
