import java.util.Scanner; 

public class Boom
{
	public void cli()
	{
		System.out.println("     =======================");
		System.out.println("            B O O M !!!");
		System.out.println("     =======================");
		System.out.println("");
		
		String phrase = "University";
		phrase = phrase.toLowerCase();
		Puzzle boom = new Puzzle(phrase);
		
		System.out.println("Enter lower case letters to guess,");
		System.out.println("! to propose a solution,");
		System.out.println("0 to exit/quit.");
		
		int timeINT = 2;
		boolean timeTF = true;
		Fuse time = new Fuse(timeINT);
		String tnt = time.toString();
		
		String underScore = boom.toString();
		
		Scanner in = new Scanner(System.in);
		char guess;
		
		boolean correctYN;
		boolean solveYN = false;
			
		while(timeTF)
		{
			System.out.println("");
			System.out.println(tnt);
			System.out.println("");
			System.out.println(underScore);
			
			System.out.print("Guess a letter: ");
			guess = in.nextLine().charAt(0);
			
			if(Character.isLetter(guess))
			{
				guess = Character.toLowerCase(guess);
				correctYN = boom.guess(guess);
				if(correctYN)
				{
					System.out.println(guess + " is in the phrase!");
					underScore = boom.toString();
					if(underScore.indexOf('_') == -1)
					{
						solveYN = true;
						break;
					}
				}
				else
				{
					System.out.println("No, "+guess+" is incorrect.");
					timeINT--;
					time = new Fuse(timeINT);
					tnt = time.toString();
					timeTF = time.burn();
				}
			}
			else if(guess == '!')
			{
				System.out.print("What is the phrase? ");
				String proposedPhrase = in.nextLine();
				proposedPhrase = proposedPhrase.toLowerCase();
				solveYN = boom.solve(proposedPhrase);
				break;
			}
			else if(guess == '0')
			{
				System.out.println("Solution was "+phrase+".");
				System.exit(0);
			}
			else
			{
				System.out.println("NOT A VAILD INPUT, PLEASE TRY AGAIN.");
				System.out.println("Enter lower case letters to guess,");
				System.out.println("! to propose a solution,");
				System.out.println("0 to exit/quit.");
			}
		}
		
		if(timeTF == false)
		{
			System.out.println("**** B O O M ****");
			System.out.println("You lost, better luck next time. The phrase was "+phrase+".");
		}
		else if(solveYN == true)
		{
			System.out.println("**** W I N N E R ****");
			System.out.println("The secret phrase was "+phrase+".");
		}
		else if(solveYN == false)
		{
			System.out.println("**** B O O M ****");
			System.out.println("You lost, better luck next time. The phrase was "+phrase+".");
		}

	}
	
	public static void main(String[] args)
	{
		Boom gameplay = new Boom();
		gameplay.cli();
	}
}