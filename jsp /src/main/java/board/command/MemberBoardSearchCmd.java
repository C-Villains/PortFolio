package board.command;

import javax.servlet.http.*;
import board.model.*;
import java.util.ArrayList;

public class MemberBoardSearchCmd implements MembmerBoardCmd{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberBoardDAO dao = new MemberBoardDAO();
		String searchOption = request.getParameter("searchOption");
		String searchWord = request.getParameter("searchWord");

		ArrayList<MemberBoardDTO> list = dao.MemberboardSearch(searchOption, searchWord);
		request.setAttribute("boardList", list);
		
	}
}
