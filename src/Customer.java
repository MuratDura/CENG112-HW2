package project;
import java.util.Random;

public class Customer {
	private double qualityThreshold;
	private String idNumber; 
	private static int instanceCounter; //helps to assign idNumber to customer objects
	
	public Customer() {
		Random random = new Random();
		qualityThreshold = random.nextDouble(1.0, 3.0);
		idNumber = Integer.toString(instanceCounter);
		instanceCounter++;
	}
	
	
	@Override
	public String toString() {
		return "Customer" + idNumber;
	}
	
	public void decreaseThreshold() {
		qualityThreshold *= 0.9;
	}
}
