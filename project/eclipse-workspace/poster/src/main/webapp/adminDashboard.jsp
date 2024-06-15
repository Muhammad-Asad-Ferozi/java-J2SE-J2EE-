<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*, java.io.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <%
        HttpSession ses = request.getSession(false);
        if (ses == null || !"admin".equals(ses.getAttribute("role"))) {
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
                                <a href="adminDashboard.jsp" class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium" aria-current="page">Dashboard</a>
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
                <h1 class="text-3xl font-bold tracking-tight text-gray-900">Admin Dashboard</h1>
            </div>
        </header>

        <main>
            <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
                <!-- User Management Section -->
                <h2 class="text-2xl font-bold text-gray-900">User Management</h2>
                <div class="flex flex-col mt-6">
                    <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                        <div class="inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8">
                            <div class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 sm:rounded-lg">
                                <table class="min-w-full divide-y divide-gray-200">
                                    <thead class="bg-gray-50">
                                        <tr>
                                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Role</th>
                                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody class="bg-white divide-y divide-gray-200">
                                        <%
                                            Connection con = null;
                                            Statement st = null;
                                            ResultSet rs = null;
                                            String loggedInEmail = (String) ses.getAttribute("email");

                                            try {
                                                Class.forName("com.mysql.jdbc.Driver");
                                                String url = "jdbc:mysql://127.0.0.1/poster";
                                                con = DriverManager.getConnection(url, "root", "");
                                                st = con.createStatement();
                                                String query = "SELECT id, email, role FROM user";
                                                rs = st.executeQuery(query);

                                                while (rs.next()) {
                                                    int userId = rs.getInt("id");
                                                    String email = rs.getString("email");
                                                    String role = rs.getString("role");
                                        %>
                                        <tr>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><%= email %></td>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><%= role %></td>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                                <%
                                                    if (!email.equals(loggedInEmail)) {
                                                %>
                                                    <a href="DeleteUser?id=<%= userId %>" class="text-red-600 hover:text-red-900">Delete</a>
                                                <%
                                                    } else {
                                                %>
                                                    <span class="text-gray-400 cursor-not-allowed">Cannot delete self</span>
                                                <%
                                                    }
                                                %>
                                            </td>
                                        </tr>
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
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Admin's Posts Management Section -->
                <h2 class="text-2xl font-bold text-gray-900 mt-12">My Posts</h2>
                <div class="flex flex-col mt-6">
                    <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                        <div class="inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8">
                            <div class="overflow-hidden shadow ring-1 ring-black ring-opacity-5 sm:rounded-lg">
                                <table class="min-w-full divide-y divide-gray-200">
                                    <thead class="bg-gray-50">
                                        <tr>
                                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Title</th>
                                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Description</th>
                                            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody class="bg-white divide-y divide-gray-200">
                                        <%
                                            try {
                                                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/poster", "root", "");
                                                st = con.createStatement();
                                                String query = "SELECT id, title, description FROM post WHERE user_id = (SELECT id FROM user WHERE email = '" + loggedInEmail + "')";
                                                rs = st.executeQuery(query);

                                                while (rs.next()) {
                                                    int postId = rs.getInt("id");
                                                    String title = rs.getString("title");
                                                    String description = rs.getString("description");
                                        %>
                                        <tr>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><%= title %></td>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500"><%= description %></td>
                                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                                                <a href="editPost.jsp?id=<%= postId %>" class="text-indigo-600 hover:text-indigo-900">Edit</a>
                                                <a href="DeletePost?id=<%= postId %>" class="text-red-600 hover:text-red-900">Delete</a>
                                            </td>
                                        </tr>
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
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                
           
            </div>
        </main>
    </div>
</body>
</html>
