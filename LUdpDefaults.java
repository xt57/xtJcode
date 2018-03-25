//
//  LUdpDefaults
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
LUdpDefaults
{
      boolean           bNormalStatus     = false;
 
      LInMemDb          db                = null;

	lDate             wd40              = null;

      LLog              log               = null;


	LUdpDefaults()   {                  //    constructor

  	                  wd40        = new lDate();               
	}

	LUdpDefaults( LLog logIn ) {

                        this();
		            log         = logIn;		
	}



	
      public
      void
      setDefaults()   throws Exception, SQLException  {

            String      sCmd;
 
            System.out.println("" );

		db    = new LInMemDb();

            db.StatusReport();
  

            sCmd = "create table " + "udpDefaults" + " ( type string, ip string, port string)";
            db.build( sCmd );


            db.build( "insert into    udp values  ( 'log',        'localhost',      7755  )"    );
            db.build( "insert into    udp values  ( 'upload',     'localhost',      7757  )"    );

            /*

            try
                  {
                  //    t.exec( "drop table if exists upd");      // if perm form of storage

                  sCmd = "create table " + "udpDefaults" + " ( type string, ip string, port string)";
                  db.build( sCmd );


                  db.build( "insert into    udp values  ( 'log',        'localhost',      7755  )"    );
                  db.build( "insert into    udp values  ( 'upload',     'localhost',      7757  )"    );
            }
            catch( SQLException e )
                  {
                  System.err.println( e.getMessage()  );
                  System.exit (39 ); 
            }
            //try/catch
            
            */

      }



	public
	void
	StatusReport()  throws Exception, ClassNotFoundException    {

            System.out.println("LUdpPacket : status report");

            try
                  {
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


	public static void
	main(String args[]) throws Exception {

            System.out.println("" );

		LUdpDefaults      t = new LUdpDefaults();

            t.setDefaults();

            System.out.println("LUdpDefaults : testing begins here" );

            t.StatusReport();
	}
}
