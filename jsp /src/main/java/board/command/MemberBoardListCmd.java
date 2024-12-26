package board.command;

import javax.servlet.http.*;
import java.util.ArrayList;
import board.model.*;

public class MemberBoardListCmd implements MembmerBoardCmd{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberBoardDAO dao = new MemberBoardDAO();
		ArrayList<MemberBoardDTO> list;
		
		int pageCnt=0;		
		String curPage = request.getParameter("curPage");
		
		if (curPage == null) curPage = "1";

		list = dao.MemberboardList(curPage);
		
		request.setAttribute("boardList", list);
		
		pageCnt = dao.MemberboardPageCnt();
		request.setAttribute("pageCnt", pageCnt);
	}
}
