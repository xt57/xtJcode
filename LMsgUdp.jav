//
//    MsgNextMsgNum
//

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class LMsgUdp
{
      Boolean           bStatus;
 
      DatagramSocket    socket;
      InetAddress       inetIpAddress;  
      int               iPort;

      String            sMode; 
      final String      sSERVER           = "s";
      final String      sCLIENT           = "c";     

      int               iBufferSize;

      byte[]            baRecv;
      byte[]            bsSend;


      DatagramPacket    packet;

      LMsgUdp()   {     //    constructor
 
            bNormalStatus     = false;
 
            sMode              = sSERVER;

            inetIpAddress     = InetAddress.getByName("localhost");
            
            iPort             = 7789;

            socket            = new DatagramSocket();

            socket.disconnect();

            socket.connect ( inetIpAddrress, iPort );

            if ( socket.isconnected() )   {}
                 bNormalStatus = true;
            }     

            iBufferSize       = 1024;

            baRecv            = new byte[ iBufferSize ];
            bsSend            = new byte[ iBufferSize ];

      }


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

            if ( ! bNormalStatus )
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










      public static void main(String args[]) throws Exception
      {
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
      }




}
