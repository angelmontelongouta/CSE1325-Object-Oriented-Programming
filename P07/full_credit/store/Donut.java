package store;
public class Donut extends Product
{
	public Donut(String name, double price, double cost, Frosting frosting, boolean sprinkles, Filling filling)
	{
		super(name, price, cost);
		this.frosting = frosting;
		this.sprinkles = sprinkles;
		this.filling = filling;
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