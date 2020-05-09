import Testing.DataLoader;

public class MainClass {
    public static void main(String args[]){
        DataLoader dataLoader = new DataLoader();
        dataLoader.callAddDataWorkHour();
        dataLoader.getData().forEach(item -> System.out.println(item.getName()));
    }
}
