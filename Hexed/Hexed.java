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
        testRun.show();

        int g = testRun.playerGreenChips().size();
        int r = testRun.playerRedChips().size();
        System.out.printf("Green Chips: %d%nRed Chips: %d%n",g,r);

        ArrayList<Chip> l = new ArrayList<Chip>();
        System.out.printf("\ncheck right diagonal up\n");
        l = testRun.checkRightDiagonalUp();
        System.out.printf("\ncheck left diagonal up\n");
        l = testRun.checkLeftDiagonalUp();
        System.out.printf("\ncheck right diagonal down\n");
        l = testRun.checkRightDiagonalDown();
        System.out.printf("\ncheck left diagonal down\n");
        l = testRun.checkLeftDiagonalDown();
        System.out.printf("\n"+l.size());
        
        sc.close();
    }
}
