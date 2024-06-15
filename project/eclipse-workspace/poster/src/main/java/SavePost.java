

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class SavePost
 */
public class SavePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SavePost() {
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession(false);
        
        if(ses != null && ses.getAttribute("email") != null)

        try {
        	String email =(String) ses.getAttribute("email");
            out.println(email);
            
            Class.forName("com.mysql.jdbc.Driver"); // Updated driver class name

            String url = "jdbc:mysql://127.0.0.1/poster";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();

            String query = "SELECT * FROM user WHERE email='" + email + "'";
            ResultSet rs = st.executeQuery(query);

            if(rs.next()) {
                int id = rs.getInt("id");
                String title = request.getParameter("title");
                String desc = request.getParameter("description");
                String ins = "INSERT INTO `post`(`title`, `description`, `user_id`) VALUES ('"+title+"','"+desc+"','"+id+"');";
                int re1= st.executeUpdate(ins);
                if(re1 == 1)
                {
                	response.sendRedirect("showPost.jsp");
                }
                else
                {
                	out.println("Post not inserted");
                }
        	}

            st.close();
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
        else
        {
        	response.sendRedirect("login.html");
        }
	}

}
