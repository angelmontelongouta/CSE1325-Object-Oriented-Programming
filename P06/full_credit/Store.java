import java.util.ArrayList;
import java.util.Scanner;

public class Store
{
	public Store(String storeName)
	{
		this.storeName = storeName;
	}
	
	public String storeName()
	{
		return storeName;
	}
	
	public void addProduct(Product product)
	{
		products.add(product);
	}
	
	public int numberOfProducts()
	{
		return products.size();
	}
	
	public String toString(int productIndex)
	{
		String result = productIndex + ") " + products.get(productIndex);
		return result;
	}
	
	@Override
	public String toString()
	{
		int size = products.size();
		String result = "0) " + products.get(0) + "\n";
		for(int i = 1; i < size; i++)
		{
			result += i + ") " + products.get(i) + "\n";
		}
		return result;
	}
	
	protected String storeName;
	protected ArrayList<Product> products = new ArrayList<>();
}