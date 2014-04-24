package building;

public class Building 
{
	public int buildingID;
	public String name;
	public int numFloors;
	public int numRooms;
	public int numApartments;
	
	public Building()
	{
		buildingID = -1;
		name = "n/a";
		numFloors = -1;
		numRooms = -1;
		numApartments = -1;
	}

	public int getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(int buildingID) {
		this.buildingID = buildingID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumFloors() {
		return numFloors;
	}

	public void setNumFloors(int numFloors) {
		this.numFloors = numFloors;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public int getNumApartments() {
		return numApartments;
	}

	public void setNumApartments(int numApartments) {
		this.numApartments = numApartments;
	}


}
