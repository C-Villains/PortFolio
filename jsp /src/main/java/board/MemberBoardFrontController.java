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

    public MemberBoardFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmdURI = requestURI.substring(contextPath.length());

		MemberBoardCmd cmd = null;
		String viewPage = null;

		// 회원게시판 리스트
		if(cmdURI.equals("/memberboardList.mbs")){
			cmd = new MemberBoardListCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.jsp";
		}
		
		// 글쓰기 폼 이동
		else if(cmdURI.equals("/memberboardWriteForm.mbs")){
			viewPage = "memberboardWrite.jsp";
		}
		
		// 글쓰기 처리
		else if(cmdURI.equals("/memberboardWrite.mbs")){
			cmd = new MemberBoardWriteCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.mbs"; // 여기에서 리다이렉트는 나중에 처리할 예정
		}
		
		// 글 읽기
		else if(cmdURI.equals("/memberboardRead.mbs")){
			cmd = new MemberBoardReadCmd();
			cmd.execute(request, response);
			viewPage = "memberboardRead.jsp";
		}
		
		// 게시글 비밀번호 설정
		else if(cmdURI.equals("/boardUpdatePassword.mbs")){
			cmd = new MemberBoardUpdatePasswordCmd();
			cmd.execute(request, response);			
			viewPage = "memberboardUpdatePassword.jsp";
		}
		
		// 글 수정 확인
		else if(cmdURI.equals("/memberboardUpdateCheck.mbs")){
			cmd = new MemberBoardUpdateCheckCmd();
			cmd.execute(request, response);
			
			// 올바른 타입으로 캐스팅
			MemberBoardUpdateCheckCmd checkCmd = (MemberBoardUpdateCheckCmd) cmd;
			if (checkCmd.password_check){
				viewPage = "memberboardUpdateForm.mbs";				
			} else {
				viewPage = "memberboardUpdateError.mbs";
			}
		}
		
		// 글 수정 에러 페이지 이동
		else if(cmdURI.equals("/memberboardUpdateError.mbs")){
			viewPage = "memberboardUpdateError.jsp";
		}
		
		// 글 수정 화면 요청
		else if(cmdURI.equals("/memberboardUpdateForm.mbs")){
			cmd = new MemberBoardUpdateFormCmd();
			cmd.execute(request, response);
			viewPage = "memberboardUpdateForm.jsp";
		}
		
		// 글 수정 처리
		else if(cmdURI.equals("/memberboardUpdate.mbs")){
			cmd = new MemberBoardUpdateCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.mbs"; // 여기에서 리다이렉트는 나중에 처리할 예정
		}
		
		// 글 삭제 비밀번호 확인 화면 요청
		else if(cmdURI.equals("/memberboardDeletePassword.mbs")){
			cmd = new MemberBoardDeletePasswordCmd();
			cmd.execute(request, response);			
			viewPage = "memberboardDeletePassword.jsp";
		}
		
		// 글 삭제 확인 처리
		else if(cmdURI.equals("/memberboardDeleteCheck.mbs")){
			cmd = new MemberBoardDeleteCheckCmd();
			cmd.execute(request, response);
			
			// 올바른 타입으로 캐스팅
			MemberBoardDeleteCheckCmd checkCmd = (MemberBoardDeleteCheckCmd) cmd;
			if (checkCmd.password_check && checkCmd.reply_check){
				viewPage = "memberboardDelete.mbs";				
			} else {
				viewPage = "memberboardDeleteError.mbs";
			}
		}
		
		// 글 삭제 에러 화면 요청
		else if(cmdURI.equals("/memberboardDeleteError.mbs")){
			viewPage = "memberboardDeleteError.jsp";
		}
		
		// 글 삭제 처리
		else if(cmdURI.equals("/memberboardDelete.mbs")){
			cmd = new MemberBoardDeleteCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.mbs"; // 여기에서 리다이렉트는 나중에 처리할 예정
		}
		
		// 게시글 검색 처리
		else if(cmdURI.equals("/memberboardSearch.mbs")){
			cmd = new MemberBoardSearchCmd();
			cmd.execute(request, response);
			viewPage = "memberboardSearchList.jsp";
		}
		
		// 답글 쓰기 화면 요청
		else if(cmdURI.equals("/memberboardReplyForm.mbs")){
			cmd = new MemberBoardReplyFormCmd();
			cmd.execute(request, response);
			viewPage = "memberboardReply.jsp";
		}
		
		// 답글 쓰기 처리
		else if(cmdURI.equals("/memberboardReply.mbs")){
			cmd = new MemberBoardReplyCmd();
			cmd.execute(request, response);
			viewPage = "memberboardList.mbs"; // 여기에서 리다이렉트는 나중에 처리할 예정
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}
}
