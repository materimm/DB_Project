package ra;

public class ResidentAdvisor 
{
	int studentID;
	int resAdID;
	String name;
	int buildingID;
	String buildingName;
	int floorNum;
	int roomNum;
	int gradYear;
	String major;
	
	public ResidentAdvisor()
	{
		studentID = -1;
		resAdID = -1;
		name = "n/a";
		buildingID = -1;
		buildingName = "n/a";
		floorNum = -1;
		roomNum = -1;
		gradYear = -1;
		major = "n/a";
	}

	public int getStudentId() {
		return studentID;
	}

	public void setStudentId(int Studentid) {
		this.studentID = Studentid;
	}
	
	public int getRedId() {
		return resAdID;
	}

	public void setResId(int resid) {
		this.resAdID = resid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public int getGradYear() {
		return gradYear;
	}

	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
}
