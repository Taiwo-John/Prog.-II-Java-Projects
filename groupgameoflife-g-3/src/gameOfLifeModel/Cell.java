package gameOfLifeModel;

public class Cell {
    // This is the state of the Cell right now
    public boolean currentState;

    // Once we look at all the cells, we can accurately compute the
    // futureState of each. This is the state this cell will be in once
    // the simulation moves forward.
    boolean futureState;

    // Any methods are up to you to create and test

    public void setCurrentState(boolean currentState) {
        // Set the Cell's current state
        this.currentState = currentState;
    }

    public void setFutureState(boolean futureState){
        // Set the cells Future state
        this.futureState = futureState;
    }

    public boolean getCurrentState(){
        // Get the Cells current state
        return this.currentState;
    }

    public boolean getFutureState(){
        // Get the Cell's Future state
        return this.futureState;
    }

}
