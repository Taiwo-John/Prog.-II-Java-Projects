package Game;

import Players.AIBrain;
import Players.AIPlayer;
import Players.Player;
import Players.HumanPlayer;

import java.util.Scanner;

public class Game {
    int turnCounter = 0;
    /* Note that Player is an interface that is implemented by both HumanPlayer and AIPlayer
    This means that you can later update your Game constructor to create the right kind of Player,
    depending on whether you want to play as two humans, or 1 human vs 1 AI (You could also do AI VS AI but you are not required for this project)
     */
    Player p1;
    Player p2;

    public Board gameBoard;

    public Game(){
        // TODO - phase 2: Allow for the creation of AI Players.
        p1 =  new HumanPlayer();
//        p2 =  new HumanPlayer();
        p2 = new AIPlayer();

        gameBoard = new Board(3, 3, true);
    }

    // Check if the state of the board is winning for player 1 or player 2.
    boolean gameOver(){
        return gameBoard.winningState(1) || gameBoard.winningState(2);
    }

    void play(){
        // Keep looping until the game is over.
        // figure out the current player. the playerId int is used later.
        Player currentPlayer = null;
        AIBrain ai;
        while (true){
            // Display the game board.
            System.out.println(gameBoard);

            int playerId = turnCounter % 2 + 1; // 1 for player 1, 2 for player 2
            if (playerId == 1){
                currentPlayer = p1;
            } else {
                currentPlayer = p2;
            }

            // Recall that a move is composed of 4 ints
            System.out.println("********* Player " + playerId + " is to play **************");
            int[] move = currentPlayer.getMove(gameBoard);
            // Keep asking for new moves until the player gives a valid one.
            while(!gameBoard.validateMove(playerId, move[0], move[1], move[2], move[3])){
                move = currentPlayer.getMove(gameBoard);
            }

            // If we are here then the move is valid, apply it.
            gameBoard.applyMove(move[0],move[1],move[2],move[3]);

            System.out.println("End of turn " + turnCounter);
            turnCounter++;

            if(gameOver()){
                System.out.println(gameBoard);
                if (currentPlayer == p1) {
                    ai = p2.learn(false);
                } else {
                    ai = p2.learn(true);
                }
                System.out.println("Game is over, winner is: " + (currentPlayer==p1? "Player 1": "Player 2"));

                System.out.println("Do you want to continue? Y or N");
                Scanner s = new Scanner(System.in);
                if (s.nextLine().equals("Y")){
                    gameBoard = new Board(3, 3, true);
                    turnCounter = 0;
                }
                else break;
            }
        }

        ai.saveBrain();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
