package homework2;

import java.awt.Point;
import java.util.ArrayList;

public class Client {
	public static void main(String[] args) {
		ArrayList<Point> al = new ArrayList<Point>();
		
		al.add( new Point(0,0)); 
		al.add( new Point(4,0));
		al.add( new Point(4,3));
		Polygon p = new Polygon( al,(ArrayList<Point> points)->
									{
										Point a = points.get(0);
										Point b = points.get(1);
										Point c = points.get(2);
										 
										double ab = a.distance(b);
										double bc = b.distance(c);
										double ca = c.distance(a); 
										double s = (ab + bc + ca)/2;
										return ((float)Math.sqrt(s*(s-ab)*(s-bc)*(s-ca)));
									});
		
		System.out.println("Area of triangle :");
		System.out.println(p.getArea());
		System.out.println("Transforming to Rectangle by adding a point");
		p.addPoint(new Point(0,3));
		p.setAreaCalc((ArrayList<Point> points)->{
										Point a = points.get(0);
										Point b = points.get(1);
										Point c = points.get(2);
										Point d = points.get(3);
										 
										double ab = a.distance(b);
										double bc = b.distance(c);
										double ca = c.distance(a);
										return(float)(ab*bc);
									});
		System.out.println("Area of Rectangle :");
		System.out.println(p.getArea());
	}
}
