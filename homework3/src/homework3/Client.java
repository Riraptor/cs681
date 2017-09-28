package homework3;

import java.awt.Point;
import java.util.ArrayList;

public class Client {
	public static void main(String[] args) {
		ArrayList<Point> al = new ArrayList<Point>();
		
		al.add( new Point(0,0)); 
		al.add( new Point(4,0));
		al.add( new Point(4,3));
		Polygon p = new Polygon(al);
		p.addPoint(new Point(0,3));
		System.out.println("Area Using Free Variable");
		System.out.println(p.getArea());
	}
}
