public class Fuse
{
	public Fuse(int time)
	{
		this.time = time;
	}
	
	public boolean burn()
	{
		if ( time > 0 )
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
		String str = "-";
		String repeat = new String(new char[time]).replace("\0", str);
		String fuse = "[TNT]";
		String fire = "*";
		return fuse+repeat+fire;
	}
		
	private int time;
}