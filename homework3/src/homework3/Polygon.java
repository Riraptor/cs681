package homework3;

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
	public Polygon(ArrayList<Point> points) {
		this(points,(Polygon p)-> {
						if(points.size() == 3) {	
							points.get(0).getX();
							Point a = points.get(0);
							Point b = points.get(1);
							Point c = points.get(2);
							 
							double ab = a.distance(b);
							double bc = b.distance(c);
							double ca = c.distance(a); 
							double s = (ab + bc + ca)/2;
							return ((float)Math.sqrt(s*(s-ab)*(s-bc)*(s-ca)));
						}
						else {
							Point a = points.get(0);
							Point b = points.get(1);
							Point c = points.get(2);
							Point d = points.get(3);
							 
							double ab = a.distance(b);
							double bc = b.distance(c);
							double ca = c.distance(a);
							return(float)(ab*bc);
						}
								  });

		
 	}
	
	public ArrayList<Point> getPoints() {
		return points;
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
		return areaCalc.getArea(this);
	}
	
}