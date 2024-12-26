package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import board.command.*;

@WebServlet("*.mbs")
public class MemberBoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberBoardFrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmdURI = requestURI.substring(contextPath.length());
		
		BoardCmd cmd = null;
		String viewPage = null;

		// 회원게시판		
		if(cmdURI.equals("/memberboardList.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.jsp";
		}
		
		// 글쓰기
		if(cmdURI.equals("/memberboardWriteForm.mbs")){
			viewPage = "memberboardWrite.jsp";
		}
		
		// 글쓰기 확인	
		if(cmdURI.equals("/memberboardWrite.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.mbs";
		}
		
		// 글 읽기
		if(cmdURI.equals("/memberboardRead.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);
			viewPage = "memberboardRead.jsp";
		}
		
		// 게시글 비밀번호 설정
		if(cmdURI.equals("/boardUpdatePassword.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);			
			viewPage = "memberboardUpdatePassword.jsp";
		}
		
		// 글 수정 확인
		if(cmdURI.equals("/memberboardUpdateCheck.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);
			
			BoardUpdateCheckCmd checkCmd = (BoardUpdateCheckCmd) cmd;
			if (checkCmd.password_check){
				viewPage = "memberboardUpdateForm.mbs";				
			} else {
				viewPage = "memberboardUpdateError.mbs";
			}
		}
		
		// 글 수정 에러
		if(cmdURI.equals("/memberboardUpdateError.mbs")){
			viewPage = "memberboardUpdateError.jsp";
		}
		
		// 글 수정 화면 요청
		if(cmdURI.equals("/memberboardUpdateForm.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);
			viewPage = "memberboardUpdateForm.jsp";
		}
		
		// 글 수정 처리
		if(cmdURI.equals("/memberboardUpdate.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.mbs";
		}
		
		// 글 삭제 비밀번호 확인 화면 요청
		if(cmdURI.equals("/memberboardDeletePassword.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);			
			viewPage = "memberboardDeletePassword.jsp";
		}
		
		// 글 삭제 확인 처리
		if(cmdURI.equals("/memberboardDeleteCheck.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);
			
			BoardDeleteCheckCmd checkCmd = (BoardDeleteCheckCmd) cmd;
			if (checkCmd.password_check && checkCmd.reply_check){
				viewPage = "memberboardDelete.mbs";				
			} else {
				viewPage = "memberboardDeleteError.mbs";
			}
		}
		
		// 글 삭제 에러 화면 요청
		if(cmdURI.equals("/memberboardDeleteError.mbs")){
			viewPage = "memberboardDeleteError.jsp";
		}
		
		// 글 삭제 처리
		if(cmdURI.equals("/memberboardDelete.mbs")){
			cmd = new BoardDeleteCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.mbs";
		}
		
		// 게시글 검색 처리
		if(cmdURI.equals("/memberboardSearch.mbs")){
			cmd = new BoardSearchCmd();
			cmd.execute(request, response);
			viewPage = "memberboardSearchList.jsp";
		}
		
		// 답글 쓰기 화면 요청
		if(cmdURI.equals("/memberboardReplyForm.mbs")){
			cmd = new BoardReplyFormCmd();
			cmd.execute(request, response);
			viewPage = "memberboardReply.jsp";
		}
		
		// 답글 쓰기 처리
		if(cmdURI.equals("/memberboardReply.mbs")){
			cmd = new BoardReplyCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.mbs";
		}
		
	
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}
}