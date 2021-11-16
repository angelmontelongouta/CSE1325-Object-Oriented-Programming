package store;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Store {
    public Store(String storeName) {
        this.storeName = storeName;
        this.products = new ArrayList<>();
		this.persons = new ArrayList<>();
		this.orders = new ArrayList<>();
    }
    public Store(BufferedReader in) throws IOException {
        this(in.readLine());
        int size = Integer.parseInt(in.readLine());
        for(int i=0; i<size; ++i) {
            String productType = in.readLine();
            switch(productType) {
                case Java.ID:  products.add(new Java(in));  break;
                case Donut.ID: products.add(new Donut(in)); break;
                default: throw new IOException("Invalid product type: " + productType);
            }
        }
        int size2 = Integer.parseInt(in.readLine());
        for(int i=0; i<size2; ++i) {
            String personType = in.readLine();
            switch(personType) {
                case Customer.ID:  persons.add(new Customer(in));  break;
				case Server.ID: persons.add(new Server(in)); break;
                default: throw new IOException("Invalid person type: " + personType);
            }
        }
    }
    public void save(BufferedWriter out) throws IOException {
        out.write(storeName + '\n');
        out.write("" + products.size() + '\n');
        for(Product p : products)
            p.save(out);
        out.write("" + persons.size() + '\n');
        for(Person p2 : persons)
            p2.save(out);
    }

    String name() {return storeName;}
    public void addProduct(Product product) {
        products.add(product);
    }
	public void addPerson(Person person) {
		persons.add(person);
	}
	public void addOrder(Order order) {
		orders.add(order);
	}
    public int numberOfProducts() {
        return products.size();
    }
	public int numberOfPeople() {
		return persons.size();
	}
	public int numberOfOrders() {
		return orders.size();
	}
    public String toString(int index) {
        return products.get(index).toString();
    }
	public String peopleToString(int index) {
		return persons.get(index).toString();
	}
	public String ordersToString(int index) {
		return orders.get(index).toString();
	}
    @Override
    public String toString() {
        String result = "Welcome to " + storeName + "\n\n";
        for(int i=0; i<products.size(); ++i) {
            result += i + ") " + products.get(i) + "\n";
        }
        return result;
    }
	public String peopleToString() {
		String result = " Our Beloved Customers \n\n";
		for(int i=0; i<persons.size(); ++i)
		{
			result += i + ") " + persons.get(i) + "\n";
		}
		return result;
	}
	public String ordersToString() {
		String results = "Our Current Orders \n\n";
		for(int i=orders.size(); i>0; --i) {
			results += i + ") " + orders.get(i) + "\n";
		}
		return results;
	}
	
    private String storeName;
	private ArrayList<Order> orders;
    private ArrayList<Product> products;
	private ArrayList<Person> persons;
}
