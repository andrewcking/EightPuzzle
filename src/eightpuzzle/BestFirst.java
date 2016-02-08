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

            h++;
        }
        return false;
    }
}

