package eightpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Andrew
 */
public class EightPuzzle {

    private Board board;

    private EightPuzzle() {
        //JFrame Setup
        JFrame frame = new JFrame("Solutions for Eight Puzzle");
        frame.setVisible(true);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JPanel setup With two buttons
        JPanel panel = new JPanel();
        frame.add(panel);
        JButton button = new JButton("Depth First");
        panel.add(button);
        JButton button2 = new JButton("Breadth First");
        panel.add(button2);
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
        System.out.println("Breadth First Solve - Steps from Completion");
        new BreadthFirst(board);
    }

    public void depthFirst() {
        resetBoard();
        System.out.println("Depth First Solve - Steps to Solution");
        new DepthFirst(board);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new EightPuzzle();

    }

}
