import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeletePost")
public class DeletePost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeletePost() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String postId = request.getParameter("id");
        String loggedInEmail = (String) session.getAttribute("email");

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/poster";
            con = DriverManager.getConnection(url, "root", "");
            st = con.createStatement();

            // Ensure only the post owner or admin can delete the post
            String getPostQuery = "SELECT user_id FROM post WHERE id=" + postId;
            rs = st.executeQuery(getPostQuery);

            if (rs.next()) {
                int userId = rs.getInt("user_id");

                // Get the user ID of the logged-in user
                String getUserQuery = "SELECT id FROM user WHERE email='" + loggedInEmail + "'";
                ResultSet userRs = st.executeQuery(getUserQuery);

                if (userRs.next()) {
                    int loggedInUserId = userRs.getInt("id");

                    // Check if the logged-in user is the owner of the post or an admin
                    if (userId == loggedInUserId || "admin".equals(session.getAttribute("role"))) {
                        String deleteQuery = "DELETE FROM post WHERE id=" + postId;
                        st.executeUpdate(deleteQuery);
                    }
                }

                if (userRs != null) userRs.close();
            }
            if( "admin".equals(session.getAttribute("role")))
            	response.sendRedirect("adminDashboard.jsp");
            else
            	response.sendRedirect("simpleDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (st != null) try { st.close(); } catch (SQLException ignore) {}
            if (con != null) try { con.close(); } catch (SQLException ignore) {}
        }
    }
}
