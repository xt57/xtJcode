//
//    MsgNextMsgNum
//

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class MsgNextMsgNum
{
      private static    Short     siMsgNum = 1;
 
      private static
      String
      getNextMsgNum()
      {
            Calendar    cCal	= Calendar.getInstance();
            String      sReturn;

            SimpleDateFormat ft =
                  new SimpleDateFormat ("yyyyMMddHHmmssSSS");
      
            String sDateSeg = ft.format( cCal.getTime()	);

            if ( siMsgNum > Short.MAX_VALUE )     {
                  siMsgNum = 1;
            }

            sReturn = sDateSeg + String.format ("%07d", siMsgNum);

            siMsgNum++;

            return sReturn;
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

                  //    the incoming sting may need to be evaluated later  

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
