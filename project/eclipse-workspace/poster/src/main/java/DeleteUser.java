import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userId = request.getParameter("id");
        String loggedInEmail = (String) session.getAttribute("email");

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/poster";
            con = DriverManager.getConnection(url, "root", "");
            st = con.createStatement();

            // Get email of user to be deleted
            String getEmailQuery = "SELECT email FROM user WHERE id=" + userId;
            rs = st.executeQuery(getEmailQuery);

            if (rs.next()) {
                String emailToDelete = rs.getString("email");

                // Ensure admin cannot delete themselves
                if (!loggedInEmail.equals(emailToDelete)) {
                    String query = "DELETE FROM user WHERE id=" + userId;
                    st.executeUpdate(query);
                }
            }

            response.sendRedirect("adminDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (st != null) try { st.close(); } catch (SQLException ignore) {}
            if (con != null) try { con.close(); } catch (SQLException ignore) {}
        }
    }
}
