package Main;

import Main.Subclasses.CargoPacket;
import Main.Subclasses.Order;
import Main.Subclasses.Package;
import Main.Subclasses.Products.*;
import Modules.List;
import Modules.Queue;
import Modules.Stack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String path = "orders.csv";
		List<Stack<Package>> listOfDefaultProducts = SetTheProducts();
		Stack<Package> cornStack = listOfDefaultProducts.get_Entry(0);
		Stack<Package> noodleStack = listOfDefaultProducts.get_Entry(1);
		Stack<Package> puddingStack = listOfDefaultProducts.get_Entry(2);
		Stack<Package> tunaStack = listOfDefaultProducts.get_Entry(3);
		Queue<Order> orderQueue = ReadFromFile(path);
		List<CargoPacket<Package>> cargoPacketList = new List<>(30);

		//Displaying the stacks before processing
		System.out.println("-------------------- Stacks before the process --------------------");
		for (int i = 0; i < 4; i++) {
			Stack<Package> temp = new Stack<>();
			if (i==0){
				System.out.println("---------- Pile of corn packages ----------");
				Stack<Package> tempStack = new Stack<>();
				for (int j = 0; j < 30; j++) {
					tempStack.push(cornStack.peek());
					String[] date =cornStack.peek().get_ExpirationDate().toString().split(" ");

					System.out.println("Product: Corn\n" + "Date: " + date[1] + " / " + date[5] + "\n");
					cornStack.pop();
				}
				for (int j = 0; j < 30; j++) {
					cornStack.push(tempStack.peek());
					tempStack.pop();
				}
			}
			else if (i==1){
				System.out.println("---------- Pile of noodle packages ----------");
				Stack<Package> tempStack = new Stack<>();
				for (int j = 0; j < 30; j++) {
					tempStack.push(noodleStack.peek());
					String[] date =noodleStack.peek().get_ExpirationDate().toString().split(" ");

					System.out.println("Product: Noodle\n" + "Date: " + date[1] + " / " + date[5] + "\n");
					noodleStack.pop();
				}
				for (int j = 0; j < 30; j++) {
					noodleStack.push(tempStack.peek());
					tempStack.pop();
				}
			}
			else if (i==2){
				System.out.println("---------- Pile of pudding packages ----------");
				Stack<Package> tempStack = new Stack<>();
				for (int j = 0; j < 30; j++) {
					tempStack.push(puddingStack.peek());
					String[] date =puddingStack.peek().get_ExpirationDate().toString().split(" ");

					System.out.println("Product: Pudding\n" + "Date: " + date[1] + " / " + date[5] + "\n");
					puddingStack.pop();
				}
				for (int j = 0; j < 30; j++) {
					puddingStack.push(tempStack.peek());
					tempStack.pop();
				}
			}
			else if (i==3){
				System.out.println("---------- Pile of tuna packages ----------");
				Stack<Package> tempStack = new Stack<>();
				for (int j = 0; j < 30; j++) {
					tempStack.push(tunaStack.peek());
					String[] date =tunaStack.peek().get_ExpirationDate().toString().split(" ");

					System.out.println("Product: Tuna\n" + "Date: " + date[1] + " / " + date[5] + "\n");
					tunaStack.pop();
				}
				for (int j = 0; j < 30; j++) {
					tunaStack.push(tempStack.peek());
					tempStack.pop();
				}
			}
		}

		//Displaying the waiting line of orders
		System.out.println("-------------------- Waiting line of orders --------------------");
		Queue<Order> tempQueue = new Queue<>();
		while (!orderQueue.isEmpty()){
			Order tempOrder = orderQueue.get_Front();
			tempQueue.enqueue(tempOrder);
			String[] date = tempOrder.get_Date().toString().split(" ");
			System.out.println("Order id: " + tempOrder.get_Id() + ",\t order date: " + date[2] + "/" + date[1] + "/" + date[5] + ",\t foods: " + tempOrder.get_FoodCategory()[0].strip() + " " + tempOrder.get_FoodCategory()[1].strip() + " " + tempOrder.get_FoodCategory()[2]);
			orderQueue.dequeue();
		}
		while (!tempQueue.isEmpty()){
			orderQueue.enqueue(tempQueue.get_Front());
			tempQueue.dequeue();
		}

		//Processing the orders
		while (!orderQueue.isEmpty()){
			Package food;
			List<Package> foods = new List();
			Order order = orderQueue.get_Front();
			String[] foodListOfOrder = order.get_FoodCategory();
			for (int i = 0; i < 3; i++) {
				if (foodListOfOrder[i].equals("corn")){
					 food = cornStack.peek();
					 cornStack.pop();
				}
				else if (foodListOfOrder[i].equals("noodle")){
					food = noodleStack.peek();
					noodleStack.pop();
				}
				else if (foodListOfOrder[i].equals("pudding")){
					food = puddingStack.peek();
					puddingStack.pop();
				}
				else {
					food = tunaStack.peek();
					tunaStack.pop();
				}
				foods.add(food);
			}
			Package[] foodArray = new Package[foods.get_Length()];
			for (int i = 0; i < foods.get_Length(); i++) {
				foodArray[i]=foods.get_Entry(i);
			}
			CargoPacket<Package> cargoPacket = new CargoPacket(foodArray,order.get_Date(),Integer.parseInt(order.get_Id()));
			cargoPacketList.replace(cargoPacket,cargoPacket.get_Id()-1);
			orderQueue.dequeue();
		}

		//Displaying the stacks after processing
		System.out.println("-------------------- Stacks after the process --------------------");
		for (int i = 0; i < 4; i++) {
			Stack<Package> temp = new Stack<>();
			if (i==0){
				System.out.println("---------- Pile of corn packages ----------");
				Stack<Package> tempStack = new Stack<>();
				while (!cornStack.isEmpty()){
					tempStack.push(cornStack.peek());
					String[] date =cornStack.peek().get_ExpirationDate().toString().split(" ");

					System.out.println("Product: Corn\n" + "Date: " + date[1] + " / " + date[5] + "\n");
					cornStack.pop();
				}
				while (!tempStack.isEmpty()) {
					cornStack.push(tempStack.peek());
					tempStack.pop();
				}
			}
			else if (i==1){
				System.out.println("---------- Pile of noodle packages ----------");
				Stack<Package> tempStack = new Stack<>();
				while (!noodleStack.isEmpty()){
					tempStack.push(noodleStack.peek());
					String[] date =noodleStack.peek().get_ExpirationDate().toString().split(" ");

					System.out.println("Product: Noodle\n" + "Date: " + date[1] + " / " + date[5] + "\n");
					noodleStack.pop();
				}
				while (!tempStack.isEmpty()) {
					noodleStack.push(tempStack.peek());
					tempStack.pop();
				}
			}
			else if (i==2){
				System.out.println("---------- Pile of pudding packages ----------");
				Stack<Package> tempStack = new Stack<>();
				while (!puddingStack.isEmpty()) {
					tempStack.push(puddingStack.peek());
					String[] date =puddingStack.peek().get_ExpirationDate().toString().split(" ");

					System.out.println("Product: Pudding\n" + "Date: " + date[1] + " / " + date[5] + "\n");
					puddingStack.pop();
				}
				while (!tempStack.isEmpty()) {
					puddingStack.push(tempStack.peek());
					tempStack.pop();
				}
			}
			else if (i==3){
				System.out.println("---------- Pile of tuna packages ----------");
				Stack<Package> tempStack = new Stack<>();
				while (!tunaStack.isEmpty()) {
					tempStack.push(tunaStack.peek());
					String[] date =tunaStack.peek().get_ExpirationDate().toString().split(" ");

					System.out.println("Product: Tuna\n" + "Date: " + date[1] + " / " + date[5] + "\n");
					tunaStack.pop();
				}
				while (!tempStack.isEmpty()) {
					tunaStack.push(tempStack.peek());
					tempStack.pop();
				}
			}
		}

		System.out.println("-------------------- Cargo Packets --------------------");
		for (int i = 0; i < cargoPacketList.get_Length(); i++) {
			CargoPacket cargoPacket = cargoPacketList.get_Entry(i);
			if (cargoPacketList.get_Entry(i)!=null){
				Package<Food>[] foodListOfCargoPacket = cargoPacketList.get_Entry(i).get_FoodPackage();
				String[] date = cargoPacket.get_ProcessDate().toString().split(" ");
				System.out.println("Cargo id: " + cargoPacket.get_Id() + ",\t process date: " + date[2] + "/" + date[1] + "/" + date[5] + ",\t foods:" + foodListOfCargoPacket[0].get_FoodProduct().getName() + ", " + foodListOfCargoPacket[1].get_FoodProduct().getName() + ", " + foodListOfCargoPacket[2].get_FoodProduct().getName());
			}
		}

		Package<Food>[] packageListWithId25 = cargoPacketList.get_Entry(25-1).get_FoodPackage();
		System.out.println("\n\nFoods in the order with id 25 are: " + packageListWithId25[0].get_FoodProduct().getName() + ": " + packageListWithId25[0].get_ExpirationDate().toString().split(" ")[1] + "/" + packageListWithId25[0].get_ExpirationDate().toString().split(" ")[5] + ", " + packageListWithId25[1].get_FoodProduct().getName() + ": " + packageListWithId25[1].get_ExpirationDate().toString().split(" ")[1] + "/" + packageListWithId25[1].get_ExpirationDate().toString().split(" ")[5] + ", " + packageListWithId25[2].get_FoodProduct().getName() + ": " + packageListWithId25[2].get_ExpirationDate().toString().split(" ")[1] + "/" + packageListWithId25[2].get_ExpirationDate().toString().split(" ")[5]);  

		cargoPacketList.remove(20);
		System.out.println("\n\n-------------------- Cargo Packets --------------------");
		for (int i = 0; i < cargoPacketList.get_Length(); i++) {
			CargoPacket cargoPacket = cargoPacketList.get_Entry(i);
			if (cargoPacketList.get_Entry(i)!=null){
				Package<Food>[] foodListOfCargoPacket = cargoPacketList.get_Entry(i).get_FoodPackage();
				String[] date = cargoPacket.get_ProcessDate().toString().split(" ");
				System.out.println("Cargo id: " + cargoPacket.get_Id() + ",\t process date: " + date[2] + "/" + date[1] + "/" + date[5] + ",\t foods:" + foodListOfCargoPacket[0].get_FoodProduct().getName() + ", " + foodListOfCargoPacket[1].get_FoodProduct().getName() + ", " + foodListOfCargoPacket[2].get_FoodProduct().getName());
			}
		}

	}
	public static List<Stack<Package>> SetTheProducts(){
		List<Stack<Package>> result = new List<Stack<Package>>();
		Stack<Package> cornStack = new Stack<Package>();
		Stack<Package> noodleStack = new Stack<Package>();
		Stack<Package> puddingStack = new Stack<Package>();
		Stack<Package> tunaStack = new Stack<Package>();
		Package food;
		Corn corn = new Corn();
		Noodle noodle = new Noodle();
		Pudding pudding = new Pudding();
		Tuna tuna = new Tuna();
		for (int i = 30; i >= 1; i--) {
			String exp_date;
			if (i>18)exp_date="-2024";
			else if (i>6)exp_date="-2023";
			else exp_date="-2022";
			exp_date=Integer.toString(((i + 5) % 12)+1)+exp_date;
			Date date = new Date();
			try {
				date = new SimpleDateFormat("MM-yyyy").parse(exp_date);
				food = new Package(corn,date);
				cornStack.push(food);
				food = new Package(noodle,date);
				noodleStack.push(food);
				food = new Package(pudding,date);
				puddingStack.push(food);
				food = new Package(tuna,date);
				tunaStack.push(food);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		result.add(cornStack);
		result.add(noodleStack);
		result.add(puddingStack);
		result.add(tunaStack);
		return result;
	}
	public static Queue<Order> ReadFromFile(String path){
		Queue<Order> orderQueue = new Queue<>();
		String line;
		String[] data;
		try {
			FileInputStream fis = new FileInputStream("orders.csv");
			try(Scanner scanner = new Scanner(fis)){
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					data = line.split(",");
					String id = data[0];
					String[] dataDate = data[1].split("-");
					String day,month,year;
					if (dataDate[0].length()==1)day="0"+dataDate[0];
					else day=dataDate[0];
					year = "20"+dataDate[2];
					if (dataDate[1].strip().equals("Jan"))month="01";
					else if (dataDate[1].strip().equals("Feb"))month="02";
					else if (dataDate[1].strip().equals("Mar"))month="03";
					else month="00";
					data[1]=day+"-"+month+"-"+year;
					Date date = new SimpleDateFormat("dd-MM-yyyy").parse(data[1]);
					String[] foods = {data[2],data[3],data[4]};
					Order order = new Order(data[0],date,foods);
					orderQueue.enqueue(order);
				}
			}
		} catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		}
		return orderQueue;
	}
}
