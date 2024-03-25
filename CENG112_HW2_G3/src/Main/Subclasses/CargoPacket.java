package Main.Subclasses;

import java.util.Date;

public class CargoPacket<T> {
    private T[] food_package;
    private Date process_date;
    private int id;

    public CargoPacket(T[] foods, Date order_date,int order_id) {
        this.food_package = foods;
        this.process_date = order_date;
        this.id = order_id;
    }

    public T[] get_FoodPackage() {
        return this.food_package;
    }

    public Date get_ProcessDate() {
        return this.process_date;
    }
    public int get_Id(){
        return id;
    }
}
