import java.util.Scanner;
/**
 * Allows the user to play a simple game of toothpicks against another human opponent.
 * You may use this as the starting point for your group assignment, or use one of your previous submissions.
 *
 * @author Mehdi Oulmakki
 * @version 28/1/2019 updated for ALU Rwanda
 */

public class ToothpicksSolution {

    public static void main(String[] args){
        // These 3 variables will track how many toothpicks are in each pile.
        int pileA = 3;
        int pileB = 3;
        int pileC = 3;

        // Set up the reader.
        Scanner reader = new Scanner(System.in);

        // We should keep track of the names of our players, let's initialize 2 strings.
        // At any point in time, there will be a player playing, and another waiting
        String currentPlayer;
        String nextPlayer;

        // Prompt both players for their name
        System.out.print("Player 1, enter your name: ");
        currentPlayer = reader.nextLine();

        System.out.print("Player 2, enter your name: ");
        nextPlayer = reader.nextLine();

        // Display the state of the game.
        // I figured I would do this a lot, and I want it to look pretty, so I created a function for it.
        printGame(pileA, pileB, pileC);

        // The game stops when all piles are empty,
        // Therefore, the game should keep going while it is NOT the case that all piles are empty.
        while(! (pileA == 0 && pileB == 0 && pileC == 0) ){

            // I have a method that checks if the user input for the pile is legal or not, more details below
            // currentPile should be either A, B, or C
            String currentPile = getLegalPile(currentPlayer, pileA, pileB, pileC);

            int toothpicksToPickUp;

            // These if statements are getting repeated, we can probably improve this.
            if (currentPile.equals("A")){
                // getLegalMove will make sure that the user input is correct
                toothpicksToPickUp = getLegalMove(currentPile, pileA);
                pileA -= toothpicksToPickUp;
            }
            else if (currentPile.equals("B")){
                toothpicksToPickUp = getLegalMove(currentPile, pileB);
                pileB -= toothpicksToPickUp;
            }
            else { // This means we are dealing with pile C, I trust that my getLegalPile function will guarantee that.
                toothpicksToPickUp = getLegalMove(currentPile, pileC);
                pileC -= toothpicksToPickUp;
            }

            // Let's print out the current user's move.
            System.out.println("Taking " + toothpicksToPickUp + " from " + currentPile);

            // Print the new state of the game.
            printGame(pileA, pileB, pileC);

            // Swap the current player with the next player
            // We have seen this trick last term!
            String temp = currentPlayer;
            currentPlayer = nextPlayer;
            nextPlayer = temp;
        }

        // Once we get out of the loop, print out who made the last move, and declare a winner!
        System.out.println(nextPlayer + " picked up the last toothpick! " + currentPlayer + " has won!");

    }

    // This is a void method that just prints the game.
    // If you want to try the fancy display stretch goal, this is the method to modify!
    public static void printGame(int pileA, int pileB, int pileC){
        System.out.println("**********************************");
        System.out.println("# of toothpicks in pile A: " + pileA);
        System.out.println("# of toothpicks in pile B: " + pileB);
        System.out.println("# of toothpicks in pile C: " + pileC);
        System.out.println("**********************************");
    }

    // This method asks the user for a pile, then checks if it is valid.
    // As long as we don't get a valid pile from the user, keep asking!
    public static String getLegalPile(String currentPlayer, int pileA, int pileB, int pileC){
        System.out.print(currentPlayer + ", choose a Pile with toothpicks in it: ");
        Scanner pileReader = new Scanner(System.in);
        String currentPile = pileReader.nextLine();

        // Prompt again and again until the provided pile is valid.
        while (! validPile(currentPile, pileA, pileB, pileC) ){
            System.out.println("Please type A, B, or C, and pick from a non empty pile. Try again:");
            currentPile = pileReader.nextLine();
        }

        return currentPile;
    }

    // What is a valid input for a pile?
    // Either A, B, or C, as long as they have at least 1 toothpick.
    public static boolean validPile(String pile, int pileA, int pileB, int pileC){
        return (pile.equals("A") && pileA > 0) || (pile.equals("B") && pileB > 0) || (pile.equals("C") && pileC > 0);
    }


    // This method asks the user for how many toothpicks to remove from a given pile
    // As long as we don't get a valid number, keep asking!
    public static int getLegalMove(String pile, int pileSize){
        System.out.print("How many toothpicks to remove from pile " + pile + ": ");

        Scanner moveReader = new Scanner(System.in);

        int toothpicksToPickUp = moveReader.nextInt();
        moveReader.nextLine();

        while (toothpicksToPickUp <= 0 || toothpicksToPickUp > pileSize){
            System.out.print("No good, you should give a number between 1 and " + pileSize + ": ");
            toothpicksToPickUp = moveReader.nextInt();
            moveReader.nextLine();
        }

        return toothpicksToPickUp;
    }
}
