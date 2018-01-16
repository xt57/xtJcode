//
//
//

//	package com.xt57;

import java.util.*;


public
class
InvParser extends LcByteListEditor
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

	InvFeed	invFeed				= null;

	LinkedList<String> llParsed	= null;

	boolean	bParseListModified	= false;

	InvParser()		{
		initializer();
	}

	InvParser( LLog logIn )		{
		initializer();
		log = logIn;			// is this okay?
	}

	public void
	initializer()	{
	
		bAllSystemsAreGo	= false;
		bWeOnHold			= true;
		bWeAreReadyForTheNextRead	= true;

		iExtractedLength	= 0;

		//	streaming fields
		sStrSymbol			= "";
		iStrTime			= 0;
		fStrPrice			= 0;
		lStrVolume			= 0;
		iStrSeq				= 0;
		iStrUpdated			= 0;

		//	LinkedList<Byte> llFeed	= null;

		invFeed				= new InvFeed();			

		llParsed			=  new LinkedList<String>();			//	????????

		bParseListModified	= false;
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

	log.ifHigh( "---> pars : %4d (%d)\n",	invFeed.getSize(), t	);

	//	setChanged();
	//	notifyObservers(	(Integer) 5		);
	//	if ( true )
	//		return;

	if ( invFeed.getSize() > 0 ) {

		bParseListModified = false;

		if ( invFeed.getSize() > 2048	) {
			log.write( "---> isl9 :%5d (%d)\n", invFeed.getSize(), t );
			byte[] baTrash = new byte[ 512 ];
			chopAndCopyBytes( 512, baTrash);
			bParseListModified = true;
		}

		if (	invFeed.getSize() > 37		) {
				chopByteVal();
				permanentlyRemoveBytes();
				// bParseListModified = true;
		}

		if ( wd40.getCurrentSeconds() > 40 && invFeed.getSize() > 100 	) {

			log.write( "---> sl19 :%5d (%d)\n", invFeed.getSize(), t );

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


/*

public void
setLists(  LinkedList<Byte> llFeedIn,  LinkedList<String> llParsedIn  ) {

	llFeed		=	llFeedIn;

	llParsed	=	llParsedIn;

	//	setByteList(	llFeedIn);	
}

*/


}
// class end
