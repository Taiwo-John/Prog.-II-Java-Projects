/*
    Taiwo Temidayo John
    Programming 2 (JAVA) Individual assignment 2.
    Cards: Card Class
 */

// Class definition
public class Card {
    // Start with your fields

    // Declaring the field variables, value of the card and its suit.
    public String value;
    public String suit;

    /* Defining the constructor, upon initial instantiation of the class, these initial values are assigned to the field
        variables through the constructor
    */

    public Card(String value, String suit){
        this.value = value;
        this.suit = suit;
    }

    // The toString method returns the value and suit of a card object in the format: value of suit (e.g ace of Hearts).
    public String toString(){

        return (this.value + " of " + this.suit);
    }

    /* The equals method is a boolean method which returns true if a card is equal to the card checked in terms of
        value and suit
    */
    public boolean equals(Card second_card){
        return (this.value.equals(second_card.value) && this.suit.equals(second_card.suit));
    }

    public static void main(String[] args) {

        Card ace1 = new Card("Ace", "Hearts");
        Card ace2 = new Card("Ace", "Clubs");

        // This should display " Ace of hearts"
        System.out.println(ace1);
        // This should display " Ace of Clubs"
        System.out.println(ace2);

        Card ace3 = new Card("Ace", "Clubs");
        // This should display True
        System.out.println(ace2.equals(ace3));

        // This should display False
        System.out.println(ace1.equals(ace3));
    }
}
