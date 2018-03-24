//
//  LUdp
//
//      low-level UDP class
//      provides tools in early stages of development
//

//	package net.xt57;

//  import java.io.*;

import java.net.*;


//  this is a comment to track through git




///////////////////////////////////////////////////////////////////
public
class
LUdp {
///////////////////////////////////////////////////////////////////

	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;

	lDate	wd40				= new lDate();

	LUdp() {
	//////
		;
	}

	LUdp( LLog logIn ) {
	////////////////////
		log			= logIn;
	}////


public
String
sGreeter() {
////////////
	return "Hello!";
}



public
void
xClient() {
///////////
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
	}////
	catch(SocketTimeoutException e){
		e.printStackTrace();
	}////
	catch(Exception e){
        e.printStackTrace();
	}finally{
		socket.close();
	}////
}////

public
void
xServer() {
///////////
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
		}////
		catch(Exception err) {
			err.printStackTrace();
		}////
	}////
}////



/*

public
void
xClient() {
///////////

	try {
		DatagramSocket socket = new DatagramSocket();
		byte[] buf = new byte[1000];
		DatagramPacket packIn = new DatagramPacket(buf, buf.length);

		InetAddress hostAddress = InetAddress.getByName("localhost");

		while (true) {
			BufferedReader stdin
				= new BufferedReader(new InputStreamReader(System.in) );

			String outMessage = stdin.readLine();

			if (outMessage.equals("bye"))
				break;

			String outString = "Client say: " + outMessage;
			buf = outString.getBytes();

			DatagramPacket packOMyQa55LMsgUdp.javaut
				= new DatagramPacket(buf, buf.length, hostAddress, 9999);

			socket.send( packOut );

			socket.receive( packIn );

			String sRecv
				=		"rcvd from "
					+	packIn.getAddress()
					+	", "
					+	packIn.getPort()
					+	": "
					+	new String(		packIn.getData(), 0, packIn.getLength()		);

			System.out.println( sRecv );
		}////
	}////
	catch (Exception e) {
		System.out.println( "client socket error" );
		System.exit( 9 );
	}////

}////

public
void
xServer() {
///////////
	try {
		int PORT = 4000;
		byte[] buf = new byte[1000];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);

		DatagramSocket socket = new DatagramSocket(PORT);

		System.out.println("Server started");
		while (true) {
			socket.receive( packet );

			String sRecv
				= new	String( packet.getData(), 0, packet.getLength()	)
					+	", from address: "
					+	packet.getAddress()
					+	", port: "
					+	packet.getPort();
			/////

			System.out.println( sRecv );

			BufferedReader stdin
				= new BufferedReader(new InputStreamReader( System.in ) );

			String outMessage = stdin.readLine();
			buf = ("Server say: " + outMessage).getBytes();
			DatagramPacket packOut
				= new DatagramPacket (
						buf,
						buf.length,
						packet.getAddress(),
						packet.getPort()
					);
				/////
			/////

			socket.send( packOut);
		}////
	}////
	catch (Exception e) {
		System.out.println( "server socket error" );
		System.exit( 9 );
	}////
}////


















public
void
coreClient() {
//////////////

	try {
		BufferedReader inFromUser =
			new BufferedReader(new InputStreamReader( System.in) );

		DatagramSocket clientSocket = new DatagramSocket();

		InetAddress IPAddress = InetAddress.getByName("localhost");

		byte[] sendData		= new byte[ 1024 ];
		byte[] receiveData	= new byte[ 1024 ];
		String sentence		= inFromUser.readLine();

		sendData = sentence.getBytes();

		DatagramPacket sendPacket
			= new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

		clientSocket.send(sendPacket);

		DatagramPacket receivePacket
			= new DatagramPacket(receiveData, receiveData.length);

		clientSocket.receive(receiMyQa55LMsgUdp.javavePacket);

		String modifiedSentence = new String(	receivePacket.getData()	);

		System.out.println("FROM SERVER:" + modifiedSentence);

		clientSocket.close();
	}////
	catch (Exception e) {
		System.out.println( "MySQL driver not found" );
		System.exit( 9 );
	}////

}////

public
void
coreServer() {
//////////////
	try {
		DatagramSocket serverSocket = new DatagramSocket(9876);

		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];

		while ( true ) {

			DatagramPacket receivePacket
				= new DatagramPacket(receiveData, receiveData.length);

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
		}////
	}////
	catch (Exception e) {
		System.out.println( "MySQL driver not found" );
		System.exit( 9 );
	}////
}////

*/

}// end of class
