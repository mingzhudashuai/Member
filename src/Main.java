public class Main {
    public static void main(String[] args) {

        GoldCardMember a = new GoldCardMember("M01 M 28");

        int[] amounts = {1500, 3000, 8000, 12000, 40000};
        for (int amount:amounts
             )
            System.out.println(a.consume(amount));

        System.out.println(a.toString());


        SilverCardMember b = new SilverCardMember("M01 M 28");
        int[] amounts2 = {2000, 3000, 6000, 3000, 4000,50};
        for (int amount:amounts2
        ) {
            System.out.println(b.consume(amount));
        }


        System.out.println(b.getGenderAgeCost());
    }

}