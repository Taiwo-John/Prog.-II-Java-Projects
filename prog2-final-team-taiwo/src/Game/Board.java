package Game;

import java.util.ArrayList;

// A board for this game has the following fields:
// rows and columns keep track of how many rows and columns we have, respectively.
// Typically, we will play this on a 3 by 3 board.
// Grid is a 2d array of int, where a 1 represents Player 1's pieces, a 2 represents Player 2's, and 0 is an empty spot
public class Board {
    int rows;
    int columns;
    int[][] grid;

    // if startState is true, we will set up 3 pieces for each player.
    // if startState is false, the board will be empty.
    public Board(int r, int c, boolean startState){
        rows = r;
        columns = c;

        grid = new int[r][c];
        if (startState){
            for (int columnIndex = 0; columnIndex < c; columnIndex++){
                grid[r - 1][columnIndex] = 1; // Player 1 pieces starts at the bottom
                grid[0][columnIndex] = 2; // Player 2 pieces starts at the top row
            }
        }
    }

    // simple getter/setter for the content of the grid
    public int get(int row, int column){
        return grid[row][column];
    }

    public void set(int row, int column, int value){
        grid[row][column] = value;
    }

    // this is a helpful helper method, feel free to use it.
    private boolean outOfGrid(int i, int j){
        return i < 0 || i >= rows || j < 0 || j >= rows;
    }

    // This is the method that checks moving up for player 1. I have implemented this method so that the validate
    // move method is easily implemented as only one step moves are permitted.
    private boolean moveUp(int currentRow, int currentCol, int nextRow, int nextColumn){
        return nextRow == currentRow - 1 && nextColumn == currentCol;
    }

    // Diagonal aka capture method for player 1.
    private boolean captureUp(int currentRow, int currentCol, int nextRow, int nextColumn){
        return nextRow == currentRow - 1 && nextColumn == currentCol - 1 || nextRow == currentRow - 1 && nextColumn == currentCol + 1;
    }

    // One step moving Down method for player 2.
    private boolean moveDown(int currentRow, int currentCol, int nextRow, int nextColumn){
        return nextRow == currentRow + 1 && nextColumn == currentCol;
    }

    // Diagonal aka capture method for player 2.
    private boolean captureDown(int currentRow, int currentCol, int nextRow, int nextColumn){
        return nextRow == currentRow + 1 && nextColumn == currentCol - 1 || nextRow == currentRow + 1 && nextColumn == currentCol + 1;
    }

    /* TODO: Finish this method:
    Given a "move" description as a parameter, return true or false if the move is valid.
    We describe a move as 5 ints, Which player is playing, the row and column of the piece they want to move, and the
    row and column of where they want to move them.
    Note that:
    * Player 1's pieces can only move upwards, while Player 2's pieces move downwards.
	* A piece can move to the position directly in front of it if it is empty. (*recall that "front" means different things for player 1 and player 2*)
	* A piece can move diagonally if **there is an opposing piece in that position**, the opposing piece is then taken out of the game.
     */
    public boolean validateMove(int player, int currentRow, int currentColumn, int nextRow, int nextColumn ){
        if(player != grid[currentRow][currentColumn]) {
            return false;
        }
        if(!outOfGrid(nextRow, nextColumn)){// First checks if where the player wants to move to is not out of the grid.
            if(get(currentRow, currentColumn) == 1){
                if(moveUp(currentRow, currentColumn, nextRow, nextColumn) && get(nextRow, nextColumn)==0){ return true;}
                else {
                    return(captureUp(currentRow, currentColumn, nextRow, nextColumn) && get(nextRow, nextColumn)==2);
                }
            }
            else if(get(currentRow, currentColumn) == 2){
                if(moveDown(currentRow, currentColumn, nextRow, nextColumn) && get(nextRow, nextColumn)==0){ return true;}
                else return (captureDown(currentRow, currentColumn, nextRow, nextColumn) && get(nextRow, nextColumn)==1);
            }
            else
                return false;
        }
        else
            System.out.println("You are moving out of the grid, please move within the grid.");
            return false; // Don't forget you need parameters here!
    }

    // This method "performs" a move. Whatever piece was in (currentRow, currentColumn) will be moved to (nextRow, nextColumn)
    // Then, we make sure the previous spot is empty.
    public void applyMove(int currentRow, int currentColumn, int nextRow, int nextColumn){
        int movingPiece = get(currentRow, currentColumn);
        set(currentRow, currentColumn, 0);
        set(nextRow, nextColumn, movingPiece);
    }
//
//    public ArrayList<int[]> getPossibleMovesOnBoard(){
//        ArrayList<int[]> allMoves = new ArrayList<>();
//        for(int i=0; i<rows; i++){
//            for(int j=0; j<columns; j++){
//                if(grid[i][j] == 2){
//                    ArrayList<int[]> temp = checkPosibleMove(2, i, j);
//                    if(temp.size() > 0){
//                        allMoves.addAll(temp);
//                    }
//                }
//            }
//        }
//        return allMoves;
//    }

    boolean checkPosibleMove(int player, int curRow, int curCol){
        /*
        Check if there is a valid move to any of it's neighbors
        if a valid move is found, then
         */
        int startRow = player==1? curRow-1: curRow;
        int stopRow = player==1? curRow: curRow+1;
        for(int i=startRow; i<=stopRow; i++){
            for(int j=curCol-1; j<=curCol+1; j++){
                if(i>=0 && j>=0 && i<rows && j<columns && !(i==curRow && j==curCol)){ // ensure selected point is valid
                    if(validateMove(player, curRow, curCol, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* Check if the board represents a won game for the given player.
     * Winning happens as follows:
     * If only the pieces of one player remain on the board, that player won.
     * If a piece from a player makes it to the opposing edge of the board, that player wins.
     * If a player has no legal moves at the beginning of their turn, that player loses.
        The last part is tricky to figure out, but you know how to check for a valid move from the methods above, so you
        should be able to check if any exist.
    */
    public boolean winningState(int player){
        int count = 0;
        int count_opposite = 0;
        int opp = player==1? 2: 1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if((grid[i][j] ==1 && i==0) || ((grid[i][j]==2) && i==2)){
                    return true;
                }
                if(grid[i][j] == player){
                    if(checkPosibleMove(player, i, j)){
                        count += 1;
                    }
                }
                else if(grid[i][j] == opp) count_opposite += 1;
            }
        }

        return count < 1 || count_opposite < 1;
    }


//    Don't worry about this until you get to Phase 2
//    @Override
//    public boolean equals(Object obj){
//        return obj instanceof Board && (this.isSame((Board)obj) || this.isMirror((Board)obj));
//    }

    @Override
    public String toString() {
        String output = "";
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                output += get(rowIndex, columnIndex);
            }
            output += '\n';
        }
        return output;
    }

    /**
     * Get the columns
     * @return
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Get the rows
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     * Get the grid
     * @return
     */
    public int[][] getGrid() {
        return grid;
    }
}
