package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ra.ResidentAdvisor;
import rd.ResidentDirector;
import student.Student;
import utils.RADAO;
import utils.RDDAO;
import utils.StudentDAO;

/**
 * Servlet implementation adding students
 */
public class StudentAdd extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		StudentDAO sdao = new StudentDAO();
		Student st = new Student();
		ResidentAdvisor ra = new ResidentAdvisor();
		ResidentDirector rd = new ResidentDirector();

		String buildingInfo = request.getParameter("build");
		String bInfo[] = buildingInfo.split(":");
		
		st.setFirstName(request.getParameter("firstName"));
		st.setLastName(request.getParameter("lastName"));
		st.setGradYear(Integer.parseInt(request.getParameter("gradYear")));
		st.setMajor(request.getParameter("major"));

		st.setBuildingID(Integer.parseInt(bInfo[0]));		
		st.setFloorNum(Integer.parseInt(request.getParameter("floornum")));
		st.setRoomNum(Integer.parseInt(request.getParameter("roomnum")));
		
		String res = request.getParameter("Res");

		//Students table insert
		st = sdao.setStudent(st);


		//RD table insert
		if(res.equals("RD"))
		{
			rd.setBuildingID(st.getBuildingID());
			rd.setStudent(st);
			RDDAO rddao = new RDDAO();
			rddao.setRD(rd);
			
		}
		//RA table insert
		else if(res.equals("RA"))
		{
			ra.setBuildingID(st.getBuildingID());
			ra.setBuildingName(bInfo[1]);
			ra.setFloorNum(st.getFloorNum());
			ra.setRoomNum(st.getRoomNum());
			ra.setStudent(st);
			RADAO radao = new RADAO();
			radao.setRA(ra);
		}
	

		//Housing insert
		sdao.setHousing(st);
		
	}
}
