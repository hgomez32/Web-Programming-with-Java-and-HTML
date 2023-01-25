package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.GuestBookEntry;


@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddComment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/AddComment.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		//insert data into database 
		
		 Connection c = null;
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu11";
	            String username = "cs3220stu11";
	            String password = "9VXxx25VH4At";

	            c = DriverManager.getConnection( url, username, password ); 
	            String sql = "insert into guestbook(name, message) values (?, ?)"; 
	            PreparedStatement pstmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            pstmt.setString(1,name);
	            pstmt.setString(2, message);
	            pstmt.executeUpdate();
	            ResultSet rs = pstmt.getGeneratedKeys();
	            rs.next();
	            System.out.println(rs.getInt(1));
	        
	            
	            
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
		
		response.sendRedirect("GuestBook");
	}

}
