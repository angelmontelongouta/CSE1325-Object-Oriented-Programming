import java.util.ArrayList;

public class Java extends Product
{
	public Java(String name, double price, double cost, Darkness darkness)
	{
		super(name,price,cost);
		this.darkness = darkness;
	}
	
	public void addShot(Shot shot)
	{
		shots.add(shot);
	}
	
	@Override
	public String toString()
	{
		String result = name + " (" + darkness + " with " + shots.get(0);
		for(int i = 1; i < shots.size(); i++)
		{
			result += ", " + shots.get(i);
		}
		result += ") $" + price;
		return result;
	}
	
	
	protected Darkness darkness;
	protected ArrayList<Shot> shots = new ArrayList<>();
}