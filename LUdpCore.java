//
//  LUdp
//
//      low-level UDP class
//      provides tools in early stages of development
//

//	package net.xt57;

import java.io.*;
import java.net.*;


public
class
LUdpCore
{
      boolean           bNormalStatus     = false;
 
      DatagramSocket    socket;
      InetAddress       inetIpAddress;  
      int               iPort;
 
	boolean           bWeHaveALogFile	= false;
	LLog              log               = null;

	lDate             wd40              = null;

	LUdpCore()   {     //    constructor

            try {
                  inetIpAddress     = InetAddress.getByName("localhost");
            }
            catch (Exception e) {}

            iPort                   = 7789;
            
            try {
                  socket            = new DatagramSocket();
            }
            catch (Exception e) {}

            socket.disconnect();

            socket.connect ( inetIpAddress, iPort );

            if ( socket.isConnected() )   {
                 bNormalStatus = true;
            }

 	      wd40                    = new lDate();               
	}

	LUdpCore( LLog logIn ) {

            this();
		log			= logIn;		
	}


      public
      boolean
      setIpAddress( String sIpAddressIn )       {

            if ( ! bNormalStatus )  {
                  return false;
            }

            bNormalStatus = false;

            socket.disconnect();
            
            try {
                  inetIpAddress     = InetAddress.getByName( sIpAddressIn );
            }
            catch (Exception e) {}

            socket.connect( inetIpAddress, iPort);

            if ( socket.isConnected() )   {
                 bNormalStatus = true;
            }

            return bNormalStatus;
      }
 

      public
      boolean
      setPortNumber( int iPortIn ) {

            if ( ! bNormalStatus )  {
                  return false;
            }     

            bNormalStatus = false;

            socket.disconnect();

            socket.connect( inetIpAddress, iPortIn);

            if ( socket.isConnected() )   {
                 bNormalStatus = true;
            }

            return bNormalStatus;
      }

	public
	void
	StatusReport()    {

            System.out.println("LUdpCore : status report");

		//	bWeHaveALogFile

		//	log

		//	wd40

            System.out.println("LUdpCore : IP Address  = " + socket.getInetAddress() );

            System.out.println("LUdpCore : Port Number = " + socket.getPort() );
      }


	public static void
	main(String args[]) throws Exception {

            System.out.println("" );

		LUdpCore	testObj		= new LUdpCore();

            testObj.StatusReport();

            System.out.println("" );

            System.out.println("LUdpCore : changing address to 192.168.1.57" );

            testObj.setIpAddress( "192.168.1.57");

            testObj.StatusReport();

	}
}
