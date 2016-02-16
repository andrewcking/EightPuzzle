package eightpuzzle;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class DepthFirst {

    private ArrayList list;
    //technically speaking we are only scrambled 10 it shouldnt need more than that
    //the limit prevents a stackoverflow error, but also technically makes it a hybrid with breadthfirst
    private int LIMIT = 15;
    Board currentBoard;

    public DepthFirst(Board board) {
        list = new ArrayList();
        //this creates a copy so it isn't actually changing board
        //we want this because otherwise we cannot accuratly compare the counts
        currentBoard = new Board(board);
        search(currentBoard, 0);
    }

    private boolean search(Board board, int count) {
        System.out.println(board);
        //has this been hit?
        for (int i = 0; i < list.size(); i++) {
            if (board.equals(list.get(i))) {
                return false;
            }
        }
        //is this the solution?
        if (board.isSolved()) {
            return true;
        }
        if (count > LIMIT) {
            return false;
        }
        //deep copy
        list.add(new Board(board));
        if (board.moveUp()) {
            if (search(board, count + 1)) {
                return true;
            }
            board.moveDown();
        }
        if (board.moveRight()) {
            if (search(board, count + 1)) {
                return true;
            }
            board.moveLeft();
        }
        if (board.moveDown()) {
            if (search(board, count + 1)) {
                return true;
            }
            board.moveUp();
        }
        if (board.moveLeft()) {
            if (search(board, count + 1)) {
                return true;
            }
            board.moveRight();
        }
        return false;
    }
}
