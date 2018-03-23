//
//  LGuru
//

//	package net.xt57;

import java.io.*;
import java.net.*;













public
class
LGuru
{
      boolean           bNormalStatus     = false;

      String

 
 	LLog              log               = null;

	lDate             wd40              = null;

	LGuru()   {                     //    constructor

  	                  wd40        = new lDate();               
	}

	LGuru( LLog logIn ) {

                        this();
		            log         = logIn;		
	}


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
