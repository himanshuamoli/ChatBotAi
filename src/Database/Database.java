package Database;

import java.sql.*;

public class Database {
    Connection con = null;
    Statement statement = null;
    ResultSet rs = null;
    int index;
    
    public Database(){
    	
    	init();
    	
    }

	public void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bot","root","root");
			statement = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in init database: " + e);
		}
		
	}
	
	public int addAnswer(String answer){
		try {
			statement.executeUpdate("Insert into answers(answer) values ('" + answer + "')");
			rs = statement.executeQuery("Select COUNT(*) from answers");
			while(rs.next()){
			 index = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in add answer: " + e);
		}
		return index;
	}
	
	public void updateAnswer(int index, String answer){
		try{
			statement.executeUpdate("Update answers SET answer='" + answer + "' where id=" + index);
		}catch(Exception e){
			System.out.println("Exception in update answer: " + e);
		}
	}
	
	public String getAnswer(int index){
		String answer=null;
		if(index == -1){
			return "Sorry i dont understand";
		}
		try{
			rs = statement.executeQuery("Select answer from answers where id=" + index);
			while(rs.next())
			{
				answer=rs.getString(1);
				
			}
		}catch(Exception e){
			System.out.println("Exception in getAnswer: " + e);
		}
		return answer;	
	}
	
	public void truncateTable(){
		try {
			statement.executeUpdate("Truncate answers");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in truncateTable : " + e);
		}
	}
}
