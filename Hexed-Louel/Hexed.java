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
        char[][] array = new char[7][9];
        Board board = new Board(array);
        
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Hexed Helper--------");
        System.out.print("Enter Starting Column: ");
        int col = sc.nextInt();
        System.out.print("Enter Starting Row: ");
        int row = sc.nextInt();
        System.out.print("Enter Team 1's Color [r/g]: ");
        char one = sc.next().charAt(0);
        System.out.print("Enter Team 2's Color [r/g]: ");
        char two = sc.next().charAt(0);
        System.out.print("Enter Team to Move 1st [1/2]: ");
        int first = sc.nextInt();
        char turnColor = (first == 1)? one : two;
        char waitColor = (first == 1)? two : one;
        System.out.print("Enter Team to Help [1/2]: ");
        int ourTeam = sc.nextInt();
        
        board.populate(row, col, Character.toLowerCase(turnColor));
        display(board);
        
        int turnTeam = 0;
        int choice = 0;
        boolean isOneHexed = false;
        boolean isTwoHexed = false;
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        
        do {
        	if (turnColor == one) {
                turnTeam = 1;
        	} else if (turnColor == two){
                turnTeam = 2;
        	}
        	
            System.out.println("---------------------------");
            System.out.println("     Turn: Team "+turnTeam+" ["+turnColor+"]");
            System.out.println("---------------------------");
            possibleMoves = board.getPossibleMoves(turnColor, waitColor);
            if (possibleMoves != null) {
                System.out.println("      Possible Moves");
                System.out.println("---------------------------");
                for(int x = 0; x < possibleMoves.size(); x++) {
                	System.out.println("["+x+"] Row: "+ (possibleMoves.get(x).getTile().getRow()+1) + "; Column: "+ possibleMoves.get(x).getTile().getCol());
                }
                System.out.println("---------------------------");
                System.out.print("Enter Move: ");
            	choice = sc.nextInt();
            	board.makeMove(possibleMoves.get(choice));
                possibleMoves = null;
            	if(turnTeam == 1) {
            		turnColor = two;
            		waitColor = one;
            		isOneHexed = false;
            	} else if(turnTeam == 2) {
            		turnColor = one;
            		waitColor = two;
            		isTwoHexed = false;
            	}
            } else {
                System.out.println("           HEXED");
                System.out.println("---------------------------");
            	if(turnTeam == 1) {
            		turnColor = two;
            		waitColor = one;
            		isOneHexed = true;
            	} else if(turnTeam == 2) {
            		turnColor = one;
            		waitColor = two;
            		isTwoHexed = true;
            	}
            }
            display(board);
            
        } while(!(isOneHexed && isTwoHexed));
        
        sc.close();
    }

	private void display(Board board) {
		System.out.println();
        System.out.println("-----------Board-----------");
        board.showBoard();
        System.out.println("---------------------------");
        int g = board.playerGreenChips().size();
        int r = board.playerRedChips().size();
        System.out.printf("Green Chips: %d%nRed Chips: %d%n",g,r);
        System.out.println("---------------------------");
        System.out.println();
	}
	
}
