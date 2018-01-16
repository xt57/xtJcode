//
//
//

//  package net.xt57;

import java.util.concurrent.*;
import java.util.*;

///////////////////////////////////////////////////////////////////
public
class
LcByteList extends Observable {
///////////////////////////////////////////////////////////////////

	ConcurrentLinkedQueue<Byte> llMain  = null;
	
    boolean	bListIsLocked	            = false;

    lDate	wd40		                = null;

    LcByteList() {
	    initializer();
    }    

    
    public void
    initializer()   {
	
        ConcurrentLinkedQueue<Byte> llMain  = null;
	
        boolean	bListIsLocked	            = false;

        lDate	wd40		                = new lDate();

        llMain = new ConcurrentLinkedQueue<>();
    }   


public boolean
isLocked() {

    return bListIsLocked;
}


public boolean
lock() {

    bListIsLocked = true;
    
    return true;
}


public boolean
unlock() {

    bListIsLocked = false;
     
    return true;
}


public boolean
add( byte byteIn) {

    String  sMyName = this.getClass().getName() + ".add()";

    boolean bWaitingForLock = true;
    
    for ( int x=0; x < 3 && bWaitingForLock; x++ ) {
	    if ( lock() ) {
	        llMain.add(	    byteIn  );
	        bWaitingForLock = false;
	    }
	    else {
	        wd40.nap( 3 );
        }
    }

    if ( bWaitingForLock ) {
	    System.out.println( sMyName + " : unable to lock the list" );
	    System.exit( 84 );
    }
   
    unlock(); 
    
    return true;
}


public boolean
delete( int iBytesToDelete ) {

    String  sMyName = this.getClass().getName() + ".delete()";

    if ( iBytesToDelete > llMain.size() ) {
	    System.out.println( sMyName + "asked 2 delete more than avail" );
	    return false;
    }

    boolean bWaitingForLock = true;
    
    for ( int x=0; x < 3 && bWaitingForLock; x++ ) {
	    if ( lock() ) {
	        while ( iBytesToDelete > 0 ) {
		        llMain.poll();	    iBytesToDelete--;
	        }
	        bWaitingForLock = false;
	    }
	    else {
	        wd40.nap( 3 );
	    }
    }

    if ( bWaitingForLock ) {
	    System.out.println( sMyName + " : unable to lock the list" );
	    System.exit( 84 );
    }

    unlock();   
    
    return true;
}



public int
getSize() {
    return llMain.size();
}


public void
sendNotification() {
    setChanged();
    notifyObservers(	(Integer) 11		);
}


}// class end

