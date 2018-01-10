//
//
//

//	package net.xt57;

import java.sql.*;


///////////////////////////////////////////////////////////////////
public
class
LSql {
///////////////////////////////////////////////////////////////////

	Connection con	= null;


	boolean	bWeHaveALogFile		= false;
	LLog	log					= null;

	lDate	wd40				= new lDate();

	LSql() {
	////////
		;
	}

	LSql( LLog logIn ) {
	////////////////////
		log			= logIn;
	}////


public
Connection
getConnectionCopy() {
/////////////////////
	return con;
}////

public
boolean
open() throws Exception {
/////////////////////////
	con				= null;
	Statement st	= null;
	ResultSet rs	= null;

	String url		= "jdbc:mysql://192.168.1.57:3306/BooyahTests";
	String user		= "xt57";
	String password	= "zzTop007";


	System.out.println( "Verifying MySQL JDBC Connection" );
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	}////
	catch (Exception e) {
		System.out.println( "MySQL driver not found" );
		System.exit( 9 );
	}////

 	System.out.println( "MySQL Driver Registered!" );

	try {
		con = DriverManager.getConnection(url, user, password);
		con.isValid( 3);
	}////
	catch (Exception e) {
		System.out.println( "connect error!"  );
		return false;
	}////

	return true;
}////

public
void
countQuotes() {
///////////////
	con				= null;
	Statement st	= null;
	ResultSet rs	= null;

	String url		= "jdbc:mysql://192.168.1.57:3306/BooyahTests";
	String user		= "xt57";
	String password	= "zzTop007";


	System.out.println( "Verifying MySQL JDBC Connection" );
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	}
	catch (Exception e) {
		System.out.println( "MySQL driver not found" );
		System.exit( 9 );
	}

 	System.out.println( "MySQL Driver Registered!" );

	try {
		con = DriverManager.getConnection(url, user, password);
		st = con.createStatement();
		rs = st.executeQuery( "SELECT COUNT(*) FROM MostRecent;" );
		while ( rs.next() ) {
			System.out.println(rs.getString( 1)  );
		}



	//	rs.absolute(5); // moves the cursor to the fifth row of rs
    //   rs.updateString("NAME", "AINSWORTH"); // updates the
	// NAME column of row 5 to be AINSWORTH
    //   rs.updateRow(); // updates the row in the data source


	//	to insert column values into the insert row. An updatable ResultSet object has a special row associated with it that serves as a staging area for building a row to be inserted. The following code fragment moves the cursor to the insert row, builds a three-column row, and inserts it into rs and into the data source table using the method insertRow.

    //   rs.moveToInsertRow(); // moves cursor to the insert row
    //   rs.updateString(1, "AINSWORTH"); // updates the
    //      // first column of the insert row to be AINSWORTH
    //   rs.updateInt(2,35); // updates the second column to be 35
    //   rs.updateBoolean(3, true); // updates the third column to true
    //   rs.insertRow();
    //   rs.moveToCurrentRow();





	}////
	catch (Exception e) {
		System.out.println( "connect error : "  );
		System.exit( 55 );
	}////

}////


}// end of class

