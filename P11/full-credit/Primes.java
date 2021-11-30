import java.util.ArrayList;
import java.text.NumberFormat;

// Committed to the Public Domain by Prof Rice
// You MAY adopt and modify this code without attribution

public class Primes {
    public Primes(int numThreads) {
		this.primes = new ArrayList<>();
    }
    public Primes findPrimes(int lower, int upper) {
		boolean result;
		for(int i = lower; i < upper; i++){
			result = isPrime(i);
			if(result == false){
				primes.add(i);
			}
		}
        return this;    
    }
    public int numberOfPrimes() {
		int result = 0;
		result = primes.size();
        return result;
    }
	public long sumOfPrimes(){
		long result = 0;
		for(int i = 0; i < primes.size(); i++){
			result += primes.get(i);
		}
		return result;
	}
	protected boolean isPrime(int number){
		boolean result = false;
		if(number < 2)
		{
			result = true;
		}
		else {
		    for (int i = 2; i <= number / 2; ++i) {
		      if (number % i == 0) {
		        result = true;
		        break;
		      }
		    }
		}
			return result;
	}
    public Integer[] toArray() {
		Integer arr[] = new Integer[primes.size()];
		        arr = primes.toArray(arr);
        return arr; // replace
    }
    
    public static void main(String[] args) {
        int numThreads = 1;
        int lower = 0;
        int upper = 1000000; // defaults
        if(args.length > 0) numThreads = Integer.parseInt(args[0]);
        if(args.length > 1) lower = Integer.parseInt(args[1]);
        if(args.length > 2) upper = Integer.parseInt(args[2]);
        if(args.length > 3) {
            System.err.println("usage: java Primes [lower upper]");
            System.exit(-args.length);
        }
        
        Primes primes = new Primes(numThreads); // Search using one thread
        int sumOfPrimes = 0;
        for(int prime : primes.findPrimes(lower, upper).toArray())
            sumOfPrimes += prime;
        System.out.println("Sum of the " + primes.numberOfPrimes() + " primes between " 
                         + lower + " and " 
                         + upper + " is " 
                         + primes.sumOfPrimes());
    }
	
	private int numThreads;
	private ArrayList<Integer> primes;
}
