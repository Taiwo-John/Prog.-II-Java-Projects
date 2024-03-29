import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    // Start with your fields
    public Card[] cards;
    public int size;

    // Define your constructor
    public Deck(){
        String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        size = suits.length * values.length;
        cards = new Card[size];
        int count = 0;
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < values.length; j++) {

                cards[count] = new Card(values[j], suits[i]);

                count++;
            }
        }
    }


    // Create all the methods from the instructions.

    public void display(int n){
        int printingLimit = n;
        // We want to print n cards, but what if n is bigger than the size of the deck?
        // In that case, I decided to just print what's left in the deck.

        // This is an in-line if statement, which works if there is only 1 thing you want to do
        // if the condition is true.
        if (n > this.size) printingLimit = this.size;


        for (int i = 0; i < printingLimit; i++){
            System.out.println(cards[i]);

        }

    }

    public void shuffle(){
        int randomIndex;
        Card temp; // Used in swapping
        for (int i = 0; i < size; i++){
            // Get a random index to send the current card to
            randomIndex = ThreadLocalRandom.current().nextInt(0, size);

            // Swap the card at position i, with the one at position randomIndex
            temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
    }


    //    New requirement for Deck Class
//    Let create a method
    //        This is how we should print the 9 card for user to pick on in mind
    public Card[] deal(int n) {
        Card[] deal = new Card[n];
        this.shuffle();
//        update n element from the first n element of Card deck
        if (n <= size){
            for (int i=0; i < n; i++){
                deal[i] = cards[i];
            }
//            print the n element of Array created
//            System.out.println(Arrays.toString(newcard));
//            System.out.println(cards.length);
        }else {
            System.out.println("You cannot deal more than " + this.size);
        }
        Card[] newDeck = new Card[this.size - n];
        int j = 0;
        for (int i = n; i < this.size;i++){
            newDeck[j] = cards[i];
            j++;
        }

        cards = newDeck;

        this.size = cards.length;

//        print info about dealing with the card

//        if (cards.length > newcard.length){
//            cards = newcard;
//            System.out.println(Arrays.toString(cards));
//            if (cards == newcard){
////                System.out.println("We have deal with the card deck and you can't deal anymore");
//            }
//        }
//        return the cards with n element
        return cards;
    }





    // You should comment out each few lines in main as you make progress, this will help
    // you test that your work is correct.

    public static void main(String[] args) {

        Deck testDeck = new Deck();
        // By default, your deck should create all 52 cards in the following order:
        // You should start from Ace, 2, 3, 4, 5 ... All the way to King
        // This should happen for Hearts, Spades, Diamonds, and clubs

        // This should print 52, as there are 52 cards in a given deck
        System.out.println(testDeck.size);

        // This should print the first 5 cards of the deck, based on the instructions above,
        // This should print Ace of hearts, 2 of hearts, 3 of hearts, 4 of hearts, then 5 of hearts.
//            testDeck.display(1);

        // This method should swap the location of cards randomly.
        // We've used random behavior before in our code, for this time around, I'll refer you to
        // a great Stack overflow answer for a few ways of doing that in Java
        // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java


        testDeck.shuffle();


        //This time, displaying the first few cards will be random
//            testDeck.display(9);


//       - This method called deal creates a new array called newCard and assign the first 9 element in.
//       - It Check info about if we have deal with card deck by comparing the new array we created from the cards deck
//        if they are the same, which means we can print a message that say that we already have dealt with the card deck,

//        testDeck.deal(3);

    }
}