//
//
//

package net.xt57;

import java.util.Observable;

/////////////////////////////
public
class
XtLowInt extends Observable {
/////////////////////////////
    
    int     iCore = 0;
    


    
public
void
set( int iIn ) {
////////////////   
    iCore = iIn;
    
    setChanged();
    notifyObservers(	iIn	);   
    
}
    
public
int
get() {
///////   
    return iCore;
}
    


}// class end



