package cs3220hw1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelhw1.Articlehw1;

/**
 * Servlet implementation class DisplayArticle
 */
@WebServlet("/DisplayArticlehw1")
public class DisplayArticlehw1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DisplayArticlehw1() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Articlehw1> list_articles = (List<Articlehw1>) getServletContext().getAttribute("list_articles");
    	int entry ;
    	if (request.getParameter("id") != null){
    		entry = Integer.parseInt(request.getParameter("id"));
    	}
    	else {
    		entry = Integer.parseInt((String) request.getSession().getAttribute("id"));
    	}
    
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html> <head> <title>Display Article</title> </head>");
		out.println("<a href='ListArticleshw1 '>Back to Articles</a><body>");
		for(Articlehw1 articles : list_articles) {
			if( articles.getId() == entry) {
				out.println("<h3>" + articles.getTitle() + "</h3><br>");
				out.println("<em>" + articles.getSubtitle() + "</em><br>");
				out.println("<br>" + articles.getContent());
			}
		}
		out.println("</body></html>");
	
	
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
