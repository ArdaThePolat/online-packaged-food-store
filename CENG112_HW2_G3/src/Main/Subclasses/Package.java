package Main.Subclasses;

import java.util.Date;

public class Package<T> {
	private T food_product;
	private Date expiration_date;
	
	public Package(T food, Date date) {
		this.food_product = food;
		this.expiration_date = date;
	}
	
	public T get_FoodProduct() {
		return this.food_product;
	}
	
	public Date get_ExpirationDate() {
		return this.expiration_date;
	}
}
