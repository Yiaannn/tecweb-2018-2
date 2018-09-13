package mvc;

import java.util.StringTokenizer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginSession {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private User user= null;
	private Cookie auth= null;
	private DAO dao;
	
	private final String COOKIENAME= "AAAAA";
	
	private boolean isValid= false;
	
	public LoginSession(DAO dao, HttpServletRequest request, HttpServletResponse response){
		this.dao= dao;
		this.response= response;
		this.request= request;
		
		//procurar e carregar um cookie
		Cookie c[]=request.getCookies();
		
		if (c != null){
			for(int i=0;i<c.length;i++){
				
				if( c[i].getName().equals(COOKIENAME) ){
					auth= c[i];
					break;
				}
			}
		}
		
		//se encontrar, conferir que de fato o cookie é válido
		if(auth!=null){
			System.out.println( "Encontrei o meu cookie de validação" );
			
			String[] tokens= auth.getValue().split("-");
			String login= tokens[0];
			String hash= tokens[1];
			
			System.out.println();
			System.out.println("O split do meu cookie resultou em:");
			System.out.println(login);
			System.out.println(hash);
			System.out.println();
				
			user = new User();
			user.setLoginName(login);
			user.setPassHash(hash);
			isValid= this.dao.validateUser(user);
			
			if(!isValid){
				user=null;
				auth=null;
			}
			return;
		}
		System.out.println( "Não Encontrei o meu cookie de validação" );
	}
	
	public boolean isValid(){

		return isValid;
	}
	
	public User getUser(){
		return user;
	}
	
	public boolean signIn(User user){
		//retornar true se login bem sucedido, false senão
		
		//chamar o DAO e confirmar o login
		isValid= dao.validateUser(user);
		if(isValid){
			//escrever um cookie
			auth = new Cookie(COOKIENAME, user.getLoginName()+"-"+user.getPassHash()); //TODO assumir que login está sanitizado?
			auth.setMaxAge(-1); //Se desfazer do cookie após a sessão
			
			response.addCookie(auth);
			System.out.println("Escrevi um cookie válido para o usuário: "+user.getLoginName());
		}
		
		System.out.println("Não escrevi um cookie válido para o usuário: "+user.getLoginName());
		System.out.println("Senha foi: "+user.getPassHash());
		
		this.user= user;
		return isValid;
	}
	
	public void signOut(){
		//deletar o cookie
		user=null;
		auth.setMaxAge(0); //Se desfazer do cookie agora
		response.addCookie(auth);
		auth=null;
	}
}