//
//  LTcp
//

//	package net.xt57;

import java.io.*;
import java.net.*;





public
class
LTcp {
//////

	Socket				sock	= null;
	ServerSocket		srvSock	= null;

	DataOutputStream	writer	= null;
	DataInputStream		reader	= null;

	byte[]	baMsg				= null;
	int		iMsgLength			= 0;

	String	sIp					= null;
	int		iPort				= 7777;

	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;

	lDate	wd40				= new lDate();

	LTcp() {
	////////
		;
	}

public
void
openServer() throws Exception {
///////////////////////////////
	String	sMyName = this.getClass().getName() + ".openServer()";
	try {
		srvSock	= new ServerSocket(		iPort );
		sock	= srvSock.accept();

		writer	= new DataOutputStream(	sock.getOutputStream()	);

		reader	= new DataInputStream(	sock.getInputStream()	);
	}////
	catch(Exception e) {
		System.out.println( sMyName + " : server setup failed");
	}////
}////

public
void
openClient() throws Exception {
///////////////////////////////
	String	sMyName = this.getClass().getName() + ".openClient()";
	try {
		sock	= new Socket(		sIp,	iPort );

		writer	= new DataOutputStream(	sock.getOutputStream()	);

		reader	= new DataInputStream(	sock.getInputStream()	);
	}////
	catch(Exception e) {
		System.out.println( sMyName + " : client setup failed");
	}////
}////

public
byte[]
readByteArray() throws Exception {
//////////////////////////////////
	String	sMyName = this.getClass().getName() + ".readByteArray()";
	try {
		iMsgLength = reader.readInt();
		baMsg = new byte[ iMsgLength ];
		reader.read( baMsg, 0, iMsgLength );

	}////
	catch(Exception e) {
		System.out.println( sMyName + " : read failed");
		System.exit( 219 );
	}//////
	return baMsg;
}////

public
String
readString() throws Exception {
///////////////////////////////
	String	sMyName = this.getClass().getName() + ".readString()";
	try {
		readByteArray();
		return new String( baMsg );
	}////
	catch(Exception e) {
		System.out.println( sMyName + " : read failed");
		System.exit( 219 );
	}//////
	return new String( baMsg );
}////

public
void
writeByteArray( byte[] baIn ) throws Exception {
///////////////////////////////////////
	String	sMyName = this.getClass().getName() + ".write()";
	try {
								iMsgLength = baIn.length;
		writer.writeInt(		iMsgLength );
		writer.write( baMsg, 0, iMsgLength );
		writer.flush();
	}////
	catch(Exception e) {
		System.out.println( sMyName + " : write failed");
		System.exit( 219 );
	}//////
}////

public
void
eric( String sIn ) throws Exception {
//////////////////////////////////////
	String	sMyName = this.getClass().getName() + ".write()";
	try {
												iMsgLength = sIn.length();
		writer.writeInt(						iMsgLength );
		writer.write(		sIn.getBytes(), 0,	iMsgLength );
		writer.flush();
	}////
	catch(Exception e) {
		System.out.println( sMyName + " : write failed");
		System.exit( 219 );
	}//////
}////

public
void
xSend() throws Exception {
//////////////////////////
	String	sMyName = this.getClass().getName() + ".xSend()";

	try {
		Socket skt = new Socket("localhost", 7777 );
		BufferedReader in
			= new BufferedReader( new InputStreamReader( skt.getInputStream()	)	);

		System.out.print("Received string: '");

		while (	! in.ready()	) {
			wd40.nap ( 100 );
		}////

		System.out.println(in.readLine()); // Read one line and output it

		System.out.print("'\n");
		in.close();

		PrintWriter out = new PrintWriter( sock.getOutputStream(), true );

		System.out.println( "Sending string: " );
		out.print( ". that is the question!");
		out.print( "and always will be!");
	}////
	catch(Exception e) {
		System.out.println( sMyName + " : the client failed");
	}////
}////

public
void
xRecv( ) throws Exception {
///////////////////////////
	String	sMyName = this.getClass().getName() + ".xRecv()";

	String data = "Toobie ornaught toobie";
	try {
		ServerSocket srvr = new ServerSocket( 7777 );
		Socket sock = srvr.accept();
		System.out.print("Server has connected!\n");

		PrintWriter out = new PrintWriter( sock.getOutputStream(), true );

		System.out.println( "Sending string: " + data);
		out.print( data + ". that is the question!");
		out.print( "and always will be!");

		out.close();
		sock.close();
		srvr.close();
	}////
	catch(Exception e) {
		System.out.println( sMyName + " : the server failed");
	}////
}////

public
String
setIp( String sIpIn) {
//////////////////////
	sIp = sIpIn;
	return sIp;
}////

public
int
setPort( int iPortIn) {
///////////////////////
	iPort = iPortIn;
	return iPort;
}////

/*

public
void
xNioSrv() {
///////////
	String	sMyName = this.getClass().getName() + ".xSend()";

	try
		{
		// Create an AsynchronousServerSocketChannel that will listen on port 5000
		final	AsynchronousServerSocketChannel listener =
				AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(5000));

		// Listen for a new request
		listener.accept( null, new CompletionHandler<AsynchronousSocketChannel,Void>() {

		@Override
		public void completed(AsynchronousSocketChannel ch, Void att)
		{
		// Accept the next connection
		listener.accept( null, this );

		// Greet the client
		ch.write( ByteBuffer.wrap( "Hello, I am Echo Server 2020, let's have an engaging conversation!\n".getBytes() ) );

		// Allocate a byte buffer (4K) to read from the client
		ByteBuffer byteBuffer = ByteBuffer.allocate( 4096 );
		try	{
			// Read the first line
			int bytesRead = ch.read( byteBuffer ).get( 20, TimeUnit.SECONDS );

			boolean running = true;
			while( bytesRead != -1 && running ) {
				System.out.println( "bytes read: " + bytesRead );

				// Make sure that we have data to read
				if( byteBuffer.position() > 2 ) {
					// Make the buffer ready to read
					byteBuffer.flip();

					// Convert the buffer into a line
					byte[] lineBytes = new byte[ bytesRead ];
					byteBuffer.get( lineBytes, 0, bytesRead );
					String line = new String( lineBytes );

					// Debug
					System.out.println( "Message: " + line );

					// Echo back to the caller
					ch.write( ByteBuffer.wrap( line.getBytes() ) );

					// Make the buffer ready to write
					byteBuffer.clear();

					// Read the next line
						bytesRead = ch.read( byteBuffer ).get( 20, TimeUnit.SECONDS );
				}
				else {
					// An empty line signifies the end of the conversation in our protocol
					running = false;
				}
			}
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                    catch (TimeoutException e)
                    {
                        // The user exceeded the 20 second timeout, so close the connection
                        ch.write( ByteBuffer.wrap( "Good Bye\n".getBytes() ) );
                        System.out.println( "Connection timed out, closing connection" );
                    }

                    System.out.println( "End of conversation" );
                    try
                    {
                        // Close the connection if we need to
                        if( ch.isOpen() )
                        {
                            ch.close();
                        }
                    }
                    catch (I/OException e1)
                    {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Void att) {
                    ///...
                }
            });
        }
        catch (I/OException e)
        {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        NioSocketServer server = new NioSocketServer();
        try
        {
            Thread.sleep( 60000 );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}



*/












}//	end of class
