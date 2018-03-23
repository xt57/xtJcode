//
//  LUdpPacket
//

//	package net.xt57;

import java.io.*;
import java.net.*;


public
class
LUdpPacket
{
      boolean           bNormalStatus     = false;
 
      DatagramPacket    Packet;

      LUdpSocket        sockIn;

      InetAddress       inetIpAddress;  
      int               iPort;
 
	boolean           bWeHaveALogFile	= false;
	LLog              log               = null;

	lDate             wd40              = null;

	LUdpPacket()   {                     //    constructor

  	                  wd40        = new lDate();               
	}

	LUdpPacket( LLog logIn ) {

                        this();
		            log         = logIn;		
	}


      public
      LUdpPacket
      Recv( LUdpSocket sockInput )       {

            if ( ! bNormalStatus )  {
                  return false;
            }

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
 
	public
	void
	StatusReport()    {

            System.out.println("LUdpPacket : status report");
      }


	public static void
	main(String args[]) throws Exception {

            System.out.println("" );

		LUdpPacket	testObj		= new LUdpPacket();

            testObj.StatusReport();

            System.out.println("" );

            System.out.println("LUdpPacket : testing begins here" );

            testObj.StatusReport();
	}
}
