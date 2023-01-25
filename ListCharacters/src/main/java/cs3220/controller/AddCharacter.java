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

import cs3220.model.Character;
import cs3220.model.House;

@WebServlet("/AddCharacter")
public class AddCharacter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddCharacter()
	{
		super();
	}

	protected void doGet( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException
	{
		Character ch = new Character( request.getParameter( "name" ),
				request.getParameter( "father" ),
				request.getParameter( "mother" ));
		String name = request.getParameter( "house" );
		Connection c = null;
		try
		{
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu11";
			String username = "cs3220stu11";
			String password = "9VXxx25VH4At";
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from house");
			List<House> houses = new ArrayList<House>();
			while(rs.next()) {
				House house = new House(rs.getString("name"));
				houses.add(house);
			}
			House house = houses.get( 0 );
			if( name != null ) for( House h : houses )
				if( h.getName().equals( name ) )
				{
					house = h;
					break;
				}
			Statement stmt2 = c.createStatement();
			ResultSet rs2 = stmt2.executeQuery("select * from characters");
			while(rs2.next()) {
				if(rs2.getString("house").equalsIgnoreCase(name)) {
					house.getCharacters().add(new Character(rs2.getString("name"), rs2.getString("father"), rs2.getString("mother")));
				}
			}
			
			request.setAttribute("houses", houses);
			request.setAttribute( "house", house );
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

		request.getRequestDispatcher( "/WEB-INF/AddCharacter.jsp" )
		.forward( request, response );
	}

	@SuppressWarnings("unchecked")
	protected void doPost( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException
	{
		Character ch = new Character( request.getParameter( "name" ),
				request.getParameter( "father" ),
				request.getParameter( "mother" ));
		String name = request.getParameter( "house" );

		Connection c = null;
		try
		{
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu11";
			String username = "cs3220stu11";
			String password = "9VXxx25VH4At";
			c = DriverManager.getConnection(url, username, password);
			String sql = "insert into characters (name, father, mother, house) values(?, ?, ?, ? )";
			PreparedStatement pstmt = c.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,ch.getName());
			pstmt.setString(2,ch.getFather());
			pstmt.setString(3,ch.getMother());
			pstmt.setString(4, name);
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
	

		response.sendRedirect( "ListCharacters?house=" + name );
	}

}
