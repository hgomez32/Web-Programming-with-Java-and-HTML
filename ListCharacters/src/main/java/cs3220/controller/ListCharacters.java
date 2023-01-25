package cs3220.controller;

import java.io.IOException;
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

import cs3220.model.House;
import cs3220.model.Character;

@WebServlet(urlPatterns = "/ListCharacters", loadOnStartup = 1)
public class ListCharacters extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ListCharacters()
    {
        super();
    }


    @SuppressWarnings("unchecked")
    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {   
    	
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
			request.setAttribute("houses", houses);
			 House house = houses.get( 0 );
		        String name = request.getParameter( "house" );
		        if( name != null ) for( House h : houses )
		            if( h.getName().equals( name ) )
		            {
		                house = h;
		                break;
		            }
		       
		      
		        
		        Statement stmt2 = c.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from characters");
				while(rs2.next()) {
					if(rs2.getString("house")!= null) {
						
					
					if(rs2.getString("house").equalsIgnoreCase(house.getName())) {
						Character ch = new Character(rs2.getString("name"),
								rs2.getString("father"), rs2.getString("mother"));
						house.getCharacters().add(ch);
						
					}
					}
				}
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
		

        request.getRequestDispatcher( "/WEB-INF/ListCharacters.jsp" )
            .forward( request, response );
    }
}
