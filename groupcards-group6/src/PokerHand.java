import java.util.Arrays;

public class PokerHand {
    private static String[] suitArray = new String[5];
    private static String[] valueArray = new String[5];
    private static   Integer[] myarray=new Integer[5];
    private static int full1 = 0, full2 = 0;

    private static int hand_rank;



/*
    This is the readHand method that is used to tell the poker hand of a deal and rank them.
 */
    public static void readHand(Card[] hand){

        /*
            This loop turns the values of our special cards like ace, jack, queen and king into numbers in the order
            that they appear in a playing card. This is necessary due to our implementation technique.
         */
        for (int card = 0; card < hand.length; card++){

            if (hand[card].value.equals("Ace")){
                hand[card].value = "1";
            }
            else if (hand[card].value.equals("Jack")){
                hand[card].value = "11";
            }

            else if (hand[card].value.equals("Queen")){
                hand[card].value = "12";
            }

            else if (hand[card].value.equals("King")){
                hand[card].value = "13";
            }

        }


        /*
         Let's create a suit array and a Value array of what we got as Object hand We store the values and the suits
         of the card that we deal in these arrays.
        */

        for (int card = 0; card < 5; card++){
            suitArray[card] = hand[card].suit;
            valueArray[card] = hand[card].value;
        }

        /*
            We use the following code to convert the value array into integers.
         */
        int value =0;
        for(String str:valueArray){
            myarray[value]=Integer.parseInt(str);//Exception in this line
            value++;
        }

        // If the hand is a straight flush, we print the hand name and rank it.
        if(straightflush()){
            System.out.println("We have a straight flush");
            hand_rank = 9;
        }

        /* If the hand is a four of a kind, we print the hand name and rank it
            Also, the four of a kind method returns the value of the card that appears four times.
         */

        else if (four_Of_Kind()){
            System.out.println("We have four of a kind");
            hand_rank = 8;
        }

        /*
            If the hand is a full house, we print the hand name and also print the two full house cards as well rank it.
         */
        else if(full_house()){
            System.out.println("We have a full house");
            System.out.println("Full house: " + full1 + " and " + full2);
            hand_rank = 7;
        }

        // If the hand is a flush, we print the hand name and rank it.
        else if(Flush()){
            System.out.println("We have a flush");
            hand_rank = 6;
        }

        // If the hand is a straight, we print the hand name and rank it.
        else if (Straight()){
            System.out.println("We have a straight hand");
            hand_rank = 5;
        }

        // If the hand is a 3 of a kind, we print the hand name and rank it.
        else if (Three_Of_a_kind()){
            System.out.println("We have 3 of a kind");
            hand_rank = 4;
        }

        //If the hand is a two pair, we print the hand name and rank it.
        else if (Two_pair()){
            System.out.println("We have 2 pair hand");
            hand_rank = 3;
        }

        // If the hand is a one pair, we print the hand name and rank it.
        else if (One_Pair()){
            System.out.println("We have 1 pair hand");
            hand_rank = 2;
        }

        // If the hand does not fit the hand types, we simply return the card with the highest value.
        else{
            The_Highest();
            hand_rank = 1;
        }

    }


//    This is the straight flush: Five cards in numerical order, all of identical suits.=============================
    public static boolean straightflush() {
            int count = 0;

            // We first sort the array, to put the cards in order.
            Arrays.sort(myarray);

            /*
                Next we loop through the array and check if the next card is greater than the former by 1, and also
                if the suits of the cards are equal, if that is true, we implement count.
                We return true if count is 4, else, we return false because the check cases are 4.
             */
            for (int i=0; i < myarray.length - 1; i++){
                if((myarray[i + 1] - myarray[i] == 1) && suitArray[i].equals(suitArray[i + 1])){
                    count++;
                }
            }
            if (count == 4){
                return true;
            }
            else {
                return false;
            }
    }

//    This is four of Kind: Four cards of the same rank, and one side card or ‘kicker’.===================================

