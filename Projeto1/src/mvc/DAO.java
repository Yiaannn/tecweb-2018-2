package mvc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	private Connection connection = null;
	
	public DAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection= DriverManager.getConnection("jdbc:mysql://localhost/tecwebprojeto1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "12063cb5d72571662926aa355c97abc8a28c87f3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Note> getActiveNoteList(User user){
		List<Note> notes= new ArrayList<Note>();
		
		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Note WHERE id_owner=?");
			stmt.setString(1, user.getID() );
			rs = stmt.executeQuery();
			boolean is_active;
			while( rs.next()){
				is_active= rs.getBoolean("is_active");
				if(is_active){
					Note note= new Note();
					note.setMessageBody(rs.getInt("id"));
					note.setCrationDate(rs.getString("nome"));
					note.setExpiryDate.setNascimento(rs.getDate("nascimento"));
					note.setPriorityLevel(rs.getDouble("altura"));
					notes.add(note);
				}
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notes;
	}
	
	public List<Note> getArchivedNoteList(User user){
		List<Note> notes= new ArrayList<Note>();
		
		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Note WHERE id_owner=?");
			stmt.setString(1, user.getID() );
			rs = stmt.executeQuery();
			boolean is_active;
			while( rs.next()){
				is_active= rs.getBoolean("is_active");
				if(!is_active){
					Note note= new Note();
					note.setMessageBody(rs.getInt("id"));
					note.setCrationDate(rs.getString("nome"));
					note.setExpiryDate.setNascimento(rs.getDate("nascimento"));
					note.setPriorityLevel(rs.getDouble("altura"));
					notes.add(note);
				}
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notes;
	}
	
	public void addUser(User user){
		String sql= "INSERT INTO User "+"(login_name, display_name, pass_hash) values(?, ?, ?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getLoginName() );
			stmt.setDate(2, user.getDisplayName() );
			stmt.setDouble(3, user.getPassHash() );
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addNote(Note note, User user){
		if(note.getExpiryDate()==null) {
			String sql = "INSERT INTO Note "+"(message_body, creation_date, priority_level, id_owner) values(?, ?, ?, ?)";
			
			PreparedStatement stmt;
			try {
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, note.getMessageBody() );
				stmt.setDate(2, note.getCreationDate() );
				stmt.setInt(3, note.getPriorityLevel() );
				stmt.setInt(4, user.getIdOwner() );
				
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else { 
			String sql = "INSERT INTO Note "+"(message_body, creation_date,expiry_date, priority_level, id_owner) values(?, ?, ?, ?, ?)";
			
			PreparedStatement stmt;
			try {
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, note.getMessageBody() );
				stmt.setDate(2, note.getCreationDate() );
				stmt.setDate(3, note.getExpiryDate() );
				stmt.setInt(4, note.getPriorityLevel() );
				stmt.setInt(5, user.getIdOwner() );
				
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void update(User user){
		//Deliberadamente não queremos que o login_name seja alterável, apenas o display_name e a senha
		String sql= "UPDATE User SET "+"display_name=?, pass_hash=? WHERE id=?";
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, user.getDisplayName() );
			stmt.setDouble(2, user.getPassHash() );
			stmt.setInt(3, user.getID );
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	public void remove(Integer id){
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM Pessoas WHERE id=?");
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}