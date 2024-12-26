import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HiddenFieldMenuServlet")
public class HiddenFieldMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String grade = request.getParameter("grade");
        String grade_name = request.getParameter("grade_name");

        // 사용자 정보가 없는 경우, 로그인 페이지로 이동
        if (id == null || pw == null || id.trim().equals("") || pw.trim().equals("") || grade == null || grade_name == null) {
            response.sendRedirect("HiddenFieldLogin.jsp");
            return;
        }

        // 세션 생성 및 사용자 정보 설정
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        session.setAttribute("grade", grade);
        session.setAttribute("grade_name", grade_name);

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");
        
        html.append("</head>");
        html.append("<body>");
        html.append("<h3>[")
            .append(id)
            .append("(")
            .append(grade_name) 
            .append(")] 접속중입니다.<br/>")
            .append("사용가능 메뉴입니다.</h3> <hr/>");
        // 사용자 등급에 따라 다른 메뉴 표시
        if (grade.equals("admin")) {
            html.append("<input type='button' value='사이트 관리'>")
                .append("<input type='button' value='회원관리'>")
                .append("<form action='boardList.bbs' method='post'>")
                .append("<input type='submit' value='자유 게시판'>")
                .append("</form>")
                .append("<form action='memberboardList.mbs'method='post'>")
                .append("<input type='submit' value='회원 게시판'>")
                .append("</form>");
        } else if (grade.equals("member")) {
            html.append("<form action='boardList.bbs' method='post'>")
                .append("<input type='submit' value='자유 게시판'>")
                .append("</form>");
            html.append("<form action='memberboardList.mbs' method='post'>")
                .append("<input type='submit' value='회원 게시판'>")
                .append("</form>");
        } else {
            html.append("<form action='boardList.bbs' method='post'>")
                .append("<input type='submit' value='자유 게시판'>")
                .append("</form>");
        }

        html.append("</body>");
        html.append("</html>");

        // HTML 코드 출력
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(html.toString());
    }
}
