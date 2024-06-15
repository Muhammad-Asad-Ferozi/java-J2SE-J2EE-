<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*, java.io.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Posts</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <%
        HttpSession ses = request.getSession(false);
        if (ses == null || ses.getAttribute("email") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <div class="min-h-full">
      <nav class="bg-gray-800">
            <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
                <div class="flex h-16 items-center justify-between">
                    <div class="flex items-center">
                        <div class="flex-shrink-0">
                            <img class="h-8 w-8" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500" alt="Your Company">
                        </div>
                        <div class="hidden md:block">
                            <div class="ml-10 flex items-baseline space-x-4">
                            	<a href="index.jsp" class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium" aria-current="page">Home</a>
                                <a href="simpleDashboard.jsp" class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium" aria-current="page">Dashboard</a>
                                <a href="showPost.jsp" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Posts</a>
                            </div>
                        </div>
                    </div>
                    <div class="hidden md:block">
                        <a href="Logout" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Logout</a>
                    </div>
                </div>
            </div>
        </nav>
        
        <header class="bg-white shadow">
            <div class="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
                <h1 class="text-3xl font-bold tracking-tight text-gray-900">Posts</h1>
                <a href="createPost.jsp" class="mt-2 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Create New Post</a>
                
                <!-- Search Form -->
                <form action="searchPost.jsp" method="GET" class="mt-2">
                    <div class="flex items-center">
                        <input type="text" name="title" class="px-3 py-2 border rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" placeholder="Search by title">
                        <button type="submit" class="ml-2 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Search</button>
                    </div>
                </form>
            </div>
        </header>
        
        <main>
            <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
                <!-- Fetch and display posts -->
                <%
                    Connection con = null;
                    Statement st = null;
                    ResultSet rs = null;
                    String email = (String) ses.getAttribute("email");
                    String role = (String) ses.getAttribute("role");
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://127.0.0.1/poster";
                        con = DriverManager.getConnection(url, "root", "");
                        st = con.createStatement();
                        String query = "SELECT post.id, post.title, post.description, user.email FROM post INNER JOIN user ON post.user_id = user.id";
                        rs = st.executeQuery(query);
                        
                        while (rs.next()) {
                            int postId = rs.getInt("id");
                            String title = rs.getString("title");
                            String description = rs.getString("description");
                            String authorEmail = rs.getString("email");
                %>
                            <div class="bg-white shadow overflow-hidden sm:rounded-lg my-6">
                                <div class="px-4 py-5 sm:px-6">
                                    <h3 class="text-lg leading-6 font-medium text-gray-900"><%= title %></h3>
                                    <p class="mt-1 max-w-2xl text-sm text-gray-500">by <%= authorEmail %></p>
                                </div>
                                <div class="border-t border-gray-200">
                                    <dl>
                                        <div class="bg-gray-50 px-4 py-5 sm:px-6">
                                            <dt class="text-sm font-medium text-gray-500">Description</dt>
                                            <dd class="mt-1 text-sm text-gray-900"><%= description %></dd>
                                        </div>
                                        <div class="bg-white px-4 py-5 sm:px-6 flex justify-between">
                                            <div></div>
                                            <div class="flex space-x-4">
                                                <% if (email.equals(authorEmail) || "admin".equals(role)) { %>
                                                    <a href="editPost.jsp?id=<%= postId %>" class="text-indigo-600 hover:text-indigo-900">Edit</a>
                                                    <a href="DeletePost?id=<%= postId %>" class="text-red-600 hover:text-red-900">Delete</a>
                                                <% } %>
                                            </div>
                                        </div>
                                    </dl>
                                </div>
                            </div>
                <%
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
                        if (st != null) try { st.close(); } catch (SQLException ignore) {}
                        if (con != null) try { con.close(); } catch (SQLException ignore) {}
                    }
                %>
            </div>
        </main>
    </div>
</body>
</html>
