package EmployeeTypes.Models;

public class TimeCard{
    private Long hours;
    private Boolean recieved;

    public TimeCard(){
        hours = 0L;
        recieved = false;
    }

    public TimeCard(Long hours){
        this.hours = hours;
        recieved = false;
    }

    public void setRecieved(Boolean recieved) {
        this.recieved = recieved;
    }

    public Long getHours() {
        return hours;
    }

    public Boolean getRecieved() {
        return recieved;
    }
}
