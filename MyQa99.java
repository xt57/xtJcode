//
//
//

//	package com.xt57;


import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;


public
class
MyQa99 implements Observer  {



public static void
main(String [] args) throws IOException	{

	LLog	log		= null;

	try {
        log = new LLog();
        log.setLoggingLevel( 99 );
        log.openNewPath( "/tmp/main.log" );
		log.write( "\n\n\n" + "New Session" + "\n\n\n"	);
    }////
    catch (IOException e) {
        System.out.println( "cannot open the log file" );
    }////

	log.ifHigh( "---> main : t=%d",	Thread.currentThread().getId()	);
	
	//	QaFeed();

}


public void
QaFeed( LLog log)	{

	log.write( "---> QaFeed entry : t=%d",	Thread.currentThread().getId()	);

	lDate 		wd40 			= new lDate( log );

	InvFeed		feed 			= new InvFeed( log);

	boolean		bAutoGenMode	= true;

	feed.setGenMode(	bAutoGenMode );
	if ( ! bAutoGenMode ) {
		feed.addObserver(	this );
	}

	log.write( "---> QaFeed exit  : t=%d",	Thread.currentThread().getId()	);

}


public void
QaTdStreamer()	{

	TdStreamer80			myTest		= new TdStreamer80();

	UtlStringFromTextFile	myConverter	= new UtlStringFromTextFile();


	String sUid = myConverter.convert( "/tmp/uid.txt" );

	String sPwd = myConverter.convert( "/tmp/pwd.txt" );

	if ( sUid.equals( null) ) {
		//	read here
	}

	if ( sPwd.equals( null) ) {
		//	read here
	}


	System.out.println(
			"*** leaving with sUid = <" + sUid + ">," +
							" sPwd = <" + sPwd + "> ***"
		);

	
	myTest.run();
}




}	//class end