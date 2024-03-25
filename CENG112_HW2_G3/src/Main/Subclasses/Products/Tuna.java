package Main.Subclasses.Products;

public class Tuna extends Food{
	private static int net_weight = 75;
	private static String name = "Tuna Can";
	private static int drained_weight = 50;
	private static String[] ingredients = {"Tuna", "Sunflower Oil", "Salt"};
	
	public int get_NetWeight() {
		return net_weight;
	}
	
	public String getName() {
		return name;
	}
	
	public int get_DrainedWeight() {
		return drained_weight;
	}
	
	public String[] get_Ingredients() {
		return ingredients;
	}
}