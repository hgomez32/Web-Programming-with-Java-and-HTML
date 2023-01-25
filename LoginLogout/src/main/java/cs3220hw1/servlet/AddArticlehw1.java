package cs3220hw1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import modelhw1.Articlehw1;




@WebServlet("/AddArticlehw1")
public class AddArticlehw1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddArticlehw1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String arr[] = {"Biz & IT", "Tech", "Science", "Policy","Car","Gaming & Culture" };
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Add Article</title></head><body>");
		out.println("<table border='1' cellpadding='5' cellspacing='2'>");
		out.println("<form action='AddArticlehw1' method='post'>");
		out.println("<tbody><tr><th>Category</th>");
		out.println("<td><select name='category'>");
		for(String val: arr) {
			out.println("<option value='" + val + "'>" + val + "</option>");
		}
		out.println("<select></td></tr>");
		out.println("<tr><th>Title</th>");
		out.println("<td><input size='60' type='text' name='title'></td></tr>");
		out.println("<tr><th>Subtitle</th>");
		out.println("<td><input size='60' type='text' name='subtitle'></td></tr>");
		out.println("<tr><th>Content</th>");
		out.println("<td><textarea = cols='60' rows='5' name='content'></textarea></td></tr>");
		out.println("<tr><td colspan='2'><button>Add</button></td></tr>");
		out.println("</tbody></form></table></body></html>");
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String content = request.getParameter("content");
		LocalDate submitted = LocalDate.now();
		LocalDate published = null;
		Articlehw1 entry = new Articlehw1(title, subtitle,  category, submitted, published,content);
		List<Articlehw1> list_articles = (List<Articlehw1>) getServletContext().getAttribute("list_articles");
		String id = Integer.toString(entry.getId());
		request.getSession().setAttribute("id", id);
		list_articles.add(0,entry);
		response.sendRedirect("DisplayArticlehw1");
		
		
	}

}
