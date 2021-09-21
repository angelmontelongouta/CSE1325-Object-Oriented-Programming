public class Puzzle
{
	public Puzzle(String solution)
	{
		this.solution = solution;
		underScore = new char[solution.length()];
		for (int i = 0; i < underScore.length; i++)
		{
			underScore[i] = '_';
		}
	}
	
	public boolean guess(char c)
	{
		guessChar = c;
		int pos = c - 'a';
		
		guesses[pos] = true;
		
		if(solution.indexOf(c) != -1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public String toString()
	{	
		char[] charSolution = solution.toCharArray();
		
		for (int i = 0; i < charSolution.length; i++) 
		{
		    if (charSolution[i] == guessChar)
			{
				underScore[i] = guessChar;
			}
		}
		
		return new String(underScore);
	}
	
	
	public boolean solve(String proposedSolution)
	{
		return solution.equals(proposedSolution);
	}
	
	public String getSolution()
	{
		return solution;
	}
	
	private String solution;
	private boolean[] guesses = new boolean[26];
	private char guessChar;
	private char[] underScore;
}