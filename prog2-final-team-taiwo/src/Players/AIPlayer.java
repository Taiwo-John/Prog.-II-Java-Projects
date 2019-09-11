package Players;

import Game.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


// TODO - phase 2: uncomment the class below, and figure out the "brain" of the AI, and use it to get the AI to play randomly.
public class AIPlayer implements Player{
    // Each AIPlayer should have a "brain" of sorts, which will map board states to possible moves, and learn from wins
    // and mistakes.
    Map<String, int[]> playRound = new HashMap<>();
    public AIBrain ai;
    Player comp;

    {
        try {
            ai = new AIBrain("src/states.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO - phase 2: To implement the interface, you will have to complete the function below.
    @Override
    public AIBrain learn(boolean win) {
        // Help the AI to learn after each round played
        // If the UI wins, the moves are added to the move list to increment frequency
        // if the UI looses, the moves are removed from the move list.
        if(!win){
            for(String state: playRound.keySet()){
                if(ai.brain.get(state).size() > 1)
                    ai.brain.get(state).remove(playRound.get(state));
            }
        }
        else{
            for(String state: playRound.keySet()){
                ai.brain.get(state).add(playRound.get(state));
            }
        }

        return ai;
    }

    @Override
    public int[] getMove(Board board) {

        Random randGen = new Random();
        int[] move;
        Map<String, ArrayList<int[]>> brain;
        brain = ai.getBrain();

        String boardState = board.toString();
        if(ai.getBrain().containsKey(boardState)){

            move = brain.get(boardState).get(randGen.nextInt(brain.get(boardState).size()));
        }

        else{
            System.out.println(boardState);
            boardState = getSymetry(board);
            System.out.println(boardState);
            move = brain.get(boardState).get(randGen.nextInt(brain.get(boardState).size()));

            // Ensure symmetry is maintained in the moves
            // Here we get the equivalent symmetrical move of the AI's chosen move.
            if(move[1] < 1) move[1] += 2;
            else if(move[1] > 1) move[1] -= 2;

            if(move[3] > 1) move[3] -= 2;
            else if(move[3] < 1) move[3] += 2;
        }
        playRound.put(boardState, move);
        return move;
    }

    private String getSymetry(Board board){
        /* In case the current board state is not found in the brain
        we get the symmetrical equivalence of the current board state
         */
        int[][] symetry = new int[board.getRows()][board.getColumns()];
        for(int i=0; i< board.getRows(); i++){
            symetry[i][0] = board.getGrid()[i][2];
            symetry[i][1] = board.getGrid()[i][1];
            symetry[i][2] = board.getGrid()[i][0];
        }

        StringBuilder output = new StringBuilder();
        for(int i=0; i<board.getRows();i++){
            for(int j=0; j<board.getColumns(); j++){
                output.append(symetry[i][j]);
            }
            output.append("\n");
        }

        return output.toString();
    }
}


    /* TODO - phase 3:
    Amongst other functions that may be useful to you, I expect this class to have functions for:
        - Learning from a defeat.
        - Saving a brain to a file.
        - Loading a brain from a file.
    */



