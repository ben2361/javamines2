package javamines.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class HighScores {
	
    public HighScores(int timePlayed) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try  {
            Properties jdbcProperties = new Properties();
            FileInputStream fisProperty = new FileInputStream("res/db.conf");
            
            jdbcProperties.load(fisProperty);
            String driver = jdbcProperties.getProperty("driver");
            String url = jdbcProperties.getProperty("url");
            String user = jdbcProperties.getProperty("user");
            String password = jdbcProperties.getProperty("password");

            Class.forName(driver);

            connection = DriverManager.getConnection(url, user, password);
            
            // ask user for a name
            String name = (String) JOptionPane.showInputDialog("Enter your name:");
            java.util.Date u_date = new java.util.Date();
            java.sql.Date date = new java.sql.Date(u_date.getTime());
            
            try {
            	// prepare statement
	            stmt = connection.prepareStatement("insert into highscore (name, score, date_added) VALUES(?,?,?);");
	
	            stmt.setString(1, name);
	            stmt.setInt(2, timePlayed);
	            stmt.setDate(3, date);
	
	            // execute the statement on the server
	            stmt.executeUpdate();
            }
            catch(SQLException se) {
            	throw new SQLException(se);
            }
        }
        catch(ClassNotFoundException ce) {
            throw new SQLException(ce);
        }
        catch (FileNotFoundException fe) {
            throw new SQLException(fe);
        }
        catch(IOException ie) {
            throw new SQLException(ie);
        }
        finally {
            if(connection != null)
                try { connection.close(); } catch(SQLException se) {}
        }
    }
}
