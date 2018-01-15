//
//
//

//	package com.xt57;


public
class
MyQa99 extends Object {



public static void
main(String [] args) throws Exception {

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