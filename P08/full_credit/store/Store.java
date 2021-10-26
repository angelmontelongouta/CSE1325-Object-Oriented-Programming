package store;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Store
{
	public Store(String storeName)
	{
		this.storeName = storeName;
	}
	
	public Store()
	{
		this.storeName = "JADE";
	}
	
	public String storeName()
	{
		return storeName;
	}
	
	public Store(BufferedReader br) throws IOException {
		storeName = br.readLine();
		
    }
	
    public void save(BufferedWriter bw) throws IOException {
		bw.write("" + storeName + '\n');
		
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