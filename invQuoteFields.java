//
//
//

//	package com.xt57;

import java.text.SimpleDateFormat;
import java.util.Calendar;



// from James G.

/*

"I was trying to chart large transactions"
Not much has changed since Richard D. Wyckoff described the market:
http://stockcharts.com/school/doku.php?id=chart_school:market_analysis:wyckoff_market_analy
http://www.readtheticker.com/Pages/IndLibrary.aspx?65tf=84_richard-wyckoff-method

Accumulation and Distribution is seen on every chart.
The code to find it is my gift to you:
if(abs(RSI[i-2]-RSI[i-1])<2 && abs(RSI[i-1]-RSI[i])<2) IsConsolidation=true;

As for creating a meaningful Volume Alert, that's easy too:
if(IsNewTrend && VolToday>VolYesterday*2) AlertMe(this.Symbol);

All of this can be done streaming L1.
Try it and you'll like it.

Happy Trading

*/

///////////////////////////////////////////////////////////////////
public
class
invQuoteFields extends Object {
///////////////////////////////////////////////////////////////////

	boolean	bAllSystemsAreGo	= false;

	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;

	//	streaming fields
	String		sTicker;
	Calendar	tBirth;
	float		fPrice;
	long		lVolume;
	int			iSeq;
	int			iUpdated;

	float		fHigh;
	float		fLow;
	float		fOpen;
	float		fClose;


	//	remember that all of the "observable" qualities are now active!

	invQuoteFields() {
	/////////////
		tBirth		= Calendar.getInstance();

		fPrice		= 0.0f;
		lVolume		= 0L;
		iSeq		= 0;
		iUpdated	= 0;

		fHigh		= 0.0f;
		fLow		= 0.0f;
		fOpen		= 0.0f;
		fClose		= 0.0f;
	}

	invQuoteFields( LLog logIn ) {
	/////////////////////////
		log = logIn;	// is this okay?

		tBirth		= Calendar.getInstance();

		fPrice		= 0.0f;
		lVolume		= 0L;
		iSeq		= 0;
		iUpdated	= 0;

		fHigh		= 0.0f;
		fLow		= 0.0f;
		fOpen		= 0.0f;
		fClose		= 0.0f;
	}


public
boolean
bPodIsComplete() {
/////////////////////////////////////

	if ( sTicker == null || sTicker.length() == 0 )
		return false;

	//	if ( fPrice == 0f )
	//		return false;

	//	if ( lVolume == 0L )
	//		return false;

	return true;
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
boolean
setTicker( String sIn ) {
/////////////////////////
	String	sMyName = this.getClass().getName() + ".setTicker()";


	//	this old code is from a time where our ticker was being considered as char[]

	//	"                   ".getChars( 0, caTicker.length, caTicker, 0);

	//	int iLength = sIn.length();
	//	if ( sIn.length() > caTicker.length  )
	//		iLength = caTicker.length;

	//	sIn.getChars( 0, iLength, caTicker, 0);

	//	log.ifExtreme( "%s : new caTicker = %s",
	//					sMyName,
	//					String.copyValueOf( caTicker )
	//					);


	//	sTicker = String.copyValueOf( caTicker );

	sTicker = sIn;

	log.ifExtreme( "%s : new sTicker = %s", sMyName, sTicker );

	return true;
}


/*
public
char[]
getTicker() {
/////////////
	//	String	sMyName = this.getClass().getName() + ".getTicker()";
	return sTicker;
}
*/

public
String
getTicker() {
/////////////
	//	String	sMyName = this.getClass().getName() + ".getTicker()";
	return sTicker;
}


public
boolean
setPrice( float fIn ) {
/////////////////////////
	String	sMyName = this.getClass().getName() + ".setPrice()";

	log.ifExtreme( "%s : new price = %f", sMyName, fIn );
	fPrice = fIn;
	return true;
}

public
float
getPrice() {
////////////
	String	sMyName = this.getClass().getName() + ".getPrice()";

	return fPrice;
}



public
boolean
setHigh( float fIn ) {
/////////////////////////
	String	sMyName = this.getClass().getName() + ".setHigh()";

	log.ifExtreme( "%s : new high = %f", sMyName, fIn );
	fHigh = fIn;
	return true;
}

public
float
getHigh() {
////////////
	String	sMyName = this.getClass().getName() + ".getHigh()";

	return fHigh;
}






public
boolean
setLow( float fIn ) {
/////////////////////////
	String	sMyName = this.getClass().getName() + ".setLow()";

	log.ifExtreme( "%s : new low = %f", sMyName, fIn );
	fLow = fIn;
	return true;
}

public
float
getLow() {
////////////
	String	sMyName = this.getClass().getName() + ".getLow()";

	return fLow;
}











public
boolean
setOpen( float fIn ) {
//////////////////////
	String	sMyName = this.getClass().getName() + ".setOpen()";

	log.ifExtreme( "%s : new open = %f", sMyName, fIn );
	fOpen = fIn;
	return true;
}

public
float
getOpen() {
////////////
	String	sMyName = this.getClass().getName() + ".getOpen()";

	return fOpen;
}


















public
boolean
setClose( float fIn ) {
///////////////////////
	String	sMyName = this.getClass().getName() + ".setClose()";

	log.ifExtreme( "%s : new price = %f", sMyName, fIn );
	fClose = fIn;
	return true;
}

public
float
getClose() {
////////////
	String	sMyName = this.getClass().getName() + ".getClose()";

	return fClose;
}


























public
boolean
setBirth( Calendar CalIn ) {
////////////////////////////
	String	sMyName = this.getClass().getName() + ".setBirth()";

	System.out.println ( "date not yet accepted by setBirth()" );

	return false;
}

public
boolean
setBirth( long lMSecsSinceMidnightIn ) {
////////////////////////////////////////
	String	sMyName = this.getClass().getName() + ".setBirth()";

	log.ifExtreme( "%s : new time (ms) = %d", sMyName, lMSecsSinceMidnightIn  );

	// set each segment to midnight values
	tBirth.set( Calendar.HOUR_OF_DAY,	0);
	tBirth.set( Calendar.MINUTE,		0);
	tBirth.set( Calendar.SECOND,		0);
	tBirth.set( Calendar.MILLISECOND,	0);
	tBirth.add( Calendar.MILLISECOND,	(int) lMSecsSinceMidnightIn );

	SimpleDateFormat ft =
        new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss, SSS");

	log.ifExtreme( "%s : new time = %s", sMyName, ft.format( tBirth.getTime()  )	);

	return true;
}

public
Calendar
getBirth() {
////////////
	String	sMyName = this.getClass().getName() + ".getBirth()";

	return tBirth;
}

String
getBirthAsString() {
////////////////////
	String	sMyName = this.getClass().getName() + ".getBirth()";

	SimpleDateFormat ft =
        new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss, SSS");

	return ft.format( tBirth.getTime()	);
}

public
boolean
setVolume( long lIn ) {
////////////////////////
	String	sMyName = this.getClass().getName() + ".setVolume()";

	log.ifExtreme( "%s : new volume = %d", sMyName, lIn );
	lVolume = lIn;
	return true;
}

public
long
getVolume() {
/////////////
	String	sMyName = this.getClass().getName() + ".getVolume()";

	return lVolume;
}

public
boolean
setSeq( int iIn ) {
////////////////////
	String	sMyName = this.getClass().getName() + ".setSeq()";

	log.ifExtreme( "%s : new seq value = %d", sMyName, iIn );
	iSeq = iIn;
	return true;
}

public
int
getSeq() {
//////////
	String	sMyName = this.getClass().getName() + ".getSeq()";

	return iSeq;
}

public
boolean
setUpdated( int iIn ) {
///////////////////////
	String	sMyName = this.getClass().getName() + ".setUpdated()";

	log.ifExtreme( "%s : new updated value = %d", sMyName, iIn );

	iUpdated = iIn;
	return true;
}

public
int
getUpdated() {
//////////////
	String	sMyName = this.getClass().getName() + ".getUpdated()";

	return iUpdated;
}

public
void
statusReport( boolean bForceThisReport ) {
//////////////////////////////////////////
	String sMyName =	this.getClass().getName() + ".statusReport()";

	if ( log == null )	{
		System.out.println( sMyName + " : log file is not open" );
	}/////////////////////////////////////////////////////////////

	lDate	lDateMisc	= new lDate();

	int	iSavedLevel		= 0;

	if ( bForceThisReport ) {
		iSavedLevel = log.getLoggingLevel();
		log.setLoggingLevel( 99 );
	}

	log.ifExtreme( "     %s",				sMyName							);
	log.ifExtreme( "     sTicker  = %s",	sTicker							);
	log.ifExtreme( "     fPrice   = %f",	fPrice							);
	log.ifExtreme( "     fHigh    = %f",	fHigh							);
	log.ifExtreme( "     fLow     = %f",	fLow							);
	log.ifExtreme( "     fOpen    = %f",	fOpen							);
	log.ifExtreme( "     fClose   = %f",	fClose							);
	log.ifExtreme( "     lVolume  = %d",	lVolume							);
	log.ifExtreme( "     tBirth   = %s",	lDateMisc.get( tBirth )			);

	if ( bForceThisReport ) {
		log.setLoggingLevel( iSavedLevel );
	}

}

public
void
statusReport() {
////////////////
	statusReport( false );	// allow logging level to dictate our write logic
}

public
void
setLogFile( LLog logIn ) {
//////////////////////////
	log = logIn;
}

//
//	end of class
//
}