
import java.util.Random;

public class Customer {
	private double qualityThreshold;
	private String idNumber; 
	private static int instanceCounter; //helps to assign idNumber to customer objects
	private static Random random = new Random();
	
	public Customer() {
		//qualityThreshold = Math.round(random.nextDouble(1.0, 3.0)*100.0)/100.0;
		qualityThreshold = random.nextDouble(1.0, 3.0);
		idNumber = Integer.toString(instanceCounter);
		instanceCounter++;
	}
	
	public double getQualityThreshold() {
		return qualityThreshold;
	}
	
	@Override
	public String toString() {
		return "Customer" + idNumber;
	}
	
	public void decreaseThreshold() {
		qualityThreshold *= 0.9;
		//qualityThreshold = Math.round(qualityThreshold*100.0)/100.0;
	}


}
