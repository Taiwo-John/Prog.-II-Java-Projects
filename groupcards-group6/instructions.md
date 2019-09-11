For your second group assignment, we will ask you to continue your work from the card and deck assignment. The objective
of this assignment is to get you to use classes you made in more complex contexts. At any point, feel free to modify your 
Card and Deck class to make your work here simpler (But make sure to comment!)

This assignment is also fairly complex, so make sure to organize your team properly.

You should start from an existing implementation. Pick one of the files that a teammate worked on, or use the
solution provided in this folder. In all cases, I encourage you to read the solution for insight 

---
**What to submit:**
-------------------
- Update the Deck class with a new required method.
- Update MagicTrick.java so that you can perform the magic trick we did in class.
- Update PokerHand.java so that you can check two poker hands and see which one is strongest.
- A readme file *from each team member* with a reflection on the work done. This must include an acknowledgment of 
upholding the honor code.
- Optionally, a separate file for extra credit.
---

**Requirements: Deck**

We will need to add a feature to the Deck class to enable what's coming next: the **_deal_** method.

**_deal_** will take as a parameter an int _n_, which should be less than or equal to the current size of the deck.

**_deal_** will then create a Card array of size _n_, and put the first _n_ cards from the deck into that array.

Before returning the array, we need to update the information about our deck, it's size has increased after all. If a 
card has been dealt from the deck, it should never be dealt again.

This means we will have to update the fields of our deck, but remember, arrays are fixed in size. You will have to create
a new array with the remaining cards.

**Requirements: MagicTrick**

In your MagicTrick class, you must have a main function that runs a use through the steps of the magic trick:
- Show the user 9 cards, and ask them to remember one. 
- Show the cards split into 3 groups, ask them for which group contains their card. 
- Show the cards split into 3 new groups, ask them again for which group contains their card.
- Reveal the correct card.

More instructions on the details of the trick can be found in the presentation that was shared with you via email.

I leave the details of the implementation up to you, you don't have to follow the matrix approach from the instructions, 
but you should display the correct card by the end of it.

**Requirements: PokerHand**

Poker is a card game that involves players comparing hands of 5 cards, and deciding which one is strongest. Your task
is to create a method that can look at an array of 5 cards, and say what kind of combination it makes. 

For more information on hand rankings, check out this link: https://www.cardplayer.com/rules-of-poker/hand-rankings 

Let's make sure we understand the hands going from strongest to weakest. Read this before you start implementing:
- The strongest hand is a straight flush:  i.e all the cards have the same suit, and their values follow one another. The 
strength of a straight flush is determined by it's highest card.
- The next strongest hand is the 4 of a kind  . You should print which value showed up 4 times.
- The next strongest hand is the full house: You should print"Full house: 3 X, 2Y", where X and Y are the values of the 
cards that showed up 3 times and twice respectively.
- The next strongest hand is the flush: When you have 5 cards of the same suit, but they don't follow each other 
(Otherwise you'd have the much stronger straight flush).
- The next strongest hand is the straight: When you have 5 cards that follow each other, but aren't all of the same suit.
This should display very similarly to the straight flush.
- The next strongest hand is the 3 of a kind, with 2 random cards. Very similar to 4 of a kind, except you only have a value repeating 3 times.
- The next strongest hand is two pairs, then a random card.
- The next strongest hand is the pair, then 3 random cards.
- Finally, if you can't make any of the combinations above, simply display the highest value.

Bear in mind for the straight flush and the straight, that Ace is a tricky card. You can have a Straight of Ace, 2, 3, 
4, 5. In this scenario, the straight is from Ace to five. You can also have a straight of 10, Jack, Queen, King, Ace, or
in other words a straight from ten to Ace. 

**Readmes** 

For your individual readmes, I expect the following:
- What role did you play in the team? This can be features you coded, research you did, ideas you contributed.
- What was challenging about your first peer assignment? Do not only think about what you did, but also how you did 
	it
	-	What did you do about this challenge?

Grading
------------
Each team will share these grades. The only exception is for each member not submitting their individual reflection,
 this will drop your individual final grade by 1 point.

Correctness:
    - Does the code run? Can I play a game without it crashing? This is graded without looking at your code,
    just by running your program.
    
Coding proficiency:
    - You correctly use functions to organize the code's logic
    - Any logic in the code is handled clearly and elegantly. If statements are used appropriately.

Style:
    -   Clear design and organization.
    -   Good variable names, function names, and comments.
    -   Functions where appropriate.

## Honor Code

Please make sure that you fully understand the Academic Honor System, and reach out if you need any clarifications. 
As a reminder: You can discuss the assignment with your peers, but you can only do so in English or Kinyarwanda or some 
other human language. Do not show, share, read, or write any code to your peers. Figuring it out the code is their 
responsibility.