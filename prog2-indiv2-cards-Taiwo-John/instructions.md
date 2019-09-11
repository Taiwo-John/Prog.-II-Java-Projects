#Individual assignment 2: Cards

This is a set-up assignment leading up to next week's group assignment. Most requirements should be straightforward.

For this assignment, I've provided a main function to each class with tests. As you make progress, 
run parts of the main function to test your progress.

##Step 1: The Card class

The Card class is straightforward: A card is made of a value (Ace, Queen, 7 etc.) and a suit (Hearts, Diamonds, Clubs, Spades).

Your two fields should be just that, one for value, and one for suit. Your constructor should expect those two.

The two methods I expect you to create are:
 - a toString method, which will display info about the card. Printing a Card should display something like 
 "King of spades" or "7 of diamonds" 
 - an equals method, which takes another card as a parameter, and returns whether or not the two cards are the same, in 
other words: same value, and same suit. Recall from the previous assignment how we can compare strings to each other!

## Step 2: The Deck class.

The Deck class is where you will find more complexity. First let's think about the field:
 - The first field is straightforward: size. This will keep track of how many cards are in the Deck. For now, once you 
 create your deck it should have 52 cards.
 - The second field is crucial: cards. This will be an array of Card objects. Your constructor should create all 52 cards 
 of a typical card deck. I'll expect you to create all the Heart cards (From Ace, 2, 3... to King), then the Spades, 
 Diamonds, and clubs.
 
Your constructor should take care of creating the array, and filling it with all the right cards.

As far as methods are concerned you will need 2:

**The display(n) method**

This method should take an int as a parameter, let's call it _n_. You will have to  print the first _n_ Cards in your Deck.
You are not removing them, just showing them. (This is like peeking into a stack, not popping from it.)

**The shuffle() method**

This method should swap cards from your array randomly, in order to shuffle the deck. 
I am not providing a specific algorithm here, try a few different approaches. A good rule of thumb is that you should 
change the previous position of every card in the deck. This should also be random!

A few things to keep in mind:
- Remember the exercise from last term for swapping the value of 2 variables using a third, temporary one? That will be 
useful here.
- To generate random integers, check out this great answer from Stack overflow
https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
