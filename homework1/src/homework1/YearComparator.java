package homework1;

import java.util.ArrayList;
import java.util.Collections;

public class YearComparator {
	public void compare(ArrayList<Car> usedCars) {
		
		Collections.sort(usedCars,
				(Car c1, Car c2) ->
				c1.getYear()-c2.getYear());
	}
}
