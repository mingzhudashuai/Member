import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Member implements Comparable<Member>{
    private String memberId; //Every member have an id and the id is unique for each member
    private char gender; //Gender can only be 'F' or 'M'
    private int age; //Age can only be a positive integer

    private ArrayList<ProductCategory> categoryRecord = new ArrayList<>();
    private ArrayList<Integer> priceRecord = new ArrayList<>();
    private ArrayList<Double> paymentRecord = new ArrayList<>();


    protected double totalCost;

    public Member(String info){
        parseInfo(info);
    }


    private void parseInfo(String info){
        String[] infoGroup = info.split(" ");
        memberId = infoGroup[0];
        gender = infoGroup[1].charAt(0);
        age = Integer.parseInt(infoGroup[2]);
    }

    public abstract double consume(int amount);

    public double order(ProductCategory category, int price){
        double payment = consume(price);
        record(category,price,payment);
        return payment;
    }

    public List<String> getRecords(){
        ArrayList<String> records = new ArrayList<>();
        String record;
        for (int i=0; i< categoryRecord.size();i++){
            record = String.format("%s %s %d %.0f",memberId, categoryRecord.get(i).toString(),priceRecord.get(i),paymentRecord.get(i));
            records.add(record);
        }
        return records;
    }

    public double getTotalCost(){
        return totalCost;
    }
    public String getGenderAgeCost(){
        return String.format("%s %c %d %.1f",memberId,gender,age,totalCost);
    }


    public String toString(){
        return String.format("%s %c %d",memberId,gender,age);
    }

    public String getMemberId(){
        return memberId;
    }

    public void record(ProductCategory category, int price, double payment){
        categoryRecord.add(category);
        priceRecord.add(price);
        paymentRecord.add(payment);
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public int compareTo(Member member) {
        if (getAge()>member.getAge()){
            return -1;
        }else if(getAge()<member.getAge()){
            return 1;
        }else if (totalCost< member.getTotalCost()){

            return -1;
        }else if (totalCost> member.getTotalCost()){

            return 1;
        }else {
            return 0;
        }
    }
}

