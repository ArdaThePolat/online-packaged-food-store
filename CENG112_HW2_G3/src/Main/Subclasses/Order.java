package Main.Subclasses;
import java.util.Date;

public class Order {
	private String id;
	private Date date;
	private String[] food_category;
	
	public Order(String id, Date date, String[] food_category) {
		this.id = id;
		this.date = date;
		this.food_category = food_category;
	}
	
	public String get_Id() {
		return this.id;
	}
	
	public Date get_Date() {
		return this.date;
	}
	
	public String[] get_FoodCategory() {
		return this.food_category;
	}
}
