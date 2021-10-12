import java.util.ArrayList;
import java.util.Scanner;

public class Controller
{
	public static void main(String[] args)
	{
		Controller store = new Controller();
		store.cli();
	}
	
	public void cli()
	{
		Scanner in = new Scanner(System.in);
		System.out.print("What is the name of the new store? ");
		String name = in.nextLine();
		Store store = new Store(name);
		String storeName = store.storeName();
		System.out.println("Welcome to " + storeName);
		
		System.out.print("\n\nOptions:\n0) Exit\n1) Add Java\n2) Add Donut\n\nEnter Choice: ");
		int choice = in.nextInt();
		in.nextLine();
		String productName;
		double cost, price;
		
		while(choice != 0)
		{
			if(choice == 1)
			{
				System.out.print("Name: ");
				productName = in.nextLine();
				System.out.print("Cost: ");
				cost = in.nextDouble();
				System.out.print("Price: ");
				price = in.nextDouble();
				in.nextLine();
				int darkness,i;
				darkness = -1;
				i = 0;
				while(darkness >= i || darkness < 0)
				{
					i = 0;
					System.out.print("\n\nBrew Options\n============\n");
					for (Darkness d: Darkness.values())
					{
					    System.out.println(i + ") " + d);
						i++;
					}
					System.out.print("\n\nWhich darkness: ");
					darkness = in.nextInt();
					in.nextLine();
					if( darkness > i-1)
					{
						System.out.print("\n\nNOT AN OPTION");
					}
				}
				int shot = 1;
				Java java = new Java(productName,price,cost,Darkness.values()[darkness]);
				while(shot != 0 && shot != -1)
				{
					i = 0;
					System.out.print("\n\nShot Options\n============\n");
					for (Shot d: Shot.values())
					{
					    System.out.println(i + ") " + d);
						i++;
					}
					System.out.print("\n\nAdd Shot (-1 when done): ");
					shot = in.nextInt();
					in.nextLine();
					if(shot > i-1)
					{
						System.out.print("\n\nNOT AN OPTION");
					}
					else if(shot != 0 && shot != -1)
					{
						java.addShot(Shot.values()[shot]);
					}
				}
				
				Product product = java;
				store.addProduct(product);
			
			}
			else if(choice == 2)
			{
				System.out.print("Name: ");
				productName = in.nextLine();
				System.out.print("Cost: ");
				cost = in.nextDouble();
				System.out.print("Price: ");
				price = in.nextDouble();
				in.nextLine();
				int frosting,i;
				frosting = -1;
				i = 0;
				while(frosting >= i || frosting < 0)
				{
					i = 0;
					System.out.print("\n\nFrosting Options\n================\n");
					for (Frosting d: Frosting.values())
					{
					    System.out.println(i + ") " + d);
						i++;
					}
					System.out.print("\n\nWhich frosting: ");
					frosting = in.nextInt();
					in.nextLine();
					if( frosting > i-1)
					{
						System.out.print("\n\nNOT AN OPTION");
					}
				}
				int filling = -1;
				i = 0;
				while(filling >= i || filling < 0)
				{
					i = 0;
					System.out.print("\n\nFilling Options\n================\n");
					for (Filling d: Filling.values())
					{
					    System.out.println(i + ") " + d);
						i++;
					}
					System.out.print("\n\nWhich filling: ");
					filling = in.nextInt();
					in.nextLine();
					if( frosting > i-1)
					{
						System.out.print("\n\nNOT AN OPTION");
					}
				}
				
				int sprinkleInt = -1;
				boolean sprinkles = false;
				while(sprinkleInt != 0 && sprinkleInt != 1)
				{
					System.out.print("\n\nSprinkles (0 for NO, 1 for YES): ");
					sprinkleInt = in.nextInt();
					in.nextLine();
					if(sprinkleInt != 0 && sprinkleInt != 1)
					{
						System.out.print("\n\nNOT AN OPTION");
					}
				}
				if(sprinkleInt == 1)
				{
					sprinkles = true;
				}
				
				Donut donut = new Donut(productName,price,cost,Frosting.values()[frosting],sprinkles,Filling.values()[filling]);
				Product product = donut;
				store.addProduct(product);
			}
			else
			{
				System.out.println("\nNOT AN OPTION");
			}
			
			System.out.println("\n\nWelcome to " + storeName);
			System.out.print("\n\n" + store.toString());
			System.out.print("\n\nOptions:\n0) Exit\n1) Add Java\n2) Add Donut\n\nEnter Choice: ");
			choice = in.nextInt();
			in.nextLine();
		}
	}
}