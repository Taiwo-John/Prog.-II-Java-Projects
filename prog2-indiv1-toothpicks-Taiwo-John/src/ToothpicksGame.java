/*
    Taiwo Temidayo John
 */

// I imported the Java Scanner class to enable user input.
import java.util.Scanner;

// This is the already created ToothpicksGame java class.
public class ToothpicksGame {

    /* The next 3 lines are used to define variables
        I have defined the variables as global variables that can be used and modified
        at any point in the prgram
     */


    public static String player1, player2, replay_choice;
    public static int grp_a = 3, grp_b = 3, grp_c = 3, playerstate = 0;
    public static Scanner input = new Scanner(System.in);

    /* This function EnterPlayers prompts the users to enter their names
        and stores the names of the players in the player1 and player2 variables.
     */


    public static void EnterPlayers(){
        System.out.println("Player 1 please enter your name: ");
        player1 = input.next();
        System.out.println("Player 2 please enter your name: ");
        player2 = input.next();
    }

    /* This function State, displays the state of the game to the users.
        The state of the game is usually displayed at certain points of the game such as
        - When the players first start the game, - After a player's turn, when a player enters the wrong
        number of toothpicks to pick from a group and - At the end of the game.
     */

    public static void State(){

        System.out.println("\n***************************************\nThere are " + grp_a +" toothpicks left in group A" +
                "\nThere are "+ grp_b + " toothpicks left in group B\nThere are " + grp_c + " toothpicks left in group C\n" +
                "***************************************\n");

    }

    /* The Boolean function is_game_over() checks to see if the game is over.
        It returns True if all groups have no toothpicks left and false otherwise.
     */
    public static boolean is_game_over(){

        return (grp_a == 0 && grp_b ==0 && grp_c ==0);
    }

    /* Here, I have created the Playertomove function, to track the turns of the players' move.
       The first player to move, has an initial playerstate value of zero, and always moves at even
       playerstate values. The second player to move has an initial player state of 1 and always moves
       at odd
       playerstate values.
       The function returns the Name of the player whose turn is next, stored in the player1 and player2
       variables respectively.
     */

    public static void Randomize_player(){
        if (playerstate == 0){
            double random_number = Math.random();
            System.out.println(random_number);
            if (0 < random_number && random_number < 0.5){
                playerstate = 2;
            }
            else if(random_number >= 0.5 && random_number < 1){
                playerstate = 1;}}

    }

    public static String Playertomove(){

        Randomize_player();
        if (playerstate % 2 == 0){
            return player1;
        }
        return player2;

    }

