//
//  LUdp
//
//      low-level UDP class
//      provides tools in early stages of development
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
LUdp
{
      Boolean           bNormalStatus;
 
	  DatagramSocket    socket;
	  
	  InetAddress       inetIpAddress;
      String			sIpAddress; 	  

      int               iPort;

      String            sMode; 
      final String      sSERVER           = "s";
      final String      sCLIENT           = "c";     

      int               iBufferSize;

      byte[]            baRecv;
      byte[]            bsSend;

      DatagramPacket    packet;

	LUdpDefaults		defs  				= null;



	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;

	lDate	wd40				= new lDate();



	LUdp()   {     //    constructor
 
			bNormalStatus     = false;
			
			try
				{
				defs = new LUdpDefaults();
				defs.setDefaults();

				sMode			= sSERVER;

				sIpAddress		= "localhost";
				inetIpAddress	= InetAddress.getByName(	sIpAddress );

				iPort			= 7789;
	
				socket			= new DatagramSocket();
	
				socket.disconnect();
	
				socket.connect ( inetIpAddress, iPort );
	
				if ( socket.isConnected() )   {
					 bNormalStatus = true;
				}     
	
				iBufferSize       = 1024;
	
				baRecv            = new byte[ iBufferSize ];
				bsSend            = new byte[ iBufferSize ];			
			}
			catch( Exception e )
				{
				System.err.println( e.getMessage()  );
				System.exit (39 ); 
			}


	}



	LUdp( LLog logIn ) {

		this();

		log			= logIn;		
	}


/*

      public
      Boolean
      setIpAddress( String sIpAddressIn )       {

            if ( ! bNormalStatus )  {
                  return false;
            }

            bNormalStatus = false;

            socket.disconnect();

            inetIpAddress     = InetAddress.getByName( sIpAddressIn );

            socket.connect( intIpAddress, iPort);

            if ( socket.isconnected() )   {
				bNormalStatus = true;
				sIpAddress	= sIpAddressIn;	 
            }

            return bNormalStatus;
      }
 

      public
      Boolean
      setPortNumber( int iPortIn ) {

            if ( ! bNormalStatus )  {
                  return false;
            }     

            bNormalStatus = false;

            socket.disconnect();

            socket.connect( intIpAddress, iPortIn);

            if ( socket.isconnected() )   {
                 bNormalStatus = true;
            }

            return bNormalStatus;
 

      public
      Boolean
      setRecieveBufferSize( int iBufferSizeIn ) {}

            if ( ! bNormalStatus )
                  return false;

            bNormalStatus = false;

            baRecv            = new byte[ iBufferSizeIn ];

            iBufferSize       = iBufferSizeIn;

            bNormalStatus = true;

            return bNormalStatus;
      }


      public
      Boolean
      setSendBufferSize( int iBufferSizeIn ) {}

            if ( ! bNormalStatus				sIpAddress		= "localhost"; )
                  return false;

            bNormalStatus = false;

            baSend            = new byte[ iBufferSizeIn ];

            iBufferSize       = iBufferSizeIn;

            bNormalStatus = true;

            return bNormalStatus;
      }


      public
      Boolean
      allocNewDatagram()     {

            if ( ! bNormalStatus )
                  return false;

            bNormalStatus = false;



            packet = new DatagramPacket(receiveData, receiveData.length);


            baSend            = new byte[ iBufferSizeIn ];

            iBufferSize       = iBufferSizeIn;






            bNormalStatus = true;

            return bNormalStatus;
      }








      public
      Boolean
      start()
      {
            if ( ! bNormalStatus )
                  return false;

            bNormalStatus = false;

            if ( sMode == sClient )       {
                  return true;
            }
      
            preStartLogic();

            run();

            return bNormalStatus;
      }
 

      public
      Boolean
      preStartLogic()   {
      {
            if ( ! bNormalStatus )
                  return false;

            bNormalStatus = false;

            if ( sMode == sClient )       {
                  return true;
            }
      
            return bNormalStatus;
      }
 

 

      public
      Boolean
      run()   {
      {
            if ( ! bNormalStatus )
                  return false;

            bNormalStatus = false;

            if ( sMode == sClient )       {
                  return true;
            }
      

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String( receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);

            serverSocket.send(sendPacket);



            return bNormalStatus;
      }





public
void
xClient() {

	DatagramSocket  socket = null;

	try {
		socket					= new DatagramSocket();
		String requestData		= "'Hello World' via UDP in JAVA";
		byte [] m				= requestData.getBytes();
		InetAddress aHost		= InetAddress.getByName("localhost");
		int serverPort			= 1234;
		DatagramPacket request	= new DatagramPacket(m, requestData.length(), aHost, serverPort);
		socket.send(request);
		byte [] buffer = new byte[1000];
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
		socket.setSoTimeout(1000000);
		socket.receive(reply);
	}
	catch(SocketTimeoutException e){
		e.printStackTrace();
	}
	catch(Exception e){
        e.printStackTrace();
	}finally{
		socket.close();
	}
}

public
void
xServer() {

	DatagramSocket  socket	= null;
	DatagramPacket  request	= null;

	while ( true ) {
		try {
			byte[] buffer	= new byte[ 1024 ];
			request			= new DatagramPacket(buffer, buffer.length);
			socket.setSoTimeout( 100000 );
			socket.receive( request );

			String sData = new String(	request.getData()	);

			//do your processing with request data


			//Sending response
			String response = "Test Reply from UDP server!";
			DatagramPacket reply
				= new DatagramPacket( response.getBytes(), response.length(), request.getAddress(), request.getPort());

			socket.send( reply );
		}
		catch(Exception err) {
			err.printStackTrace();
		}

      int               iPort;	}
}

*/

	public
	void
	statusReport() throws Exception, ClassNotFoundException {

		System.out.println("LUdp : status report");

		System.out.println("LUdp : defaults values");

		try {
			ResultSet myResult = defs.getDb().exec("select * from udpDefaults");

			while (myResult.next()) {

				// read the result set
				System.out.println("type = "	+ myResult.getString("type"));
				System.out.println("ip = "		+ myResult.getString("ip"));
				System.out.println("port = "	+ myResult.getString("port"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(39);
		}
		//try/catch   

		System.out.println("live IP   = "	+ sIpAddress	);
		System.out.println("live Port = "	+ iPort			);		
		



	}




	public static void main(String args[]) throws Exception
	{
		 
		LUdp	t = new LUdp();
		
		t.statusReport();

		System.exit( 0 );

		/*

		DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());

                  //    the incoming string may need to be evaluated later  

                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String sMsgNum = getNextMsgNum();   //    get next msg number
                  sendData = sMsgNum.getBytes();
                  DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
			   }
		
		*/

	}




}



/*



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
      boolean           bNormalStatus     =             System.out.println("" );

		LUdpDefaults      t = new LUdpDefaults();

            t.setDefaults();

            System.out.println("LUdpDefaults : testing begins here" );

            t.StatusReport();false;
 
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
            System.out.println("" );

		LUdpDefaults      t = new LUdpDefaults();

            t.setDefaults();

            System.out.println("LUdpDefaults : testing begins here" );

            t.StatusReport();            db.build( sCmd );


            db.build( "insert into    udpDefaults values  ( 'log',        'localhost',      7755  )"    );
            db.build( "insert into    udpDefaults values  ( 'upload',     'localhost',      7757  )"    );



		}



		public
		void
		StatusReport()  throws Exception, ClassNotFoundException    {
	
				System.out.println("LUdpPacket : status report");
	
				try
					  {
					  ResultSet myResult = db.exec (       "select * from udpDefaults" );
	            System.out.println("" );

		LUdpDefaults      t = new LUdpDefaults();

            t.setDefaults();

            System.out.println("LUdpDefaults : testing begins here" );

            t.StatusReport();
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
	




*/