    public static boolean four_Of_Kind(){
        int count=0; int repeating = 0;

        // We sort the cards so that we have them in order. This is to enable us have the four repeating values in order
        Arrays.sort(myarray);

        /*
            Next, we loop through to check if adjacent cards are equal in value. If they are equal, we store the value of
            the card, and increment count.
         */
         for (int i=0; i < myarray.length - 1; i++){

             if(myarray[i].equals(myarray[i + 1])){
                 repeating = myarray[i];
                 count++;
             }
         }

         /*
         If the value of count is 3 and the hand is not full house, we return true and print the repeating card, else,
          we return false.
          */
         if (count == 3 && !full_house()){
             System.out.println("repeating card is " + repeating);
             return true;
         }
         else {
             return false;
         }
    }

//        This is the full house: Three cards of the same rank, and two cards of a different, matching rank.============================
        public static boolean full_house(){
            int i = 0;
            int j = 2;

            /*
                For the full house, we first sort the array, and then  explored all the possible cases of having two
                small numbers and 3 large numbers or 3 small numbers and 3 large numbers, which will give us a full
                house. If the condition is met, we return true and store the numbers that occur, else, we return false.
             */
            Arrays.sort(myarray);
                if (myarray[i].equals(myarray[i+1]) && myarray[j].equals(myarray[j + 1]) &&  myarray[j].equals(myarray[j + 2])
                || myarray[i].equals(myarray[i+1])&& myarray[i].equals(myarray[i+ 2]) && myarray[j + 1].equals(myarray[j + 2]) ){

                    full1 = myarray[i]; full2 = myarray[j+1];
                    return true;
                }
                else{
                    return false;
                }

            }

// Flush Implementation : Five cards of the same suit
    public static boolean Flush(){

        /*
            For the flush, we first sort the array, and check if their suits are equal. If yes, we increment count.
            We return true if count is 4, because by then all the cards would have been checked. We return false otherwise.
         */
        int count = 0;
        Arrays.sort(suitArray);

        for (int i =0; i < suitArray.length - 1; i++){
            if (suitArray[i].equals(suitArray[i+1])){
                count = count + 1;
            }
        }
        if(count == 4){
            return true;
        }
        else {
            return false;
        }
    }

//  This is Straight: Five cards in sequence.
    public static boolean Straight(){
        /*
            For the straight, we sort the array, and loop through to check if two adjacent card values differ by 1.
            If yes, we increment count, and we return true if count = 4 (this would have made sure all cards have been
            checked). We return false otherwise.
         */
        int count = 0;
        Arrays.sort(myarray);
        for (int i=0; i < myarray.length - 1; i++){
            if((myarray[i + 1] - myarray[i] == 1)){
                count++;
            }
        }
        if (count == 4){
            return true;
        }
        else {
            return false;
        }
    }


//    This is Three of a kind: Three cards of the same rank, and two unrelated side cards.

    public static boolean Three_Of_a_kind(){

        /*
            We first sort the array, and check if two adjacent card values are equal. If yes, we increment count and
            then we check if count is equal to 2 (this would ensure that three cards of the same type has been found)
            and also, if there is no two pair. If these two conditions are met, we return true. We return false otherwise.
         */
        int count=0;
        Arrays.sort(myarray);
        for (int i=0; i < myarray.length - 1; i++){

            if(myarray[i].equals(myarray[i + 1])){
                count++;
            }
        }

        if (count == 2  && !Two_pair()){
            return true;
        }
        else {
            return false;
        }
    }

// This is Two pair: Two cards of a matching rank, another two cards of a different matching rank, and one side card.
    public static boolean Two_pair(){

        // For two pair, we first sort the array and explore all the possibilities.
        int i = 0;
        int j = 2;
        Arrays.sort(myarray);

        // These are all possibilities that the two pair appears

        if ((myarray[i].equals(myarray[i+1]) && myarray[j].equals(myarray[j + 1]) && !myarray[j + 2].equals(myarray[i]) && !myarray[j + 2].equals(myarray[j])
            || myarray[i + 1].equals(myarray[i+2]) && myarray[j + 1].equals(myarray[j + 2]) && !myarray[i].equals(myarray[i + 1]) && !myarray[i].equals(myarray[j + 1])
                || myarray[i].equals(myarray[i+1])&& myarray[j + 1].equals(myarray[j + 2])  && !myarray[j].equals(myarray[i]) && !myarray[j].equals(myarray[j + 1]))
                && !four_Of_Kind()){
            return true;
        }
        else{
            return false;
        }
    }

//    This is One pair: Two cards of a matching rank, and three unrelated side cards.
    public static boolean One_Pair(){

        /*
            For one pair, we first sort the array, and then we loop through the array to see if two adjacent card values
            are equal, if yes, we increment count.
            We then check to see if count is equal to 1 (this would be true if we have only one pair). If that is true,
            we return true, otherwise, we return false.
         */
        int count = 0;
        Arrays.sort(myarray);
        for (int i =0; i < myarray.length - 1; i++){
            if (myarray[i].equals(myarray[i+1])){
                count = count + 1;
            }
        }
        if(count == 1){
            return true;
        }
        else {
            return false;
        }
    }

//    This is the Highest : if you can't make any of the combinations above, simply display the highest value.
    public static int The_Highest() {

        /*
            We check to see if none of the combinations above are met, then we sort the cards in the array in ascending
            order and return the card in the last index of the array (the highest card).
          */

        if (!straightflush() && !four_Of_Kind() && !full_house() && !Flush() && !Straight() && !Three_Of_a_kind() && !Two_pair() && !One_Pair()) {
            Arrays.sort(myarray);
        }
        return myarray[myarray.length -1];
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

// comment the lines below back in when you have implemented the deal method in Deck.
        Card[] hand1 = deck.deal(5);
        Card[] hand2 = deck.deal(5);
        Card[] hand3 = deck.deal(5);

        readHand(hand3);
        readHand(hand2);
    }
}