//
//  LInMemDb
//

//	package net.xt57;

import java.io.*;
import java.net.*;

//    import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public
class
LInMemDb
{
      boolean           bNormalStatus     = false;


 	LLog              log               = null;

	lDate             wd40              = null;

	LInMemDb()   {                     //    constructor

  	                  wd40        = new lDate();               
	}

	LInMemDb( LLog logIn ) {

                        this();
		            log         = logIn;		
	}



/*
      public
      String
      getFromKey( String sKeyIn )       {

            bNormalStatus = false;

            sockIn = sockInput;
 
            DatagramPacket    packetIn = new DatagramPacket();

            int   iRecvLength = packetIn.getLength();

            byte[iRecvLength] baIn = new byte{ }

            socketIn.receive( baIn, iRecvLength)
 
 

            ////  more needed here


            if ( Packet.isNormal() )   {
                 bNormalStatus = true;
            }

            return bNormalStatus;
      }
*/

	public
	void
	StatusReport()    {

            System.out.println("LInMemDb : status report");
      }


	public static void
	main(String args[]) throws Exception, ClassNotFoundException      {

            System.out.println("" );

		LInMemDb	testObj		= new LInMemDb();

            testObj.StatusReport();

            System.out.println("" );

            System.out.println("LInMemDb : testing begins here" );

            testObj.StatusReport();


            // load the sqlite-JDBC driver using the current class loader
            
            
            Class.forName("org.sqlite.JDBC");

	
            //    DriverManager.registerDriver( new org.sqlite.JDBC()    );




            Connection connection = null;
            ResultSet rs;
      try
            {
            // create a database connection
            //    connection = DriverManager.getConnection("jdbc:sqlite:memory");
            connection = DriverManager.getConnection("jdbc:sqlite::memory:");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            //    statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            rs = statement.executeQuery("select * from person");
            while(rs.next())  {
                  // read the result set
                  System.out.println("name = " + rs.getString("name"));
                  System.out.println("id = " + rs.getInt("id"));
            }

            
            Statement ss = connection.createStatement();    //    ss = SQL statement
            ss.setQueryTimeout(30);  // set timeout to 30 sec.
            //    ss.executeUpdate("drop table if exists upd");
            ss.executeUpdate("create table upd (id integer, type string, ip string)");
            ss.executeUpdate("insert into upd values(1, 'log', 'localhost')");
            ss.executeUpdate("insert into upd values(2, 'upload', 'localhost')");
            ResultSet myResult = ss.executeQuery("select * from upd");
            while( myResult.next())  {
                  // read the result set
                  System.out.println("id = " + myResult.getInt("id"));
                  System.out.println("type = " + myResult.getString("type"));
                  System.out.println("ip = " + myResult.getString("ip"));                
            }




      }
      catch(SQLException e)   {
            // if the error message is "out of memory", 
            // it probably means no database file is found
            System.err.println(e.getMessage());
      }
      finally     {
            try   {
                  if(connection != null)
                        connection.close();
                  }
            catch(SQLException e)   {
                  // connection close failed.
                  System.err.println(e);
            }
      }
 



	}
}





