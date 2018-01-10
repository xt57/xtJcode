/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//	package com.xt57;

import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////////////////
public
class
invFeed  extends Observable {
///////////////////////////////////////////////////////////////////

	int		iBaReadBufSize		= 16384;

	boolean	bAtEOF				= false;
	boolean	bGenMode			= false;

	DataInputStream streamMain	= null;

	byte[]	baReadBuf			= new byte[ iBaReadBufSize ];

	int		iBytesRead			= 0;

	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;

	lDate	wd40				= new lDate();

	LinkedList<Byte> llFeed		= null;

	invFeed() {
	////////////////
		;
	}

	invFeed( LLog logIn ) {
	////////////////////////////
		log = logIn;	// is this okay?
	}

	invFeed( LinkedList<Byte> llIn, LLog logIn ) {
	////////////////////////////////////////////////////////////////////////
		llFeed		= llIn;
		log			= logIn;
	}

public
void
statusReport() {
////////////////
	String sMyName =	this.getClass().getName() + ".statusReport()";

	if ( log == null )	{
		System.out.println( sMyName + " : log file is not open" );
	}////

	log.ifExtreme( "     %s",					sMyName );

	log.ifExtreme( "     last read size = %d",	iBytesRead		);
}


public
void
setGenMode( boolean bModeIn ) {
///////////////////////////////
	bGenMode = bModeIn;
}




public
void
setLogFile( LLog logIn ) {
//////////////////////////
	log = logIn;
}

public
boolean
bWeHaveALogFile() {
///////////////////
	return true;
}

public
void
setInputStream( InputStream streamIn ) {
////////////////////////////////////
	streamMain = new DataInputStream( streamIn );
}

public
long
getThreadId()  {
//////////////////////////////////////////////////////
    return (long) Thread.currentThread().getId();
}

public
boolean
readNextRecord() {
//////////////////

	if ( bGenMode ) {
		byte[] baTmp = gen();
		iBytesRead = baTmp.length;
		baReadBuf = Arrays.copyOf( baTmp, iBytesRead);
		wd40.nap( 200 );
		return true;
	}////

	try {
		iBytesRead = streamMain.read( baReadBuf, 0, iBaReadBufSize );
	}////
	catch (IOException e) {
		log.write( "%s : read error : %d, bailing ...\n", iBytesRead	);
		System.out.println( "read error : " + e );
		System.exit( 99 );
	}////

	//	log.ifExtreme( "streaming bytes read : %d", iBytesRead );

	if ( iBytesRead == -1 ) {
		bAtEOF = true;
	}////

	wd40.nap( 2 );

	return true;
}////

public
boolean
intake() {
//////////
	String	sMyName = this.getClass().getName() + ".run()";

	//	require our log file
	if ( log == null ) {
		System.out.println( sMyName + " : log file is not active, but is required" );
		System.exit( 87 );
	}////

	long t = Thread.currentThread().getId();

	readNextRecord();

	while ( ! bAtEOF ) {

		//	log.ifHigh( "%s : %d read        @ %s", sMyName, iBytesRead, wd40.get() );

		log.ifHigh( "---> read : %4d (%d)", iBytesRead, t );

		if ( iBytesRead > 0 ) {

			for ( int ndx = 0;  ndx < iBytesRead;  ndx++ ) {
				llFeed.add(		(Byte) baReadBuf[ ndx ]	);
			}////

			setChanged();
			notifyObservers(	(Integer) 3		);
		}////

		readNextRecord();
	}////

	log.ifHigh( "datInStream : ibr = " + iBytesRead + "@ exit" );

	if ( iBytesRead == -1 ) {
		log.ifExtreme( "%s : foul ball! (ibr = %d)", sMyName, iBytesRead	);
		return( false );
	}////

	return true;

}////

public
byte[]
gen() {
/////
	final String[] words = { "woof", "meow", "tweet", "ribbet", "moo" };

	Random rGen = new Random();

	int r = rGen.nextInt(5) + 1;

	return words[r-1].getBytes();
}////


//
//	end of class
//
}


