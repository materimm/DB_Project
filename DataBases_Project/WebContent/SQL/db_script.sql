-- source C:\Users\Morey\workspace\Databases_Project\WebContent\SQL\db_script.sql
-- source C:\Users\Owner\workspace\DataBases_Project\WebContent\SQL\db_script.sql

CREATE TABLE Buildings (
	BuildingID int(10) NOT NULL AUTO_INCREMENT,	
	Name varchar(50) NOT NULL, 
	PRIMARY KEY(BuildingID)
)Engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 


CREATE TABLE Students (
	StudentID int(10) NOT NULL AUTO_INCREMENT,
	FirstName varchar(50) NOT NULL,
	LastName varchar(50) NOT NULL,
	GradYear int(10) DEFAULT NULL,
	Major varchar(100) DEFAULT NULL,
	PRIMARY KEY(StudentID)
)Engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE ResidentAdvisors (
	ResAdID int(10) NOT NULL AUTO_INCREMENT,
	StudentID int(10) NOT NULL,
	BuildingID int(10) NOT NULL,
	FloorNumber int(10) NOT NULL,
	PRIMARY KEY(ResAdID),
	FOREIGN KEY(StudentID) REFERENCES Students(StudentID),
	FOREIGN KEY(BuildingID) REFERENCES Buildings(BuildingID)
)Engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE ResidentDirectors (
	StudentID int(10) NOT NULL,
	BuildingID int(10) NOT NULL,
	PRIMARY KEY(StudentID),
	FOREIGN KEY(StudentID) REFERENCES Students(StudentID),
	FOREIGN KEY(BuildingID) REFERENCES Buildings(BuildingID)
)Engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE Housing (
	StudentID int(10) NOT NULL,
	BuildingID int(10) NOT NULL,
	FloorNumber int(10) NOT NULL,
	RoomNumber int(10) NOT NULL,
	ResAdID int(10) NOT NULL,
	PRIMARY KEY(StudentID),
	FOREIGN KEY(StudentID) REFERENCES Students(StudentID),
	FOREIGN KEY(BuildingID) REFERENCES Buildings(BuildingID),
	FOREIGN KEY(ResAdID) REFERENCES ResidentAdvisors(ResAdID)
)Engine=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
