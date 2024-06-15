

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		response.setContentType("text/html");

		// get PrintWriter object
		PrintWriter out = response.getWriter();

		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String confirm_password=request.getParameter("confirm_password");
		if(!password.equals(confirm_password))
		{
			out.println("<h1>Password Doesnot match</h1>"); 
			out.println("</body></html>");
			
			return;
		}

		out.println("<html>");
		out.println("<head><title>Response</title></head>");
		out.println("<body bgcolor=\"#ffffff\">");


		try{

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://127.0.0.1/poster";

		Connection con=DriverManager.getConnection(url, "root", "");

		Statement st=con.createStatement();

		String check = "SELECT * from user where email = '"+email+"'"; 
		ResultSet crs = st.executeQuery(check);
		
		if(crs.next())
		{
			out.println("Cannot inserst record Email alreday registered");
			
		}
		else
		{
		 String query = "INSERT INTO user (email,password)VALUES('"+ email + "','" + password+ "') ";

		 System.out.println(query);

		  int rs = st.executeUpdate( query );

		 if(rs==1){	
			 out.println("<h1>Insertion successful</h1>"); 	
			 HttpSession session = request.getSession(true);
             session.setAttribute("email", email);
             session.setAttribute("role", "user");
             response.sendRedirect("simpleDashboard.jsp");
		 }
		else{	out.println("<h1>Record could not be inserted.</h1>"); 		}


		       st.close();
		       con.close();
		}
		out.println("</body></html>");

		}catch(Exception e){

		  out.println(e);
		}

	   
	}

}



