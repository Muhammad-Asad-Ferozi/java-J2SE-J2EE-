import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/UpdatePost")
public class UpdatePost extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdatePost() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession ses = request.getSession(false);
        
        if (ses == null || ses.getAttribute("email") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String postId = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/poster";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();
            
            String query = "UPDATE post SET title='" + title + "', description='" + description + "' WHERE id=" + postId;
            int rs = st.executeUpdate(query);
            
            if (rs == 1) {
                response.sendRedirect("showPost.jsp");
            } else {
                out.println("<h1>Post could not be updated.</h1>");
            }
            
            st.close();
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }
}
