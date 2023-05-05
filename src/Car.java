package project;
import java.util.Random;

public class Car{
	private double qualityScore;
	private String idNumber;
	private static int instanceCounter; //helps to assign idNumber to car objects

	public Car() {
		Random random = new Random();
		qualityScore = random.nextDouble(1.00, 3.00);
		idNumber = Integer.toString(instanceCounter);
		instanceCounter++;
	}
	
	public String toString() {
		return "Car" + idNumber;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
}
