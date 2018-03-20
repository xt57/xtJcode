//
//    MsgNextMsgNum
//

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class LMsgUdpX
{
      private     Short             siMsgNum;
 
      private     Calendar          cCal;
 
      private     SimpleDateFormat  ft;

      LMsgUdpX() {

            siMsgNum    = 1;
           
            cCal        = Calendar.getInstance();
            
            ft          = new SimpleDateFormat ("yyyyMMddHHmmssSSS");
      }

      public
      String
      getNextMsgNum() {

            String      sReturn;

            String sDateSeg = ft.format( cCal.getTime()	);

            if ( siMsgNum > Short.MAX_VALUE )     {
                  siMsgNum = 1;
            }

            sReturn = sDateSeg + String.format ("%07d", siMsgNum);

            siMsgNum++;

            return sReturn;
      }
}




class MsgNextMsgNum {

      private static    Short     siMsgNum;
 
      Calendar          cCal;
 
      SimpleDateFormat  ft;

      MsgNextMsgNum()
      {
            siMsgNum    = 1;
           
            cCal        = Calendar.getInstance();
            
            ft          = new SimpleDateFormat ("yyyyMMddHHmmssSSS");
      }

      private static
      String
      getNextMsgNum()   {
            
            String      sReturn;

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