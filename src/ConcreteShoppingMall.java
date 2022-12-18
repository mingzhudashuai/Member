import java.util.*;

public class ConcreteShoppingMall implements ShoppingMall{

    private List<Member> members = new ArrayList<>();
    private HashMap<ProductCategory,Integer> orderNumberTable = new HashMap<>();
    private HashMap<ProductCategory,Double> paymentTable = new HashMap<>();


    @Override
    public void addMember(String info) {
        char type = info.split(" ")[3].charAt(0);
        switch (type){
            case 'G':
                members.add(new GoldCardMember(info));
                break;
            case 'S':
                members.add(new SilverCardMember(info));
        }

    }

    @Override
    public void addMember(List<String> infos) {
        for (String info:infos
             ) {
            addMember(info);
        }
    }

    @Override
    public Member getMember(String memberId) {
        for (Member member:members
             ) {
            if (member.getMemberId().equals(memberId)){
                return member;
            }
        }
        return null;
    }

    @Override
    public double placeOrder(String memberId, int cost, ProductCategory category) {
        Member member = getMember(memberId);
        double payment = member.order(category, cost);

        if (!orderNumberTable.containsKey(category)){
            orderNumberTable.put(category,1);
            paymentTable.put(category,payment);
        }else{
            orderNumberTable.put(category,orderNumberTable.get(category)+1);
            paymentTable.put(category,paymentTable.get(category)+payment);
        }

        return payment;
    }

    @Override
    public List<String> getMemberRecords(String memberId) {
        Member member = getMember(memberId);
        return member.getRecords();
    }

    @Override
    public List<String> getCostByCategory() {
        ArrayList<String> records = new ArrayList<>();
        String record;
        int orderNumber;
        double payment;
        for (ProductCategory category: ProductCategory.values()
             ) {
            if (orderNumberTable.containsKey(category)){
                orderNumber = orderNumberTable.get(category);
                payment = paymentTable.get(category);
            }else{
                orderNumber=0;
                payment=0;
            }
            record = String.format("%s %d %.0f", category.toString(), orderNumber, payment);
            records.add(record);
        }

        return records;
    }

    @Override
    public List<String> getMemberRecordByGenderAndAge(char gender, int lowerAge, int upperAge) {


        ArrayList<String> records = new ArrayList<>();
        for (Member member: members
             ) {
            if (member.getGender()==gender && member.getAge()>lowerAge && member.getAge()<upperAge){
                records.add(member.getGenderAgeCost());
            }
        }

        return records;
    }

    @Override
    public double getTotalCost(String memberId) {
        return getMember(memberId).getTotalCost();
    }

    @Override
    public double getTotalIncome() {
        double income = 0;
        for (Member member: members
             ) {
            income += member.getTotalCost();
        }
        return income;
    }
}
