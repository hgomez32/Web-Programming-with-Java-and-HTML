package cs3220.controller;

import java.io.IOException;
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

import cs3220.model.House;

@WebServlet("/AddHouse")
public class AddHouse extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddHouse()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        request.getRequestDispatcher( "/WEB-INF/AddHouse.jsp" )
            .forward( request, response );
    }

    @SuppressWarnings("unchecked")
    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	String house = request.getParameter( "house");
        
        Connection c = null;
		try
		{
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu11";
			String username = "cs3220stu11";
			String password = "9VXxx25VH4At";
			c = DriverManager.getConnection(url, username, password);
			String sql = "insert into house (name) values(?)";
			PreparedStatement pstmt = c.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,house);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
		}
		catch(SQLException e ){
			throw new ServletException(e);
		}
		finally 
		{
			try {
				if(c != null)c.close();
			}
			catch(SQLException e) {
				throw new ServletException(e);
			}
		}
		
        
        
        response.sendRedirect( "ListCharacters" );
    }

}
