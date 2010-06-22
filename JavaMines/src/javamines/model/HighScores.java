package javamines.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import nvl.db.ConnectionManager;

public class HighScores {
	
	/**
	 * 
	 * @param timePlayed
	 * @throws SQLException
	 */
    public static void submitNew(int timePlayed) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        // ask user for a name
        String name = (String) JOptionPane.showInputDialog("Enter your name:");
        
        java.util.Date u_date = new java.util.Date();
        java.sql.Date date = new java.sql.Date(u_date.getTime());

        try {
        	connection = ConnectionManager.getConnection();
        	
        	// prepare statement
            stmt = connection.prepareStatement(
            		"insert into highscore (name, score, date_added, game) VALUES(?,?,?);");

            stmt.setString(1, name);
            stmt.setInt(2, timePlayed);
            stmt.setDate(3, date);
            stmt.setString(4, "javamines");

            // execute the statement on the server
            stmt.executeUpdate();
        }
        catch(SQLException se) {
        	throw new SQLException(se);
        }
        finally {
        	ConnectionManager.close(connection);
        }
    }
    
    
    public static void showAll() throws SQLException {
    	Connection connection = null;
    	Statement stmt = null;
    	ResultSet result = null;
    	
        try {
        	connection = ConnectionManager.getConnection();
        	
        	// prepare statement
            stmt = connection.createStatement();
            
            // execute the statement on the server
            result = stmt.executeQuery(
            		"select name, score, date_added from highscore " +
            		"where game='javamines' order by score ASC limit 10;");
            
            String msg = new String("Highscores:\n\n");
            int i = 1;
            
            while(result.next()) {
            	msg += i + ": " + result.getString("name") + " - " + result.getInt("score") + "s\n";
        		i++;
            }
            
            if(i == 1)
            	msg = "No highscores yet.";
            
            JOptionPane.showMessageDialog(null, msg);
        }
        catch(SQLException se) {
        	throw new SQLException(se);
        }
        finally {
        	ConnectionManager.close(connection);
        }
    }
}
