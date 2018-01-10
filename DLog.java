//
//
//

package net.xt57;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

//  import java.math.BigDecimal;
//  import java.sql.Connection;
//  import java.sql.ResultSet;
//  import java.sql.SQLException;
//  import java.sql.Statement;
//  import java.util.Observable;
//  import java.util.Observer;
//  import java.util.concurrent.CompletableFuture;
//  import java.util.concurrent.Executor;
//  import java.util.concurrent.Executors;


public
///////////////////////////////////////////////////////////////////////
class
DLog extends Object {
///////////////////////////////////////////////////////////////////////

    String          sLogPath        = "/tmp/xt57.log";
    String          sCharSet        = "utf-8";
    PrintWriter     pwLog           = null;
    boolean         bIsOpen         = false;
    int             iMasterLevel    = 1;

	LinkedList<String>	llQueue		= new LinkedList<>();

	boolean			bCachedMode		= false;

DLog() throws Exception {
/////////////////////////

    try {
        open( sLogPath );
    }
    catch (IOException e) {
        System.out.println( "log file open error" + e );
    }

}

DLog( String sInPath ) throws Exception {
/////////////////////////////////////////

	sLogPath = sInPath;
    try {
        open( sLogPath );
    }
    catch (IOException e) {
        System.out.println( "log file open error (in constr) " + e );
    }

}

public
void
open() throws Exception {
/////////////////////////

    try {
        open( sLogPath );
    }
    catch (IOException e) {
        System.out.println( "log write error" + e );
    }

}

public
void
open( String sInPath ) throws Exception {
/////////////////////////////////////////

    if ( bIsOpen )
        close();

    try {
        bIsOpen		= false;
		sLogPath	= sInPath;
        pwLog =
            new PrintWriter
                ( new BufferedWriter
                    ( new FileWriter( sLogPath, true )
                    )
                );
        bIsOpen = true;
    }
    catch (IOException e) {
        System.out.println( "log open error" + e );
    }

}

public
void
openNewPath( String sInPath ) throws Exception {
////////////////////////////////////////////////

    try {
        close();
        open( sInPath );
    }
    catch (IOException e) {
        System.out.println( "log write error" + e );
    }
}

public
void
close() {
/////////

    if ( bIsOpen ) {
        pwLog.flush();
        pwLog.close();
    }
    bIsOpen = false;
}

public
String
getStandardPreamble() {
///////////////////////
	Calendar cCal	= Calendar.getInstance();

    SimpleDateFormat ft =
        new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss.SSS");

	String pString = ft.format( cCal.getTime()	);

    pString += " : ";

	return pString;
}


public
boolean
terminateLine() {
/////////////////
	return writeAddToRawEnd( System.lineSeparator()	);
}


public
boolean
write( String sInFormat, Object... oInArgs ) {
//////////////////////////////////////////////
    return write( String.format( sInFormat, oInArgs )	);
}

public
boolean
write( String sMsgIn ) {
////////////////////////
	writeLeaveRawEnd( sMsgIn );
	terminateLine();
	return true;
}

public
boolean
writeLeaveRawEnd( String sInFormat, Object... oInArgs ) {
/////////////////////////////////////////////////////////
    return writeLeaveRawEnd( String.format( sInFormat, oInArgs )	);
}

public
boolean
writeLeaveRawEnd( String sMsgIn ) {
///////////////////////////////////
    String pString = getStandardPreamble() + sMsgIn;

	if ( bCachedMode ) {
		llQueue.add( pString );
	}////
	else {
	    pwLog.print( pString );
		pwLog.flush();
	}////

	return true;
}

public
boolean
writeAddToRawEnd( String sInFormat, Object... oInArgs ) {
/////////////////////////////////////////////////////////
    return writeAddToRawEnd( String.format( sInFormat, oInArgs )	);
}

public
boolean
writeAddToRawEnd( String sMsgIn ) {
///////////////////////////////////
    pwLog.print( sMsgIn );
	pwLog.flush();
	return true;
}////

public
boolean
flushTheQueue() {
/////////////////
	while ( llQueue.size() > 0 ) {
		String	pString	= llQueue.poll();
		if ( pString == null ) {
			return false;
		}////
		pwLog.println(	pString	 );
		pwLog.flush();
	}////
	return true;
}

public
void
setCachedMode( boolean bModeIn ) {
//////////////////////////////////
    bCachedMode = bModeIn;
}



public
void
setLoggingLevel(int iInLevel) {
///////////////////////////////
    if (iInLevel > 0 && iInLevel < 101)
        iMasterLevel = iInLevel;
}


public
int
getLoggingLevel() {
///////////////////
    return iMasterLevel;
}



public
boolean
loggingLevelIsModerate() {
//////////////////////////
    if ( iMasterLevel > 30 )
        return true;
    else
        return false;
}


public
boolean
loggingLevelIsHigh() {
//////////////////////////
    if ( iMasterLevel > 50 )
        return true;
    else
        return false;
}


public
boolean
loggingLevelIsVeryHigh() {
//////////////////////////
    if ( iMasterLevel > 75 )
        return true;
    else
        return false;
}


public
boolean
loggingLevelIsExtreme() {
/////////////////////////
    if ( iMasterLevel > 96 )
        return true;
    else
        return false;
}





public
boolean
ifVeryHigh( String sInFormat, Object... oInArgs )  {
////////////////////////////////////////////////////
    if ( loggingLevelIsVeryHigh()  )	{
        return write( String.format( sInFormat, oInArgs )	);
		//	return write( sInFormat, oInArgs );
	}//////////////////////////////////////
    else
        return false;
}




public
boolean
ifHigh( String sInFormat, Object... oInArgs )  {
////////////////////////////////////////////////
    if ( loggingLevelIsHigh()  )	{
        return write( String.format( sInFormat, oInArgs )	);
		//	return write( sInFormat, oInArgs );
	}//////////////////////////////////////
    else
        return false;
}





public
boolean
ifModerate( String sInFormat, Object... oInArgs )  {
////////////////////////////////////////////////////
    if ( loggingLevelIsModerate()  )	{
        return write( String.format( sInFormat, oInArgs )	);
		//	return write( sInFormat, oInArgs );
	}//////////////////////////////////////
    else
        return false;
}






public
boolean
ifExtreme( String sInFormat, Object... oInArgs )  {
///////////////////////////////////////////////////
    if ( loggingLevelIsExtreme()  )	{
        return write( String.format( sInFormat, oInArgs )	);
		//	return write( sInFormat, oInArgs );
	}//////////////////////////////////////
    else
        return false;
}










public
boolean
postThreadId( String sInFormat, Object... oInArgs )  {
//////////////////////////////////////////////////////
    long tId = Thread.currentThread().getId();

    String  sMsg = String.format( sInFormat, oInArgs ) + tId;

    return write( sMsg );
}





public
boolean
postThreadIdToSysOut( String sInFormat, Object... oInArgs )  {
//////////////////////////////////////////////////////
    long tId = Thread.currentThread().getId();

    String  sMsg = String.format( sInFormat, oInArgs ) + tId;

    System.out.println( sMsg );

    return true;
}







public
boolean
sleepAndLeaveANote( int iMillisIn )  {
//////////////////////////////////////
	write( "napping (%d)", iMillisIn);
	try {
		Thread.sleep(	(long) 1000	);
	} catch (Exception e) {
		;
	}
	return true;
}

//	============================================================================


/*

///////////////////////////////////////////////////////////////////
public
class
invStore extends Observable implements Observer {
///////////////////////////////////////////////////////////////////

	boolean	bSessionInProgress	= false;
	boolean	bThreadedMode		= false;

	int		iBaSize				= 0;

	byte[]	baBuf				= null;

	LinkedList<String> llParsed	= null;

	lSql	db					= null;
	Connection	con				= null;


	boolean	bWeHaveALogFile		= false;
	lLog	log					= null;

	long	sessionId			= 0;
	long	nextSessionId		= 1;

	lDate	wd40				= new lDate();

	Executor	readPool		= Executors.newCachedThreadPool();

	invStore() {
	////////////////
		;
	}

	invStore( LinkedList<String> llParsedIn ) {
	///////////////////////////////////////////////////////
		llParsed	= llParsedIn;
	}

public
void
setLogFile( String logPathIn ) throws Exception {
/////////////////////////////////////////////////
	String	sMyName = this.getClass().getName() + ".setLogFile()";
    try {
        log = new lLog();
        log.setLoggingLevel( 99 );
        log.openNewPath( logPathIn );
		log.write( "\n\n\n" + "New Session" + "\n\n\n"	);
	}////
    catch (IOException e) {
        System.out.println( sMyName + " : cannot open the log file" );
    }////
}

public
void
setThreadedMode( boolean bModeIn ) {
////////////////////////////////////
	bThreadedMode = bModeIn;
}////

@Override
public
void
update(Observable obj, Object arg) {
////////////////////////////////////
	long t = Thread.currentThread().getId();

	log.ifHigh( "---> updt : store, t=%d", t );


	if ( bSessionInProgress ) {
		log.ifHigh( "---> store : busy (bool test)"	);
		return;
	}////

	long iMySessionId = requestSession();
	if ( iMySessionId < 1 ) {
		log.ifHigh( "---> store : busy (method-based rejection)"	);
		return;
	}////


	if ( true ) {
		;
		//	runSessionThreaded( iMySessionId );
		//	return;
	}////



	if (	llParsed.size() > 7	&&	bThreadedMode	) {
		runSessionThreaded( iMySessionId );
	}////
	else {
		runSessionInThisThread( iMySessionId );
	}////
}////

public
void
runSessionInThisThread( long sessionIdIn ) {
////////////////////////////////////////////
	try {
		runSession(		sessionIdIn );
		releaseSession(	sessionIdIn );
	}////
	catch (Exception e) {
		return;
	}////
}////

public
void
runSessionThreaded( long sessionIdIn ) {
////////////////////////////////////////
	CompletableFuture<String> cf3
        = CompletableFuture.supplyAsync
        (
            ( ) -> {
				////
				long tId = Thread.currentThread().getId();
				log.ifHigh( "---> storing : t=%d",	tId	);

				try {
					runSession(		sessionIdIn );
					releaseSession(	sessionIdIn );
					return "203";
				}////
				catch (Exception e) {
					return "253";
				}////
            },///
			readPool
        );///
	/////

}////

public
boolean
bSessionInProgress() {
//////////////////////
	return bSessionInProgress;
}

public
long
requestSession() {
//////////////////

	if ( bSessionInProgress ) {
		log.ifExtreme( "store session request rejected"		);
		return -1;
	}////

	bSessionInProgress = true;

	sessionId = nextSessionId;
	nextSessionId++;

	return sessionId;
}

public
void
releaseSession( long sessionIdIn) {
///////////////////////////////////
	if ( ! bSessionInProgress ) {
		log.ifExtreme( "store session release ignored"		);
		return;
	}////

	sessionId			= 0;
	bSessionInProgress	= false;

	log.ifExtreme( "stor - session released normally"		);
}



public
void
activate() {
////////////
	wd40.sleep( 60 * 60 * 24 * 14 );
}////



public
int
runSession( long sessionIdIn ) throws Exception {
/////////////////////////////////////////////////
	String	sMyName = this.getClass().getName() + ".runSession()";

	if ( sessionId != sessionIdIn ) {
			log.ifExtreme( "stor - session verification rejected"		);
		return -1;
	}////

	//	require our log file
	if ( log == null ) {
		System.out.println( sMyName + " : log file is not active, but is required" );
		System.exit( 87 );
	}/////////////////////

	long t = Thread.currentThread().getId();

	log.ifHigh( "---> stor : psize=%d, t=%d",	llParsed.size(), t	);

	//	access each entry, remove spaces, split into string[] pairs, then dispatch
	while ( llParsed.size() > 0 ) {
		insertThisEntry( llParsed.getFirst().replaceAll( " ", "" ).split( "," )  );
	}////

	log.ifHigh( "---> stor : leaving stor logic" );

	return 0;


	//	this old code ensures that we have only iCeiling - iFactor entries in the list

	//	int	iCeiling	= 37;
	//	int	iFactor		= 7;

	//	if (	llParsed.size() > iCeiling		) {
	//		for ( int x = 0; x < iFactor; x++ )
	//			;	//	llParsed.removeFirst();
	//	}////

}////


public
void
insertThisEntry( String[] saPairs ) throws Exception {
////////////////////////////////////
	String	sMyName = this.getClass().getName() + ".insertThisEntry()";

	long tX = Thread.currentThread().getId();

	log.ifExtreme( "%s : stor - new insert session (q=%d, t=%d)",
					sMyName, llParsed.size(), tX
		);
	/////

	log.ifExtreme( "%s : stor - testing connection for validity", sMyName );

	try {
		con.isValid(  3 );
	}////
	catch ( Exception e ) {
		System.out.println( "stor - connection is not valid inside streaming parse" );
		System.exit( 218 );
	}////


	//	establish jdbc Statement and ResultSet instances

	log.ifExtreme( "%s : stor - ready to init statement instance", sMyName );

	Statement stmt	= null;
	ResultSet rs	= null;

	try {
		stmt = con.createStatement(
				ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE
			);
	}////
	catch ( Exception e ) {
		System.out.println( "stor - cannot init statement instance" );
		System.exit( 188 );
	}////

	log.ifExtreme( "%s : stor - ready to init result set", sMyName );

	try {
		rs = stmt.executeQuery( "SELECT * FROM JustArrived" );
	}////
	catch ( Exception e ) {
		System.out.println( "stor - cannot init result set" );
		System.exit( 188 );
	}////



	// verify "updateability"

	log.ifExtreme( "%s : stor - ready to check updatability", sMyName );

	try {
		if (rs.getConcurrency() == ResultSet.CONCUR_READ_ONLY) {
			System.out.println("stor - ResultSet non-updatable.");
			System.exit( 55 );
		}
		else {
			System.out.println("stor - ResultSet updatable.");
		}
	}////
	catch ( Exception e ) {
		System.out.println( "stor - cannot move to insert row" );
		System.exit( 188 );
	}////



	// move to the logical "insert" row

	log.ifExtreme( "%s : stor - ready to move to the insert row", sMyName );

	try {
		rs.moveToInsertRow();
	}////
	catch ( Exception e ) {
		System.out.println( "stor - cannot move to insert row" );
		System.exit( 188 );
	}////

	for ( int i = 1; i < saPairs.length; i++	) {

		String[] saBreakout = saPairs[i].split( "=" );

		String	sName	= null;
		String	sValue	= null;
		int f = Integer.parseInt( saBreakout[0] );	//	extract the field ID
		int t = 0;									//	prepare to assign field type

		switch ( f ) {

			case 0 : // Symbol
				sName = "Ticker";	sValue = saBreakout[1];		t = 0;	// string
				break;

			case 1:	// Birth Time, vendor-supplied seconds past midnight
				sName = "VendSecs";	sValue = saBreakout[1];		t = 1;	// int
				break;

			case 2:	// Price
				sName = "Price";	sValue = saBreakout[1];		t = 3;	// float
				break;

			case 3:	// Volume
				sName = "Volume";	sValue = saBreakout[1];		t = 2;	// long
				break;

			case 4:	// Sequence
				sName = "Seq";		sValue = saBreakout[1];		t = 1;	// int
				break;

			case 5:	// Updated
				sName = "Updated";	sValue = saBreakout[1];		t = 1;	// int
				break;

			case 97:	// read-time seconds past midnight
				sName = "RecvSecs";	sValue = saBreakout[1];		t = 1;	// int
				break;

			case 99:	// read-time seconds past midnight
				sName = "DiffSecs";	sValue = saBreakout[1];		t = 1;	// int
				break;

			default:
				log.ifExtreme( "%s : unknown column number : %d", sMyName, i	);
				System.exit( 33 );
				break;
			//////////

		}//switch





		switch ( t ) {

			case 0 : // string
				try {
					rs.updateString( sName, sValue	);
				}////
				catch ( Exception e ) {
					System.out.println( "cannot set + sName" );
					System.exit( 55 );
				}////
				break;

			case 1:	// int
				try {
					rs.updateInt( sName, Integer.parseInt( sValue )		);
				}////
				catch ( Exception e ) {
					System.out.println( "cannot set + sName" );
					System.exit( 55 );
				}////
				break;

			case 2:	// long (notice that we are using int)
				try {
					rs.updateInt( sName, Integer.parseInt( sValue )		);
				}////
				catch ( Exception e ) {
					System.out.println( "cannot set + sName" );
					System.exit( 55 );
				}////
				break;

			case 3:	// BigDecimal - float
				try {
					BigDecimal bd = new BigDecimal( sValue );
					rs.updateBigDecimal( sName, bd		);
				}////
				catch ( Exception e ) {
					System.out.println( "cannot set + sName" );
					System.exit( 55 );
				}////
				break;

			default:
				log.ifExtreme( "%s : unknown update field type : %d", sMyName, t	);
				System.exit( 33 );
				break;
			//////////

		}//switch

	}//for



	try {
		rs.insertRow();
	}////
	catch(SQLException se) {
		se.printStackTrace();
		System.out.println( "stor - cannot insert row" );
		System.exit( 57 );
	}////

	llParsed.remove();

	setChanged();
	notifyObservers(	(Integer) 5		);	// let observers know we have added

	log.ifExtreme( "%s : stor - insert completed (%d)", sMyName, llParsed.size()  );
}


public
void
setLogFile( lLog logIn ) {
//////////////////////////
	log = logIn;
}

public
void
setList(  LinkedList<String> llParsedIn  ) {
////////////////////////////////////////////
	llParsed	= llParsedIn;
}



public
void
setDb(  lSql dbIn  ) {
//////////////////////
	String	sMyName = this.getClass().getName() + ".setDb()";

	db		=	dbIn;

	try {
		db.open();
		con = db.getConnectionCopy();
		con.isValid(  3 );
	}////
	catch ( Exception e ) {
		log.write( "%s : db error at open()", sMyName );
		System.exit( 88 );
	}////

}////




*/




}
// ======================================================================
// end of class
// ======================================================================
