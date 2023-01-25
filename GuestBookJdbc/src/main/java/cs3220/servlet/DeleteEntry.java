package cs3220.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.GuestBookEntry;

@WebServlet("/DeleteEntry")
public class DeleteEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DeleteEntry() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		GuestBookEntry entry = null ;

		List<GuestBookEntry> entries = (List<GuestBookEntry>) getServletContext().getAttribute("entries");
		for(GuestBookEntry e: entries) 
			if(e.getId() == id) {
				entry = e;
				break;
			}
		
		if(entry != null) entries.remove(entry);
		response.sendRedirect("GuestBook");
	}

  
}
