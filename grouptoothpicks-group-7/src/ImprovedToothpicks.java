import java.util.Scanner;

public class ImprovedToothpicks {
    /* The next 3 lines are used to define variables
        I have defined the variables as global variables that can be used and modified
        at any point in the prgram
     */


    public static String player1, player2, replay_choice, mode_choice, GroupSelection;
    public static int grp_a = 3, grp_b = 3, grp_c = 3, playerstate = 0;
    public static Scanner input = new Scanner(System.in);
    public static double AI_random_number;



    /* This function EnterPlayer prompts the user to enter their name when the Single player mode is selected
        and stores the names of the player in the player1 variable and makes the player 2 variable equal to the name of
        the AI (Intelli-AI)
     */
    public static void EnterPlayer(){
        System.out.println("Please Enter your name: ");
        player1 = input.next();
        player2 = "Intelli-AI";
    }

    /* This function EnterPlayers prompts the users to enter their names if the 2-player game mode is selected
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

    /* This is the Randomize_player function, it randomizes the player to move first.

        If the randomized number is between 0 and 0.49, playerstate is made even (2), and player 1 plays first.

        If the randomized number is between 0.5 and 1, player state is made odd(1) and player 2 plays first.

     */
    public static void Randomize_player(){
        if (playerstate == 0){
            double random_number = Math.random();
            if (0 < random_number && random_number < 0.5){
                playerstate = 2;
            }
            else if(random_number >= 0.5 && random_number < 1){
                playerstate = 1;}}

    }

    /*
        This fucntion decides the player to move, based on playerstate values stored from calling the Randomize_player()
        function.
        The function returns the Name of the player whose turn is next, stored in the player1 and player2
        variables respectively.
        It is useful to alternate the turns between players.
     */

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
            GroupSelection = input.next();

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

