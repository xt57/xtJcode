//
//
//

//	package com.xt57;

//	import static com.xt57.tdStreamer80.log;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.nio.charset.Charset;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

public
class
InvAdmin extends Object
{
	boolean	bAllSystemsAreGo	= false;

	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;

	//	remember that all of the "observable" qualities are now active!

	String	sResult		= null;
	String	sSource		= "ECWK";
	String	sSession	= null;
	String	sAcct		= "757176996";		//	user at TD

	String	sUrl		= null;
	String	sToken		= null;

	String	sCompany	= "AMER";
	String	sSegment	= "AMER";

	String	sDomain		= null;
	String	sGroup		= null;
	String	sAccess		= null;
	String	sAuth		= null;
	String	sAcl		= null;

	String	sTimeStamp	= null;

	String	sAppId		= null;

	String	sErrorMsg	= null;

	String	sStreamerUrl			=	null;

	final String	sStreamerInfoUrlBase	=
			"https://apis.tdameritrade.com/apps/100/StreamerInfo;jsessionid=";

	String	sStreamerInfoUrl		= null;

	final String	sLoginUrl				=
			"https://apis.tdameritrade.com/apps/100/LogIn";

	final String	sLogoutUrl				=
			"https://apis.tdameritrade.com/apps/100/LogOut";

	String	sPostData = null;


	InvAdmin() {
	///////////////////

	}

	InvAdmin( LLog logIn ) {
	/////////////////////////
	log = logIn;	// is this okay?
	}




public
void
setDefaultValues() throws Exception {
/////////////////////////////////////
	try {
		if ( ! bWeHaveALogFile ) {
			log = new LLog( "/tmp/invDataPod.log" );
		}
	}
	catch( Exception e ) {
		;
	}

}




public
void
setLogFile( LLog logIn ) {
//////////////////////////
	log = logIn;
}




public
void
statusReport() {
////////////////
	String sMyName =	this.getClass().getName() + ".statusReport()";

	if ( log == null )	{
		System.out.println( sMyName + " : log file is not open" );
	}/////////////////////////////////////////////////////////////

	log.ifHigh( "     %s",				sMyName					);

	//	log.ifHigh( "     sTicker  = %s",	sTicker					);
	//	log.ifHigh( "     fPrice   = %d",	fPrice					);
	//	log.ifHigh( "     lVolume  = %d",	lVolume					);
	//	log.ifHigh( "     dTime    = %d",	dActionTime.toString()	);

}

public
void
start() {
/////////
	String	sMyName = this.getClass().getName() + ".start()";

	//	require our log file
	if ( log == null ) {
		System.out.println( sMyName + " : log file is not active, but is required" );
		System.exit( 87 );
	}/////////////////////

	//	document our thread ID
	long lMyThread = Thread.currentThread().getId();
	log.write( "%s : thread ID at entry : %d", sMyName, lMyThread );

}//method




public
boolean
handshake() {
/////////////
	String	sMyName = this.getClass().getName() + ".handshake()";

	//	require our log file
	if ( log == null ) {
		System.out.println( sMyName + " : log file is not active, but is required" );
		System.exit( 87 );
	}/////////////////////

	//	document our thread ID
	long lMyThread = Thread.currentThread().getId();
	log.write( "%s : thread ID at entry : %d", sMyName, lMyThread );

	try {
		if ( ! logout()	) {
			return false;
		}////////////////

		if ( ! login()	) {
			return false;
		}////////////////

		if ( ! getStreamerInfo()	) {
			return false;

		}////////////////
	}////
    catch (Exception e) {
        System.out.println( "url failure : " + e );
		System.exit( 97 );
    }/////////////////////

	return true;
}//method


public
InputStream
tdStreamingTest() throws InterruptedException, IOException, XPathExpressionException {
//////////////////////////////////////////////////////////////////////////////////////

    final String USER_AGENT = "Mozilla/5.0";

    String	myCharset = "UTF-8";

	log.ifHigh( "inside streaming test" );

	String	sThisUrl = "https://" + getStreamerUrl() + "/";

	log.ifHigh( "StreamingTest : sThisUrl = %s", sThisUrl );

    URL urlHandle = new URL( sThisUrl );
    HttpsURLConnection con = (HttpsURLConnection) urlHandle.openConnection();

    //add request header
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", USER_AGENT);
    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

	String sPost, sFmt, sFmt1, sFmt2, sFmt3, sTmp;

	sFmt1	=
		"=%s&W=%s&A=userid=%s&token=%s&company=%s&segment=%s&cddomain=%s";

	sFmt2	=
		"&usergroup=%s&accesslevel=%s&authorized=%s&acl=%s&timestamp=%s&appid=%s";

	sFmt3	=
		"|S=%s&C=%s&P=%s+%s+%s+%s+%s+%s+%s+%s&T=%s+%s+%s+%s|control=%s&source=%s";

	sFmt =		sFmt1 + sFmt2 + sFmt3;

	sTmp	= String.format
		(
			sFmt,
			getAcct(),
			getToken(),
			getAcct(),
			getToken(),
			getCompany(),
			getSegment(),
			getDomain(),
			getGroup(),
			getAccess(),
			getAuth(),
			getAcl(),
			getTimeStamp(),
			getAppId(),
			"QUOTE",	"SUBS",
			"AAPL", "JCI", "AMT", "CAT", "IBM", "VZ", "VMW", "F",
			"0",		"3",	"8",	"10",
			"false",	"EWCK"
		);

	sPost =		"!U" + URLEncoder.encode( sTmp, "UTF-8" );
	sPost +=	"\n\n";

    log.ifHigh( " "								);
    log.ifHigh( "POST URL     : %s", urlHandle	);
	log.ifHigh( "POST encoded : %s", sPost		);
    log.ifHigh( " "								);

	log.ifHigh( "just before streaming test https dispatch ..." );

    // Send post request
    con.setDoOutput( true );
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes( sPost );
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();

    log.ifHigh( "response code    : " + responseCode	);
    log.ifHigh( " "										);

	System.out.println( "tdStreamingTest() - at exit" );

	return con.getInputStream();
}


public
boolean
login() throws Exception, InterruptedException, IOException, XPathExpressionException
{
    final String USER_AGENT = "Mozilla/5.0";

    //	Charset		myCharset = Charset.defaultCharset();

	UtlStringFromTextFile	myConverter	= new UtlStringFromTextFile();
	String sUid;
	String sPwd;
	
	try {
		sUid = myConverter.convert( "/var/etc/uid.txt" );
		sPwd = myConverter.convert( "/var/etc/pwd.txt" );
	}
    catch (Exception e) {
		log.writeBlankLine();
   		log.write( "failed to load Uid and Pwd" );
		log.writeBlankLine();		
		return false;
    }

	if ( sUid.equals( null) ) {
		log.write( "tdLogin() - sUid is null" );
		//	throw exception here
	}

	if ( sPwd.equals( null) ) {
		log.write( "tdLogin() - sPwd is null" );
		//	throw exception here
	}

	log.ifHigh( "tdLogin() - proceeding with non-null sUid and sPwd" );

	String	sWrkUrl		= sLoginUrl;
	String	sWrkPost   	= "source=ECWK&version=1.0&userid=" + sUid + "&password=" + sPwd;

    long tId = Thread.currentThread().getId();
	log.ifHigh( "tdLogin() - tId : " + tId );

	HttpsURLConnection con;

	try {

		URL obj = new URL( sWrkUrl );

		con = (HttpsURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		con.setDoOutput( true );
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes( sWrkPost );
		wr.flush();
		wr.close();
	}
    catch (Exception e) {
		log.writeBlankLine();
   		log.write( "POST URL           : " + sWrkUrl		);
    	log.write( "POST parameters    : " + sWrkPost		);
		log.writeBlankLine();		
        System.out.println( "login/post failure : " + e );
		return false;
    }

    int responseCode = con.getResponseCode();

    log.ifHigh( " "										);
    log.ifHigh( "POST URL           : " + sWrkUrl		);
    log.ifHigh( "POST parameters    : " + sWrkPost		);
    log.ifHigh( "post response code : " + responseCode	);
    log.ifHigh( " "										);

    BufferedReader in = new BufferedReader (
        new InputStreamReader(con.getInputStream()  )
        );

    String          inputLine;
    StringBuffer    response = new StringBuffer();

    while ( (inputLine = in.readLine()  ) != null   ) {
        response.append(inputLine);
    }
    in.close();

    //print result before XML parsing
    //	System.out.println( response.toString() 	);
    log.ifHigh( response.toString()					);

	// parse XML reply
    String xml = response.toString();

    InputSource source = new InputSource(new StringReader(xml));

    DocumentBuilderFactory  dbf;
    DocumentBuilder         db;
    Document                doc;
    XPathFactory            xpathFactory = XPathFactory.newInstance();
    XPath xpath =           xpathFactory.newXPath();

    try {
        dbf = DocumentBuilderFactory.newInstance();
        db	= dbf.newDocumentBuilder();
        doc = db.parse(source);

        setResult(	xpath.evaluate( "/amtd/result",					doc )	);
        setSession(	xpath.evaluate( "/amtd/xml-log-in/session-id",	doc )	);
        setAcct(	xpath.evaluate(
							"//amtd/xml-log-in/accounts/account/account-id",
																	doc
										)
					);

        log.ifHigh( " "										);
		log.ifHigh( "result   = [ %s ]", getResult()		);
		log.ifHigh( "session  = [ %s ]", getSession()	);
		log.ifHigh( "acct     = [ %s ]", getAcct()		);
        log.ifHigh( " " );
    }
    catch (Exception e) {
        System.out.println( "builder/xpath exception in http response area : " + e );
		return false;
    }

    System.out.println( "tdLogin() - at exit" );

	return true;
}



public
boolean
logout() throws InterruptedException, IOException, XPathExpressionException {
/////////////////////////////////////////////////////////////////////////////

    final String USER_AGENT = "Mozilla/5.0";

    //	Charset		myCharset = Charset.defaultCharset();

	String	sWrkUrl		= sLogoutUrl;
	String	sWrkPost   	= "source=ECWK";

    long tId = Thread.currentThread().getId();
	log.ifHigh( "tdLogout() - tId : " + tId );

	HttpsURLConnection con;

	try {

		URL obj = new URL( sWrkUrl );

		con = (HttpsURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		con.setDoOutput( true );
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes( sWrkPost );
		wr.flush();
		wr.close();
	}
    catch (Exception e) {
        System.out.println( "url failure : " + e );
		return false;
    }

    int responseCode = con.getResponseCode();

    log.ifHigh( " "										);
    log.ifHigh( "POST URL         : " + sWrkUrl			);
    log.ifHigh( "POST parameters  : " + sWrkPost		);
    log.ifHigh( "response code    : " + responseCode	);
    log.ifHigh( " "										);

    BufferedReader in = new BufferedReader (
        new InputStreamReader(con.getInputStream()  )
        );

    String          inputLine;
    StringBuffer    response = new StringBuffer();

    while ( (inputLine = in.readLine()  ) != null   ) {
        response.append(inputLine);
    }
    in.close();

    //print result before XML parsing
    //	System.out.println( response.toString() 	);
    log.ifHigh( response.toString()					);

	// parse XML reply
    String xml = response.toString();

    InputSource source = new InputSource(new StringReader(xml));

    DocumentBuilderFactory  dbf;
    DocumentBuilder         db;
    Document                doc;
    XPathFactory            xpathFactory = XPathFactory.newInstance();
    XPath xpath =           xpathFactory.newXPath();

    try {
        dbf = DocumentBuilderFactory.newInstance();
        db	= dbf.newDocumentBuilder();
        doc = db.parse(source);

        setResult(	xpath.evaluate( "/amtd/result",					doc )	);

        log.ifHigh( " "										);
		log.ifHigh( "result   = [ %s ]", getResult()		);
        log.ifHigh( " " );
    }
    catch (Exception e) {
        System.out.println( "builder/xpath exception in http response area : " + e );
		return false;
    }

    System.out.println( "tdLogout() - at exit" );

	return true;
}




public
boolean
getStreamerInfo() throws InterruptedException, IOException, XPathExpressionException {
//////////////////////////////////////////////////////////////////////////////////////

    final String USER_AGENT = "Mozilla/5.0";

    Charset         myCharset = Charset.defaultCharset();

	sUrl	=	sStreamerInfoUrlBase + getSession();

	String	sPost	=	"source=ECWK";

    long tId = Thread.currentThread().getId();
    System.out.println( "tdStreamerInfo() - tId : " + tId );

    URL obj = new URL( sUrl);
    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

    //add request header
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", USER_AGENT);
    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

    // Send post request
    con.setDoOutput( true );
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes( sPost );
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();

    log.ifHigh( "\n"											);
    log.ifHigh( "POST URL         : " + sUrl			+ "\n"	);
    log.ifHigh( "POST parameters  : " + sPost			+ "\n"	);
    log.ifHigh( "response code    : " + responseCode	+ "\n"	);
    log.ifHigh( "\n"											);

    BufferedReader in = new BufferedReader
        (
        new InputStreamReader(con.getInputStream()  )
        );

    String          inputLine;
    StringBuffer    response = new StringBuffer();

    while ( (inputLine = in.readLine()  ) != null   ) {
        response.append(inputLine);
    }
    in.close();

    //print result before XML parsing
    //	System.out.println( response.toString() 	);
    log.ifHigh( response.toString()					);

    // parse XML reply
    String xml = response.toString();

    InputSource source = new InputSource(new StringReader(xml));

    DocumentBuilderFactory  dbf;
    DocumentBuilder         db;
    Document                doc;
    XPathFactory            xpathFactory = XPathFactory.newInstance();
    XPath xpath =           xpathFactory.newXPath();

    try {
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        doc = db.parse(source);

		setResult(		xpath.evaluate( "/amtd/result",						doc ) );
		setStreamerUrl(	xpath.evaluate( "/amtd/streamer-info/streamer-url", doc ) );
		setToken(		xpath.evaluate( "/amtd/streamer-info/token",        doc ) );
		setTimeStamp(	xpath.evaluate( "/amtd/streamer-info/timestamp",    doc ) );
		setDomain(		xpath.evaluate( "/amtd/streamer-info/cd-domain-id", doc ) );
		setGroup(		xpath.evaluate( "/amtd/streamer-info/usergroup",    doc ) );
		setAccess(		xpath.evaluate( "/amtd/streamer-info/access-level",	doc ) );
		setAcl(			xpath.evaluate( "/amtd/streamer-info/acl",          doc ) );
		setAuth(		xpath.evaluate( "/amtd/streamer-info/authorized",   doc ) );
		setAppId(		xpath.evaluate( "/amtd/streamer-info/app-id",		doc ) );
		setErrorMsg(	xpath.evaluate( "/amtd/streamer-info/error-msg",    doc ) );

        log.ifHigh( " "											);

		log.ifHigh( "result   = [ %s ]", getResult()			);

		log.ifHigh( "url      = [ %s ]", getStreamerUrl()		);

		log.ifHigh( "token    = [ %s ]", getToken()		);

		log.ifHigh( "timestmp = [ %s ]", getTimeStamp()	);

		log.ifHigh( "domain   = [ %s ]", getDomain()		);

		log.ifHigh( "group    = [ %s ]", getGroup()		);

		log.ifHigh( "acc lvl  = [ %s ]", getAccess()		);

		log.ifHigh( "acl      = [ %s ]", getAcl()		);

		log.ifHigh( "auth     = [ %s ]", getAuth()		);

		log.ifHigh( "app id   = [ %s ]", getAppId()		);

		log.ifHigh( "err msg  = [ %s ]", getErrorMsg()	);

        log.ifHigh( " " );

    }
    catch (Exception e) {
        System.out.println( "builder/xpath exception in http response area : " + e );
		return false;
    }

    System.out.println( "tdStreamerInfo() - at exit" );

	return true;
}







































public
void
setResult( String sIn ) {
/////////////////////////
	sResult = sIn;
}


public
String
getResult() {
/////////////
	return sResult;
}







public
void
setSource( String sIn ) {
/////////////////////////
	sSource = sIn;
}


public
String
getSource() {
/////////////
	return sSource;
}







public
void
setSession( String sIn ) {
//////////////////////////
	sSession = sIn;
}


public
String
getSession() {
//////////////
	return sSession;
}






public
void
setAcct( String sIn ) {
///////////////////////
	sAcct = sIn;
}


public
String
getAcct() {
///////////
	return sAcct;
}



public
void
setStreamerUrl( String sInUrl ) {
//////////
	sStreamerUrl = sInUrl;
}



public
String
getStreamerUrl() {
//////////////////
	return sStreamerUrl;

}





public
String
getStreamerInfoUrl() {
//////////
			sStreamerInfoUrl = sStreamerInfoUrlBase + getSession();
	return	sStreamerInfoUrl;
}





public
void
setToken( String sIn ) {
////////////////////////
	sToken = sIn;
}


public
String
getToken() {
////////////
	return sToken;
}









public
void
setComapny( String sIn ) {
//////////////////////////
	sCompany = sIn;
}


public
String
getCompany() {
//////////////
	return sCompany;
}









public
void
setSegment( String sIn ) {
//////////////////////////
	sSegment = sIn;
}


public
String
getSegment() {
//////////////
	return sSegment;
}










public
void
setDomain( String sIn ) {
/////////////////////////
	sDomain = sIn;
}


public
String
getDomain() {
/////////////
	return sDomain;
}









public
void
setGroup( String sIn ) {
////////////////////////
	sGroup = sIn;
}


public
String
getGroup() {
////////////
	return sGroup;
}










public
void
setAccess( String sIn ) {
/////////////////////////
	sAccess = sIn;
}


public
String
getAccess() {
/////////////
	return sAccess;
}





public
void
setAuth( String sIn ) {
///////////////////////
	sAuth = sIn;
}


public
String
getAuth() {
///////////
	return sAuth;
}




public
void
setAcl( String sIn ) {
//////////////////////
	sAcl = sIn;
}


public
String
getAcl() {
//////////
	return sAcl;
}












public
void
setTimeStamp( String sIn ) {
////////////////////////////
	sTimeStamp = sIn;
}


public
String
getTimeStamp() {
////////////////
	return sTimeStamp;
}






public
void
setAppId( String sIn ) {
////////////////////////
	sAppId = sIn;
}


public
String
getAppId() {
////////////
	return sAppId;
}





public
void
setErrorMsg( String sIn ) {
///////////////////////////
	sErrorMsg = sIn;
}


public
String
getErrorMsg() {
///////////////
	return sErrorMsg;
}














}//class



