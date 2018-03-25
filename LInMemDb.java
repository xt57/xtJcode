//
//  LInMemDb
//

//	package net.xt57;

import java.io.*;
import java.net.*;

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

      Connection        db                = null;
 
      Statement         cmd               = null;

      LLog              log               = null;

	lDate             wd40              = null;

	LInMemDb()   {                     //    constructor


            try
                  {
                  //    load the SQLite JDBC driver
                  Class.forName( "org.sqlite.JDBC" );

                  // create an in-memory database connection
                  db    = DriverManager.getConnection("jdbc:sqlite::memory:?cache=shared");   
                  cmd   = db.createStatement();
                  cmd.setQueryTimeout(7);      
            }
            catch( ClassNotFoundException e )   {
      
                  System.err.println( e.getMessage()  );
                  //    throw here
            }      
            catch( SQLException e )   {
      
                  System.err.println( e.getMessage()  );
                  //    throw here
            }               
            
            wd40        = new lDate();   
	}

	LInMemDb( LLog logIn ) {

            this();
            log         = logIn;		
	}

      public
      boolean
      build( String sStatementIn )    {

            try
                  {
                  cmd.executeUpdate( sStatementIn );  //    exec our SQL statement
                  return true;
            }
            catch( SQLException e )
                  {
                  System.err.println( e.getMessage()  );
                  System.exit( 19);
            }
            
            System.exit( 9 );            

            return false;
      }


      public
      ResultSet
      exec( String sStatementIn )    {

            try
                  {
                  return cmd.executeQuery( sStatementIn );  //    exec our SQL statement
            }
            catch( SQLException e )
                  {
                  System.err.println( e.getMessage()  );
                  System.exit( 29);      
            }

            return (ResultSet) null;
      }


	public
	void
	StatusReport()    {

            System.out.println("LInMemDb : status report");
      }


	public static void
	main(String args[]) throws Exception, ClassNotFoundException      {

            String      sCmd;
 
            System.out.println("" );

		LInMemDb	db    = new LInMemDb();

            db.StatusReport();

            System.out.println( "" );

            System.out.println("LInMemDb : testing begins here" );

            db.StatusReport();
  
            try
                  {
                  //    t.exec( "drop table if exists upd");      // if perm form of storage

                  sCmd = "create table " + "udp" + " ( type string, ip string, port string)";
                  db.build( sCmd );


                  db.build( "insert into    udp values  ( 'log',        'localhost',      7755  )"    );
                  db.build( "insert into    udp values  ( 'upload',     'localhost',      7757  )"    );

                  ResultSet myResult = db.exec (       "select * from udp" );
 
                  while( myResult.next()  )  {

                        // read the result set
                        System.out.println("type = "  + myResult.getString("type")  );
                        System.out.println("ip = "    + myResult.getString("ip")    );                
                        System.out.println("port = "  + myResult.getString("port")  );  
                  }
            }
            catch( SQLException e )
                  {
                  System.err.println( e.getMessage()  );
                  System.exit (39 ); 
            }
            //try/catch
      }
}







/*
            try
                  {
                  t.setCmd( t.getDb().createStatement() );              // store SQL exec handle
                  t.getCmd().setQueryTimeout(30);                       // set timeout to 30 sec.
                  //    ss.executeUpdate("drop table if exists upd");   // if perm form of storage

                  t.getCmd().executeUpdate("create table upd        ( type string,    ip string)"        );
                  t.getCmd().executeUpdate("insert into upd values  ( 'log',          'localhost')"      );
                  t.getCmd().executeUpdate("insert into upd values  ( 'upload',       'localhost')"      );

                  ResultSet myResult = t.getCmd().executeQuery("select * from upd");

                  while( myResult.next()  )  {
                        // read the result set
                        System.out.println("type = "  + myResult.getString("type")  );
                        System.out.println("ip = "    + myResult.getString("ip")    );                
                  }
            }
            catch( SQLException e )
                  {
                  System.err.println( e.getMessage()  );
            }
            finally
                  {
                  try
                        {
                        if( t.db != null )      {
                              t.db.close();
                        }
                  }
                  catch( SQLException e )
                        {
                        // connection close failed.
                        System.err.println( e   );
                  }
                  //try/catch
            }
            //try/catch
      }
      //main
}
//class

*/



