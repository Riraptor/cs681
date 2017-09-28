package homework2;

import java.awt.Point;
import java.util.ArrayList;

@FunctionalInterface
public interface AreaCalculator {
	public abstract float getArea(ArrayList<Point> points);
}
