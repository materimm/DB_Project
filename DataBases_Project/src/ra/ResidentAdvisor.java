package ra;

import student.Student;

public class ResidentAdvisor 
{
	public int resAdID;
	public int buildingID;
	public String buildingName;
	public int floorNum;
	public int roomNum;
	public Student st;
	
	public ResidentAdvisor()
	{
		resAdID = -1;
		buildingID = -1;
		buildingName = "n/a";
		floorNum = -1;
		roomNum = -1;
		st = new Student();
	}

	public int getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getResAdID() {
		return resAdID;
	}

	public void setResAdID(int resAdID) {
		this.resAdID = resAdID;
	}
	
	public Student getStudent() {
		return st;
	}

	public void setStudent(Student st) {
		this.st = st;
	}

}
