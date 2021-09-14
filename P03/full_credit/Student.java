public class Student
{
	private String studentName;
	private Double examSum = 0.00;
	private Double examNumGrades = 0.00;
	
	public Student(String name)
	{
		studentName = name;
	}
	
	String getName()
	{
		return studentName;
	}
	
	public void addExam(Double grade)
	{
		double tempgrade = grade;
		examSum = examSum + tempgrade;
		examNumGrades = examNumGrades + 1;
	}
	
	Double average()
	{
		return (examSum/examNumGrades);
	}
	
}