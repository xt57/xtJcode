//
//
//

package net.xt57;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

///////////////////////////////////////////////////////////////////
public
class
LDate extends Object {
///////////////////////////////////////////////////////////////////

	boolean		bAllSystemsAreGo	= false;

	boolean		bWeHaveALogFile		= false;
	LLog		log					= null;

	Date		dUtil				= null;

	Calendar	calMain				= null;

	LDate() {
	/////////
		calMain = Calendar.getInstance();
	}

	LDate( LLog logIn ) {
	/////////////////////
		log = logIn;	// is this okay?
		calMain = Calendar.getInstance();
	}



public
boolean
setToMidnight() {
///////////////////////////////////
	String	sMyName = this.getClass().getName() + ".setToMidnight()";

	Calendar calTmp = Calendar.getInstance();

// This gets you today's date at midnight

	calMain.set( Calendar.YEAR,			calTmp.YEAR				);
	calMain.set( Calendar.MONTH,		calTmp.MONTH			);
	calMain.set( Calendar.DAY_OF_MONTH,	calTmp.DAY_OF_MONTH		);

	calMain.set( Calendar.HOUR_OF_DAY,	0						);
	calMain.set( Calendar.MINUTE,		0						);
	calMain.set( Calendar.SECOND,		0						);

	calMain.add( Calendar.MILLISECOND,	0						);

	return true;
}













public
boolean
setFromMidnight( long lMillisIn ) {
///////////////////////////////////
	String	sMyName = this.getClass().getName() + ".setFromMidnight()";

	if ( bWeHaveALogFile )
		log.ifHigh( "%s : incoming MSecs = %d", sMyName, lMillisIn );

	Calendar calTmp = Calendar.getInstance();

// This gets you today's date at midnight

	calMain.set( Calendar.YEAR,			calTmp.YEAR				);
	calMain.set( Calendar.MONTH,		calTmp.MONTH			);
	calMain.set( Calendar.DAY_OF_MONTH,	calTmp.DAY_OF_MONTH		);

	calMain.set( Calendar.HOUR_OF_DAY,	0				);
	calMain.set( Calendar.MINUTE,		0				);
	calMain.set( Calendar.SECOND,		0				);

	// Next we add the number of milliseconds since midnight
	calMain.add( Calendar.MILLISECOND,	(int) lMillisIn);

	return true;
}








public
boolean
setFromEpoch( long lMillisIn ) {
////////////////////////////////
	String	sMyName = this.getClass().getName() + ".setFromEpoch()";

	if ( bWeHaveALogFile )
		log.ifExtreme( "%s : incoming Millis = %d", sMyName, lMillisIn );

	// set time to milliseconds since epoch
	calMain.setTimeInMillis(	lMillisIn	);

	return true;
}



public
String
get( Calendar calIn ) {
///////////////////////
	String	sMyName = this.getClass().getName() + ".get()";

    SimpleDateFormat ft =
        new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss.SSS");

	return ft.format( calIn.getTime()	);
}


public
String
getDisplay() {
//////////////
	String	sMyName = this.getClass().getName() + ".getDisplay()";

    SimpleDateFormat ft =
        new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss.SSS");

	return ft.format( calMain.getTime()	);
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
	String	sMyName = this.getClass().getName() + ".get()";

	log.ifHigh( "%s : incoming MSecs = %d", sMyName, lMSecsIn );

	// This gets you today's date at midnight

	calMain = Calendar.getInstance();

	calMain.set( Calendar.HOUR_OF_DAY,	0);
	calMain.set( Calendar.MINUTE,		0);
	calMain.set( Calendar.SECOND,		0);

	// Next we add the number of milliseconds since midnight
	calMain.set( Calendar.MILLISECOND,	(int) lMSecsIn);

	Date dReturn = calMain.getTime();

	return dReturn;
}


public
int
getCurrentSecondsSinceMidnight() {
//////////////////////////////////


	Calendar	cMidnight = getNow();
				cMidnight.set( Calendar.HOUR_OF_DAY,	0	);
				cMidnight.set( Calendar.MINUTE,			0	);
				cMidnight.set( Calendar.SECOND,			0	);
				cMidnight.set( Calendar.MILLISECOND,	0	);

	Calendar	cNow = getNow();

	long		lNowMillis		= cNow.getTimeInMillis();
	long		lMidnightMillis	= cMidnight.getTimeInMillis();

	return  (int) (		( lNowMillis - lMidnightMillis )   /   1000 );
}




public
int
getCurrentSeconds() {
/////////////////////
	return getNow().get( Calendar.SECOND );
}

public
int
getCurrentHour() {
//////////////////
	return getNow().getInstance().get( Calendar.HOUR );
}

public
int
getCurrentDay() {
/////////////////
	return getNow().getInstance().get( Calendar.DAY_OF_MONTH );
}

public
int
getCurrentMonth() {
///////////////////
	return getNow().getInstance().get( Calendar.MONTH );
}

public
int
getCurrentYear() {
//////////////////
	return getNow().getInstance().get( Calendar.YEAR );
}

public
Calendar
getNow() {
//////////
	return Calendar.getInstance();
}

public
void
sleep( int iSecondsIn ) {
/////////////////////////
	//	log.ifHigh( "about to sleep %d seconds - t=%d",
	//				iSecondsIn, Thread.currentThread().getId()
	//			);
	try {
		Thread.sleep( iSecondsIn * 1000 );
	}////
	catch (Exception e) {
		System.out.println( "sleep error : " + e );
	}////
}


public
void
sleep( int iSecondsIn, char cQuiet ) {
//////////////////////////////////////

	if ( cQuiet != 'q' ) {
		log.ifHigh( "about to sleep %d seconds - t=%d",
					iSecondsIn, Thread.currentThread().getId()
					);
	}/////

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
////////////////
    return (long) Thread.currentThread().getId();
}








}	//	end of class
