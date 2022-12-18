public class GoldCardMember extends Member{

    public GoldCardMember(String info) {
        super(info);
    }

    @Override
    public double consume(int amount) {
        {

            int[] range = {0,2000, 5000, 10000, 20000};
            double[] discount = {0, 0.05, 0.10, 0.15, 0.20};

            double payment = 0;

            for (int i=1;i<5;i++){
                if (amount>range[i]){
                    payment += (range[i]-range[i-1])*(1-discount[i-1]);
                }else{
                    payment += (amount-range[i-1])*(1-discount[i-1]);
                    break;
                }
            }

            if (amount>range[4]){
                payment += (amount-range[4])*(1-discount[4]);
            }

            totalCost += payment;
            return payment;
        }
    }

    public String toString() {
        return String.format("GoldCardMember: %s",super.toString());
    }



}
