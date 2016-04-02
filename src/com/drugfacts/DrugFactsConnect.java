
package com.drugfacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpoose of this class is to connect to the HSQL database database using JDBC
 * @author Jude KIkuyu
 */
public class DrugFactsConnect {
    /* the default framework is embedded*/
    private String framework = "embedded";
    private String driver = "org.hsqldb.jdbc.JDBCDriver";
    private String protocol = "jdbc:hsqldb:";
    private Connection con;
    /*
     * @param args - Optional argument specifying which framework or JDBC driver
     *        to use to connect to Derby. Default is the embedded framework,
     *        see the <code>main()</code> method for details.
     * @see #main(String[])
     */
    public DrugFactsConnect(){
        try {
            try {
                Class.forName(driver).newInstance();
                System.out.println("Loaded the appropriate driver");
            } catch (ClassNotFoundException cnfe) {
                System.err.println("\nUnable to load the JDBC driver " + driver);
                System.err.println("Please check your CLASSPATH.");
                cnfe.printStackTrace(System.err);
            } catch (InstantiationException ie) {
                System.err.println("\nUnable to instantiate the JDBC driver " + driver);
                ie.printStackTrace(System.err);
            } catch (IllegalAccessException iae) {
                System.err.println("\nNot allowed to access the JDBC driver " + driver);
                iae.printStackTrace(System.err);
            }
            con = DriverManager.getConnection("jdbc:hsqldb:drugeffects", "SA", "");
        } catch (SQLException ex) {
            Logger.getLogger(DrugFactsConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean drugFactsDown(){
        boolean isDown = true;

        if (framework.equals("embedded")){
            try{
                DriverManager.getConnection("jdbc:hsqldb:;shutdown=true");

                // To shut down a specific database only, but keep the
                // engine running (for example for connecting to other
                // databases), specify a database in the connection URL:
                //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
            }
            catch (SQLException se){
                if (( (se.getErrorCode() == 50000)
                        && ("XJ015".equals(se.getSQLState()) ))) {
                    // we got the expected exception
                    System.out.println("HSQLDB shut down normally");
                    // Note that for single database shutdown, the expected
                    // SQL state is "08006", and the error code is 45000.
                }
                else {
                    // if the error code or SQLState is different, we have
                    // an unexpected exception (shutdown failed)
                    System.err.println("HSQLDB did not shut down normally");
                    Logger.getLogger(DrugFactsConnect.class.getName())
                            .log(Level.SEVERE, null, se);
                }
            }
         }
        else{
            try {
                Statement st = con.createStatement();
                // db writes out to files and performs clean shuts down
                // otherwise there will be an unclean shutdown
                // when program ends
                st.execute("SHUTDOWN");
                con.close(); // if there are no other open connection
                isDown = true;
              } catch (SQLException ex) {
                Logger.getLogger(DrugFactsConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
       }

        return isDown;
    }
    public synchronized void query(String expression){


        try {
            Statement st = null;
            ResultSet rs = null;
            st = con.createStatement(); // statement objects can be reused with
            // repeated calls to execute but we
            // choose to make a new one each time
            rs = st.executeQuery(expression); // run the query
            // do something with the result set.
            dump(rs);
            st.close(); // NOTE!! if you close a statement the associated ResultSet is
            // closed too
            // so you should copy the contents to some other object.
            // the result set is invalidated also  if you recycle an Statement
            // and try to execute some other query before the result set has been
            // completely examined.
        } catch (SQLException ex) {
            Logger.getLogger(DrugFactsConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//use for SQL commands CREATE, DROP, INSERT and UPDATE
    public synchronized void update(String expression){


        try {
            Statement st = null;
            st = con.createStatement(); // statements
            int i = st.executeUpdate(expression); // run the query
            if (i == -1) {
                System.out.println("db error : " + expression);
            }
            st.close();
        } // void update()
        catch (SQLException ex) {
            Logger.getLogger(DrugFactsConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    // void update()

    public static void dump(ResultSet rs) {
        try {
            // the order of the rows in a cursor
            // are implementation dependent unless you use the SQL ORDER statement
            ResultSetMetaData meta = rs.getMetaData();
            int colmax = meta.getColumnCount();
            int i;
            Object o = null;
            // the result set is a cursor into the data.  You can only
            // point to one row at a time
            // assume we are pointing to BEFORE the first row
            // rs.next() points to next row and returns true
            // or false if there is no next row, which breaks the loop
            for (; rs.next();) {
                for (i = 0; i < colmax; ++i) {
                    o = rs.getObject(i + 1); // Is SQL the first column is indexed
                    // with 1 not 0
                    System.out.print(o.toString() + " ");
                }
                System.out.println(" ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrugFactsConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                      

}    // class DrugfactsConnect





