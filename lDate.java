//
//
//

//	package com.xt57;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

///////////////////////////////////////////////////////////////////
public
class
lDate extends Object {
///////////////////////////////////////////////////////////////////

	boolean		bAllSystemsAreGo	= false;

	boolean		bWeHaveALogFile		= false;
	LLog		log					= null;

	Date		dMain				= null;

	Calendar	cMain			= null;

	lDate() {
	/////////////
		;
	}


	lDate( LLog logIn ) {
	/////////////////////////
		log = logIn;	// is this okay?
	}


public
void
setDefaultValues() throws Exception {
/////////////////////////////////////
	try {
		if ( ! bWeHaveALogFile ) {
			log = new LLog( "/tmp/invDataPod.log" );
		}
	}
	catch( Exception e ) {
		;
	}
}

public
String
get( Calendar calIn ) {
//////////////////////
	String	sMyName = this.getClass().getName() + ".get()";

    SimpleDateFormat ft =
        new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss.SSS");

	return ft.format( calIn.getTime()	);
}

public
String
get() {
///////
	return get( getNow() );
}

public
Date
get( long lMSecsIn ) {
//////////////////////
	String	sMyName = this.getClass().getName() + ".set()";

	log.ifHigh( "%s : incoming MSecs = %d", sMyName, lMSecsIn );

	// This gets you today's date at midnight

	cMain = Calendar.getInstance();

	cMain.set( Calendar.HOUR_OF_DAY,	0);
	cMain.set( Calendar.MINUTE,			0);
	cMain.set( Calendar.SECOND,			0);

	// Next we add the number of milliseconds since midnight
	cMain.set( Calendar.MILLISECOND,	(int) lMSecsIn);

	Date dReturn = cMain.getTime();

	return dReturn;
}

public
int
getCurrentSeconds() {
////////////////
	return getNow().get( Calendar.SECOND );
}

public
int
getCurrentHour() {
////////////////
	return getNow().getInstance().get( Calendar.HOUR );
}

public
int
getCurrentDay() {
////////////////
	return getNow().getInstance().get( Calendar.DAY_OF_MONTH );
}

public
int
getCurrentMonth() {
////////////////
	return getNow().getInstance().get( Calendar.MONTH );
}

public
int
getCurrentYear() {
////////////////
	return getNow().getInstance().get( Calendar.YEAR );
}

public
Calendar
getNow() {
////////////////
	return Calendar.getInstance();
}

public
void
sleep( int iSecondsIn ) {
/////////////////////////
	log.ifHigh( "about to sleep %d seconds", iSecondsIn );
	try {
		Thread.sleep( iSecondsIn * 1000 );
	}////
	catch (Exception e) {
		System.out.println( "sleep error : " + e );
	}////
}

public
void
nap( int iMillis ) {
////////////////////
	try {
		Thread.sleep( iMillis );
	}////
	catch (Exception e) {
		System.out.println( "sleep error : " + e );
	}////
}




public
long
getThreadId()  {
//////////////////////////////////////////////////////
    return (long) Thread.currentThread().getId();
}








}	//	end of class
