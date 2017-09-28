package homework1;

public class Car {
	private int Price;
	private int Year;
	private	float Milage;

	
	public Car(int price, int year, float milage){
		this.Price = price;
		this.Year = year;
		this.Milage = milage;
	}
	
	public int getPrice(){
		return Price;
	}
	public  int getYear(){
		return Year;
	}
	public  float getMilage(){
		return Milage;
	}
}