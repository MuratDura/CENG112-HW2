package project;
import java.util.Random;

public class Car{
	private double qualityScore;
	private int leftDays;
	private String idNumber;
	private static int instanceCounter; //helps to assign idNumber to car objects
	private static Random random = new Random();

	public Car() {
		qualityScore = Math.round(random.nextDouble(1.00, 3.00)*100.0)/100.0;
		idNumber = Integer.toString(instanceCounter);
		instanceCounter++;
	}
	
	public double getQualityScore() {
		return qualityScore;
	}
	
	public int getLeftDays() {
		return leftDays;
	}
	
	public String toString() {
		return "Car" + idNumber;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public void rent() {
		leftDays = random.nextInt(1, 5);
	}
	
	public int decreaseOccupancy() {
		leftDays--;
		return leftDays;
	}
}

