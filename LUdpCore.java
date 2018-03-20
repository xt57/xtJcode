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
      boolean              bNormalStatus;
 
      DatagramSocket    socket;
      InetAddress       inetIpAddress;  
      int               iPort;
 
      int               iBufferSize;
      
	  DatagramPacket    packet;


	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;

	lDate	wd40				= new lDate();

	LUdpCore()   {     //    constructor
 
            bNormalStatus     = false;
 
            try {
                  inetIpAddress     = InetAddress.getByName("localhost");
            }
            catch (Exception e) {}

            iPort             = 7789;
            
            try {
                  socket            = new DatagramSocket();
            }
            catch (Exception e) {}

            socket.disconnect();

            socket.connect ( inetIpAddress, iPort );

            if ( socket.isConnected() )   {
                 bNormalStatus = true;
            }     
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

		//	bStatus;
 
		//	inetIpAddress;

		//	bWeHaveALogFile

		//	log

		//	wd40
      }


	public static void
	main(String args[]) throws Exception {

		LUdpCore	testObj		= new LUdpCore();
	}
}
