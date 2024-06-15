<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*, java.io.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
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
                <h1 class="text-3xl font-bold tracking-tight text-gray-900">Search Results</h1>
            </div>
        </header>

        <main>
            <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
                <!-- Fetch and display search results -->
                <%
                    Connection con = null;
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    String email = (String) ses.getAttribute("email");
                    String role = (String) ses.getAttribute("role");
                    String title = request.getParameter("title");

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://127.0.0.1/poster";
                        con = DriverManager.getConnection(url, "root", "");
                        String query = "SELECT post.id, post.title, post.description, user.email FROM post INNER JOIN user ON post.user_id = user.id WHERE post.title LIKE ?";
                        ps = con.prepareStatement(query);
                        ps.setString(1, "%" + title + "%");
                        rs = ps.executeQuery();

                        while (rs.next()) {
                            int postId = rs.getInt("id");
                            String postTitle = rs.getString("title");
                            String description = rs.getString("description");
                            String authorEmail = rs.getString("email");
                %>
                            <div class="bg-white shadow overflow-hidden sm:rounded-lg my-6">
                                <div class="px-4 py-5 sm:px-6">
                                    <h3 class="text-lg leading-6 font-medium text-gray-900"><%= postTitle %></h3>
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
                        if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
                        if (con != null) try { con.close(); } catch (SQLException ignore) {}
                    }
                %>
            </div>
        </main>
    </div>
</body>
</html>
