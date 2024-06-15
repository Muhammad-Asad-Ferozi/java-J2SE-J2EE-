import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();


        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.jdbc.Driver"); // Updated driver class name

            String url = "jdbc:mysql://127.0.0.1/poster";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();

            String query = "SELECT * FROM user WHERE email='" + email + "'";
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                String pass = rs.getString("password");
                if (password.equals(pass)) {
                    // Set session attribute for authenticated user
                	String role = rs.getString("role");
                	
                    HttpSession session = request.getSession(true);
                    session.setAttribute("email", email);
                    session.setAttribute("role", role);

                    // Redirect to the dashboard
                    	if("admin".equals(role))
                    	{
                    	response.sendRedirect("adminDashboard.jsp");
                    	}
                    	else
                    	{
                    		response.sendRedirect("simpleDashboard.jsp");
                    	}
                   
                } else {
                    out.println("<h1>Password does not match</h1>");
                }
            } else {
                out.println("<h1>No record found</h1>");
            }

            st.close();
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }
}
