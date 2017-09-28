package homework1;

import java.util.ArrayList;


public class testmain {
	public static void main(String[] args){
		ArrayList<Car> usedCars = new ArrayList<Car>();
		usedCars.add(new Car(1000,1995,11000)); //price,year,milage
		usedCars.add(new Car(2000,2000,12000));
		usedCars.add(new Car(30000,2010,5200));
		usedCars.add(new Car(20500,2005,3200));
		/*
		Collections.sort(usedCars,
						(Car c1, Car c2) ->
						c1.getYear()-c2.getYear());
		for(Car y: usedCars)
			System.out.println(y.getYear());
		Collections.sort(usedCars,
				(Car c1, Car c2) ->
				c1.getPrice()-c2.getPrice());
		for(Car y: usedCars)
			System.out.println(y.getPrice());
			
		Collections.sort(usedCars,
				(Car c1, Car c2) ->
				(int)(c1.getMilage()-c2.getMilage()));
		for(Car y: usedCars)
		System.out.println(y.getMilage());
		*/
		
		YearComparator ye = new YearComparator();
		ye.compare(usedCars);
		System.out.println("SORT BY YEAR: ");
		for(Car y: usedCars)
			System.out.println(y.getYear());
		
		MilageComparator me = new MilageComparator();
		me.compare(usedCars);
		System.out.println("SORT BY MILAGE: ");
		for(Car y: usedCars)
			System.out.println(y.getMilage());
		
		PriceComparator pc = new PriceComparator();
		pc.compare(usedCars);
		System.out.println("SORT BY PRICE: ");
		for(Car y: usedCars)
			System.out.println(y.getMilage());
		
	}
	
}
