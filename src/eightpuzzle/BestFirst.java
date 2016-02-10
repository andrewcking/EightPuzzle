/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzle;

import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class BestFirst {
       private ArrayList<Board> list;

    public BestFirst(Board board) {
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
