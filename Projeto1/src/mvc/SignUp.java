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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String message = "";
		response.sendRedirect(  "SignUp.jsp?message=" + URLEncoder.encode(message, "UTF-8")  );
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		//TODO fazer alguma espécie de check se o User já não existe
		
		DAO dao= new DAO();
		
		User user= new User();
		user.setLoginName(request.getParameter("login"));
		user.setDisplayName(request.getParameter("login")); //na criação são o mesmo valor, depois pode ser trocado
		
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(  request.getParameter("pass").getBytes(StandardCharsets.UTF_8)  );
			String passhash = bytesToHex(hash);
			
			System.out.println("Para garantir, printar o hash:");
			System.out.println(passhash);
			user.setPassHash(passhash);
			
			
			boolean isAvailable= dao.checkIfLoginIsAvailable(user);
			
			if (isAvailable){
				dao.addUser(user);
				System.out.println("Login não existe, addUser continua");
				
				//Escrever o cookie
				LoginSession ls= new LoginSession(dao, request, response);
				ls.signIn(user);
				
				//Redirecionar de volta à home
				response.sendRedirect("/Projeto1/");
				
			}else{
				String message = "O Nome de usuário desejado já foi usado";
				response.sendRedirect(  "SignUp.jsp?message=" + URLEncoder.encode(message, "UTF-8")  );
			}
		}catch (Exception e){
			System.out.println("Something went really wrong while hashing");
			e.printStackTrace();
		}
		

		dao.close();
	}
	
	private static String bytesToHex(byte[] bytes) { //adaptado de https://stackoverflow.com/a/9855338
		final char[] hexArray = "0123456789ABCDEF".toCharArray();
		
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}