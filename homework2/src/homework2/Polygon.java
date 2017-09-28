package homework2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;
public class Polygon {
	private ArrayList<Point> points = new ArrayList<Point>();
	private AreaCalculator areaCalc;
	
	public Polygon(ArrayList<Point> points, AreaCalculator areaCalc){
		this.points = Objects.requireNonNull(points);
		this.areaCalc = Objects.requireNonNull(areaCalc);
	}
	
	public void setAreaCalc(AreaCalculator calc){
		this.areaCalc = Objects.requireNonNull(calc);
	}
	
	public void addPoint(Point point){
		points.add(point);	
		}
	
	public void removePoint(Point point){
		points.remove(point);
	}
	
	public float getArea(){
		return areaCalc.getArea(points);
	}
	
}