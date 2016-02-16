package eightpuzzle;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class BestFirst {

    private ArrayList<Board> list;
    private ArrayList<Integer> checked;
    private int flag;
    private int highValue;

    public BestFirst(Board board, int setFlag) {
        list = new ArrayList();
        flag = setFlag;
        checked = new ArrayList();
        search(board);
    }

    public int getTheBest(ArrayList list) {
        //the max value an int can have
        highValue = Integer.MAX_VALUE;
        int bestBoard = 0;
        //Run through list (queue)
        for (int i = 0; i < list.size(); i++) {
            //For each item check it against the checked list
            if (!checked.contains(i)) {
                //Get the unchecked board
                Board board = (Board) list.get(i);
                //Pick Heuristic based on flag
                //Hamming distance
                if (flag == 0) {
                    if (board.getMatch(board) < highValue) {
                        highValue = board.getMatch(board);
                        bestBoard = i;
                    }
                }
                //Manhattan Distance
                if (flag == 1) {
                    if (board.getMatchManhat(board) < highValue) {
                        highValue = board.getMatchManhat(board);
                        bestBoard = i;
                    }
                }
                //Other Heuristic
                if (flag == 2) {
                    if (board.getMatchOther(board) < highValue) {
                        highValue = board.getMatchOther(board);
                        bestBoard = i;
                    }
                }
            }
        }

        //add the best board so we don't run it again
        checked.add(bestBoard);
        return bestBoard;
    }

    private boolean search(Board board) {
        list.add(new Board(board));
        while (true) {
            Board currentBoard = list.get(getTheBest(list));
            if (currentBoard.isSolved()) {
                currentBoard.printAll();
                System.out.println(checked);
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
