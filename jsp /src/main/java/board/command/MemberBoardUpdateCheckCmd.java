package board.command;

import javax.servlet.http.*;

import board.model.*;

public class MemberBoardUpdateCheckCmd implements MembmerBoardCmd{
	
	public boolean password_check;

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String inputNum = request.getParameter("num");
		String inputPassword = request.getParameter("password");
		
		request.setAttribute("num", inputNum);
		
		MemberBoardDAO dao = new MemberBoardDAO();
		password_check = dao.MemberboardPasswordCheck(inputNum, inputPassword);
		
	}
}
