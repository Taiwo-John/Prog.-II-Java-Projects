//package gameOfLifeTests;
//
//import gameOfLifeModel.Cell;
//import gameOfLifeModel.CellGrid;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//// testing the cell grid methonds
//class CellGridTest {
//    CellGrid testing = new CellGrid(12, 10);
//// Make nested loop to test the current, future state and update state.
//    @Test
//    void testgrid() {
//        for (int i = 0; i < testing.getGrid().length; i++) {
//            for (int j = 0; j < testing.getGrid()[0].length; j++) {
//                assertTrue(testing.getGrid()[i][j].getCurrentState(), "if its returns the grid cells");
//            }
//        }
//
//
//    }
//
//
//    @Test
//    void testfuturestate() {
//        Cell[][] grid;
//        for (int i = 0; i < testing.getGrid().length; i++) {
//            for (int j = 0; j < testing.getGrid()[0].length; j++) {
//
//                assertTrue(testing.getGrid()[i][j].getFutureState(), "it returns the future of cell state");
//
//            }
//
//
//        }
//    }
//    @Test
//    void testupdateGrid() {
//        testing.updateGrid();
//        for (int i = 0; i < testing.getGrid().length; i++) {
//            for (int j = 0; j < testing.getGrid()[0].length; j++) {
//
//
//                assertSame(testing.getGrid()[i][j].getCurrentState(), testing.getGrid()[i][j].getFutureState(), "It should be the same");
//            }
//        }
//    }
////testing the living neighbor methond
//        @Test
//        void testlivingneighbor(){
//                assertEquals(0, testing.livingNeighbours(0, 0), "tell us if the cell is alive or not");
//            }
//
//    }
