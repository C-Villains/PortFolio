import java.io.IOException;
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
import javax.servlet.http.HttpSession;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

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

            // 이미 가입된 사용자인지 확인
            String checkSql = "SELECT * FROM users WHERE username = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 이미 가입된 사용자인 경우
                response.sendRedirect("userExists.jsp?error=UsernameExists");
                return;
            }

            // 사용자 정보 저장
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.executeUpdate();

            // 회원가입 성공 후 세션에 사용자 아이디 추가
            HttpSession session = request.getSession();
            session.setAttribute("userId", username);

            // 회원가입 성공 페이지로 이동
            response.sendRedirect("SignupSuccess.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            // 예외 처리
            e.printStackTrace();
            response.sendRedirect("LoginFail.jsp?error=SignUpFailed");
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
