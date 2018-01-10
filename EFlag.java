//
//
//

//	package net.xt57;

//  import java.text.SimpleDateFormat;
//  import java.util.Date;
//  import java.util.Calendar;

///////////////////////////////////////////////////////////////////
public
class
EFlag extends Object {
///////////////////////////////////////////////////////////////////

	boolean		bAllSystemsAreGo	= false;

	boolean		bWeHaveALogFile		= false;
	LLog		log					= null;


	LSql		db					= null;

	EFlag() {
	/////////
		;
	}

	EFlag( LLog logIn ) {
	/////////////////////
		log = logIn;
	}


public
String
get( String sFlagIn) {
//////////////////////
	String sToReturn = "return";
	return sToReturn;
}


public
long
getThreadId()  {
////////////////
    return (long) Thread.currentThread().getId();
}



}	//	end of class
