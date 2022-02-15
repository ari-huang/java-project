import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactPersonQueries {

		private static final String URL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";
		private static final String USERNAME = "java";
		private static final String PASSWORD = "java";
		private Connection connection;
		private PreparedStatement selectAllPeople;
		private PreparedStatement insertNewPerson;
		private PreparedStatement deletePerson;
		private PreparedStatement updatePerson;
		private PreparedStatement queryPerson;
	public ContactPersonQueries() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			selectAllPeople = connection.prepareStatement("SELECT * FROM PEOPLE");
			insertNewPerson = connection.prepareStatement("INSERT INTO PEOPLE" + "(MemberID, name, type, phone)"+"VALUES(?, ?, ?, ?)");
			updatePerson = connection.prepareStatement("UPDATE PEOPLE SET  name=?, type=?, phone=? where NAME= ?");
			deletePerson = connection.prepareStatement("DELETE FROM PEOPLE WHERE NAME=?");
			queryPerson = connection.prepareStatement("SELECT * FROM PEOPLE WHERE NAME=? OR PHONE = ?");
		
		}catch(SQLException sqlException) {
			System.exit(1);
		}
		
	
	}
	
	public List<ContactPerson> getAllPeople(){
		List<ContactPerson> results = null;
		ResultSet resultSet = null;
		try {
			resultSet = selectAllPeople.executeQuery();
			results = new ArrayList<ContactPerson>();
			while(resultSet.next()) {
				results.add(new ContactPerson(resultSet.getInt("MemberID"),resultSet.getString("name"),resultSet.getString("type"),resultSet.getString("phone")));
				
			}

		
		
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				resultSet.close();
			}catch(SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			}
		}
		return results;
	}
	public void DeletePerson(String name) throws SQLException{
		deletePerson.setString(1, name);
		
		deletePerson.execute();
		
	}
	public void InsertNewPerson(String MemberID,String name, String type, String phone) throws SQLException{
		insertNewPerson.setInt(1,Integer.parseInt(MemberID));
		insertNewPerson.setString(2,name);
		insertNewPerson.setString(3,type);
		insertNewPerson.setString(4,phone);
		insertNewPerson.execute();
		
	}
	public void UpdatePerson(String name1, String type, String phone,String name ) throws SQLException{
		updatePerson.setString(1, name1);
		updatePerson.setString(2, type);
		updatePerson.setString(3, phone);
		updatePerson.setString(4,name);
		updatePerson.executeUpdate();
	}
	public List<ContactPerson> QueryName(String name)  {
		List<ContactPerson> answer = null;
		ResultSet rs = null;
		try {
		queryPerson.setString(1,name);
		queryPerson.setString(2,name);
		rs = queryPerson.executeQuery();
		answer = new ArrayList<ContactPerson>();
		while(rs.next()) {
			ContactPerson tem = new ContactPerson();
			tem.setMemberID(rs.getInt("MemberID"));
			tem.setName(rs.getString("name"));
			tem.setType(rs.getString("type"));
			tem.setPhoneNumber(rs.getString("phone"));
			answer.add(tem);
		}
		}catch(SQLException sqlException)
		 {
	         sqlException.printStackTrace();
	      } 
	      finally
	      {
	         try 
	         {
	            rs.close();
	         }
	         catch (SQLException sqlException)
	         {
	            sqlException.printStackTrace();         
	            close();
	         }
	      } 
		return answer;
	}
	public void close() {
		try {
			connection.close();
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
