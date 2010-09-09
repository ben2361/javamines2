package nvl.highscores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javamines.model.Difficulty;

import javax.swing.JOptionPane;

import nvl.db.ConnectionManager;

/**
 * reusable HighScores class 
 * uses a database to store/retreive game highscores
 * 
 * @author Nik Van Looy
 */
public class HighScores {
	
	/**
	 * 
	 * @param score
	 * @param game
	 * @param diff
	 * @throws SQLException
	 */
    public static void submitNew(int score, String game, Difficulty diff) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        // ask user for a name
        String name = (String) JOptionPane.showInputDialog("Enter your name:");
        
        java.util.Date u_date = new java.util.Date();
        java.sql.Date date = new java.sql.Date(u_date.getTime());

        try {
        	connection = ConnectionManager.getInstance().getConnection();
        	
        	// prepare statement
            stmt = connection.prepareStatement(
            		"insert into highscore (name, score, date_added, game, difficulty) " +
            		"VALUES(?,?,?,?,?);");

            stmt.setString(1, name);
            stmt.setInt(2, score);
            stmt.setDate(3, date);
            stmt.setString(4, game);
            stmt.setString(5, diff.toString());

            // execute the statement on the server
            stmt.executeUpdate();
        }
        catch(SQLException se) {
        	throw new SQLException(se);
        }
        finally {
        	ConnectionManager.getInstance().close(connection);
        }
    }
    
    /**
     * 
     * @param game
     * @param diff
     * @param descending
     * @throws SQLException
     */
    public static void showAll(String game, Difficulty diff, boolean descending) throws SQLException {
    	String orderDir = descending ? "DESC" : "ASC";
    	
    	Connection connection = null;
    	Statement stmt = null;
    	ResultSet result = null;
    	
        try {
        	connection = ConnectionManager.getInstance().getConnection();
        	
        	// prepare statement
            stmt = connection.createStatement();
            
            // execute the statement on the server
            result = stmt.executeQuery(
            		"select name, score, date_added from highscore where game='" + game.toLowerCase() + "' " +
            		"and difficulty='" + diff.toString() + "' order by score " + orderDir + " limit 10;");
            
            String msg = new String("Highscores - " + diff.toString().toUpperCase() + ":\n\n");
            int i = 1;
            
            while(result.next()) {
            	msg += i + ": " + result.getString("name") + " - " + result.getInt("score") + "s\n";
        		i++;
            }
            
            if(i == 1)
            	msg += "No highscores yet, be the first!\n\n";
            else
                msg += "\n";
            
            JOptionPane.showMessageDialog(null, msg, game + " - Highscores", JOptionPane.PLAIN_MESSAGE);
        }
        catch(SQLException se) {
        	throw new SQLException(se);
        }
        finally {
        	ConnectionManager.getInstance().close(connection);
        }
    }
}
