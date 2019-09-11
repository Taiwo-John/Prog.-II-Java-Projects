package Players;

import Game.Board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class AIBrain {
    Map<String, ArrayList<int[]>> brain;

    public AIBrain(String memoryFile) throws Exception {
        setKnowledge(memoryFile);
    }

    public Map<String, ArrayList<int[]>> getBrain(){
        return this.brain;
    }


    public void setKnowledge(String memoryFile) throws Exception{
        brain = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(memoryFile));
        ArrayList<int[]> moves;
        int number_of_states = Integer.parseInt(reader.readLine());
        for(int i=0; i<number_of_states; i++){
            int count = 0;
            StringBuilder boardState = new StringBuilder();
            int input_lines = Integer.parseInt(reader.readLine());
            moves = new ArrayList<>();

            for(int j=0; j<input_lines; j++){
                if(input_lines-count <= 3){
                    boardState.append(String.join("", reader.readLine().split(" ")));
                    boardState.append("\n");
                }
                else {
                    moves.add(Stream.of(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
                }
                count += 1;
            }
            brain.put(boardState.toString(), moves);
        }
    }

    public void saveBrain(){
        FileWriter fw;
        try {
            fw = new FileWriter("BrainState.txt");
            fw.write(Integer.toString(brain.size()));
            fw.write("\n");

            for (String st: brain.keySet()){
                fw.write(Integer.toString(brain.get(st).size()+3));
                fw.write("\n");
                char [] array = st.toCharArray();
                for(int[] move: brain.get(st)){

                    String mv = "" + move[0];
                    for(int i=1; i<move.length; i++){
                        mv += " " + move[i];
                    }
                    fw.write(mv);
                    fw.write("\n");
                }

                String bd = "" + array[0];
                for(int j=1; j<array.length; j++){
                    if(array[j] == '\n' || array[j-1]=='\n') bd +=  array[j];
                    else bd += " " + array[j];
                }
                fw.write(bd);
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
