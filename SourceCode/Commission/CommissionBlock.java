package Commission;

public class CommissionBlock {
    private static Double lastID = Double.valueOf(0);
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

    public String getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return paid;
    }

    private void setPaid(boolean paid) {
        this.paid = paid;
    }
}
