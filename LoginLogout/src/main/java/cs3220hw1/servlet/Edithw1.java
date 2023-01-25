package cs3220hw1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelhw1.Articlehw1;

@WebServlet("/Edithw1")
public class Edithw1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Edithw1() {
		super();
	}

	private Articlehw1 getEntry(int id) {
		List<Articlehw1> list_articles = (List<Articlehw1>) getServletContext().getAttribute("list_articles");
		for(Articlehw1 entry: list_articles) 
			if(entry.getId() == id) return entry;
		return null;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Articlehw1 entry = getEntry(Integer.parseInt(id)) ;
		String arr2[] = {"Biz & IT", "Tech", "Science", "Policy","Car","Gaming & Culture" };
		ArrayList<String> arr = new ArrayList<String>();
		for(String val : arr2) {
			arr.add(val);
		}
		arr.remove(entry.getCategory());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Edithw1</title></head><body>");
		out.println("<table border='1' cellpadding='5' cellspacing='2'>");
		out.println("<form action='Edithw1' method='post'><input type='hidden' name='id' value='" + id + "'><tbody><tr><th>Category</th>");
		out.println("<td><select name='category'>");
		out.println("<option value='" + entry.getCategory()+ "'>" + entry.getCategory() + "</option>");
		for(String val: arr) {
			out.println("<option value='" + val + "'>" + val + "</option>");
		} 
		out.println("</select></td></tr>");
		out.println("<tr><th>Title</th>");
		out.println("<td><input size='60' type='text' name='title' value='"+ entry.getTitle()+ "'></td></tr>");
		out.println("<tr><th>Subtitle</th>");
		out.println("<td><input size='60' type='text' name='subtitle' value=\""+ entry.getSubtitle()+ "\"></td></tr>");
		out.println("<tr><th>Content</th>");
		out.println("<td><textarea cols='60' rows='5' name='content'>" + entry.getContent()+ "</textarea></td></tr>");
		out.println("<tr><td colspan='2'><button>Save</button></td></tr>");
		request.getSession().setAttribute("id", id);
		out.println("</tbody></form></table>");
		out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idsession = (String) request.getSession().getAttribute("id");
		request.getSession().setAttribute("id", idsession);
		String id = request.getParameter("id");
		Articlehw1 entry = getEntry(Integer.parseInt(id));
		entry.setTitle(request.getParameter("title"));
		entry.setSubtitle(request.getParameter("subtitle"));
		entry.setCategory(request.getParameter("category"));
		entry.setContent(request.getParameter("content"));
		entry.setSubmitted(LocalDate.now());
		entry.setPublished(null);
		response.sendRedirect("DisplayArticlehw1");
		}
	}


