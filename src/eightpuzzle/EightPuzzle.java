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

    private JLabel DepthFirst;
    private JLabel BreadthFirst;
    private JLabel BestFirst;
    private JLabel BestFirstManhat;
    private JLabel BestFirstCustom;

    private EightPuzzle() {
        //JFrame Setup
        JFrame frame = new JFrame("Solutions for Eight Puzzle");
        frame.setVisible(true);
        frame.setSize(280, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //JPanel setup
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(25, 10, 10, 10));
        frame.add(panel);
        
        //Buttons
        JButton button0 = new JButton("Generate New Board");
        panel.add(button0);
        JButton button6 = new JButton("Perform All Searches");
        panel.add(button6);
        JButton button1 = new JButton("Depth First");
        panel.add(button1);
        JButton button2 = new JButton("Breadth First");
        panel.add(button2);
        JButton button3 = new JButton("Best First - Hamming Distance");
        panel.add(button3);
        JButton button4 = new JButton("Best First - Manhattan Distance");
        panel.add(button4);
        JButton button5 = new JButton("Best First - Custom");
        panel.add(button5);

        //Labels
        JLabel depthTitle = new JLabel("Depth First Searched");
        panel.add(depthTitle);
        DepthFirst = new JLabel("0");
        panel.add(DepthFirst);

        JLabel breadthTitle = new JLabel("Breadth First Searched");
        panel.add(breadthTitle);
        BreadthFirst = new JLabel("0");
        panel.add(BreadthFirst);

        JLabel bestHamTitle = new JLabel("Best First Hamming Searched");
        panel.add(bestHamTitle);
        BestFirst = new JLabel("0");
        panel.add(BestFirst);

        JLabel bestManhatTitle = new JLabel("Best First Manhattan Searched");
        panel.add(bestManhatTitle);
        BestFirstManhat = new JLabel("0");
        panel.add(BestFirstManhat);

        JLabel bestCustomTitle = new JLabel("Best First Custom Searched");
        panel.add(bestCustomTitle);
        BestFirstCustom = new JLabel("0");
        panel.add(BestFirstCustom);

        //Button Action Listeners
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        button1.addActionListener(new ActionListener() {
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
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bestFirstManhat();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bestFirstOther();
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAll();
            }
        });

        //Set up the board
        resetBoard();
    }

    public void depthFirst() {
        Board.resetNumCreated();
        System.out.println("Depth First Solve - Steps to Solution");
        new DepthFirst(board);
        DepthFirst.setText(String.valueOf(Board.getNumCreated()));
    }

    public void breadthFirst() {
        Board.resetNumCreated();
        System.out.println("Breadth First Solve - Steps from Completion");
        new BreadthFirst(board);
        BreadthFirst.setText(String.valueOf(Board.getNumCreated()));
    }

    public void bestFirst() {
        Board.resetNumCreated();
        System.out.println("Best First Solve, Hamming Distance - Steps from Completion");
        new BestFirst(board, 0);
        BestFirst.setText(String.valueOf(Board.getNumCreated()));
    }

    public void bestFirstManhat() {
        Board.resetNumCreated();
        System.out.println("Best First Solve, Manhattan Distance - Steps from Completion");
        new BestFirst(board, 1);
        BestFirstManhat.setText(String.valueOf(Board.getNumCreated()));
    }

    public void bestFirstOther() {
        Board.resetNumCreated();
        System.out.println("Best First Solve, Custom Heuristic - Steps from Completion");
        new BestFirst(board, 2);
        BestFirstCustom.setText(String.valueOf(Board.getNumCreated()));;
    }

    public void performAll() {
        breadthFirst();
        depthFirst();
        bestFirst();
        bestFirstManhat();
        bestFirstOther();
    }

    public void resetBoard() {
        System.out.println("Solution State of Board");
        board = new Board();
        System.out.println(board);
        for (int i = 0; i < 12; i++) {
            board.randomMove();
        }
        System.out.println("Randomized Board");
        System.out.println(board);
        //Reset count labels
        DepthFirst.setText(String.valueOf("0"));;
        BreadthFirst.setText(String.valueOf("0"));;
        BestFirst.setText(String.valueOf("0"));;
        BestFirstManhat.setText(String.valueOf("0"));;
        BestFirstCustom.setText(String.valueOf("0"));;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new EightPuzzle();

    }

}
