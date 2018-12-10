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
        
        // testRun.populate(row, col, Character.toLowerCase(color));
        testRun.setBoard();
        testRun.showBoard();
        // System.out.println();
        // testRun.showArray();
        // System.out.println();

        int g = testRun.playerGreenChips().size();
        int r = testRun.playerRedChips().size();
        System.out.printf("Green Chips: %d%nRed Chips: %d%n",g,r);

        ArrayList<Chip> l = new ArrayList<Chip>();
        int size = 0;

        System.out.printf("\n\nCheck North East");
        l = testRun.checkNorthEast();

        for(int i = 0; i < l.size(); i++){
            System.out.printf("\n"+l.get(i).toString());
        }

        size += l.size();

        System.out.printf("\n\nCheck North West");
        l = testRun.checkNorthWest();

        for(int i = 0; i < l.size(); i++){
            System.out.printf("\n"+l.get(i).toString());
        }
        
        size += l.size();

        System.out.printf("\n\nCheck South East");
        l = testRun.checkSouthEast();

        for(int i = 0; i < l.size(); i++){
            System.out.printf("\n"+l.get(i).toString());
        }

        size += l.size();

        System.out.printf("\n\nCheck South West");
        l = testRun.checkSouthWest();

        for(int i = 0; i < l.size(); i++){
            System.out.printf("\n"+l.get(i).toString());
        }
        
        size += l.size();

        System.out.printf("\n\nCheck North");
        l = testRun.checkNorth();

        for(int i = 0; i < l.size(); i++){
            System.out.printf("\n"+l.get(i).toString());
        }

        size += l.size();

        System.out.printf("\n\nCheck South");
        l = testRun.checkSouth();

        for(int i = 0; i < l.size(); i++){
            System.out.printf("\n"+l.get(i).toString());
        }

        size += l.size();

        System.out.printf("%n%nNumber of Possible Moves: %d%n",size);
        
        sc.close();
    }
}
