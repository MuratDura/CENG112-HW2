import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter available car count, N=");
        int numberOfCars = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter customer count, k=");
        int numberOfCustomers = Integer.parseInt(scanner.nextLine());
        //Create a car waiting list to rent a car from that has user-entered number of cars
        DequeInterface<Car> carWaitingList = new WaitingList(numberOfCars);
        for (int i = 0; i < numberOfCars; i++) {
            carWaitingList.addToBack(new Car());
        }
        //Create a customer waiting list that has user-entered number of customers
        QueueInterface<Customer> customerWaitingList = new WaitingList(numberOfCustomers);
        for (int i = 0; i < numberOfCustomers; i++) {
            customerWaitingList.enqueue(new Customer());
        }

        //declarations and initializations needed for the while loop which simulates the renting process
        Car car;
        Customer customer;
        UsedCarsList<Car> occupiedCars = new UsedCarsList<>();
        UsedCarsList<Customer> currentRenter = new UsedCarsList<>(); //to keep track of the current renters of the occupied cars
        int dayCounter = 0;
        boolean keepRenting = true;
        int leftCustomers = numberOfCustomers; //to store the number of customers iterated through each time
        int leftCars = numberOfCars; //to store the number of available cars iterated through
        int numberOfRejection;
        //One loop represents one day
        while (keepRenting) {
            dayCounter++;
            System.out.println("*******************"+"Day"+dayCounter+"*******************");
            numberOfCars = leftCars;
            for (int i=1; i <= numberOfCars; i++) {
                numberOfRejection = 0;
                car = carWaitingList.removeFront();
                System.out.println("Current "+car+" quality="+car.getQualityScore()+" is offering to");
                numberOfCustomers = leftCustomers;
                for (int j=1; j<= numberOfCustomers; j++) {
                    customer = customerWaitingList.dequeue();
                    if (car.getQualityScore() > customer.getQualityThreshold()) {
                        System.out.printf("\tCurrent "+customer+" threshold="+"%.2f"+"\t\t\t"+"---accepted\n", customer.getQualityThreshold());
                        car.rent(); //rent the car assigning it a random number of days of occupancy
                        occupiedCars.add(car); //the indices of the car and the customer will be the same in these lists
                        currentRenter.add(customer); //also add the current renter of that car correspondingly to the currentRenter list
                        if (numberOfRejection>0){
                            while ((customerWaitingList.getLength()-numberOfRejection)>0){
                                customer = customerWaitingList.dequeue();
                                customerWaitingList.enqueue(customer);
                                numberOfRejection++;
                        }}
                        leftCustomers--;
                        leftCars--;
                        break;
                    }else {
                        System.out.printf("\tCurrent "+customer+" threshold="+"%.2f"+"\t\t\t"+"---not accepted\n", customer.getQualityThreshold());
                        customer.decreaseThreshold();
                        customerWaitingList.enqueue(customer);
                        numberOfRejection++;
                    }
                }
                if (!occupiedCars.contains(car)) {
                    carWaitingList.addToBack(car);
                    System.out.println("\t---not accepted by any customer---");
                }
            }System.out.println("All cars have been seen.");
            keepRenting = !customerWaitingList.isEmpty();
            if (!keepRenting) {
                System.out.println("All customers rented a car.");
            }else {
                //for loop to display results at the end of the day and to take care of availability of rented cars
                System.out.println("But there are still customers waiting.");
                System.out.println("Rented cars: ");
                for (int i=1; i<=occupiedCars.getLength(); i++) { //the length of occupiedCars list and that of currentRenter list are the same
                    Car occupiedCar = occupiedCars.getEntry(i);
                    Customer renter = currentRenter.getEntry(i);
                    System.out.println(occupiedCar+" by "+renter+" occupancy="+occupiedCar.getLeftDays());}
                if(carWaitingList.getLength()>0){
                System.out.println("Available cars:");
                    for (int i =1; i<= carWaitingList.getLength();i++){
                        Car CAR = carWaitingList.removeFront();
                        carWaitingList.addToBack(CAR);
                        System.out.println("\t\t"+CAR);
                    }}
                for (int i=1; i<=occupiedCars.getLength(); i++){
                    Car occupiedCar = occupiedCars.getEntry(i);
                    occupiedCar.decreaseOccupancy();
                    if (occupiedCar.getLeftDays() == 0) {
                        occupiedCars.remove(i);
                        currentRenter.remove(i);
                        carWaitingList.addToFront(occupiedCar);
                        leftCars++;
                    }}
                System.out.println("*******************"+"End of the Day"+"*******************");
                System.out.println();
            }
        }

    }}
