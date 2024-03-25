package Main.Subclasses.Products;

public class Pudding extends Food{
	private static int net_weight = 120;
	private static String name = "Pudding Packet";
	private static String flavor = "Banana";
	
	public int get_NetWeight() {
		return net_weight;
	}
	
	public String getName() {
		return name;
	}
	
	public String get_Flavor() {
		return flavor;
	}
}