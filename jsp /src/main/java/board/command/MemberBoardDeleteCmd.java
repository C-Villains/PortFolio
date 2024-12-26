package board.command;

import javax.servlet.http.*;

import board.model.*;

public class MemberBoardDeleteCmd implements MembmerBoardCmd{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String inputNum = request.getParameter("num");
		
		BoardDAO dao = new BoardDAO();
		dao.boardDelete(inputNum);
	}
}
