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


@WebServlet("/Publishhw1")
public class Publishhw1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Publishhw1() {
        super();
    }


	private Articlehw1 getEntry(int id) {
		List<Articlehw1> list_articles = (List<Articlehw1>) getServletContext().getAttribute("list_articles");
		for(Articlehw1 entry: list_articles) 
			if(entry.getId() == id) return entry;
		return null;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Articlehw1 entry = getEntry( Integer.parseInt(request.getParameter("id")));
		entry.setPublished(LocalDate.now());
		int index = 0;
		List<Articlehw1> list_articles = (List<Articlehw1>) getServletContext().getAttribute("list_articles");
		for(Articlehw1 e: list_articles) {
			if(e.getId() == entry.getId()) {
				entry.setPublished(LocalDate.now());
				break;
			}
			index++;
		}
		
		list_articles.get(index).setPublished(LocalDate.now());
		response.sendRedirect("ListArticleshw1");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
