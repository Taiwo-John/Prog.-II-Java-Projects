# Instructions
---------------

For your first individual assignments, your task is to create a program that allows 2 players
to play the Toothpicks game we've tried in class.

You can try the game yourself as well, here is a recap of the rules:
- Split your toothpicks into 3 groups of 3.
- Each player take turns removing 1, 2, or 3 toothpicks from any group. You can't take toothpicks
from multiple groups at once.
- The player to remove the last toothpick loses.

# Key requirements
-----------------
Your requirements are as follow:
- Prompt both players for their name.
- Display the state of the game to the players. i.e: How many toothpicks are in each group?
- The current player should tell you which group they want to pick from, then how many toothpicks they want to pick.
    - You must only accept valid inputs for the group name, or for the number of toothpicks.
    - If the user gives you the wrong input, keep asking them until they give you the correct one.
- When a player removes the last toothpick, declare them the loser, and the other player the winner. The game ends then.

# Advice:
---------
You are free to approach this problem whichever way you want, but here are a few hints.

- Have a test that checks for if the game is over, this will allow you to loop the game
as often as needed.
- You can simplify your task by having a few functions. For example in my solution I have a void function
that prints the state of the game. I have a function that keeps asking the user for a move until its valid, then returns
the valid move...
- The scanner class is tricky, and can be buggy. I would recommend you do the following in your code:
If you have something like this:
        ```
                Scanner moveReader = new Scanner(System.in);
                int toothpicksToPickUp = moveReader.nextInt();
        ```

Follow it with the line to avoid complications. I will explain this issue in details in class on Monday.
        ```
                moveReader.nextLine();
        ```

# Grading
-------
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
other human language. Do not show, share, read, or write any code to your peers. That's on them.


What to turn in
---------------

Make sure your git repository contains the following:
- Your updated version of the ToothpicksGame file.
- Optionally: a second java file for the extra credit version of the simulation
- A text file describing the following:
    - An acknowledgement of upholding the honor code, or information if any breach occurred.
    - Any extra credits or additional features you attempted.
    - Any notes you want to bring to the attention of the grader. 


Extra Credit ideas:
------------

- Configure the initial state of the game.
- Randomize which player goes first.
- Allow players to restart the game at the end, potentially keeping score.