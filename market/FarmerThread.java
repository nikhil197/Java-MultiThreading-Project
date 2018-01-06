package market;

import java.util.HashMap;

public class FarmerThread extends Thread {

	public Market market;
	
	public static int count = 0;

	public HashMap<Fruits, Integer> quantity;

	public int totalQuantity;

	public FarmerThread(Market market, int apples, int oranges, int grapes, int watermelons) {
		this.market = market;
		quantity = new HashMap<>();
		quantity.put(Fruits.APPLE, apples);
		quantity.put(Fruits.ORANGE, oranges);
		quantity.put(Fruits.GRAPE, grapes);
		quantity.put(Fruits.WATERMELON, watermelons);
		totalQuantity = apples + oranges + grapes + watermelons;
	}

	public void run() {

		try {
			while(totalQuantity != 0) {
				if(market.availableQuantity == market.capacity)
					wait();
				else 
					market.AddFruit(quantity);
				UpdateQuantity();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateQuantity() {
		totalQuantity = 0;
		for(int i = 0; i < Fruits.values().length; i++) {
			totalQuantity += quantity.get(Fruits.values()[i]);
		}
	}

}