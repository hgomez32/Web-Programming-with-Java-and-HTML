package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.User;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	List<User> user_list = new ArrayList<User>();
    	User user1 = new User("John Doe", "jdoe", "abcd");
    	
    	User user2 = new User("Vivian Pho", "vpho", "ab");
    	
    	user_list.add(user1);
    	user_list.add(user2);
    	getServletContext().setAttribute("user_list", user_list); 	
    	
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Login</title></head></html><body>");
		out.println("<form action='Login' method='post'>");
		String username = (String) request.getSession().getAttribute("username");
		String password = (String) request.getSession().getAttribute("password");
		out.println("Username: <input type='text' name='username'><br>");
		out.println("Password: <input type='password' name='password'><br>");
		out.println("<button>Login</button></form>");
		out.println("</body></html>"); 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		String password = (String) request.getSession().getAttribute("password");
		
		if(username == null || password == null) {
			username = request.getParameter("username");
			password = request.getParameter("password");
		}
		List<User> user_list =  (ArrayList<User>) getServletContext().getAttribute("user_list");	
	
		for(User users : user_list) {
			System.out.println("Username " + username);
			System.out.println("Users " + users.getUsername());
			if(users.getUsername().equalsIgnoreCase(username) && users.getPassword().equalsIgnoreCase(password)){
				System.out.println("True");
				request.getSession().setAttribute("name", users.getName());
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("password", password);
				response.sendRedirect("Members");
				return;
				
			}
			
		}
		response.sendRedirect("Login");
		
	
		
	}

}
