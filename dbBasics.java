//
//
//

//	package com.xt57;

import java.util.*;


///////////////////////////////////////////////////////////////////
public
class
dbBasics extends Observable {
///////////////////////////////////////////////////////////////////

	int		iBaSize				= 0;

	byte[]	baBuf				= null;

	LinkedList<String> llParsed	= null;


	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;



	lDate	wd40				= new lDate();


	dbBasics() {
	////////////////
		;
	}

	dbBasics( LinkedList<String> llParsedIn, LLog logIn ) {
	///////////////////////////////////////////////////////
		llParsed	= llParsedIn;
		log			= logIn;
	}


public
void
nextSession() {
///////////////
	String	sMyName = this.getClass().getName() + ".nextSession()";

	//	require our log file
	if ( log == null ) {
		System.out.println( sMyName + " : log file is not active, but is required" );
		System.exit( 87 );
	}/////////////////////

	long t = Thread.currentThread().getId();

	log.ifHigh( "---> stor : %4d (%d)\n",	llParsed.size(), t	);

}////

public
void
setLogFile( LLog logIn ) {
//////////////////////////
	log = logIn;
}

public
void
setList(  LinkedList<String> llParsedIn  ) {
////////////////////////////////////////////
	llParsed	= llParsedIn;
}


//
//	end of class
//
}