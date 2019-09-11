package gameOfLifeUI;

import gameOfLifeModel.CellGrid;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {
    boolean start = false;
    long click_count = 0;
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        VBox root = new VBox();
        Scene scene = new Scene(root, 550, 600);

//        scene.setFill(Color.gray(1));
        GridPane grid = new GridPane();
        grid.setVgap(0.4);
        grid.setHgap(0.4);
        grid.setBackground(new Background(new BackgroundFill(Color.gray(0.6), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setPadding(new Insets(5, 0, 5, 0));
        grid.setAlignment(Pos.CENTER);
        root.getChildren().add(grid);

        // Create a cellgrid object with desired width adn height
        CellGrid cellgrid = new CellGrid(500, 500);

        // Used Calculated horizontal and vertical numbers to create a 2D array of UI Cell
        UICell [][] gridTab = new UICell [cellgrid.getHorizontalCellNumber()][cellgrid.getVerticalCellNumber()];

//
        for (int i = 0; i <cellgrid.getHorizontalCellNumber(); i++){
            // Load the UI cells into our grid pane
            for (int j = 0; j < cellgrid.getVerticalCellNumber(); j++){
                gridTab[i][j] = new UICell(i, j, cellgrid.getGrid()[i][j]);
                grid.add(gridTab[i][j], i, j);
                gridTab[i][j].HProperty().bind(scene.heightProperty().subtract(150).divide(40));
            }
        }

        // Next Button
        Button nxt_btn = new Button("Next");
        nxt_btn.setPrefSize(100, 30);
        VBox.setMargin(nxt_btn, new Insets(30, 0, 0, 50));

        nxt_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try{
                            cellgrid.computeFutureState();
                            cellgrid.updateGrid();
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    for(int i=0; i<cellgrid.getHorizontalCellNumber(); i++){
                                        for(int j=0; j<cellgrid.getVerticalCellNumber(); j++){
                                            if(gridTab[i][j].gameOfLifeCell.getCurrentState()) {
                                                gridTab[i][j].sq.setFill(Color.YELLOW);
                                            }
                                            else{
                                                gridTab[i][j].sq.setFill(Color.GREY);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                        catch (Exception e){}

                        return null;
                    }
                };

                executorService.execute(task);
            }
        });

        // Reset Button
        Button reset_btn = new Button("Reset");
        reset_btn.setPrefSize(100, 30);
        VBox.setMargin(reset_btn, new Insets(-30, 0, 0, 200));

        reset_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(int i=0; i<cellgrid.getHorizontalCellNumber(); i++){
                    for(int j=0; j<cellgrid.getVerticalCellNumber(); j++){
                        gridTab[i][j].sq.setFill(Color.GREY);
                        gridTab[i][j].gameOfLifeCell.setFutureState(false);
                        gridTab[i][j].gameOfLifeCell.setCurrentState(false);
                    }
                }
            }
        });

        //Start button for continuous play
        Button start_stop = new Button("Start");
        start_stop.setPrefSize(100, 30);
        VBox.setMargin(start_stop, new Insets(-30, 0, 0, 350));

        start_stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                click_count += 1;

                if (click_count % 2 == 0){
                    start = false;
                    executorService.shutdownNow();
                    executorService = Executors.newFixedThreadPool(1);
                    start_stop.setText("Start");
                }
                else {
                    start = true;
                    start_stop.setText("Stop");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (start) {
                                nxt_btn.fire();
                                try{
                                    Thread.sleep(750);
                                }
                                catch (InterruptedException e) {}
                            }
                        }
                    }).start();
                }
            }
        });

        // Add buttons to VBox
        root.getChildren().addAll(nxt_btn);
        root.getChildren().addAll(reset_btn);
        root.getChildren().addAll(start_stop);

        scene.heightProperty().addListener(e ->{
            primaryStage.setWidth(primaryStage.getHeight() - 100);
        });

        scene.widthProperty().addListener(e ->{
            primaryStage.setHeight(primaryStage.getWidth() + 100);
        });

        primaryStage.setTitle("Cohort 1 Group 3 Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
             /*
        Your application must have:
        - A grid of at least 40x40 to play the game of life
        - Clicking on an element of the grid should switch their state (if it was alive, it is dead, and vice versa)
        - Under the grid, you should have at least 2 buttons:
            - a 'Next' button which moves the simulation forward
            - a 'Reset' button, which clears the grid and allows you to restart.
        Feel free to add more functionality as extra credit, and use any design approach from javafx to do this.
         */
    }
}
