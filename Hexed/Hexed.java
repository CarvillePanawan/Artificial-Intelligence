import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

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
        Random rand = new Random();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter col: ");
        int col = sc.nextInt();
        System.out.print("Enter row: ");
        int row = sc.nextInt();
        System.out.print("Enter top color[R/G]: ");
        char top = sc.next().charAt(0);
        System.out.print("Enter first move[R/G]: ");
        char firstMove = sc.next().charAt(0);
        System.out.print("Enter your color[R/G]: ");
        char color = sc.next().charAt(0);
        char turn = firstMove;
        
        testRun.populate(row, col, Character.toLowerCase(top));
        // testRun.setBoard();
        
        // System.out.println();
        // testRun.showArray();
        // System.out.println();

        // while(){

        // }

        int g = testRun.playerGreenChips().size();
        int r = testRun.playerRedChips().size();
        System.out.printf("Green Chips: %d%nRed Chips: %d%n",g,r);

        ArrayList<Move> l = new ArrayList<Move>();
        ArrayList<Move> s = new ArrayList<Move>();

        int choice = 0;

        while(true){

            testRun.showBoard();
            
            l.addAll(testRun.checkNorthEast());
            l.addAll(testRun.checkNorthWest());
            l.addAll(testRun.checkSouthEast());
            l.addAll(testRun.checkSouthWest());
            l.addAll(testRun.checkNorth());
            l.addAll(testRun.checkSouth());

            s = testRun.showAllPossibleMoves(turn,l);

            if(l.size() == 0){
                System.out.println("Hexed!");

                if(turn == 'r'){
                    turn = 'g';
                }else{
                    turn = 'r';
                }
            }else{

                if(turn != color){
                    System.out.printf("\nSelect a move: ");
                    choice = sc.nextInt();
                }else{
                    choice = rand.nextInt(s.size()) + 0;
                    System.out.printf("Choice: %d%n", choice);
                    System.out.printf("Turn %nRow: %d Col: %d%n",s.get(choice).getMove().getRow(),s.get(choice).getMove().getCol());
                    sc.nextLine();
                }

                if(turn == 'r'){
                    turn = 'g';
                }else{
                    turn = 'r';
                }

                testRun = new Board(s.get(choice).getBoard());

                l = new ArrayList<Move>();

            }
        }
    }
}
