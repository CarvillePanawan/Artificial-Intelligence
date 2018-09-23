import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Convoy {
	public static void main(String[] args) {
		File inputs = new File("inputs.txt");
		
		try {
			Scanner reader = new Scanner(inputs);
			int maxLoad = reader.nextInt();
			int bridgeLength = reader.nextInt();
			int totalVehicles = reader.nextInt();
			ArrayList<Truck> vehicleList = new ArrayList<Truck>();
			
			while(reader.hasNextLine()) {
				vehicleList.add(new Truck(reader.nextInt(), reader.nextInt()));
			}
			reader.close();
			
			State initialState = new State(vehicleList, null, 0, 0, 0, null);
		} catch(FileNotFoundException e) {
			System.out.println("File not Found");
		}
	}
}
