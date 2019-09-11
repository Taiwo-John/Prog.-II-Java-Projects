// We first import the scanner class to allow user input
import java.util.Scanner;

//Let use inheritance methods to have access to parent info

public class MagicTrick extends Deck{

    // Let create default method for deal method.
    public Card[] deal(int n) {
        return super.deal(n);
    }

    // THis is the card trick method
    public void trick() {

        Scanner scanner= new Scanner(System.in);
        //Let create a Matrix of elements called " matrix ", we split the array into three Columns and three lines

        Card[][] matrix = new Card[3][3];
        int column = 0;
        int line = 0;
        int count = 0;

        //This for loop populates the matrix regarding to the indexes and to the size of that arrays we return in deal.
        for (column = 0; column < 3; column++) {
            for (line = 0; line < 3; line++) {
                matrix[line][column] = cards[count];

                count = count + 1;
            }
        }


        //Print a message showing each column and lines of the matrix and ask user input
        System.out.println("\n   Column 0           Column 1           Column 2");
        System.out.println("=======================================================\n");
        System.out.println(matrix[0][0]+ "    " + matrix[0][1] + "    " + matrix[0][2] + "\n"
                + matrix[1][0]+ "    " + matrix[1][1] + "    " + matrix[1][2] + "\n"
                + matrix[2][0]+ "    " + matrix[2][1] + "    " + matrix[2][2] + "\n");


        //Let crete user input for Column choice;
        System.out.println("Which column contain your card location?, choice 0/1 or 2");
        int input = scanner.nextInt();
        Card temp1, temp2;
        //Let's keep track our card position in the middle
        Card swap1, swap2, swap3;


        //if user input is 0 we swap Column' cards through the middle of each Column;
        if (input == 0){

            //temp helps to save matrix indexes at the given point and being able to use it back.
            temp1 = matrix[0][0];
            temp2 = matrix[2][0] ;

            //Let's swap them looking to user column choice. And we decided to display them in the second line of the matrix
            matrix[0][0] = matrix[1][1];
            matrix[1][1] = temp1;

            matrix[2][0] = matrix[1][2];
            matrix[1][2] = temp2;

            //record row of player secret card
            swap1 = matrix[1][0];
            swap2 = matrix[1][1];
            swap3 = matrix[1][2];

        }
        else if (input == 1){
            temp1 = matrix[0][1];
            temp2 = matrix[2][1];

            //Let's swap them looking to user column choice. And we decided to display them in the second line of the matrix
            matrix[0][1] = matrix[1][0];
            matrix[1][0] = temp1;

            matrix[2][1] = matrix[1][2];
            matrix[1][2] = temp2;

            //record row of player secret card
            swap1 = matrix[1][0];
            swap2 = matrix[1][1];
            swap3 = matrix[1][2];
        }
        else {
            temp1 = matrix[0][2];
            temp2 = matrix[2][2];

            //Let's swap them looking to user column choice. And we decided to display them in the second line of the matrix
            matrix[0][2] = matrix[1][0];
            matrix[1][0] = temp1;
            matrix[2][2] = matrix[1][1];
            matrix[1][1] = temp2;

            //record row of player secret card
            swap1 = matrix[1][0];
            swap2 = matrix[1][1];
            swap3 = matrix[1][2];
        }


        //This is the final and second display for user to identify the location of his secret card

        System.out.println("\n   Column 0           Column 1           Column 2");
        System.out.println("=======================================================\n");
        System.out.println(matrix[0][0]+ "    " + matrix[0][1] + "    " + matrix[0][2] + "\n"
                + matrix[1][0]+ "    " + matrix[1][1] + "    " + matrix[1][2] + "\n"
                + matrix[2][0]+ "    " + matrix[2][1] + "    " + matrix[2][2] + "\n");


        //Let's crete user input for Column choice;
        System.out.println("Which column contain your card location?, choice 0/1 or 2");
        int input2 = scanner.nextInt();


        //Let's create a condition to display magic card by using user record we called swap1, 2 and 3;
        if (input2 == 0){
            System.out.println("your magic card is :" + swap1);
        }
        else if(input2 == 1){
            System.out.println("your magic card is :" + swap2);
        }
        else {
            System.out.println("your magic card is :" + swap3);
        }


    }


    // This is the main function that runs the trick card.

    public static void main(String[] args) {
        MagicTrick message = new MagicTrick();

        System.out.println("Please can you record one card in your mind among those 9?");

//       We created a method and call our trick function above to run MagicTrick
        message.trick();
    }

}