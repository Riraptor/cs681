package homework1;

import java.util.ArrayList;
import java.util.Collections;

public class PriceComparator {
public void compare(ArrayList<Car> usedCars) {
		
		Collections.sort(usedCars,
				(Car c1, Car c2) ->
				c1.getPrice()-c2.getPrice());
	}
}
