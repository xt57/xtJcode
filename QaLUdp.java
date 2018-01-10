
//  QaLUdp

//	package net.xt57;

import java.io.*;

public class QaLUdp {

    public static void main(String[] args) throws Exception {
        System.out.println( "XtCoreQa is active");

	    //  long outsideId = Thread.currentThread().getId();
	    //  System.out.println( "main() outside cf code : " + outsideId );

	    //  setup a logging object for default logging
	    LLog    myLog = new LLog();
	    try {
		    myLog.openNewPath( "./eric.log" );
		    System.out.println( "log file opened" );

	    }
	    catch (IOException e) {
		    System.out.println( "log open error" + e );
	    }

		//  add a few informative log entries
	    myLog.write( "initial   = " + myLog.getLoggingLevelAsString()     );

	    myLog.setLoggingLevel( 7 );
	    myLog.write( "adj to 7  = " + myLog.getLoggingLevelAsString()     );

	    myLog.writeBlankLine();
	    myLog.write( "==============================================" );
	    myLog.write( "new logging run unit" );
	    myLog.write( "==============================================" );

	    myLog.setLoggingLevel( 99 );
	    myLog.write( "adj to 99 = " + myLog.getLoggingLevelAsString()     );

	    //  write various entries to the log file
	    for ( int i=17; i > 0; i-- ) {
		    myLog.subtractFromLoggingLevel( 11 );
	        //  myLog.writeBlankLine();
		    if ( myLog.loggingLevelIsHigh() )
		        myLog.ifHigh(   "high level : " + myLog.getLoggingLevelAsString()   );
		    else
		        myLog.write(    "           : " + myLog.getLoggingLevelAsString()     );
	    }

	    //  adding use of the token method as a simple git test
	    System.out.println( "token = " + myLog.token() );

	    System.out.println( "mission = " + myLog.mission() );


	    LUdp    myLUdp = new LUdp();

	    System.out.println( "greeting = " + myLUdp.sGreeter() );


    }

}
