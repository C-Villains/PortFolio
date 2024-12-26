package board.command;

import javax.servlet.http.*;

import board.model.*;

public class MemberBoardDeleteCheckCmd implements MembmerBoardCmd{
	
	public boolean password_check;
	public boolean reply_check;

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String inputNum = request.getParameter("num");
		String inputPassword = request.getParameter("password");
		
		request.setAttribute("num", inputNum);
		
		MemberBoardDAO dao = new MemberBoardDAO();
		
		password_check = dao.MemberboardPasswordCheck(inputNum, inputPassword);
		reply_check = dao.MemberboardReplyCheck(inputNum);
		
		System.out.println("password_check : " + password_check);
		System.out.println("reply_check : " + reply_check);
	}
}
