package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Notes")
public class Notes extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//fazer um *if já estiver logado*
		DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		if (ls.isValid()){
			String message = ls.getUser().getLoginName();
			message="Logado como "+message;
			
			List<Note> notes= dao.getActiveNoteList( ls.getUser() );
			
			request.setAttribute("notes", notes);
			request.setAttribute("message", message);
			request.getRequestDispatcher("ListActive.jsp").forward(request, response);
		}else{
			response.sendRedirect("HomeUnlogged.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String methodcheck= request.getParameter("_method");
		DAO dao= new DAO();
		LoginSession ls= new LoginSession(dao, request, response);
		
		if(methodcheck.equals("DELETE")){
		
			String target= request.getParameter("target");
			
			dao.disableNote(Integer.parseInt(target));
			
			//deploy
			String message = ls.getUser().getLoginName();
			message="Logado como "+message;
			List<Note> notes= dao.getActiveNoteList( ls.getUser() );
			request.setAttribute("notes", notes);
			request.setAttribute("message", message);
			request.getRequestDispatcher("ListActive.jsp").forward(request, response);
		}else if( methodcheck.equals("POST") ){
			
			String messageBody= request.getParameter( "messageBody" );
			int priorityLevel= Integer.parseInt( request.getParameter("priority") );
			java.sql.Date creationDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			
			java.sql.Date expiryDate= null;
			try{
				String tmp= request.getParameter("expiryDate");
				expiryDate= new java.sql.Date(   (new SimpleDateFormat("yyyy-MM-dd").parse(tmp)).getTime()   );
			}catch(Exception e){
				System.out.println("expiry date inválida, tratando");
			}
			
			Note note= new Note();
			note.setMessageBody(messageBody);
			note.setPriorityLevel(priorityLevel);
			note.setCreationDate(creationDate);
			note.setExpiryDate( expiryDate );
			
			dao.addNote(note, ls.getUser());
			
			//deploy
			String message = ls.getUser().getLoginName();
			message="Logado como "+message;
			List<Note> notes= dao.getActiveNoteList( ls.getUser() );
			request.setAttribute("notes", notes);
			request.setAttribute("message", message);
			request.getRequestDispatcher("ListActive.jsp").forward(request, response);
		}
	}
}