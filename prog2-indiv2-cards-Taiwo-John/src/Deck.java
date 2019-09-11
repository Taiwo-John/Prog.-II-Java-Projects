/*
    Taiwo Temidayo John
    Programming 2 (JAVA) Individual assignment 2.
    Cards: Deck Class
 */


// The Deck class creation
public class Deck {
    // Start with your fields

    /*
        Since the constructor loads in all the cards into the deck upon instantiation of the Deck class, I have created
        two arrays containing the four possible suits and 13 possible faces(values) respectively.
        I also created Card array, and declared the size variable.
     */
    String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
    String [] faces = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    public int size;
    Card [] deck;

    /*
        The Deck Class constructor
        In the constructor, the size variable is assigned the value 52, the number of cards in a deck.
        The size variable is also assigned to the length of the card array.
     */
    public Deck(){
        this.size = 52;
        deck = new Card[this.size];

        /* This line of code reads all 52 cards into the deck.
           It first loops through the suits, and then through the faces(values), then adds a card object in the array at
           the position card(where card is incremented from 0 to 51).
         */
        int card = 0;
        for (int suit = 0; suit < suits.length; suit++){
            for (int face = 0; face< faces.length; face++){
                deck[card] = new Card(faces[face], suits[suit] );
                card ++;
            }
        }

    }

    /*
        The display(n) function takes an integer n(the number of cards you want to display from the deck), and then
        loops through the deck and prints these cards. It leverages on the toString() method that was created in the
        Card class.
     */
    public void display(int n) {

        for (int i = 0; i < n; i++) {
            System.out.println(deck[i]);
        }
    }

    /*
        The shuffle method leverages on three integer variables.
        The variable count loops through the deck, the rand1 variable generates a random variable between 0 and 51 (52
        total), the same for the rand2 variable.

        a temporary card is created where the value of the card in the position rand1 is assigned, then the card in the
        rand 2 position is assigned to the rand1 position, and then the rand2 position is assigned the Card temp. This
        process switches the card positions and loops through all the cards in the deck.
     */
    public void shuffle() {
        int count, rand1, rand2;
        for (count = 0; count < size; count++) {
            rand1 = (int) (size * Math.random());
            rand2 = (int) (size * Math.random());

            Card temp = deck[rand1];
            deck[rand1] = deck[rand2];
            deck[rand2] = temp;
        }
    }

    /* The main method in which an instance(object) of the class is created and all other methods of the class are created
       through the instance(object)
     */

    public static void main(String[] args) {
        Deck testDeck = new Deck();
        testDeck.display(5);
        testDeck.shuffle();
        testDeck.display(5);
    }
}