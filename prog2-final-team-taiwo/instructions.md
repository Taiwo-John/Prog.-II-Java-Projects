# Overview of the project.
If there is one technology that has made waves in the last few years, it is Machine Learning. Many of your peers have shown a keen interest in it, and next year you will take an AI and Machine Learning focused course in Term 2. The idea of this project however is to make the term Machine Learning less intimidating, by creating a very old school, but thorough learning machine. 

The inspiration for this project is heavily inspired by the following video. Get together with your team and watch it, then re-watch it, and make sure you get the ideas behind it before moving on to the rest of the instructions: [hexachess video](https://www.youtube.com/watch?v=sw7UAZNgGg8)

Now one disclaimer about the instructions below: A lot of the decisions about **HOW** to represent all the data needed for this game to happen are in your hands. I will not be answering questions like: "How do we store where the pieces are?" or "How can we tell who's turn it is?", at this point, this is well within your ability. I will however suggest a class structure for you to use.

Our objective is to re-create the simplest version of the game: Human VS AI, with the Human player always going first. The instructions below will guide you through this.

Your requirements are to:
- Implement all the features described in the 3 phases below.
- Comment your code thoroughly, and have each team  member share their own readme file
- Stick to the honor code principles, and make sure to acknowledge them in your readme.
- You do not have to use Java FX for this, however, using it properly will lead to extra credit that will be calculated in your final grade. This will not get you over the max grade for the project, but can help you offset some of the more challenging parts of the project.

## Phase 1: playing the game. 

**Phase 1 milestone: Read through all the provided code, and play a few games with your peers**

For this phase, we will focus on studying code provided to you. The code implements some the rules of the game, but you will have to finish some parts of it. Everyone in the team needs to be aware of some of the **decisions you will make in this phase**, but some team members can get started on other work once those decisions are made.

Let's do a quick recap of the basics from the video:
* The game is played on a 3 by 3 grid. (At this point you should know these things inside out :)
* Each player starts with 3 pieces on the top and bottom rows of the grid. As a convention, let's agree that **Player 1 will start at the bottom row, and Player 2 at the top row.**
* Pieces can move as follows:
	* Player 1's pieces can move upwards, while Player 2's pieces move downwards. 
	* A piece can move to the position directly in front of it if it is empty. (*recall that "front" means different things for player 1 and player 2*)
	* A piece can move diagonally if **there is an opposing piece in that position**, the opposing piece is then taken out of the game.
* Winning happens as follows:
	* If only the pieces of one player remain on the board, that player won.
	* If a piece from a player makes it to the opposing edge of the board, that player wins.
	* If a player has no legal moves at the beginning of their turn, that player loses. 

In order to bring this to life in our code, we will need a few specialized classes:

**The Game class**: This class should keep track of key elements of the game: How many turns has the game lasted,  the state of the board, as well as whose turn it is. 

To do the later, it will have 2 Player objects. At the moment, these are actually HumanPlayer objects, which already know how to ask a human user for a move. The class keeps track of which one is currently playing.

The loop for the Game class is simple:
- Check if the game is over
- Figure out the current player
- Keep asking that player for a move until the player gives a valid move
- Apply the move
- Repeat

** The Board class:**
Figuring out if a move is valid, and if the game is over is the responsibility of the **Board** class. 

This class is the guardian of the rules. You should have a method in this class that checks if anyone has won, and a method that determines whether or not a given move is valid. Obviously, you should write tests for both those methods.

You should also be able to change the state of the game board, moving pieces based on *valid* moves. How do you describe a move? Well we will be using 4 ints: The coordinates of the piece you want to move, and the coordinates of where it is going.Here again, you will need tests.


**The Player classes**: Note here that I am saying classe**s**, as we will need quite a few to accomplish our goal. Let's start with the simplest one: The human player. 

Essentially, the human player only requires one method: getMove() This should show the board to the player, and ask them to input a move. It should keep prompting the player until the player provides a valid move. You should be leveraging methods from the Game class here. 

Once you have implemented these classes, you should be able to play a game within your team by printing strings and inputing moves. Take a screenshot of that and include it in your submission.

Make sure that you thoroughly test your ability to assess if a move is valid, and if the game is over before moving on.

## Phase 2: Introducing the AI player.
**Phase 2 milestone: Being able to play a game against a very, very, very basic AI**	

Let's look at the other Player classes: the AI Player. This class will be more complex than the human player, but ultimately, it should also have a **getMove()** method since it implements the same interface, which we will use to get a valid move to play. 

Initially, as shown in the video, our AI should randomly pick between all the valid options it has. Let's recap how it's done in the video:
- There is a matchbox for each state that can be achieved in the game (keep in mind that the video only shows an AI for player 2, but that's fine: Your AI players will always go second)
- We know all the valid moves for a given state, each of these moves is represented by a color, and the boxes have a bead of each color. 
- When it comes time for the AI to play, shake the box and get a bead, that will tell you how the AI "decided" to move. 

We should replicate the same idea in our AI:
- The AI should **recognize** the board state.
- The AI should then **randomly** pick from within the set of possible moves for that particular board state.

What data structures will be needed for this? That is the biggest part of the assignment, and is your responsibility to figure out(Hint, [Hashmaps](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) may be handy here, but aren't the only way to do this.). Make sure that you figure out - and explain through comments - the following:
- How do you represent a board state?
- How do you represent a move? 
- How do you connect state to moves?

This data structure is really the ***BRAIN*** of your AI! In fact I created a Brain class in my solution just to keep track of this. 

Once you've built this, you should be able to play the game against the AI. After the human player plays, the AI should should look at the board state, find the matching moves, and then pick a **random** one out of the group.

Picking at random is a key insight to machine learning, you don't write code to decide what move is best here, it's expected and normal for your random playing AI to be terrible... at first. The next phase will fix it. 

*Hints*:
- If you look closely at the video (Pause at 3:03) , you can see all the states you care about for this project are displayed there as the matchboxes: The picture on the matchbox is the board state, and the arrows are the moves. I recommend that you *transcribe* this information into a file, and write code to read that file and create your **Brain** using the data in it. 
- Moreover, if you look closely at the states in the video, you'll realize they are somewhat incomplete. For example in turn 2, it shows only 2 states, but there are 3 aren't there? The first player could move either their left, middle, or right piece. The reason why they are using only two states instead of 3 is because two of those states are *symmetrical!*. This means you have two options:
	- Either build logic in your code so that you only use the 24 states from the video, but detect symmetry in the game so they are always relevant. 
	- Make sure that you document all possible states, ignoring the advantage of symmetry.
- Both approaches are valid, but make sure to clearly state which one you are doing.


## Phase 3: Learning

**Phase 3 milestone: Smart AI, and continuity between games**

How do we learn? Fundamentally, it's about recognizing what the "good" behaviors are, and what the "bad" ones are for a given set of tasks. In order to give your AI the ability to do just that, you must keep track of **all moves your AI made for specific board states**.

Once the game is done. You should call a function that will leverage that information:
- If the AI lost, then those moves were not good. You should update the AI's brain accordingly! Remove those options for the specific board states that happened this game.
- If the AI won, then great, we want to *incentivize* the AI to play those same moves more often. We should make those options more likely to happen again. This can be done in a few different ways, I'll let you figure it out. 

Once you achieve this, you should be able to play multiple games against the same AI object and notice it improving, after about 5 games it should be putting up a pretty good fight - especially if you keep playing the same way, so diversify!

To make the training simpler, update your game class so that it asks you at the end of each game if you want to start a new one.

There is an important bit of information in that previous sentence: Playing against the **same AI**, the one that has been learning, is key! make sure that your AI object is not reset between games so you can experience the learning.

Moreover, it's no fun if whenever you start your programme you have to play against a "dumb" AI and train it all over again. We must save our ***BRAIN!***

If you want something to be remembered between when you quit your programme and when you run it again, you must use [files](https://www.tutorialspoint.com/java/java_files_io.htm). Your solution should be able to save the brain once you quit the programme, and give you option to load a "brain" once you run it again. 

Your Brain class should have two methods: a saveTo method which takes a path and writes a file with all the brain information, and a createFrom method which takes a path to a file, opens it, and uses its content to set up our AI. 

Your game class should be updated so that:
- The start of the game asks you for whether you want to play a "fresh" AI or one from a file - note that your "fresh" AI **may** still need a file depending on your implementation, that file is just the default behavior of the AI.
- The end of the game should ask you whether or not you want to save the AI to a file.