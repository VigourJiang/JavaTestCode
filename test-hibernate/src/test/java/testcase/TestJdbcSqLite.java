package testcase;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.Calendar;

public class TestJdbcSqLite {
    @BeforeClass
    public static void beforeClass() throws ClassNotFoundException {
        // register the driver
        String sDriverName = "org.sqlite.JDBC";
        Class.forName(sDriverName);
    }

    @Test
    public void test() throws Exception {
//        double sum = 0;
//        for (int i = 0; i < 1000; i++）{
            long start = Calendar.getInstance().getTimeInMillis();
            doTestUpdate();
            long end = Calendar.getInstance().getTimeInMillis();
//            sum += (end - start);
//        }
    }

   // @Test
    public void test2() throws Exception {
        long start = Calendar.getInstance().getTimeInMillis();
        doTestQuery();
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Query:" + Long.toString(end - start));
    }

    private void doTestUpdate() throws SQLException {
        // now we set up a set of fairly basic string variables to use in the body of the code proper
        String sTempDb = "F:\\trickyCode\\def.db";
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + sTempDb;

        // which will produce a legitimate Url for SqlLite JDBC :
        // jdbc:sqlite:hello.db
        int iTimeout = 30;
        Connection conn = DriverManager.getConnection(sDbUrl);
        try {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement("insert into JOBS values(?, 'Developer', 4500, 1500)");
            stmt.setQueryTimeout(iTimeout);

            for (int i = 0; i < 1000; i++) {
                stmt.setInt(1, i + 1);
                stmt.executeUpdate();
            }
            conn.commit();
        } finally {
            try {
                conn.close();
            } catch (Exception ignore) {
            }
        }
    }

    private void doTestQuery() throws SQLException {
        // now we set up a set of fairly basic string variables to use in the body of the code proper
        String sTempDb = "F:\\trickyCode\\abc.db";
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + sTempDb;

        // which will produce a legitimate Url for SqlLite JDBC :
        // jdbc:sqlite:hello.db
        int iTimeout = 30;
        Connection conn = DriverManager.getConnection(sDbUrl);
        try {
            Statement stmt = conn.createStatement();
            try {
                stmt.setQueryTimeout(iTimeout);
                ResultSet rs = stmt.executeQuery("select * from JOBS");
                try {
                    while (rs.next()) {
                        String sResult = rs.getString(1);
                        // System.out.println(sResult);
                    }
                } finally {
                    try {
                        rs.close();
                    } catch (Exception ignore) {
                    }
                }
            } finally {
                try {
                    stmt.close();
                } catch (Exception ignore) {
                }
            }
        } finally {
            try {
                conn.close();
            } catch (Exception ignore) {
            }
        }
    }

}