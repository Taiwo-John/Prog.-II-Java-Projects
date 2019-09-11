For your first group assignment, we will ask you to continue your work from last week's assignment but in a team format.

You should start from an existing toothpicks implementation. Pick one of the files that a teammate worked on, or use the
solution provided in this folder. In all cases, I encourage you to read the solution. 

---
**What to submit:**
-------------------
- Update ImprovedToothpicks.java so that you can play the game, in addition to the new features listed below.
- A readme file *from each team member* with a reflection on the work done.
- Optionally, a separate file for extra credit.
---

**New requirements:**
- Offer players the option to restart a game after the game over. I should be able to keep playing all kinds of games
without restarting the application.
- Randomize who starts the game using Math.random() (This will return a double between 0.0 and 1.0)
- Whenever a game begin, choose if you want to play against a human opponent, or against an AI.
- Move your existing game logic to its own function, and create a second function where the game plays against an AI you
create.

Let's talk more about this AI. While the term may seem scary, ultimately what you need is a function that decides: Given 
the state of the game, what can I do? Which of these options should I pick?

At a minimum, create an AI that plays a random move: So pick a random valid pile, and a random valid number to take from 
that pile. I'm sure you can create a smarter one though! Make sure that your logic is well commented.

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
other human language. Do not show, share, read, or write any code to your peers. That's on them.

 
