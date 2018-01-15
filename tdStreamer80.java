//
//	TdStreamer
//

//
//	The thread logic herein :
//
//		cf1) gets feed, cf2) parses queue, 3) db-stores the data
//


//	package com.xt57;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;

public
class
TdStreamer80 implements Observer
{
	boolean	bAutoGenMode			= false;

	static  Executor	readPool    = Executors.newCachedThreadPool();
    static  LLog		log;

	LinkedList<Byte>	llFeed		= new LinkedList<>();
	LinkedList<String>	llParsed	= new LinkedList<>();

	static	String		sTdSession	= null;

	static	InvAdmin	vendor;

	static	InvFeed		feed;

	static	InvParser	parse;

	static	dbBasics	store;

	boolean	bParseInProgress		= false;
	boolean	bStoreInProgress	= false;

	static	lDate		wd40		= null;


// method definitions begin here
public
void
run() throws Exception
{
    try {
        log = new LLog();
        log.setLoggingLevel( 99 );
        log.openNewPath( "/tmp/cf.log" );
		log.write( "\n\n\n" + "New Session" + "\n\n\n"	);
    }////
    catch (IOException e) {
        System.out.println( "cannot open the log file" );
    }////

	log.ifHigh( "---> main : t=%d",	Thread.currentThread().getId()	);

	wd40 = new lDate( log );

	feed = new InvFeed( log) ;
	feed.setGenMode(	bAutoGenMode );
	if ( ! bAutoGenMode ) {
		feed.addObserver(	this );
	}


	parse = new InvParser(	log );
	//	parse.setLists(			llFeed, llParsed);
	if ( bAutoGenMode ) {
		wd40.sleep( 45 * 60 );
		log.ifHigh( "main() : natural exit, after sleep" );
		System.exit( 3 );
	}////

	parse.addObserver(	this );


	store = new dbBasics(	llParsed, log );
	store.addObserver(		this );

	//	cf (async-based) acceptance of alert-delivered streamed feed reads
	CompletableFuture<String> cf1
        = CompletableFuture.supplyAsync
        (
            ( ) -> {
				////
				long t = Thread.currentThread().getId();
				log.ifHigh( "---> cf1 : t=%d",	t	);
				try {
					vendor	= new InvAdmin( log );
					vendor.handshake();
					feed.setInputStream( vendor.tdStreamingTest()	);
					feed.intake();
					return "177";
				}
				catch (Exception e) {
					return "255";
				}
				/////
            },///
			readPool
        );///
	/////

	wd40.sleep( 45 * 60 );

	log.ifHigh( "main() : natural exit, after sleep" );

	System.exit( 3 );

	//
	//	===================================================================
	//

    try {
		String	cf1Result = cf1.get();
        System.out.println( "main() result : " + cf1Result );
	}////
    catch (Exception e) {
        System.out.println( "main() exception in cf1.get() call : " + e );
		System.exit( 0 );
    }////

	System.exit( 0 );
}////


@Override
public
void
update(Observable obj, Object arg)
{
	long t = Thread.currentThread().getId();

	int i = (Integer) arg;

	wd40.nap( 1 );

	if ( i == 3 ) {
		log.ifHigh( "---> updt : rsig (%d)", t );
		type3();
	}////

	if ( i == 5 ) {
		log.ifHigh( "---> updt : psig (%d)", t );
		type5();
	}////

}////

public
void
type3() {

	if ( bParseInProgress ) {
		log.ifHigh( "---> typ3 : busy\n"	);
		return;
	}
	
	bParseInProgress = true;

	CompletableFuture<String> cf3
        = CompletableFuture.supplyAsync
        (
            ( ) -> {
				////
				long t = Thread.currentThread().getId();
				try {
					log.ifHigh( "---> cf3x :      (%d)",	t	);
					parse.nextSession();
					return "203";
				}////
				catch (Exception e) {
					return "253";
				}////
            },///
			readPool
        );///
	/////

	bParseInProgress = false;

    try {
		;
		//	String	cf3Result = cf3.get();
        //	System.out.println( "main() result : " + cf3Result );
	}////
    catch (Exception e) {
        System.out.println( "main() exception in cf3.get() call : " + e );
		System.exit( 0 );
    }////
}////

public
void
type5() {

	if ( bStoreInProgress ) {
		log.ifHigh( "---> typ5 : busy\n"	);
		return;
	}////

	bStoreInProgress = true;

	CompletableFuture<String> cf5
        = CompletableFuture.supplyAsync
        (
            ( ) -> {
				////
				long t = Thread.currentThread().getId();
				log.ifHigh( "---> cf5x :      (%d)",	t	);
				try {
					store.nextSession();
					bStoreInProgress = false;
					return "205";
				}
				catch (Exception e) {
					return "255";
				}
				/////
            },///
			readPool
        );///
	/////

	bStoreInProgress = false;

	try {
		;
		//	String	cf5Result = cf5.get();
        //	System.out.println( "main() result : " + cf5Result );
	}////
    catch (Exception e) {
        System.out.println( "main() exception in cf5.get() call : " + e );
		System.exit( 0 );
    }////
}////


public
long
getThreadId()  {

    return (long) Thread.currentThread().getId();
}




public
boolean
cfLogSetup( LLog logIn ) throws IOException, Exception {

	System.out.println( "opening the cf-internal log file" );
	try {
		logIn.setLoggingLevel( 99 );
		logIn.openNewPath( "/tmp/cf.log" );
	}
	catch (IOException e) {
		System.out.println( "cannot open the cf-internal log file" );
		System.exit( 9 );
	}////////////////////

	logIn.write( "\n\n\n" + "New Session" + "\n\n\n"	);
	return true;
}////


}
// ======================================================================
// end of class
// ======================================================================

