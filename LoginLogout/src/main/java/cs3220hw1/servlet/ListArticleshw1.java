package cs3220hw1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelhw1.Articlehw1;




@WebServlet(urlPatterns="/ListArticleshw1", loadOnStartup=1)
public class ListArticleshw1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListArticleshw1() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException{
    	super.init(config);
    	Articlehw1 article1 = new Articlehw1();
    	article1.setTitle("Japan declares war on floppy disks for government use.");
    	article1.setSubtitle("In Japan, 1,900 government procedures still require submission on floppy disk.");
    	article1.setCategory("Biz & IT");
    	article1.setSubmitted(LocalDate.of(2022, 8, 31));
    	article1.setPublished(null);
    	article1.setContent("Japan's newly appointed Minister of Digital Affairs, Taro Kono, has declared war on the floppy"
    			+ " disk and other forms of obsolete media, which the government still requires as a submission medium for around "
    			+ "1,900 types of business applications "
    			+ "and other forms. The goal is to modernize the procedures by moving the information submission process online.");
    	
    	Articlehw1 article2 = new Articlehw1();
    	article2.setTitle("Android 13 review: Plans for the future, but not much to offer today.");
    	article2.setSubtitle("Android 13 is a very small update, but it's also the second one this year.");
    	article2.setCategory("Tech");
    	article2.setSubmitted(LocalDate.of(2022, 8, 30));
    	article2.setPublished(LocalDate.of(2022, 8, 31));
    	article2.setContent("The Android update treadmill continues with the release of Android 13. It's one of the smallest "
    			+ "Android releases in recent memory, with barely any user-facing features to point to. Keep in mind, though, that "
    			+ "this update follows the monster Android 12 release from last year. This is also the second Android OS release "
    			+ "this year, the previous one being the tablet-focused Android 12L update that was rushed out the door in March.");
    	List<Articlehw1> list_articles = new ArrayList<Articlehw1>();
    	list_articles.add(article1);
    	list_articles.add(article2);
    	getServletContext().setAttribute("list_articles", list_articles);
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Articlehw1> list_articles = (List<Articlehw1>) getServletContext().getAttribute("list_articles");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html> <head> <title>List Articles</title> </head>");
		out.println("<a href=\"AddArticlehw1\">New Article</a>");
		out.println("<body> <table border=\"1\" padding=\"1\">");
		out.println("<thead> "
				+ "<tr>"
					+ "<th><center>Article</center></th> "
					+ "<th><center>Category</center></th>"
					+ "<th><center>Submitted</center></th>"
					+ "<th><center>Published</center></th>"
					+ "<th><center>Operations</center></th>"
				+ "</tr></thead>"
				+ "<tbody>");
		for(int i =0; i < list_articles.size(); i++) {
			out.println("<tr><td><a href='DisplayArticlehw1?id=" + list_articles.get(i).getId() + "'>" + list_articles.get(i).getTitle()  + "</a><br>");
			out.println(list_articles.get(i).getSubtitle() + "</td> <br>");
			out.println("<td>" + list_articles.get(i).getCategory() + "</td>");
			out.println("<td>" + list_articles.get(i).getSubmitted() + "</td> ");
			if (list_articles.get(i).getPublished() == null){
				out.println("<td><a href='Publishhw1?id=" + list_articles.get(i).getId() + "'>Publish</a></td>");
			}
			else
				out.println("<td>" + list_articles.get(i).getPublished()+ "</td>");
			out.println("<td><a href='Edithw1?id=" + list_articles.get(i).getId() + "'> Edit</td></tr>");
		}
			
		out.println("</tbody></table></body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
