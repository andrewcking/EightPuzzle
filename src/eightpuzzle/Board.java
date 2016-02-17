package eightpuzzle;

import java.util.Random;

/**
 *
 * @author Andrew
 */
public class Board {

    //2 dimensional array
    // 00 01 02
    // 10 11 12
    // 20 21 22
    public int[][] array;
    private int zeroX;
    private int zeroY;

    private Random rand = new Random();
    private Board parent;
    //to determine the number of searches performed
    private static Integer numCreated = 0;
    //For Best First Heuristics - Matching Scores
    private int matchScore;
    private int matchManhat;
    private int matchOther;

    Board() {
        //initialize array values to zero
        array = new int[3][3];
        //give them values 0-8
        int q = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = q++;
            }
        }
        zeroX = 0;
        zeroY = 0;
    }

    //deep copy
    public Board(Board board) {
        this.array = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.array[i][j] = board.array[i][j];
            }
        }
        //to determine match score for best first
        matchScore = evaluate(board);
        matchManhat = evaluateManhat(board);
        matchOther = evaluateOther(board);
        this.zeroX = board.zeroX;
        this.zeroY = board.zeroY;
        parent = board;
        numCreated++;
    }

    public int getMatch(Board board) {
        return matchScore;
    }

    public int getMatchManhat(Board board) {
        return matchManhat;
    }

    public int getMatchOther(Board board) {
        return matchOther;
    }

    //Checking for equality (has board been hit before?)
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Board) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (((Board) obj).array[i][j] != this.array[i][j]) {
                        //a return will end 
                        return false;
                    }
                }
            }

            return true;
        }
        return false;
    }

    public void randomMove() {
        int num = rand.nextInt(4);
        switch (num) {
            case 0:
                moveUp();
                break;
            case 1:
                moveRight();
                break;
            case 2:
                moveDown();
                break;
            case 3:
                moveLeft();
        }
    }

    //Movements of the zero peice
    public boolean moveDown() {
        if (zeroY + 1 > 2) {
            return false;
        }
        //swaps pushed peice in new position
        array[zeroY][zeroX] = array[zeroY + 1][zeroX];
        //setting the old position to 0 (none)
        array[++zeroY][zeroX] = 0;
        return true;
    }

    public boolean moveRight() {
        if (zeroX + 1 > 2) {
            return false;
        }
        array[zeroY][zeroX] = array[zeroY][zeroX + 1];
        array[zeroY][++zeroX] = 0;
        return true;
    }

    public boolean moveUp() {
        if (zeroY - 1 < 0) {
            return false;
        }
        array[zeroY][zeroX] = array[zeroY - 1][zeroX];
        array[--zeroY][zeroX] = 0;
        return true;
    }

    public boolean moveLeft() {
        if (zeroX - 1 < 0) {
            return false;
        }
        array[zeroY][zeroX] = array[zeroY][zeroX - 1];
        array[zeroY][--zeroX] = 0;
        return true;
    }

    public boolean isSolved() {
        //check and see if the board is the same as the new board (initial state)
        return this.equals(new Board());
    }

    //For printing the solution path
    public void printAll() {
        System.out.println(this);
        if (parent != null) {
            parent.printAll();
        }
    }

    //To compare methods and heuristics
    public static int getNumCreated() {
        return numCreated;
    }

    public static void resetNumCreated() {
        numCreated = 0;
    }

    //Heuristics
    public int evaluate(Board board) {
        int noMatch = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.array[i][j] != new Board().array[i][j]) {
                    noMatch++;
                }
            }
        }
        return noMatch;
    }

    public int evaluateManhat(Board board) {
        int manhattanDistanceSum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = board.array[i][j];
                if (value != 0) {
                    int targetI = value / 3; // expected row coord
                    int targetJ = value % 3; // expected col coord
                    int dx = i - targetI; // x-distance
                    int dy = j - targetJ; // y-distance
                    manhattanDistanceSum += Math.abs(dx) + Math.abs(dy);
                }
            }
        }
        return manhattanDistanceSum;
    }

    // This increases the count if the peice is more than one off
    public int evaluateOther(Board board) {
        int customHeuristic = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = board.array[i][j];
                if (value != 0) {
                    int targetI = value / 3; // expected row coord
                    int targetJ = value % 3; // expected col coord
                    int dx = i - targetI; // x-distance
                    int dy = j - targetJ; // y-distance
                    if (Math.abs(dx) + Math.abs(dy) > 1) {
                        customHeuristic++;
                    };
                }
            }
        }
        return customHeuristic;
    }

    @Override
    public String toString() {
        String string = " _____\n";
        string = string.concat("|" + array[0][0] + "|" + array[0][1] + "|" + array[0][2] + "|\n");
        string = string.concat("|" + array[1][0] + "|" + array[1][1] + "|" + array[1][2] + "|\n");
        string = string.concat("|" + array[2][0] + "|" + array[2][1] + "|" + array[2][2] + "|\n");
        string = string.concat(" ‾‾‾‾‾‾\n");
        return string;
    }

    //    public void printOut() {
//        System.out.println("" + array[0][0] + "" + array[0][1] + "" + array[0][2]);
//        System.out.println("" + array[1][0] + "" + array[1][1] + "" + array[1][2]);
//        System.out.println("" + array[2][0] + "" + array[2][1] + "" + array[2][2] + "\n");
//    }
}