                        while (ToothpickSelection < 1 || ToothpickSelection > grp_a) {
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

                        while (ToothpickSelection < 1 || ToothpickSelection > grp_b) {
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

                        while (ToothpickSelection < 1 || ToothpickSelection > grp_c) {
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
        This  is a boolean function and it  checks to see if a group is valid.
        It takes in 4 paramenters, the value of the selected group and the values of the number of toothpicks left in
        each of the 3 groups.

        It returns true if the selected group is valid and false otherwise.
     */
    public static boolean validPile(String GroupSelection, int grp_a, int grp_b, int grp_c) {
        return (GroupSelection.equals("A") && grp_a > 0) || (GroupSelection.equals("B") && grp_b> 0) || (GroupSelection.equals("C") && grp_c > 0);
    }

    /*
        This function executes the AI's group selection process. It returns a String.
     */
    public static String AI_Group_Selection(){

        /*
            A variable AI_random_number is created, which stores randomized numbers between 1 and 3.
         */
        AI_random_number = (int) (Math.random() * ((3 - 1) + 1)) + 1;

        /*
          If the randomized number is 1.0, A is returned, if it is 2.0, B is returned, else(when it is 3.0), C is
          returned.
         */
        if(AI_random_number == 1.0){
           return "A";

        }
        else if(AI_random_number == 2.0){
            return "B";
        }

        else{
            return "C";
        }
    }

    /*
        This is the function that executes the AI's toothpick selection process. It returns an integer.
     */
    public static int AI_Toothpick_Selection(){

        /* The variable AI_random_toothpick is declared to stored the number of toothpicks that the AI selects from a
           particular group.
        */
        double AI_random_toothpick;

        /* If the AI has chosen to select from group A, a randomized value within the range of 1 and the number of
           toothpicks left in the group is stored in the AI_random_toothpick variable.
           The randomization is modelled such that the numbers randomized are within the range of 1 and the number of
           toothpicks left in the group.

           - This step is repeated if the AI selects group B and C as well.
        */
        if (GroupSelection.equals("A")){
            AI_random_toothpick = (int) (Math.random() * ((grp_a - 1) + 1)) + 1;
        }

        else if(GroupSelection.equals("B")){
            AI_random_toothpick = (int) (Math.random() * ((grp_b - 1) + 1)) + 1;

        }
        else{
            AI_random_toothpick = (int) (Math.random() * ((grp_c - 1) + 1)) + 1;
        }

        /*
            Because the AI_random_toothpick is a double variable, when it is equal to 1.0, the function returns the
            integer 1, when it is equal to 2.0, 2 is returned and when it is 3.0, 3 is returned.
         */
        if(AI_random_toothpick == 1.0){
            return 1;
        }

        else if(AI_random_toothpick == 2.0){
            return 2;
        }
        else{
            return 3;
        }
    }

    // This function executes the AI's selection process when it is the AI turn to move.
    public static void AISelection(){

        /*
            This while function checks to see if the game is not over before running
         */
        while(!is_game_over()) {

            String whose_move = Playertomove();

            // This checks to see if it is the AI's turn to move
            if(whose_move.equals(player2)) {

                // This saves the value returned from the AI_Group_Selection function into the GroupSelection Variable
                GroupSelection = AI_Group_Selection();

                /* While the AI does not select from a valid group, i.e a group that contains non-zero elements, AI will
                   be prompted to execute the AI_Group_Selection() function again, until a valid group is gotten.
                */
                while(!validPile(GroupSelection, grp_a, grp_b, grp_c)){
                    GroupSelection = AI_Group_Selection();
                }

                /* If the AI selects group A, it will be prompted to enter the number of toothpicks it wants to select
                   from Group A by calling the AI_ToothpicksSelection function. Then, an output of the number of
                   toothpicks that the AI selected from group A will be shown, group A will be decremented and the
                   playerstate value will be incremented, denoting the turn of the next player(Human).
                   The state of the game is then displayed.

                   - This step is repeated for when the AI selects B or C as well.
                */
                if(GroupSelection.equals("A")){
                    int ToothpicksSelection = AI_Toothpick_Selection();
                    System.out.println(whose_move + " selects " + ToothpicksSelection + " toothpicks from group " + GroupSelection);
                    grp_a-= ToothpicksSelection;
                    playerstate++;
                    State();

                }

                if(GroupSelection.equals("B")){
                    System.out.println("How many toothpicks do you want to select from group " +GroupSelection );
                    int ToothpicksSelection = AI_Toothpick_Selection();
                    System.out.println(whose_move + " selects " + ToothpicksSelection + " toothpicks from group " + GroupSelection);
                    grp_b-= ToothpicksSelection;
                    playerstate++;
                    State();
                }

                if(GroupSelection.equals("C")){
                    System.out.println("How many toothpicks do you want to select from group " +GroupSelection );
                    int ToothpicksSelection = AI_Toothpick_Selection();
                    System.out.println(whose_move + " selects " + ToothpicksSelection + " toothpicks from group " + GroupSelection);
                    grp_c-= ToothpicksSelection;
                    playerstate++;
                    State();

                }
            }
            // This else function executes if it is the user's turn to move.
            else{
                System.out.println(whose_move + " please enter the letter of the group you want to pick from in upper case: ");
                GroupSelection = input.next();

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

                            while (ToothpickSelection < 1 || ToothpickSelection > grp_a) {
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

                            while (ToothpickSelection < 1 || ToothpickSelection > grp_b) {
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

                            while (ToothpickSelection < 1 || ToothpickSelection > grp_c) {
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
    }


    /*
        This function displays the Game Over message.
        - Indicating the winner and the loser, based on the playerstate values.
     */
    public static void DeclareWinner(){

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

    /*
        This is the replay function.
        In a sense, it starts and ends the game because the game will only execute if its value is true and end
        otherwise.
     */
    public static boolean replay(){

        /* The user is prompted to enter Y to start the game and N to quit. The user input is stored in the
        variable replay_choice
        */
        System.out.println("Please enter Y to start the game and N to quit: ?");

        replay_choice = input.next();

        /*
            This checks to see if the replay choice is the correct value, that is; Y or N. If otherwise, it prompts the
            user to enter the correct value.
         */
        while(!replay_choice.equals("Y") && !replay_choice.equals("N")){
            System.out.println("\nInvalid input, please enter Y to start the game and N to quit: ");
            replay_choice = input.next();
        }

        /* If the user enters Y, the value of each of the three groups are initialized to be 3 and the function returns
        true
         */
        if(replay_choice.equals("Y")){
            grp_a = 3; grp_b = 3; grp_c = 3; playerstate =0;

            return true;
        }
        // This works for if the input is N, the function returns false.
        else {
            return false;
        }
    }

    /*
        This is the Game function, it first displays the two mode options available to the user and prompts them
        to select one. Afterwards, it executes the game(program) based on the user's mode preference.
     */
    public static void Game(){

        System.out.println("***************\nSelect Game mode: \n1.Play against a friend\n2.Play against computer " +
                "(Intelli AI)\n***************");

        mode_choice = input.next();

        /*
            If the user selects an input other than 1 or 2, it prompts them until they select the correct one.
         */
        while(!mode_choice.equals("1") && !mode_choice.equals("2")){
            System.out.println("\n Invalid input, please enter 1 to play a friend and 2 to play against computer: ");
            mode_choice = input.next();
        }
        // If the user selects 1, the Two Player function is called.
        if (mode_choice.equals("1")){
            TwoPlayer();
        }
        // If the user selects one, the SinglePlayer function is called and the user plays against the computer.
        else{
            SinglePlayer();
        }
    }

        /*
            The two player function calls the other function that allows two players to play as shown below.
         */
    public static void TwoPlayer(){
        EnterPlayers();
        State();
        Selection();
        DeclareWinner();
        // At the end, it displays a message to notify the user that they can play again.
        System.out.println("\n********************\n Don't worry :) You can play again\n********************");
    }

    /*
       The two player function calls the other function that allows two players to play as shown below.
     */
    public static void SinglePlayer(){
        EnterPlayer();
        State();
        AISelection();
        DeclareWinner();
    }

    /*
        This is the main function.
        It first checks if the replay function is true, if yes, it executes the Game function.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the toothpicks game :)\n");
        while(replay()) {
            Game();

        }
    }
}
