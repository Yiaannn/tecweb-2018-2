package mvc;
import java.sql.Date;

public class Note {

	private Integer id;
	private String messageBody;
	private Date creationDate;
	private Date expiryDate;
	private Integer priorityLevel;
	
	public Integer getID() {return this.id;}
	public void setID(Integer id) {this.id=id;}
	
	public String getMessageBody() {return this.messageBody;}
	public void setMessageBody(String messageBody) {this.messageBody=messageBody;}
	
	public Date getCreationDate(){return this.creationDate;}
	public void setCreationDate(Date creationDate) {this.creationDate=creationDate;}
	
	public Date getExpiryDate(){return this.expiryDate;}
	public void setExpiryDate(Date expiryDate) {this.expiryDate=expiryDate;}
	
	public Integer getPriorityLevel() {return this.priorityLevel;}
	public void setPriorityLevel(Integer priorityLevel) {this.priorityLevel=priorityLevel;}
}
