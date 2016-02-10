package eightpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Andrew
 */
public class EightPuzzle {

    private Board board;

    private JLabel test;

    private EightPuzzle() {
        //JFrame Setup
        JFrame frame = new JFrame("Solutions for Eight Puzzle");
        frame.setVisible(true);
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JPanel setup With two buttons
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(25, 10, 10, 10));
        frame.add(panel);
        JButton button = new JButton("Depth First");
        panel.add(button);
        JButton button2 = new JButton("Breadth First");
        panel.add(button2);
        JButton button3 = new JButton("Best First");
        panel.add(button3);
        JLabel searchTitle = new JLabel("Boards Searched");
        panel.add(searchTitle);
        test = new JLabel("0");
        panel.add(test);
        //Button Action Listeners
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depthFirst();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                breadthFirst();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bestFirst();
            }
        });

    }

    public void updateCount() {
        test.setText(String.valueOf(Board.getNumCreated()));
    }

    public void resetBoard() {
        System.out.println("Solution State of Board");
        board = new Board();
        System.out.println(board);
        for (int i = 0; i < 10; i++) {
            board.randomMove();
        }
        System.out.println("Randomized Board");
        System.out.println(board);
    }

    public void breadthFirst() {
        resetBoard();
        Board.resetNumCreated();
        System.out.println("Breadth First Solve - Steps from Completion");
        new BreadthFirst(board);
        updateCount();
    }

    public void depthFirst() {
        resetBoard();
        Board.resetNumCreated();
        System.out.println("Depth First Solve - Steps to Solution");
        new DepthFirst(board);
        updateCount();
    }

    public void bestFirst() {
        resetBoard();
        Board.resetNumCreated();
        System.out.println("Best First Solve - Steps from Completion");
        new BestFirst(board);
        updateCount();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new EightPuzzle();

    }

}