    /*
        This is the Selection function. It is the main heart of the program.
        I will fix comments in the function itself for better understanding.
     */
    public static void Selection(){

        // This condition checks that the game is not over, before asking a player to select toothpicks.
        while(!is_game_over()) {

            // This whose_move variable stores the name of the player to move as referred by the Playertomove() function
            String whose_move = Playertomove();

            // This prompts the player to move to enter the letter of the group they want to select from.
            System.out.println(whose_move + " please enter the letter of the group you want to pick from in upper case: ");
            String GroupSelection = input.next();

            /* This while condition checks to see if the user inputs the correct correct letter, if not, it keeps
                prompting them until they enter the correct input.
             */

            while (!GroupSelection.equals("A") && !GroupSelection.equals("B") && !GroupSelection.equals("C")) {
                System.out.println("Your input must be A, B or C in uppercase\n");
                GroupSelection = input.next();
            }

            // The if condition works for when the user enters A, and wants to select toothpicks from group A.
            if (GroupSelection.equals("A")) {

                /*
                    Provided that group A is not empty, the user is prompted to enter the number of toothpicks
                    to select from group A
                 */
                if(grp_a != 0) {
                    System.out.println("How many toothpicks do you want to pick from group " + GroupSelection);

                     /*
                      If the user selects more than the number of toothpicks in a group
                      Or the user selects a negative value, they will be prompted to select a
                      correct input. The while condition below ensures that

                      Also, the try - catch exception handler, checks for input mismatch errors, and
                      prompts the user to only input
                     */

                    try {
                        int ToothpickSelection = input.nextInt();

                        while (ToothpickSelection < 1 || ToothpickSelection > 3) {
                            System.out.println("You may only select from 1 to " + grp_a + " toothpicks");
                            ToothpickSelection = input.nextInt();
                        }

                     /*If the user has input the correct number of toothpicks to select from Group A, the group toothpick
                       value is decreased, the playerstate is increased to signal the turn of the next player and
                       the state of the game is displayed.
                     */
                        grp_a -= ToothpickSelection;
                        playerstate++;
                        State();
                    }
                    catch (Exception e){
                        System.out.println("\n *****Error!!! You may only input integer values*****\n");
                    }

                }

                /*
                    If a group contains zero number of toothpicks, this else statement tells the user that
                    the group is empty and displays the state for the user to pick from the non-empty group(s).
                 */
                else {
                    System.out.println("\nGroup " + GroupSelection + " has no toothpick left, select from the non-empty " +
                            "group(s) displayed below\n");
                    State();
                }
            }
                /*
                    This is the same iteration for group be as when the user selected group A.
                 */
            else if (GroupSelection.equals("B")) {
                if(grp_b !=0) {

                    System.out.println("How many toothpicks do you want to pick from group " + GroupSelection);

                    try {
                        int ToothpickSelection = input.nextInt();

                        while (ToothpickSelection < 1 || ToothpickSelection > 3) {
                            System.out.println("You may only select from 1 to " + grp_b + " toothpicks");
                            ToothpickSelection = input.nextInt();
                        }

                        grp_b -= ToothpickSelection;
                        playerstate++;
                        State();
                    }
                    catch (Exception e){
                        System.out.println("\n *****Error!!! You may only input integer values*****\n");
                    }
                }
                else {
                    System.out.println("\nGroup " + GroupSelection + " has no toothpick left, select from the non-empty " +
                            "group(s) displayed below\n");
                    State();
                }
            }

            /*
                This is the iteration for the last possible group Selection; Group C.
                It is pretty much the same selection process as the other groups. So I will save you a
                lot of comment redundancy :)
             */
            else {
                if (grp_c != 0) {

                    System.out.println("How many toothpicks do you want to pic from group " + GroupSelection);

                    try {
                        int ToothpickSelection = input.nextInt();

                        while (ToothpickSelection < 1 || ToothpickSelection > 3) {
                            System.out.println("You may only select from 1 to " + grp_c + " toothpicks");
                            ToothpickSelection = input.nextInt();
                        }

                        grp_c -= ToothpickSelection;
                        playerstate++;
                        State();
                    }
                    catch (Exception e){
                        System.out.println("\n *****Error!!! You may only input integer values*****\n");
                    }
                }
                else {
                    System.out.println("\nGroup " + GroupSelection + " has no toothpick left, select from the non-empty " +
                            "group(s) displayed below\n");
                    State();
                }
            }
        }
    }

    /*
        This function displays the Game Over message.
        - Indicating the winner and the loser, based on the playerstate values.
     */
    public static void EndGame(){

        if (is_game_over()){

            System.out.println("Game Over!!!");

            if (Playertomove() == player1){
                System.out.println(player1 + " wins!!!\n" + player2 + " you lose. Better luck next time");
            }
            else {
                System.out.println(player2 + " wins!!!\n" + player1 + " you lose. Better luck next time");
            }
        }


    }

    public static boolean replay(){

        System.out.println("Please enter Y to start the game and N to quit: ?");

        replay_choice = input.next();
        while(!replay_choice.equals("Y") && !replay_choice.equals("N")){
            System.out.println("\nInvalid input, please enter Y to start the game and N to quit: ");
            replay_choice = input.next();
        }

        if(replay_choice.equals("Y")){

            return true;
        }
        else {
            return false;
        }
    }

    /*
        This is the main function.
        In it, I have called all the other functions to enable the program to run.
        I have also added some starting notes and ending notes to give a bit of intro and outro. :)
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the toothpicks game :)\n");
        while(replay()) {
            EnterPlayers();
            State();
            Selection();
            EndGame();
            System.out.println("\n********************\n Don't worry :) You can play again\n********************");
        }

        System.out.println("\nCredits: \n Taiwo Temidayo John\n ALUCS 2021\n C.2019 \n This program is open to " +
                "modifications only at the authorization of the author :)");
    }
}
