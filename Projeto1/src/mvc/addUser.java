package mvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addUser")
public class addUser extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		/*
		PrintWriter out= response.getWriter();
		String tmpstring=
				"<html><body>"
				+ "<form method='post'>"
				+"	Login: <input type='text' name='login'><br>"
				+"	Senha: <input type='password' name='pass'><br>"
				+"	<input type='submit' value='Submit'>"
				+"</form>"
				+"<body><html>";
		out.println(tmpstring);
		*/
		
		//System.out.println("Teste: "+login);
		
		System.out.println("Teste, caí no doGet");
		response.sendRedirect("addUser.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//TODO fazer alguma espécie de check se o User já não existe
		
		DAO dao= new DAO();
		
		User user= new User();
		user.setLoginName(request.getParameter("login"));
		user.setDisplayName(request.getParameter("login")); //na criação são o mesmo valor, depois pode ser trocado
		
		String passhash= "porenquantoteste";
		user.setPassHash(passhash);
		
		
		dao.addUser(user);
		
		//PrintWriter out= response.getWriter();
		//out.println("<html><body>");
		//out.println("adicionado"+pessoa.getNome() );
		//out.println("</body></html");
		
		System.out.println("Teste, caí no doPost");
		System.out.println(request.getParameter("login"));
		System.out.println(request.getParameter("pass"));
		
		
		dao.close();
	}
}