import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int DAY = 1;

        System.out.print("Enter available car count, N=");
        while (!input.hasNextInt()) {
            System.out.print("Please enter a integer! ");
            input.next();
        }
        int carCount = input.nextInt();

        System.out.print("Enter customer count, k=");
        while (!input.hasNextInt()) {
            System.out.print("Please enter a integer! ");
            input.next();
        }
        int customerCount = input.nextInt();

        DequeInterface<Car> Cars= new WaitingList(carCount);
        for(int i=0;i<carCount;i++){
            Cars.addToBack(new Car());
        }
        QueueInterface<Customer> CustomerList = new WaitingList(customerCount);
        for(int i=0;i<customerCount;i++){
            CustomerList.enqueue(new Customer());
        }

        while (!CustomerList.isEmpty()){
            System.out.println("**********************Day"+Integer.toString(DAY)+"**********************");
            DAY++;
            CustomerList.dequeue();
        }
    }}
