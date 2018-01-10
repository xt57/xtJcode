//
//  XtObelInt
//
//      Observable - extending XtLowInt
//
//

package net.xt57;


public
class
XtObleInt extends XtLowInt {
////////////////////////////


public
void
notify( int iNotifyValueIn ) {
//////////////////////////////   
 
    setChanged();
    notifyObservers(	iNotifyValueIn		);   
 }
    


} // class end

