package Players;

import Game.Board;

import java.util.Scanner;

public class HumanPlayer implements Player{

    public int[] getMove(Board board) {

        Scanner s = new Scanner(System.in);

        System.out.println("please input 2 numbers separated by spaces");
        System.out.println("The numbers should be the row and col of the piece you want to move");
        int fromRow = s.nextInt();
        int fromCol = s.nextInt();

        System.out.println("please input 2 numbers separated by spaces");
        System.out.println("The numbers should be the row and col of where you want your piece to move");
        int toRow = s.nextInt();
        int toCol = s.nextInt();

        int[] move =  {fromRow, fromCol, toRow, toCol};
        return move;
    }

    @Override
    public AIBrain learn(boolean win) {
        return null;
    }
}
