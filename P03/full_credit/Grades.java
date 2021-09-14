import java.util.Scanner; 

public class Grades
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter student's name: ");
		Student sname = new Student(in.nextLine());
		String name = sname.getName();
		
		Double grade;
		System.out.print("Enter next grade (< 0 when done): ");
		grade = in.nextDouble();
		while ( grade > 0)
		{
			sname.addExam(grade);
			System.out.print("Enter next grade (< 0 when done): ");
			grade = in.nextDouble();
		}
		
		Double sAverage = sname.average();
		
		System.out.println(name + " has a " + sAverage + " average.");
	}
}