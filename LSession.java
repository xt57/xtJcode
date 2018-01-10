//
//
//

//	package net.xt57;

import java.io.IOException;

public
///////////////////////////////////////////////////////////////////////
class
LSession {
///////////////////////////////////////////////////////////////////////

	long	lSessionId		= 0;
	long	lNextSessionId	= 1;

	boolean	bIsLocked		= false;

	LLog	log				= null;

public
boolean
isLocked() {
////////////
	return bIsLocked;
}

public long
requestNewSession() {
/////////////////////
	if ( bIsLocked ) {
		log.ifExtreme( "new session request rejected"		);
		return -1;
	}////

	bIsLocked = true;

	lSessionId = lNextSessionId;
	lNextSessionId++;

	return lSessionId;
}

public void
releaseSession( long sessionIdIn) {
///////////////////////////////////
	if ( ! bIsLocked ) {
		log.ifExtreme( "session release ignored"		);
		return;
	}////

	lSessionId	= 0;
	bIsLocked	= false;
}

public boolean
runSession( long sessionIdIn ) {
////////////////////////////////
	String	sMyName = this.getClass().getName() + ".runSession()";

	if ( lSessionId != sessionIdIn ) {
		log.ifExtreme( "parse - session verification rejected"		);
		return false;
	}

	return true;
}	//////

public void
setLogFile( String logPathIn ) throws Exception
{
	String	sMyName = this.getClass().getName() + ".setlogFile()";
    if ( log != null) {
		log.close();
	}
	try {
        log = new LLog();
        log.setLoggingLevel( 99 );
        log.openNewPath( logPathIn );
		log.write( "\n\n\n" + "New Session" + "\n\n\n"	);
	}
    catch (IOException e) {
        System.out.println( sMyName + " : cannot open the log file" );
    }
}

public long
getSessionId()
{
	return lSessionId;
}	//////





}
// ======================================================================
// end of class
// ======================================================================

