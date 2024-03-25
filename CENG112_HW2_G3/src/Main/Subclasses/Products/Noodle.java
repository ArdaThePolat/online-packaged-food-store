package Main.Subclasses.Products;

public class Noodle extends Food{
	private static int net_weight = 120;
	private static String name = "Instant Noodle Packet";
	private static int simmer_duration_by_minutes = 3;
	
	public int get_NetWeight() {
		return net_weight;
	}
	
	public String getName() {
		return name;
	}
	
	public int get_SimmerDurationByMinutes() {
		return simmer_duration_by_minutes;
	}
}