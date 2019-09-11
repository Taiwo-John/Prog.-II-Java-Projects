package gameOfLifeModel;

public class CellGrid {
    int width;
    int height;

    Cell[][] grid;
    // Creating a CellGrid object involves creating multiple Cell objects
    // Objects of this class are responsible for managing the rules of the game of Life
    // You should be able to update all the cells in your grid based on the rules
    // Feel free to create any methods you deem necessary, and write unit tests for them.
    // I do ask you to use the following three methods, you will have to write unit tests for them.

    public CellGrid(int width, int height){
        this.width = width;
        this.height = height;

        int horiNum = getHorizontalCellNumber();
        int vertNum = getVerticalCellNumber();

        grid = new Cell[horiNum][vertNum];

        // Create a 2D grid of Cells using the calculated horizontal and vertical numbers
        for (int i=0; i<horiNum; i++){
            for (int j=0; j<vertNum; j++){
                grid[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getGrid(){
        // Returns a grid of Cells
        return this.grid;
    }

    public int getHorizontalCellNumber(){
        /* Calculate the number of cells horizontally
           we divide by 12.4 because 12 is the size of
           the cell and 0.4 is the horizontal gap
         */
        return (int) ((double) this.width/12.4);
    }

    public int getVerticalCellNumber(){
        /* Calculate the number of cells vertically
           we divide by 12.4 because 12 is the size
           of our cell and 0.4 is the vertical gap
         */
        return (int) ((double) this.height/12.4);
    }

    // compute the future state of each cell in your grid.
    public void computeFutureState(){

        // Implement game rules for updating future state.
        for(int i=0; i<getHorizontalCellNumber(); i++){
            for(int j=0; j<getVerticalCellNumber(); j++){

                int neighboursCount = livingNeighbours(i, j);

                // If living cell has 1 or zero living neighbours, it dies
                if (neighboursCount < 2 && grid[i][j].getCurrentState()){
                    grid[i][j].setFutureState(false);
                }

                // If living cell has 4 and above living neighbors, it dies
                else if(neighboursCount >= 4 && grid[i][j].getCurrentState()){
                    grid[i][j].setFutureState(false);
                }

                // If living cell has 2 or 3 living neighbors, it remains alive
                else if(neighboursCount >= 2 && grid[i][j].getCurrentState()){
                    grid[i][j].setFutureState(true);
                }

                // If non living cell has 3 living neighbours, it comes to live
                else if(neighboursCount == 3 && !grid[i][j].getCurrentState()){
                    grid[i][j].setFutureState(true);
                }
            }
        }
    }

    // update the current state of each cell in your grid.
    public void updateGrid(){
        /* This method, copies the Future State into the current state
           and set the future state back to false.
         */
        for(int i=0; i<getHorizontalCellNumber(); i++){
            for(int j=0; j<getVerticalCellNumber(); j++){
                grid[i][j].setCurrentState(grid[i][j].getFutureState());
                grid[i][j].setFutureState(false);
            }
        }
    }

    // For a cell in position i,j, how many living neighbours does it have?
    // bear in mind that a cell has at most 8 living neighbours.
    public int livingNeighbours(int i, int j){
        /* Each cell has at most 8 neighbors, for extreme cells,
           the number of living neighbors will vary and some indexes
           will be out of range, so we handle that expected behaviour
           by ensuring the indexes are greater than zero and less than
           the respective horizontal and vertical cell number.
         */
        int numOfActiveNeighbors = 0;
        int horizontalNumber = this.getHorizontalCellNumber();
        int verticalNumber = this.getVerticalCellNumber();

        for (int x=i-1; x<=i+1; x++){
            for(int y=j-1; y<=j+1; y++){
                if(x>=0 && y>=0 && x<horizontalNumber && y<verticalNumber && !(x==i && y==j)){
                    if(grid[x][y].getCurrentState()){
                        numOfActiveNeighbors += 1;
                    }
                }
            }
        }


//        try{ if(grid[i][j-1].getCurrentState()) numOfActiveNeighbors += 1;}
//        catch (Exception e){}
//
//        try{if(grid[i][j+1].getCurrentState()) numOfActiveNeighbors += 1;}
//        catch (Exception e){}
//
//        try{if(grid[i-1][j-1].getCurrentState()) numOfActiveNeighbors += 1;}
//        catch (Exception e){}
//
//        try{if(grid[i-1][j].getCurrentState()) numOfActiveNeighbors += 1;}
//        catch (Exception e) {}
//
//        try{if(grid[i-1][j+1].getCurrentState()) numOfActiveNeighbors += 1;}
//        catch (Exception e) {}
//
//        try{if(grid[i+1][j-1].getCurrentState()) numOfActiveNeighbors += 1;}
//        catch (Exception e) {}
//
//        try{if(grid[i+1][j].getCurrentState()) numOfActiveNeighbors += 1;}
//        catch (Exception e){}
//
//        try{if(grid[i+1][j+1].getCurrentState()) numOfActiveNeighbors += 1;}
//        catch (Exception e){}

        return numOfActiveNeighbors;
    }
}
