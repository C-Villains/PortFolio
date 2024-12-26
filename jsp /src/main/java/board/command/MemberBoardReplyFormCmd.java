package board.command;

import javax.servlet.http.*;
import board.model.*;

public class MemberBoardReplyFormCmd implements MembmerBoardCmd{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String inputNum = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		
		BoardDTO writing = dao.boardReplyForm(inputNum);
		request.setAttribute("boardReplyForm", writing);
	}
}
