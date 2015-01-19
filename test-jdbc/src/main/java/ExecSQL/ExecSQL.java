package ExecSQL;

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * Executes all SQL statements in a file. Call this program as <br>
 * java -classpath driverPath:. ExecSQL commandFile
 *
 * @author Cay Horstmann
 * @version 1.30 2004-08-05
 */
class ExecSQL {
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            conn.setAutoCommit(false);
            try {
                Statement stat = conn.createStatement();
                String sql =
                        "CREATE TABLE Publishers (Publisher_Id CHAR(6), Name CHAR(30), URL CHAR(80))"
                + " " +
                              "INSERT INTO Publishers VALUES ('0201', 'Addison-Wesley', 'www.aw-bc.com')" + " " ;//+
                  //              "INSERT INTO Publishers VALUES ('0471', 'John Wiley & Sons', 'www.wiley.com');" + " " +
                  //              "INSERT INTO Publishers VALUES ('0262', 'MIT Press', 'mitpress.mit.edu');" + " " +
                  //              "INSERT INTO Publishers VALUES ('0596', 'O''Reilly', 'www.ora.com');" + " " +
                  //              "INSERT INTO Publishers VALUES ('019', 'Oxford University Press', 'www.oup.co.uk');" + " " +
                  //              "INSERT INTO Publishers VALUES ('013', 'Prentice Hall', 'www.phptr.com');" + " " +
                  //              "INSERT INTO Publishers VALUES ('0679', 'Random House', 'www.randomhouse.com');" + " " +
                  //              "INSERT INTO Publishers VALUES ('07434', 'Simon & Schuster', 'www.simonsays.com');" + " " +
                  //              "SELECT * FROM Publishers;";

                boolean retval = stat.execute(sql);
                ResultSet rs;
                int count;
                do {
                    if (retval == false) {
                        count = stat.getUpdateCount();
                        if (count == -1) {
                            break;
                        } else {
                            System.out.println(count);
                        }
                    } else { // ResultSet
                        rs = stat.getResultSet();
                        System.out.println("rs");
                    }
                    retval = stat.getMoreResults();
                }
                while (true);
            } finally {
                conn.rollback();
                conn.close();
            }
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main2(String args[]) {
        try {
            Scanner in;
            if (args.length == 0) in = new Scanner(System.in);
            else in = new Scanner(new File(args[0]));

            Connection conn = getConnection();
            try {
                Statement stat = conn.createStatement();

                while (true) {
                    if (args.length == 0)
                        System.out.println("Enter command or EXIT to exit:");

                    if (!in.hasNextLine()) return;

                    String line = in.nextLine();
                    if (line.equalsIgnoreCase("EXIT")) return;
                    if (line.trim().endsWith(";")) // remove trailing semicolon
                    {
                        line = line.trim();
                        line = line.substring(0, line.length() - 1);
                    }
                    try {
                        boolean hasResultSet = stat.execute(line);
                        if (hasResultSet) showResultSet(stat);
                    } catch (SQLException ex) {
                        for (Throwable e : ex)
                            e.printStackTrace();
                    }
                }
            } finally {
                conn.close();
            }
        } catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a connection from the properties specified in the file database.properties
     *
     * @return the database connection
     */
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        String path = Thread.currentThread().getContextClassLoader()
                .getResource("database.properties").getPath();
        FileInputStream in = new FileInputStream(path);
        props.load(in);
        in.close();

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) System.setProperty("jdbc.drivers", drivers);

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Prints a result set.
     *
     * @param stat the statement whose result set should be printed
     */
    public static void showResultSet(Statement stat) throws SQLException {
        ResultSet result = stat.getResultSet();
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) System.out.print(", ");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1) System.out.print(", ");
                System.out.print(result.getString(i));
            }
            System.out.println();
        }
        result.close();
    }
}
