package store;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Java extends Product
{
	public Java(String name, double price, double cost, Darkness darkness)
	{
		super(name,price,cost);
		this.darkness = darkness;
	}
	
    public void save(BufferedWriter bw) throws IOException {
		bw.write("" + "Java" + '\n');
		bw.write("" + name + '\n');
		bw.write("" + price + '\n');
		bw.write("" + cost + '\n');
		bw.write("" + darkness + '\n');
    }
	
	public void addShot(Shot shot)
	{
		shots.add(shot);
	}
	
	@Override
	public String toString()
	{
		String result;
		if(shots.get(0) != Shot.None)
		{
			result = name + " (" + darkness + " with " + shots.get(0);
			for(int i = 1; i < shots.size(); i++)
			{
				result += ", " + shots.get(i);
			}
			result += ") $" + price;
		}
		else
		{
			result = name + " (" + darkness + " with no shots) $" + price;
		}

		return result;
	}
	
	
	protected Darkness darkness;
	protected ArrayList<Shot> shots = new ArrayList<>();
}