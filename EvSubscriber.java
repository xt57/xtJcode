//
//
//

package net.xt57;

import java.util.Observable;
import java.util.Observer;


/** The Observer normally maintains a view on the data */
public
///////////////////////////////////////////////////////////////////
class
EvSubscriber implements Observer {
///////////////////////////////////////////////////////////////////
	public
	void
	update(Observable obsIn, Object objArgIn) {
	//////////////////////////////////
		 System.out.println("update(" + obsIn + "," + objArgIn + ");" );
	}
}



