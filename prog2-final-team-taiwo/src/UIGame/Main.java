package UIGame;

import Game.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    GameUIPlay gui;
    UICell[][] cellGrid;
    Game g;

    public static void main(String[] args) {
        launch(args);
    }

    public void createPawnBoard(GridPane grid, Image whitePawn, Image blackPawn){
        grid.getChildren().clear();

        UICell cell;
        for(int i=0; i<g.gameBoard.getRows(); i++){
            for(int j=0; j<g.gameBoard.getColumns(); j++){
                cell = new UICell(80, 70, i, j, gui, cellGrid);
                Image image = g.gameBoard.getGrid()[i][j]==0?null: g.gameBoard.getGrid()[i][j]==1?whitePawn: blackPawn;
                cell.imageView.setImage(image);

                cellGrid[i][j] = cell;

                grid.add(cell.rectangle, j, i);
                grid.add(cell.imageWrapper, j, i);
                cell.createEventListener();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        Scene scene = new Scene(root, 350, 280);
        scene.setFill(Color.BLACK);

        String style1 = "-fx-background-color:\n" +
                "            linear-gradient(#ffd65b, #e68400),\n" +
                "            linear-gradient(#ffef84, #f2ba44),\n" +
                "            linear-gradient(#ffea6a, #efaa22),\n" +
                "            linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                "            linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 10px;\n" +
                "    -fx-padding: 10 20 10 20;";

        String style2 = "-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                "        #9d4024,\n" +
                "        #d86e3a,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;";

        g = new Game();
        cellGrid = new UICell[g.gameBoard.getRows()][g.gameBoard.getColumns()];
        gui = new GameUIPlay(g, cellGrid);
        GridPane grid = new GridPane();
        grid.setBackground(new Background(new BackgroundFill(Color.gray(0.6), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setPadding(new Insets(5, 0, 5, 0));
        grid.setAlignment(Pos.CENTER);

        Image whitePawn = new Image(new FileInputStream("./Images/white.png"));
        Image blackPawn = new Image(new FileInputStream("./Images/black.png"));

        createPawnBoard(grid, whitePawn, blackPawn);


        Button two_player = new Button("2 Players");
        Button computer = new Button("Computer");

        two_player.setStyle(style1);
        two_player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UICell.alertDialog(Alert.AlertType.INFORMATION, "", "You have chosen to play against Another player");
                computer.setDisable(true);
            }
        });

        Button reset = new Button("Reset Game");
        reset.setStyle(style2);
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                computer.setDisable(false);
                two_player.setDisable(false);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want a new AI or Current AI ?",
                        ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                g = new Game();

                if (alert.getResult() == ButtonType.YES) {
                    gui = new GameUIPlay(g, cellGrid);
                }
                else if(alert.getResult() == ButtonType.NO){
                    gui.from_set = false;
                    gui.compPlayer = false;
                    gui.player = 1;
                    gui.game = g;
                }

                createPawnBoard(grid, whitePawn, blackPawn);

            }
        });


        computer.setStyle(style1);
        computer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UICell.alertDialog(Alert.AlertType.INFORMATION, "", "You have chosen to play against the Computer");
                two_player.setDisable(true);
                gui.setComputerAsPlayer(true);

            }
        });

        HBox btnHb = new HBox(10);
        btnHb.setAlignment(Pos.CENTER);
        btnHb.setPadding(new Insets(15, 0, 0, 10));
        btnHb.getChildren().addAll(two_player, reset, computer);

        root.getChildren().add(grid);
        root.getChildren().addAll(btnHb);
        primaryStage.setTitle("ShrexaPawn");
        primaryStage.setScene(scene);
        primaryStage.show();

        String message = " Welcome to Shrexapawn. To get started, select 2 players to play against " +
                "a human or computer to play agains the computer when either of you win, you will be requested " +
                "to continue with same AI version which will be more smater (Selecting No) or you will be asked " +
                "to continue with a new AI (selecting Yes). GOOD LUCK !!!!! :)";

        UICell.alertDialog(Alert.AlertType.INFORMATION, "Instructions", message);


    }
}
