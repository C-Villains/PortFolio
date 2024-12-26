package board.command;

import javax.servlet.http.*;

import board.model.*;

public class MemberBoardUpdateCmd implements MembmerBoardCmd{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String inputNum = request.getParameter("num");
		String inputSubject = request.getParameter("subject");
		String inputContent = request.getParameter("content");
		String inputName = request.getParameter("name");
		String inputPassword = request.getParameter("password");
		
		MemberBoardDAO dao = new MemberBoardDAO();
		dao.MemberboardUpdate(inputNum, inputSubject, inputContent, inputName, inputPassword);
	}
}
