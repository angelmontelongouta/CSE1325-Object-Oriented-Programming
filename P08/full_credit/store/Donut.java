package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Donut extends Product
{
	public Donut(String name, double price, double cost, Frosting frosting, boolean sprinkles, Filling filling)
	{
		super(name, price, cost);
		this.frosting = frosting;
		this.sprinkles = sprinkles;
		this.filling = filling;
	}
	
    public void save(BufferedWriter bw) throws IOException {
		bw.write("" + "Donut" + '\n');
		bw.write("" + name + '\n');
		bw.write("" + price + '\n');
		bw.write("" + cost + '\n');
		bw.write("" + frosting + '\n');
		bw.write("" + sprinkles + '\n');
		bw.write("" + filling + '\n');
    }
	
	@Override
	public String toString()
	{
		String result = name + " (frosted with " + frosting + ", filled with "+ filling;
		if(sprinkles == true)
		{
			result += ", and sprinkles";
		}
		result += ") $" + price;
		return result;
	}
	
	protected Frosting frosting;
	protected boolean sprinkles;
	protected Filling filling;
}