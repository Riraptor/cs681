package homework6;

import java.util.ArrayList;

public class Client {
	public static void main(String[] args) {
		ArrayList<Car> cars = new ArrayList<>();
		Car BMW = new Car(60);
		Car Tesla = new Car(70);
		Car Honda = new Car(40);
		Car Toyota = new Car(30);
		cars.add(BMW);
		cars.add(Tesla);
		cars.add(Honda);
		cars.add(Toyota);
		
		
		 Integer price = cars.stream() 
				 .map((Car car)-> car.getPrice())
				 .reduce(0, (result, carPrice)->
						 		{ 
						 			if(result==0) return carPrice;
						 			else if(carPrice < result) return carPrice; 
						 			else return result;
						 		});
		 System.out.println("Minimum price using reduce(): for a price set of {60, 70 40 , 30}");
		 System.out.println(price);
		 
	}
	

}
