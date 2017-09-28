package homework1;

import java.util.ArrayList;
import java.util.Collections;

public class MilageComparator {
public void compare(ArrayList<Car> usedCars) {
		
		Collections.sort(usedCars,
				(Car c1, Car c2) ->
				(int)(c1.getMilage()-c2.getMilage()));
	}
}
