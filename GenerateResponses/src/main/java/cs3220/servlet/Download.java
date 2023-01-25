package cs3220.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Download() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file = new File("/Users/Hugogomez_1/Downloads/cs3320.jpg");
		FileInputStream in = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();
		
		response.setContentType("images/jpeg");
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Content-Disposition", "inline; filename=cs3320.jpg");
		byte[] buffer = new byte[2048];
		int bytesRead;
		while((bytesRead = in.read(buffer)) > 0)
			out.write(buffer, 0 , bytesRead);
		in.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
