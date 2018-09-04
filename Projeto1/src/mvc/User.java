package mvc;
import java.sql.Date;

public class User {
	
	private Integer id;
	private String login_name;
	private String display_name;
	private String pass_hash;
	
	public Integer getID() {return this.id;}
	public void setID(Integer id) {this.id=id;}
	
	public String getLoginName() {return this.login_name;}
	public void setLoginName(String login_name) {this.login_name=login_name;}
	
	public String getDisplayName() {return this.display_name;}
	public void setDisplayName(String display_name) {this.display_name=display_name;}
	
	public String getPassHash() {return this.pass_hash;}
	public void setPassHash(String pass_hash) {this.pass_hash=pass_hash;}
	
}
