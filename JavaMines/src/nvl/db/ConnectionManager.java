package nvl.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionManager {
	
	/**
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		
        try  {
            Properties jdbcProperties = new Properties();
            InputStreamReader fisProperty = new InputStreamReader(ConnectionManager.class.getClassLoader().getResourceAsStream("db.conf"));
            
            jdbcProperties.load(fisProperty);
            String driver = jdbcProperties.getProperty("driver");
            String url = jdbcProperties.getProperty("url");
            String user = jdbcProperties.getProperty("user");
            String password = jdbcProperties.getProperty("password");

            Class.forName(driver);

            connection = DriverManager.getConnection(url, user, password);
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
        
        return connection;
	}
    
	/**
	 * 
	 * @param connection
	 */
    public static void close(Connection connection) {
        if(connection != null)
            try { connection.close(); } catch(SQLException se) {}
    }
}
