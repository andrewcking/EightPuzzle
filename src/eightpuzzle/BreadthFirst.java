package eightpuzzle;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class BreadthFirst {

    private ArrayList<Board> list;

    public BreadthFirst(Board board) {
        list = new ArrayList();
        search(board);
    }

    private boolean search(Board board) {
        list.add(new Board(board));
        int h = 0;
        while (h < list.size()) {
            Board currentBoard = list.get(h);
            if (currentBoard.isSolved()) {
                currentBoard.printAll();
                return true;
            }
            if (currentBoard.moveUp()) {
                list.add(new Board(currentBoard));
                currentBoard.moveDown();
            }
            if (currentBoard.moveRight()) {
                list.add(new Board(currentBoard));
                currentBoard.moveLeft();
            }
            if (currentBoard.moveDown()) {
                list.add(new Board(currentBoard));
                currentBoard.moveUp();
            }
            if (currentBoard.moveLeft()) {
                list.add(new Board(currentBoard));
                currentBoard.moveRight();
            }
            h++;
        }
        return false;
    }
}
