//
//
//

//	package net.xt57;

import java.io.IOException;
import java.nio.*;
import java.util.*;

public
class
LcByteListEditor extends LcByteList {

	Calendar	calHeartbeat		= null;

	int			iFocusNdx	    	= 0;
	int			iFocus		    	= 0;

	int			ndx		    		= 0;

	int			iRemovalBytes	    = 0;


	int			iPrevFocusNdx	    = 0;
	int			iPrevRemovalBytes   = 0;

	boolean		bWeHaveALogFile		= false;
	LLog		log			    	= null;

	lDate		wd40			    = null;

	
	public void
	initializer()	{

		calHeartbeat		= null;

		iFocusNdx	    	= 0;
		iFocus		    	= 0;

		ndx		    		= 0;

		iRemovalBytes	    = 0;


		iPrevFocusNdx	    = 0;
		iPrevRemovalBytes   = 0;

		bWeHaveALogFile		= false;
		log			    	= null;

		wd40			    = new lDate();
	}




public boolean
bXt57ExitHeader() {

	if ( llMain.size() < 2 ) {
		return false;
	}////

	if ( peekChar( 0 )	== 'X' ) {
		int x = 0;
	}////

	if ( peekChar( 0 )	== 'X' && peekChar( 1 ) == 'T' ) {
		return true;
	}////

	return false;
}

public boolean
bHeartbeatHeader() {

	if ( llMain.size() < 10 ) {
		return false;
	}////

	if ( peekChar( 0 )	== 'H' && peekChar( 1 ) == 'T' ) {
		return true;
	}////

	return false;
}

public boolean
bSnapshotHeader() {
///////////////////
	if ( llMain.size() == 0 ) {
		return false;
	}////

	if ( peekChar( 0 ) == 'N' ) {
		return true;
	}////

	return false;
}

public boolean
bStreamingHeader() {
////////////////////
	if ( llMain.size() == 0 ) {
		return false;
	}////

	if ( peekChar( 0 )	== 'S' ) {
		return true;
	}////

	return false;
}

public byte
peekByte( int ndxIn ) {
///////////////////////

	if ( llMain.isEmpty()	) {
		System.exit ( 253 );
	}////

	Iterator<Byte> itr = llMain.iterator();

	for ( int x=0; x < ndxIn && itr.hasNext(); x++	) {
		itr.next();
	}////

	return (byte) itr.next();	//	used2b -> return (byte) llMain.get( ndxIn );
}////

public char
peekChar( int ndxIn ) {
///////////////////////

	return (char) peekByte( ndxIn );
}////

public char
peekChar() {
////////////
	String	sMyName = this.getClass().getName() + ".peekChar()";

	char cResult = peekChar( iFocusNdx);

	log.ifExtreme( "%s : cResult : %c", sMyName, cResult	);

	return cResult;
}////

public char
printableElseBlank() {
//////////////////////
	//	String	sMyName = this.getClass().getName() + ".printableElseBlank()";

	char	cResult = ' ';

	int		i = peekByteVal();

	if ( i > 31 && i < 127 )
		cResult = (char) i;
	else
		cResult = ' ';

	return cResult;
}////

public char
printableElseBlank( byte bIn ) {
////////////////////////////////
	//	String	sMyName = this.getClass().getName() + ".printableElseBlank()";

	char	cResult = ' ';

	int	i = (int) bIn;

	if ( i > 31 && i < 127 ) {
		cResult = (char) i;
	}////

	return cResult;
}////


public boolean
saveUndoMetrics() {
///////////////////
	String	sMyName = this.getClass().getName() + ".setUndoMetrics()";

	iPrevFocusNdx		= iFocusNdx;
	iPrevRemovalBytes	= iRemovalBytes;

	return true;
}////


public boolean
restoreUndoMetrics() {
//////////////////////
	String	sMyName = this.getClass().getName() + ".setUndoMetrics()";

	iFocusNdx		= iPrevFocusNdx;
	iRemovalBytes	= iPrevRemovalBytes;

	return true;
}////


public boolean
permanentlyRemoveBytes() {
//////////////////////////
    String
	sMyName = this.getClass().getName() + ".permanentlyRemoveBytes()";

    //  log.ifExtreme( "%s : managing removal bytes", sMyName );

    //  log.ifExtreme( "%s : llMain.size()   : %d",
    //		    sMyName, llMain.size()
    //			);
    
    //  log.ifExtreme( "%s : orig bytes read : %d",
    //			sMyName, iRemovalBytes
    //			);

    if ( ! delete( iRemovalBytes )  ) {
	log.write( "%s : removal failure", sMyName );
	System.exit( 86 );
    }////
    
    iRemovalBytes = 0;

    return true;
}////


public int
nonChoppedBytesRemaining()
{
    return llMain.size() - iRemovalBytes;
}


public String
chopString( int iInLength )
{
	String	sMyName = this.getClass().getName() + ".chopString()";

	char[]	caField	= new char[ iInLength ];

	for (	int src=iFocusNdx, dst=0; dst < iInLength; src++, dst++ ) {
		caField[ dst ]	= peekChar( src );
	}
	
	iRemovalBytes	+= iInLength;
	iFocusNdx		+= iInLength;

	return String.copyValueOf( caField );
}

public void
chopAndCopyChars( int iLengthIn, char[] caIn )
{
	String	sMyName = this.getClass().getName() + ".chopAndCopyChars()";

	int	iSrcMax			= llMain.size();
	int	iDstMax			= caIn.length;

	for ( int i = 0; i < caIn.length; i++)
		caIn[ i ]	= ' ';

	if ( iLengthIn < iDstMax )
		iDstMax = iLengthIn;

	for (	int iSrc=iFocusNdx, iDst=0;
			iDst < iDstMax && iSrc < iSrcMax;
			iSrc++, iDst++
		) {
		caIn[ iDst ]	= peekChar( iSrc );
	}

	iRemovalBytes	+= iLengthIn;
	iFocusNdx		+= iLengthIn;
}

public void
chopAndCopyBytes( int iLengthIn, byte[] baIn )
{
	String	sMyName = this.getClass().getName() + ".chopAndCopyBytes()";

	int	iSrcMax			= llMain.size();
	int	iDstMax			= baIn.length;

	for ( int i = 0; i < baIn.length; i++)
		baIn[ i ]	= ' ';

	if ( iLengthIn < iDstMax )
		iDstMax = iLengthIn;

	for (	int iSrc=iFocusNdx, iDst=0;
			iDst < iDstMax && iSrc < iSrcMax;
			iSrc++, iDst++
		) {
		baIn[ iDst ]	= peekByte( iSrc );
	}////

	iRemovalBytes	+= iLengthIn;
	iFocusNdx		+= iLengthIn;
}

public void
chopBytes( int iLengthIn )
{
	String	sMyName = this.getClass().getName() + ".chopBytes()";

	iRemovalBytes	+= iLengthIn;
	iFocusNdx		+= iLengthIn;
}

public int
chopInt()
{
	String	sMyName = this.getClass().getName() + ".chopInt()";

	int		iBinFieldSize	= 4;	//	size, in bytes, of this binary type
	byte[]	baField	= new byte[ iBinFieldSize ];

	for (	int src=iFocusNdx, dst=0; dst < iBinFieldSize; src++, dst++ ) {
		baField[ dst ]	= peekByte( src );
	}

	int iResult =
			ByteBuffer
				.wrap(	baField )
				.order(	ByteOrder.BIG_ENDIAN )
				.getInt();

	log.ifExtreme( "%s : iResult : %d", sMyName, iResult	);

	iRemovalBytes	+= iBinFieldSize;
	iFocusNdx		+= iBinFieldSize;

	return iResult;
}

public short
chopShort()
{
	String	sMyName = this.getClass().getName() + ".chopShort()";

	int		iBinFieldSize	= 2;	//	size, in bytes, of this binary type
	byte[]	baField	= new byte[ iBinFieldSize ];

	for (	int src=iFocusNdx, dst=0; dst < iBinFieldSize; src++, dst++ ) {
		baField[ dst ]	= peekByte( src );
	}////

	short shResult =
			ByteBuffer
				.wrap(	baField )
				.order(	ByteOrder.BIG_ENDIAN )
				.getShort();

	log.ifExtreme( "%s : shResult : %d", sMyName, shResult	);

	iRemovalBytes	+= iBinFieldSize;
	iFocusNdx		+= iBinFieldSize;

	return shResult;
}

public long
chopLong()
{
	String	sMyName = this.getClass().getName() + ".chopLong()";

	int		iBinFieldSize	= 8;	//	size, in bytes, of this binary type
	byte[]	baField	= new byte[ iBinFieldSize ];

	for (	int src=iFocusNdx, dst=0; dst < iBinFieldSize; src++, dst++ ) {
		baField[ dst ]	= peekByte( src );
	}////

	long lResult =
			ByteBuffer
				.wrap(	baField )
				.order(	ByteOrder.BIG_ENDIAN )
				.getLong();

	log.ifExtreme( "%s : lResult : %d", sMyName, lResult	);

	iRemovalBytes	+= iBinFieldSize;
	iFocusNdx		+= iBinFieldSize;

	return lResult;
}

public float
chopFloat()
{
	String	sMyName = this.getClass().getName() + ".chopFloat()";

	int		iBinFieldSize	= 4;	//	size, in bytes, of this binary type
	byte[]	baField	= new byte[ iBinFieldSize ];

	for (	int src=iFocusNdx, dst=0; dst < iBinFieldSize; src++, dst++ ) {
		baField[ dst ]	= peekByte( src );
	}////

	float fResult =
			ByteBuffer	.wrap(	baField )
						.order(	ByteOrder.BIG_ENDIAN )
						.getFloat();

	log.ifExtreme( "%s : fResult : %f", sMyName, fResult	);

	iRemovalBytes	+= iBinFieldSize;
	iFocusNdx		+= iBinFieldSize;

	return fResult;
}

public int
chopByteVal()
{
	String	sMyName = this.getClass().getName() + ".chopByteVal()";

	int		iResult;

	iResult = peekByteVal();

	//	log.ifExtreme( "%s : iResult : %d", sMyName, iResult	);

	iRemovalBytes	+= 1;
	iFocusNdx		+= 1;

	return iResult;
}

public int
peekByteVal()
{
	String	sMyName = this.getClass().getName() + ".peekByteVal()";

	byte	b;
	int		iResult;

	b = peekByte( iFocusNdx );

	iResult = (int) b &0xFF;

	//	log.ifExtreme( "%s : iResult : %d", sMyName, iResult	);

	return iResult;
}

public
void
statusReport()
{
	String sMyName =	this.getClass().getName() + ".statusReport()";

	if ( log == null )	{
		System.out.println( sMyName + " : log file is not open" );
	}

	if ( log.getLoggingLevel() < 70 )
		return;

	log.write( "     %s",					sMyName );

	//	log.write( "     last read size = %d",	iBytesRead			);

	log.write( "     iFocusNdx         = %d",	iFocusNdx			);
	log.write( "     iRemovalBytes     = %d",	iRemovalBytes		);
	log.write( "     llMain.size()     = %d",	llMain.size()		);
	log.write( "     iPrevFocusNdx     = %d",	iPrevFocusNdx		);
	log.write( "     iPrevRemovalBytes = %d",	iPrevRemovalBytes	);

	for ( int src=iFocusNdx; src < llMain.size(); src++ ) {
		logIfPrintable( (byte) peekChar( src), log);
	}
}

public void
setLogFile( LLog logIn )
{
	log = logIn;
}

public boolean
bWeHaveALogFile()
{
	return true;
}

public boolean
hasBytes()
{
	if ( llMain.size() > 0 )
		return true;
	return false;
}

public int
size()
{
	return (int) llMain.size();
}


public
boolean
bIsPrintable( byte b )
{
	int		i = (int) b;
	if ( i < 32 || i > 126 ) {
		return false;
	}
	return true;
}


public
void
logIfPrintable( byte b, LLog logIn )
{
	if ( ! logIn.loggingLevelIsVeryHigh() ) {
		return;
	}

	char c;
	
	if ( bIsPrintable( b ) ) {
		c = (char) b;
	}
	else {
		c = '.';
	}

	System.out.println( "char : " + c + "  (" + b + ")" );
	return;
}

public int
inventory()
{
	int i = 0;

	for (byte b : llMain ) {
		i++;
	}

	return i;
}


public void
setByteEdLogFile( String logPathIn ) throws Exception
{
	String	sMyName = this.getClass().getName() + ".setLogFile()";
    if ( log != null) {
		log.close();
	}
	try {
        log = new LLog();
        log.setLoggingLevel( 99 );
        log.openNewPath( logPathIn );
		log.write( "\n\n\n" + "New Session" + "\n\n\n"	);
	}
    catch (IOException e) {
        System.out.println( sMyName + " : cannot open the log file" );
    }
}


}// class end
