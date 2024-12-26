import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // UTF-8 인코딩 설정
        PrintWriter out = response.getWriter();

        String id = request.getParameter("username");
        String pw = request.getParameter("password");

        String grade;
        String grade_name;

        if (id == null || pw == null || id.trim().equals("") || pw.trim().equals("")) {
            // 아이디나 비밀번호가 공백인 경우 손님으로 분류
            grade = "guest";
            grade_name = "손님";
            id = "";
            pw = "";
        } else {
            if (id.equals("admin")) {
                // 관리자 계정인 경우 비밀번호 검증 없이 로그인 성공
                grade = "admin";
                grade_name = "관리자";
            } else {
                // 회원 계정인 경우 아이디와 비밀번호를 검증하여 로그인 처리
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                try {
                    // JDBC 연결 정보
                    String jdbcUrl = "jdbc:mysql://localhost:3306/userdb";
                    String dbUser = "yeonchan"; 
                    String dbPassword = "loli24pop79!";

                    // JDBC 드라이버 로딩
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 데이터베이스 연결
                    conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

                    // SQL 쿼리 준비
                    String sql = "SELECT * FROM users WHERE username=? AND password=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    pstmt.setString(2, pw);

                    // SQL 쿼리 실행
                    rs = pstmt.executeQuery();

                    // 아이디와 비밀번호가 일치하는 회원이 존재하는 경우
                    if (rs.next()) {
                        grade = "member";
                        grade_name = "회원";
                    } else {
                        // 회원 계정의 아이디와 비밀번호가 일치하지 않는 경우
                        response.sendRedirect("LoginFail.jsp");
                        return;
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    // 예외 처리
                    e.printStackTrace();
                    response.sendRedirect("LoginFail.jsp?error=DatabaseError");
                    return;
                } finally {
                    // 리소스 해제
                    try {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // 로그인 성공시 메뉴 확인 페이지로 이동
        out.println("<html>");
        out.println("<head>");
        out.println("<title>환영합니다!</title>");
        
        // JavaScript로 팝업 창 띄우기
        out.println("<script type='text/javascript'>");
        out.println("alert('" + grade_name + "으로 접속하셨습니다. 환영합니다!');");
        out.println("</script>");

        out.println("</head>");
        out.println("<body>");
        out.println("<h3>[" + grade_name + "]으로 접속하셨습니다. 환영합니다!</h3>");
        out.println("<hr/>");
        out.println("<form action='HiddenFieldMenuServlet' method='post'>");
        out.println("<input type='hidden' name='id' value='" + id + "'/>");
        out.println("<input type='hidden' name='pw' value='" + pw + "'/>");
        out.println("<input type='hidden' name='grade' value='" + grade + "'/>");
        out.println("<input type='hidden' name='grade_name' value='" + grade_name + "'/>");
        out.println("<input type='submit' value='사용가능 메뉴 확인'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
