package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.GuestBookEntry;

@WebServlet(urlPatterns="/GuestBook", loadOnStartup=1)
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public GuestBook() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get data from database
		 Connection c = null;
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu11";
	            String username = "cs3220stu11";
	            String password = "9VXxx25VH4At";

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from guestbook" );
	            
	            // create a List<GuestbookEntry>
	            List<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
	            
	            while( rs.next() )
	            {
	            	GuestBookEntry entry = new GuestBookEntry();
	            	entry.setId(rs.getInt("id"));
	            	entry.setName(rs.getString("name"));
	            	entry.setMessage(rs.getString("message"));
	            	entries.add(entry);
	            }
	            request.setAttribute("entries", entries);
	            //Pass the liset of view 
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

		request.getRequestDispatcher("/WEB-INF/GuestBook.jsp").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
