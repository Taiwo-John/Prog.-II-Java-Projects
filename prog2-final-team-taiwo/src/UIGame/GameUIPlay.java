package UIGame;

import Game.Game;
import Players.AIPlayer;
import Players.Player;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class GameUIPlay {
    int from_x, from_y, to_x, to_y, player;
    boolean from_set, compPlayer, curPlayer;
    Game game;
    Player comp;
    UICell[][] cellgrid;

    public GameUIPlay(Game gameboard, UICell[][] cellgrid){
        from_set = false;
        this.game = gameboard;
        comp = new AIPlayer();
        this.cellgrid = cellgrid;
        this.compPlayer = false;
        curPlayer = true;
        player = 1;
    }

    public void setComputerAsPlayer(boolean compPlayer){
        this.compPlayer = compPlayer;
    }

    public void setFromCoords(int x, int y){
        from_x = x;
        from_y = y;
        from_set = true;
    }

    public  void setToCoords(int x, int y){
        to_x = x;
        to_y = y;
        from_set = false;
    }

    public boolean validateMove(int from_x, int from_y, int to_x, int to_y){
        player = curPlayer? 1: 2;
        return game.gameBoard.validateMove(player, from_x, from_y, to_x, to_y);
    }

    public void makePlay(){
        game.gameBoard.applyMove(from_x, from_y, to_x, to_y);
        if(game.gameBoard.winningState(curPlayer? 2:1)){
            if(compPlayer) comp.learn(false);
            String message = "Player " + player + " has Won the Game";
            UICell.alertDialog(Alert.AlertType.INFORMATION, "Game Over", message);
        }
        else if(compPlayer){
            int[] move = comp.getMove(game.gameBoard);
            game.gameBoard.applyMove(move[0], move[1], move[2], move[3]);
            UICell imageCell = cellgrid[move[0]][move[1]];
            cellgrid[move[2]][move[3]].imageView.setImage(imageCell.imageView.getImage());
            imageCell.imageView.setImage(null);

            if(game.gameBoard.winningState(1)){
                comp.learn(true);
                String message = "The Computer has Won the Game";
                UICell.alertDialog(Alert.AlertType.INFORMATION, "Game Over", message);
            }
        }
        else {
            curPlayer = !curPlayer;
        }
    }
}
