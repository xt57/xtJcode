//
//
//

//	package com.xt57;

import java.util.*;


public
class
invParser extends LcByteListEditor
{
	boolean	bAllSystemsAreGo	= false;
	boolean	bWeOnHold			= true;
	boolean	bWeAreReadyForTheNextRead	= true;

	int		iExtractedLength	= 0;

	//	streaming fields
	String	sStrSymbol			= "";
	int		iStrTime			= 0;
	float	fStrPrice			= 0;
	long	lStrVolume			= 0;
	int		iStrSeq				= 0;
	int		iStrUpdated			= 0;

	LinkedList<Byte> llFeed		= null;

	LinkedList<String> llParsed	= null;

	boolean	bParseListModified	= false;

	invParser()
	{
		;
	}

	invParser( LLog logIn )
	{
		log = logIn;	// is this okay?
	}

public void
nextSession()
{
	String	sMyName = this.getClass().getName() + ".nextSession()";

	//	require our log file
	if ( log == null ) {
		System.out.println( sMyName + " : log file is not active, but is required" );
		System.exit( 87 );
	}

	long t = Thread.currentThread().getId();

	log.ifHigh( "---> pars : %4d (%d)\n",	llFeed.size(), t	);

	//	setChanged();
	//	notifyObservers(	(Integer) 5		);
	//	if ( true )
	//		return;

	if ( llFeed.size() > 0 ) {

		bParseListModified = false;

		if ( llFeed.size() > 2048	) {
			log.write( "---> isl9 :%5d (%d)\n", llFeed.size(), t );
			byte[] baTrash = new byte[ 512 ];
			chopAndCopyBytes( 512, baTrash);
			bParseListModified = true;
		}

		if (	llFeed.size() > 37		) {
				chopByteVal();
				permanentlyRemoveBytes();
				// bParseListModified = true;
		}

		if ( wd40.getCurrentSeconds() > 40 && llFeed.size() > 100 	) {

			log.write( "---> sl19 :%5d (%d)\n", llFeed.size(), t );

			int iTrashSize = 25;
			byte[] baTrash = new byte[ iTrashSize ];
			chopAndCopyBytes( iTrashSize, baTrash);
			permanentlyRemoveBytes();
			bParseListModified = true;
		}

		if ( bParseListModified ) {
			setChanged();
			notifyObservers(	(Integer) 5		);
		}
	}
	statusReport();
}


public void
setLists(  LinkedList<Byte> llFeedIn,  LinkedList<String> llParsedIn  ) {

	llFeed		=	llFeedIn;

	llParsed	=	llParsedIn;

	//	setByteList(	llFeedIn);	
}


}
// class end
