//
//
//

package com.xt57;

///////////////////////////////////////////////////////////////////
public
class
datByteArray {
///////////////////////////////////////////////////////////////////

	int		iBaSize		= 0;

	byte[]	baBuf		= null;


	datByteArray() {
	////////////////
		;
	}

	datByteArray( byte[] baIn) {
	////////////////////////////
		iBaSize = baIn.length;
		baBuf = new byte[ iBaSize ];

		System.arraycopy( baBuf, 0, baIn, 0, iBaSize );
	}


	datByteArray( byte[] baIn, int iInSize) {
	////////////////////////////
		iBaSize = iInSize;
		baBuf = new byte[ iInSize ];

		System.arraycopy( baBuf, 0, baIn, 0, iBaSize );
	}



public
void
set( byte[] baIn ) {
////////////////////
	if ( iBaSize == 0 ) {
		iBaSize = baIn.length;
		baBuf = new byte[ iBaSize ];
	}////

	System.arraycopy( baBuf, 0, baIn, 0, iBaSize );
}////






public
byte[]
get() {
///////
	return baBuf;
}////

public
byte
getByte( int iNdx) {
///////
	return baBuf[ iNdx ];
}////

public
int
size() {
////////////////////
	return iBaSize;
}////



//
//	end of class
//
}