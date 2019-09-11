package gameOfLifeUI;

import gameOfLifeModel.Cell;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// This class is a customized child of the Rectangle class from javafx
// Objects of this class is what you will draw in your UI
// The methods here should mostly deal with where and how cells should be shown
// to the user, each UICell object will have a Cell to take care of game logic
public class UICell extends Parent {

    Cell gameOfLifeCell;

    private int x_coord;
    private int y_coord;
    private SimpleDoubleProperty h;
    private SimpleDoubleProperty w;
    Rectangle sq;

    public UICell(int x, int y, Cell boxCell){
        x_coord = x;
        y_coord = y;

        h = new SimpleDoubleProperty();
        w = new SimpleDoubleProperty();
        h.set(12);
        w.set(12);
        w.bind(h);

        sq = new Rectangle(12, 12, Color.GREY);
        sq.heightProperty().bind(h);
        sq.widthProperty().bind(w);

        this.getChildren().add(sq);
        this.createOnActionEvent();

        // Link a Cell from Grid cell to a UICell and set its states to false
        gameOfLifeCell = boxCell;
        gameOfLifeCell.setCurrentState(false);
        gameOfLifeCell.setFutureState(false);

    }

    public void createOnActionEvent(){
        /* Method creates a Mouse onclicked event
           if the currentState is inactive, the state is
           set to active and the color changed to yellow
           else, the currentState is set to inactive and
           the color back to grey
         */
        sq.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(gameOfLifeCell.getCurrentState()){
                    gameOfLifeCell.setCurrentState(false);
                    sq.setFill(Color.GREY);
                }
                else{
                    gameOfLifeCell.setCurrentState(true);
                    sq.setFill(Color.YELLOW);
                }
            }
        });
    }

    public int getX_coord(){
        return x_coord;
    }

    public int getY_coord(){
        return y_coord;
    }
    public SimpleDoubleProperty HProperty(){
        return h;
    }
    public SimpleDoubleProperty WProperty(){
        return w;
    }




}
