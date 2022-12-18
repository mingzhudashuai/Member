public class SilverCardMember extends Member{

    private double points;
    private double ratio;
    public SilverCardMember(String info) {
        super(info);
        ratio = 1.0;
    }

    @Override
    public double consume(int amount) {
        double newPoints = (amount/30)*ratio;
        double payment = amount;
        if (payment>points){
            payment-=points;
            points = newPoints;
        }else{
            payment = 0;
            points+=newPoints-payment;
        }

        totalCost +=payment;
        if (totalCost>10000)
            ratio=1.5;

        return payment;
    }

    public String toString() {
        return String.format("SilverCardMember: %s points=%.1f",super.toString(),points);
    }

}
