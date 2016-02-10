package eightpuzzle;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class BestFirst {

    private ArrayList<Board> list;
    private ArrayList<Integer> checked;

    public BestFirst(Board board) {
        list = new ArrayList();
        checked = new ArrayList();
        search(board);
    }

    public int getTheBest(ArrayList list) {
        int max = Integer.MIN_VALUE;
        int bestValue = 0;
        checked.add(-1);
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < checked.size(); k++) {
                if (i != checked.get(k)) {
                    Board board = (Board) list.get(i);
                    if (board.evaluate(board) > max) {
                        max = board.evaluate(board);
                        bestValue = i;
                    }
                }
            }
        }

        checked.add(max);
        return bestValue;
    }

    private boolean search(Board board) {
        list.add(new Board(board));
        while (true) {
            Board currentBoard = list.get(getTheBest(list));
            if (currentBoard.isSolved()) {
                currentBoard.printAll();
                return false;
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
        }
    }
}
