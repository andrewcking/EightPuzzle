package eightpuzzle;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class BestFirst {

    private ArrayList<Board> list;
    private int flag;
    private int highValue;

    public BestFirst(Board board, int setFlag) {
        list = new ArrayList();
        flag = setFlag;
        search(board);
    }

    public Board getTheBest(ArrayList list) {
        //the max value an int can have
        highValue = Integer.MAX_VALUE;
        Board bestBoard = null;
        //Run through list (queue)
        for (int i = 0; i < list.size(); i++) {
            Board board = (Board) list.get(i);
            //Pick Heuristic based on flag
            //Hamming distance
            if (flag == 0) {
                if (board.getMatch(board) < highValue) {
                    highValue = board.getMatch(board);
                    bestBoard = board;
                }
            }
            //Manhattan Distance
            if (flag == 1) {
                if (board.getMatchManhat(board) < highValue) {
                    highValue = board.getMatchManhat(board);
                    bestBoard = board;
                }
            }
            //Other Heuristic
            if (flag == 2) {
                if (board.getMatchOther(board) < highValue) {
                    highValue = board.getMatchOther(board);
                    bestBoard = board;
                }
            }

        }

        list.remove(bestBoard);
        return bestBoard;
    }

    private boolean search(Board board) {
        list.add(new Board(board));
        while (true) {
            Board currentBoard = getTheBest(list);
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
