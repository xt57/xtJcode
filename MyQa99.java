//
//
//

//	package com.xt57;


import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;

import java.util.Observer;


//	public
class
MyQa99 	{


/*
@Override
public void update() {
   System.out.println( "Binary String: " ); 
}
*/

public void
QaFeed( LLog log)	{

	log.write( "---> QaFeed entry : t=%d",	Thread.currentThread().getId()	);

	lDate 		wd40 			= new lDate( log );

	InvFeed		feed 			= new InvFeed( log);

	boolean		bAutoGenMode	= true;

	/*
	feed.setGenMode(	bAutoGenMode );
	if ( ! bAutoGenMode ) {
		feed.addObserver(
			this );
	}
	*/

	log.write( "---> QaFeed exit  : t=%d",	Thread.currentThread().getId()	);
}


public void
QaTdStreamer()	throws Exception	{

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


public static void

main(String [] args) throws Exception	{

//  main(String [] args) 	{	

	LLog	log		= null;

	try {
        log = new LLog();
        log.setLoggingLevel( 99 );
        log.openNewPath( "/tmp/main.log" );
		log.write( "\n\n\n" + "New Session" + "\n\n\n"	);
    }
    catch (IOException e) {
        System.out.println( "cannot open the log file" );
    }

	log.ifHigh( "---> main : t=%d",	Thread.currentThread().getId()	);
	
	//	QaFeed();

}


}	//	class end

/*

//Run Observer and Observable
class MyQa77 {
    public static void main(String args[])  {
        News observedNews = new News();
        FirstNewsReader reader1 = new FirstNewsReader();
        SecondNewsReader reader2 = new SecondNewsReader();
        observedNews.addObserver(reader1);
        observedNews.addObserver(reader2);
        observedNews.news();
    }
}



//Second Observer
class SecondNewsReader implements Observer {
    public void update(Observable obj, Object arg)  {
        System.out.println("SecondNewsReader got The news:"+(String)arg);
    }
}

// This is the class being observed.
class News extends Observable {
    void news() {
        String[] news = {"News 1", "News 2", "News 3"};
        for(String s: news){
            //set change
            setChanged();
            //notify observers for change
            notifyObservers(s);
            try {
                Thread.sleep(377);
            }
            catch (InterruptedException e) {
                System.out.println("Error Occurred.");
            }
        }
    }
}

*/