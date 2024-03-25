package Main.Subclasses.Products;

public class Corn extends Food{
	private static int net_weight = 220;
	private static String name = "Corn Can";
	private static int drained_weight = 132;
	private static String production_country = "Turkey";
	
	public int get_NetWeight() {
		return net_weight;
	}
	
	public String getName() {
		return name;
	}
	
	public int get_DrainedWeight() {
		return drained_weight;
	}
	
	public String get_ProductionCountry() {
		return production_country;
	}
}