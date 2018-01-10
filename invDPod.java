//
// this is a working example of CompletableFuture logic
//
//
//

package com.xt57;












	//
	//	this logic must become a chopper/compiler!
	//
	//		1) read data, chop each field, & add its byte[] text to a queue
	//
	//		2) notify an observer to process the queue
	//
	//		3) each queue entry would be interpreted and result in :
	//
	//			*	decompilation into the correct data type
	//			*	decompilation should use the Command pattern to simplify execution
	//					of the class/method that handles the specific data type
	//
	//		4) any failure would "scrap" the entire linux "process" & restart it
	//














/*








import java.util.LinkedList;
import java.util.Queue;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import java.util.Observable;

import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Arrays;

///////////////////////////////////////////////////////////////////
public
class
invDPod  extends Observable {
/////////////////////////////////////////////////////////////////////
	int		iBaReadBufSize		= 16384;

	boolean	bAllSystemsAreGo	= false;
	boolean	bWeOnHold			= true;
	boolean	bWeAreReadyForTheNextRead	= true;

	LinkedList<Byte> llMain		= new LinkedList<>();



	LinkedList<invListTracker> llTracker
								= new LinkedList<>();

	LinkedList<invByteArray> llChunks
								= new LinkedList<>();

	LinkedList<Calendar> llBirthdays
								= new LinkedList<>();



	LinkedList<invQuoteFields> llTs	= new LinkedList<>();

	//	byte[]	baFocus				= new byte[ iBaFocusSize ];
	int		iFocusNdx			= 0;
	int		iFocus				= 0;

	int		ndx					= 0;

	int		iRemovalBytes		= 0;

	DataInputStream disMain		= null;

	byte[]	baReadBuf			= new byte[ iBaReadBufSize ];
	int		iBytesRead			= 0;

	byte[]	baExtractedPayload	= new byte[ iBaReadBufSize ];
	int		iExtractedLength	= 0;

	boolean	bWeHaveALogFile		= false;
	lLog	log					= null;

	invQuoteFields	tdf				= new invQuoteFields();

	//	remember that all of the "observable" qualities are now active!



	invDPod() {
	///////////
		;
	}


	invDPod( lLog logIn ) {
	///////////////////////
		log = logIn;	// is this okay?
	}



	invDPod( InputStream isIn, lLog logIn ) {
	/////////////////////////////////////////
		disMain = new DataInputStream( isIn );

		log = logIn;
	}



public
void
setDefaultValues() throws Exception {
/////////////////////////////////////
	try {
		if ( ! bWeHaveALogFile ) {
			log = new lLog( "/tmp/invDataPod.log" );
		}
	}
	catch( Exception e ) {
		;
	}

}



public
void
examineActive( ) {
//////////////////


	//
	//	this logic must become a chopper/compiler!
	//
	//		1) read data, chop each field, & add its byte[] text to a queue
	//
	//		2) notify an observer to process the queue
	//
	//		3) each queue entry would be interpreted and result in :
	//
	//			*	decompilation into the correct data type
	//			*	decompilation should use the Command pattern to simplify execution
	//					of the class/method that handles the specific data type
	//
	//		4) any failure would "scrap" the entire linux "process" & restart it
	//


	String	sMyName = this.getClass().getName() + ".examineActive()";

	boolean		baAHeartbeatIsInProgress = false;

	//	bWeAreReadyForTheNextRead = false;

	log.ifExtreme( "%s : llMain.size() : %d", sMyName, llMain.size() );

	if ( llMain.size() < 10 ) {
		//	bActiveReviewLoop = false;
		return;
	}////

	if ( peekChar( 0 )	== 'H' && peekChar( 1 ) == 'T' ) {
		// add logic here to allow for randomly getting 'HT' in the mid-stream
		heartbeatReview();
		removeBytesFromMain();
	}////

	if ( llMain.size() == 0 ) {
		return;
	}////

	if ( peekChar( 0 ) == 'N' ) {
		log.ifExtreme( "%s : snapshot N rec found", sMyName );
		snapshotReview();
		removeBytesFromMain();
		//	payloadReview();
	}////

	if ( llMain.size() == 0 ) {
		return;
	}////

	if ( peekChar( 0 )	== 'S' ) {
		log.ifExtreme( "%s : streaming S rec found", sMyName );
		while ( llMain.size() > 25 ) {
			streamingDataReview();
			removeBytesFromMain();
		}/////////////////////////
	}////

	removeBytesFromMain();

}



public
byte
peekByte( int ndxIn ) {
///////////////////////
	//	String	sMyName = this.getClass().getName() + ".peekByte()";

	//	log.ifExtreme( "%s : llMain.size() : %d", sMyName, llMain.size() );

	//	if ( ndxIn > llMain.size() ) {
	//		return (byte) -1;
	//	}

	return (byte) llMain.get( ndxIn );
}





public
char
peekChar( int ndxIn ) {
///////////////////////
	//	String	sMyName = this.getClass().getName() + ".peekChar()";

	//	log.ifExtreme( "%s : llMain.size() : %d", sMyName, llMain.size() );

	//	if ( ndxIn > llMain.size() ) {
	//		return (char) -1;
	//	}

	return (char) peekByte( ndxIn );
}




public
char
peekChar() {
////////////
	String	sMyName = this.getClass().getName() + ".peekChar()";

	char cResult = peekChar( iFocusNdx);

	log.ifExtreme( "%s : cResult : %c", sMyName, cResult	);

	return cResult;
}




public
char
printableElseBlank() {
//////////////////////
	//	String	sMyName = this.getClass().getName() + ".peekChar()";

	//	log.ifExtreme( "%s : llMain.size() : %d", sMyName, llMain.size() );

	//	if ( ndxIn > llMain.size() ) {
	//		return (char) -1;
	//	}

	char	cResult = ' ';

	int		i = peekByteVal();

	if ( i > 31 && i < 127 )
		cResult = (char) i;
	else
		cResult = ' ';

	return cResult;
}


public
char
printableElseBlank( byte bIn ) {
////////////////////////////////
	//	String	sMyName = this.getClass().getName() + ".peekChar()";

	//	log.ifExtreme( "%s : llMain.size() : %d", sMyName, llMain.size() );

	//	if ( ndxIn > llMain.size() ) {
	//		return (char) -1;
	//	}

	char	cResult = ' ';

	int	i = (int) bIn;

	if ( i > 31 && i < 127 ) {
		cResult = (char) i;
	}//////////////////////

	return cResult;
}





public
boolean
removeBytesFromMain() {
///////////////////////
	String	sMyName = this.getClass().getName() + ".removeBytesFromMain()";

	log.ifExtreme( "%s : managing removal bytes", sMyName );

	log.ifExtreme( "%s : llMain.size()   : %d", sMyName, llMain.size() );
	log.ifExtreme( "%s : orig bytes read : %d", sMyName, iRemovalBytes );

	if ( iRemovalBytes > llMain.size() ) {
		log.ifExtreme( "%s : cleanup on isle 9", sMyName );
		return false;
	}////////////////

	for ( int i=0; i < iRemovalBytes; i++ ) {
		llMain.remove();
		iFocusNdx--;
	}///////////////

	statusReport();

	iRemovalBytes = 0;

	return true;
}





public
void
streamingDataReview() {
//////////////////
	String	sMyName = this.getClass().getName() + ".streamingDataReview()";

	iFocusNdx = 0;

	//
	//	we have an "S" record in the "consideration window"
	//


	//	push the indexing fields to point past the "S" character
	iRemovalBytes	+= 1;
	iFocusNdx		+= 1;

	if ( llMain.size() < 10 ) {
		return;
	}//////////


	//	consider the MsgLength/PayloadLen field
	//	=======================================

	short	shStreamingMsgLength = chopShort();

	log.ifExtreme( "%s : shStreamingMsgLength : %d", sMyName, shStreamingMsgLength	);





	//	consider the SID integer
	//	========================

	short	shStreamingSID = chopShort();

	log.ifExtreme( "%s : shStreamingSID : %d", sMyName, shStreamingSID	);



	//	create a new invQuoteFields instance in which to store the incoming record
	invQuoteFields tdFields = new invQuoteFields( log );

	//	now, loop through the real data fields
	//	======================================

	int iCol = peekByteVal();
	while ( iCol > -1 && iCol < 255 ) {

		log.ifExtreme( "%s : Streaming Column Number : %d", sMyName, iCol );

		switch ( iCol ) {

			case 0 : // Symbol
				chopByteVal();	// throw away the column number we just peek'd
				int	iLength = (int) chopShort();
				log.ifExtreme( "%s : Symbol Length : %d", sMyName, iLength	);

				tdFields.setTicker ( chopString( iLength )	);
				tdFields.statusReport();
				break;

			case 1:	// Birth Time
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setBirth( (long) chopInt() );
				tdFields.statusReport();
				break;

			case 2:	// Price
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setPrice( chopFloat() );
				tdFields.statusReport();
				break;

			case 3:	// Volume
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setVolume( chopLong() );
				statusReport();
				tdFields.statusReport();
				break;

			case 4:	// Sequence
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setSeq( chopInt() );
				break;

			case 5:	// Updated
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setUpdated( chopInt() );
				break;

			case 12:	// High
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setHigh( chopFloat() );
				tdFields.statusReport();
				break;

			case 13:	// Low
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setLow( chopFloat() );
				tdFields.statusReport();
				break;

			case 15:	// Close
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setClose( chopFloat() );
				tdFields.statusReport();
				break;

			case 28:	// Open
				chopByteVal();	// throw away the column number we just peek'd
				tdFields.setOpen( chopFloat() );
				tdFields.statusReport();
				break;

			default:
				log.ifExtreme( "%s : unknown column number : %d", sMyName, iCol	);
				break;
			//////////

		}//switch

		iCol = peekByteVal();

	}//while

	if ( ! tdFields.bPodIsComplete()	) {
		log.write(	"pod is not complete, bailing ..." );
		tdFields.statusReport( true);	// force the status report to our log
		System.exit( 218 );
	}

	llTs.add( tdFields );

	log.write( "TS data : %5s, %12f (%5d, %7d)", tdFields.getTicker(), tdFields.getPrice(),
				llTs.size(), tdFields.getVolume()
				);

	if ( llTs.size() > 9000 ) {
		log.write(	"pod is full, ready for empty_to_db code, bailing ..." );
		System.exit( 7 );
	}////////////////////

	if ( llTs.size() > 100 ) {
		for ( int i=0; i < 10; i++ ) {
			llTs.remove();
		}/////////////////
	}/////////////////////



	//	verify that we are really at the end of this record
	//	===================================================

	iFocus = chopByteVal();
	if (	( iFocus & 0xFF ) == 255	) {
		log.ifExtreme( "%s : 0xFF delim found, as expected", sMyName );
	}/////
	else {
		log.ifExtreme( "%s : streaming decomp failure : 255 expected", sMyName );
		System.exit( 98 );
	}////////////////////





	log.ifExtreme( "%s : i peek after 255 : %d", sMyName, peekByteVal()		);

	log.ifExtreme( "%s : c peek after 255 : %c", sMyName, printableElseBlank()	);




	iFocus = chopByteVal();
	if (	( iFocus & 0xFF ) == 10	) {
		log.ifExtreme( "%s : 0x0A delim found, as expected", sMyName );
	}/////
	else {
		log.ifExtreme( "%s : streaming decomp failure : 10 expected", sMyName );
		System.exit( 99 );
	}/////////////////////

	log.ifExtreme( "     status report near exit (%s)", sMyName );
	statusReport();

	bWeAreReadyForTheNextRead = true;

}


public
String
chopString( int iInLength ) {
/////////////////////////////
	String	sMyName = this.getClass().getName() + ".chopString()";

	char[]	caField	= new char[ iInLength ];

	for (	int src=iFocusNdx, dst=0; dst < iInLength; src++, dst++ ) {
		caField[ dst ]	= peekChar( src );
	}////

	iRemovalBytes	+= iInLength;
	iFocusNdx		+= iInLength;

	return String.copyValueOf( caField );
}


public
void
chopAndCopyChars( int iLengthIn, char[] caIn ) {
////////////////////////////////////////////////
	String	sMyName = this.getClass().getName() + ".chopChars()";

	int	iSrcMax			= llMain.size();
	int	iDstMax			= caIn.length;

	for ( int i = 0; i < caIn.length; i++)
		caIn[ i ]	= ' ';

	if ( iLengthIn < iDstMax )
		iDstMax = iLengthIn;

	for (	int iSrc=iFocusNdx, iDst=0;
			iDst < iDstMax && iSrc < iSrcMax;
			iSrc++, iDst++
		) {
		caIn[ iDst ]	= peekChar( iSrc );
	}////

	iRemovalBytes	+= iLengthIn;
	iFocusNdx		+= iLengthIn;

	return;
}


public
int
chopInt() {
/////////////
	String	sMyName = this.getClass().getName() + ".chopInt()";

	int		iBinFieldSize	= 4;	//	size, in bytes, of this binary type
	byte[]	baField	= new byte[ iBinFieldSize ];

	for (	int src=iFocusNdx, dst=0; dst < iBinFieldSize; src++, dst++ ) {
		baField[ dst ]	= peekByte( src );
	}

	int iResult =
			ByteBuffer
				.wrap(	baField )
				.order(	ByteOrder.BIG_ENDIAN )
				.getInt();

	log.ifExtreme( "%s : iResult : %d", sMyName, iResult	);

	iRemovalBytes	+= iBinFieldSize;
	iFocusNdx		+= iBinFieldSize;

	return iResult;
}


public
short
chopShort() {
//////////////
	String	sMyName = this.getClass().getName() + ".chopShort()";

	int		iBinFieldSize	= 2;	//	size, in bytes, of this binary type
	byte[]	baField	= new byte[ iBinFieldSize ];

	for (	int src=iFocusNdx, dst=0; dst < iBinFieldSize; src++, dst++ ) {
		baField[ dst ]	= peekByte( src );
	}////

	short shResult =
			ByteBuffer
				.wrap(	baField )
				.order(	ByteOrder.BIG_ENDIAN )
				.getShort();

	log.ifExtreme( "%s : shResult : %d", sMyName, shResult	);

	iRemovalBytes	+= iBinFieldSize;
	iFocusNdx		+= iBinFieldSize;

	return shResult;
}




public
long
chopLong() {
/////////////
	String	sMyName = this.getClass().getName() + ".chopLongt()";

	int		iBinFieldSize	= 8;	//	size, in bytes, of this binary type
	byte[]	baField	= new byte[ iBinFieldSize ];

	for (	int src=iFocusNdx, dst=0; dst < iBinFieldSize; src++, dst++ ) {
		baField[ dst ]	= peekByte( src );
	}////

	long lResult =
			ByteBuffer
				.wrap(	baField )
				.order(	ByteOrder.BIG_ENDIAN )
				.getLong();

	log.ifExtreme( "%s : lResult : %d", sMyName, lResult	);

	iRemovalBytes	+= iBinFieldSize;
	iFocusNdx		+= iBinFieldSize;

	return lResult;
}








public
float
chopFloat() {
///////////////
	String	sMyName = this.getClass().getName() + ".chopFloat()";

	int		iBinFieldSize	= 4;	//	size, in bytes, of this binary type
	byte[]	baField	= new byte[ iBinFieldSize ];

	for (	int src=iFocusNdx, dst=0; dst < iBinFieldSize; src++, dst++ ) {
		baField[ dst ]	= peekByte( src );
	}////

	float fResult =
			ByteBuffer
				.wrap(	baField )
				.order(	ByteOrder.BIG_ENDIAN )
				.getFloat();

	log.ifExtreme( "%s : fResult : %f", sMyName, fResult	);

	iRemovalBytes	+= iBinFieldSize;
	iFocusNdx		+= iBinFieldSize;

	return fResult;
}








public
int
chopByteVal() {
///////////////
	String	sMyName = this.getClass().getName() + ".chopByteVal()";

	int		iResult;

	iResult = peekByteVal();

	log.ifExtreme( "%s : iResult : %d", sMyName, iResult	);

	iRemovalBytes	+= 1;
	iFocusNdx		+= 1;

	return iResult;
}





public
int
peekByteVal() {
///////////////
	String	sMyName = this.getClass().getName() + ".peekByteVal()";

	byte	b;
	int		iResult;

	b = peekByte( iFocusNdx );

	iResult = (int) b &0xFF;

	log.ifExtreme( "%s : iResult : %d", sMyName, iResult	);

	return iResult;
}




public
void
payloadReview() {
//////////////////
	String	sMyName = this.getClass().getName() + ".payloadReview()";

	if ( iExtractedLength == 0 ) {
		log.ifExtreme( "%s : no payload to process", sMyName );
		System.exit( 88 );
	}/////////////////////


	if (	(char) baExtractedPayload[ 0 ]	!= 'S'  )  {
		log.ifExtreme( "%s : payload breaks the rules", sMyName );
		System.exit( 89 );
	}/////////////////////
}




public
void
snapshotReview() {
//////////////////
	String	sMyName = this.getClass().getName() + ".snapshotReview()";

	iFocusNdx = 0;

	//
	//	we have an "N" record in the "consideration window"
	//
	//		*** improve this code later, in case better "window"
	//		***		management is required!
	//

	if ( llMain.size() < 18 ) {
		return;
	}//////////

	chopByteVal();		//	throw-away the Header-ID


	//	extract the SID string
	//	========================================

	String sSID = chopString( chopShort() );

	log.ifExtreme( "%s : sSID : %s", sMyName, sSID	);





	//	extract the payload length
	//	==========================

	int	iPayloadLength = chopInt();

	log.ifExtreme( "%s : iPayloadLength : %d", sMyName, iPayloadLength	);



	//	let's consider the payload itself, at some point
	//	================================================























	//	verify that we are really ast the end of the snapshot header
	//	============================================================


	iFocus = (int) peekByte( iFocusNdx );
	if (	( iFocus & 0xFF ) == 255	) {
		log.ifExtreme( "%s : 0xFF delim found, as expected", sMyName );
		iRemovalBytes	+= 1;
		iFocusNdx		+= 1;
	}////////////////////////
	else {
		log.ifExtreme( "%s : streaming decomp failure", sMyName );
		System.exit( 0 );
	}//else


	iFocus = (int) peekByte( iFocusNdx );
	if (	( iFocus & 0xFF ) == 10	) {
		log.ifExtreme( "%s : 0x0A delim found, as expected", sMyName );
		iRemovalBytes	+= 1;
		iFocusNdx		+= 1;
	}////////////////////////
	else {
		log.ifExtreme( "%s : streaming decomp failure", sMyName );
		System.exit( 0 );
	}////////////////////

	log.ifExtreme( "     status report near exit point (%s)", sMyName );
	statusReport();

	removeBytesFromMain();
}






public
void
heartbeatReview() {
///////////////////
	String	sMyName = this.getClass().getName() + ".heartbeatReview()";

	iFocusNdx		+= 2;
	iRemovalBytes	+= 2;

	long lEpochMsecs = chopLong();

	log.ifExtreme( "%s : lEpochMsecs : %d", sMyName, lEpochMsecs	);

	//creating Date from millisecond
	Date currentDate = new Date( lEpochMsecs );

	//printing value of Date
	System.out.println("current Date: " + currentDate);

	DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");

	//formatted value of current Date
	log.write( "HT @ " + df.format(currentDate) );

	removeBytesFromMain();
	statusReport();
}




public
void
statusReport() {
////////////////
	String sMyName =	this.getClass().getName() + ".statusReport()";

	if ( log == null )	{
		System.out.println( sMyName + " : log file is not open" );
	}/////////////////////////////////////////////////////////////

	log.ifExtreme( "     %s",					sMyName );

	log.ifExtreme( "     last read size = %d",	iBytesRead		);
	log.ifExtreme( "     iFocusNdx      = %d",	iFocusNdx		);
	log.ifExtreme( "     iRemovalBytes  = %d",	iRemovalBytes	);
	log.ifExtreme( "     llMain.size()  = %d",	llMain.size()	);

}




public
void
setLogFile( lLog logIn ) {
//////////////////////////
	log = logIn;
}




public
void
setInputStream( InputStream isIn ) {
////////////////////////////////////
	disMain = new DataInputStream( isIn );
}




public
boolean
readNextRecord() {
//////////////////
	String	sMyName = this.getClass().getName() + ".readNextRecord()";

	try {
		iBytesRead = disMain.read( baReadBuf );
	}
	catch (IOException e) {
		log.write( "%s : read error : %d, bailing ...\n", sMyName, iBytesRead	);
		System.out.println( "read error : " + e );
		System.exit( 99 );
	}
	log.ifExtreme( "%s : streaming bytes read : %d", sMyName, iBytesRead	);

	return true;
}



public
void
start() {
/////////
	String	sMyName = this.getClass().getName() + ".start()";

	//	require our log file
	if ( log == null ) {
		System.out.println( sMyName + " : log file is not active, but is required" );
		System.exit( 87 );
	}/////////////////////

	//	document our thread ID
	long lMyThread = Thread.currentThread().getId();
	log.write( "%s : thread ID at entry : %d", sMyName, lMyThread );

	//	read data from the provider
	readNextRecord();

	while ( iBytesRead > 0 ) {

		for (ndx=0; ndx < iBytesRead; ndx++ ) {
			byte b =	(byte) baReadBuf[ndx];
			llMain.add( b );
		}///////////////////

		while ( llMain.size() > 9 ) {
			log.ifExtreme( "%s : top of start() loop", sMyName );
			statusReport();
			examineActive();
			removeBytesFromMain();
			log.ifExtreme( "%s : bottom of start() loop", sMyName );
			statusReport();
		}/////////////////////////

		//	setChanged();
		//	notifyObservers();

		readNextRecord();
	}////

	if ( iBytesRead == -1 ) {
		log.ifExtreme( "%s : foul ball! (ibr = %d)", sMyName, iBytesRead	);
		System.exit( 85 );
	}/////////////////////


}//method



}//class

*/
