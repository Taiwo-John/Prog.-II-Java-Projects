package UIGame;


import Game.Game;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class UICell {
    Rectangle rectangle;
    ImageView imageView;
    int x_coord, y_coord;
    GameUIPlay game;
    UICell[][] cellgrid;
    HBox imageWrapper = new HBox();

    public UICell(int l, int w, int i, int j, GameUIPlay game, UICell[][] cellgrid){
        rectangle = new Rectangle(l, w);
        setColor(i, j);
        imageView = new ImageView();
        imageView.setFitHeight(l-20);
        imageView.setFitWidth(w-20);
        imageWrapper.getChildren().add(imageView);
        imageWrapper.setAlignment(Pos.CENTER);

        x_coord = i;
        y_coord = j;
        this.game = game;
        this.cellgrid = cellgrid;
    }

    private void setColor(int x, int j){
        if((x+j) % 2 == 0){
            rectangle.setFill(Color.GREEN);
        }
        else rectangle.setFill(Color.GREENYELLOW);
    }

    public static void alertDialog(Alert.AlertType type, String title, String message){
        /* function used to send an alert to the user so the user knows
        why an operation failed
         */
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void checkWin(){

    }

    public void createEventListener(){
        imageWrapper.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!game.from_set){
                    game.setFromCoords(x_coord, y_coord);
                }
                else{

                    if(game.validateMove(game.from_x, game.from_y, x_coord, y_coord)){
                        game.setToCoords(x_coord, y_coord);
                        UICell from_cell = cellgrid[game.from_x][game.from_y];
                        cellgrid[x_coord][y_coord].imageView.setImage(from_cell.imageView.getImage());
                        from_cell.imageView.setImage(null);
                        game.makePlay();
                    }
                    else{
                        String message = "Wrong move for Current Player. Choose new Source and Destination cell";
                        alertDialog(Alert.AlertType.ERROR, "Wrong Move", message);
                    }
                    game.from_set = false;
                }
            }
        });
    }
}
