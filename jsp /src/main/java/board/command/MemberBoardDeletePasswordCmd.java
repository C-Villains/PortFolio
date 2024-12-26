package board.command;

import javax.servlet.http.*;

public class MemberBoardDeletePasswordCmd implements MembmerBoardCmd{
	
	public boolean password_check;

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String inputNum = request.getParameter("num");
		request.setAttribute("num", inputNum);
	}
}
