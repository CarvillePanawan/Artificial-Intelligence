import java.util.Scanner;
import java.util.ArrayList;

public class Hexed {
    public static void main(String[] args) {
        try {
            Hexed test = new Hexed();
            test.run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        char[][] board = new char[7][9];
        Board testRun = new Board(board);
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter col: ");
        int col = sc.nextInt();
        System.out.print("Enter row: ");
        int row = sc.nextInt();
        System.out.print("Enter top color[R/G]: ");
        char top = sc.next().charAt(0);
        System.out.print("Enter player color[R/G]: ");
        char color = sc.next().charAt(0);
        System.out.print("Enter first move[R/G]: ");
        char firstMove = sc.next().charAt(0);
        
        testRun.populate(row, col, Character.toLowerCase(top));
        // testRun.setBoard();
        testRun.showBoard();
        // System.out.println();
        // testRun.showArray();
        // System.out.println();

        Chip x = new Chip (0,0,'g');

        int g = testRun.playerGreenChips().size();
        int r = testRun.playerRedChips().size();
        System.out.printf("Green Chips: %d%nRed Chips: %d%n",g,r);

        ArrayList<Chip> l = new ArrayList<Chip>();
        int size = 0;

        l.addAll(testRun.checkNorthEast());
        l.addAll(testRun.checkNorthWest());
        l.addAll(testRun.checkSouthEast());
        l.addAll(testRun.checkSouthWest());
        l.addAll(testRun.checkNorth());
        l.addAll(testRun.checkSouth());

        l = testRun.showAllPossibleMoves(x,l);

        System.out.printf("%n%nNumber of Possible Moves: %d%n",l.size());
        
        // sc.close();
    }
}
