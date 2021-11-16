package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Comparator;

public class Order {
	
	public Order(Customer customer, Server server) {
		this.customer = customer;
		this.server = server;
		id = nextID;
		nextID++;
	}
	
    public Order(BufferedReader in) throws IOException {
		this.id = Integer.parseInt(in.readLine());
		if(id >= nextID)
		{
			nextID = id+1;
		}
        
    }
	
    public void save(BufferedWriter out) throws IOException {
        out.write("" + id  + '\n');
        customer.save(out);
		server.save(out);
    }

	private int id;
	private Customer customer;
	private Server server;
	private HashMap<Product,Integer> products = new HashMap<>();
	static int nextID = 0;
}