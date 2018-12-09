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
        char color = sc.next().charAt(0);
        
        testRun.populate(row, col, Character.toLowerCase(color));
        testRun.showBoard();
        System.out.println();
        testRun.showArray();
        System.out.println();

        int g = testRun.playerGreenChips().size();
        int r = testRun.playerRedChips().size();
        System.out.printf("Green Chips: %d%nRed Chips: %d%n",g,r);

        ArrayList<Chip> l = new ArrayList<Chip>();
        System.out.printf("\nCheck North East\n");
        l = testRun.checkNorthEast();
        System.out.printf("\nCheck North West\n");
        l = testRun.checkNorthWest();
        System.out.printf("\nCheck South East\n");
        l = testRun.checkSouthEast();
        System.out.printf("\nCheck South West\n");
        l = testRun.checkSouthWest();
        System.out.printf("\n"+l.size());
        
        sc.close();
    }
}
