package rd;

import student.Student;

public class ResidentDirector 
{
	public int buildingID;
	public Student st;
	
	public ResidentDirector()
	{
		buildingID = -1;
		st = new Student();
	}

	public int getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}
	
	public Student getStudent() {
		return st;
	}

	public void setStudent(Student st) {
		this.st = st;
	}

}
