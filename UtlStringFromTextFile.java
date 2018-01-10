//
//  UtlStringFromTextFile
//

//  package net.xt57;

import java.text.*;
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;


//  import org.apache.commons.io.FileUtils;


public
class
UtlStringFromTextFile extends Object
{
    private
	String          sLogPath        = "/tmp/xtText2Str.log";
    String          sCharSet        = "utf-8";
    PrintWriter     pwLog           = null;
    boolean         bIsOpen         = false;

    LLog            log             = null;


public
String
convert( String fileName ) throws Exception
{
    BufferedReader reader = null;
    StringBuilder stringBuilder = new StringBuilder();

    try {
        if ( log == null ) {
            log = new LLog();
            log.openNewPath( sLogPath);
        }

        reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        // delete the last ls
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    finally {
        if (reader != null) {
            try {
                reader.close();
            }
            catch (IOException e) {
					e.printStackTrace();
            }
		}
    }
    
    return stringBuilder.toString();
}



}
// end of class








/*


package com.journaldev.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class JavaReadFileToString {


	public static void main(String[] args) {
		String fileName = "/Users/pankaj/Downloads/myfile.txt";

		String contents = readUsingScanner(fileName);
		System.out.println("*****Read File to String Using Scanner*****\n" + contents);

		contents = readUsingApacheCommonsIO(fileName);
		System.out.println("*****Read File to String Using Apache Commons IO FileUtils*****\n" + contents);

		contents = readUsingFiles(fileName);
		System.out.println("*****Read File to String Using Files Class*****\n" + contents);

		contents = readUsingBufferedReader(fileName);
		System.out.println("*****Read File to String Using BufferedReader*****\n" + contents);

		contents = readUsingBufferedReaderCharArray(fileName);
		System.out.println("*****Read File to String Using BufferedReader and char array*****\n" + contents);

		contents = readUsingFileInputStream(fileName);
		System.out.println("*****Read File to String Using FileInputStream*****\n" + contents);

	}

	private static String readUsingBufferedReaderCharArray(String fileName) {
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();
		char[] buffer = new char[10];
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while (reader.read(buffer) != -1) {
				stringBuilder.append(new String(buffer));
				buffer = new char[10];
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return stringBuilder.toString();
	}

	private static String readUsingFileInputStream(String fileName) {
		FileInputStream fis = null;
		byte[] buffer = new byte[10];
		StringBuilder sb = new StringBuilder();
		try {
			fis = new FileInputStream(fileName);

			while (fis.read(buffer) != -1) {
				sb.append(new String(buffer));
				buffer = new byte[10];
			}
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return sb.toString();
	}

	private static String readUsingBufferedReader(String fileName) {
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			// delete the last ls
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return stringBuilder.toString();
	}

	private static String readUsingFiles(String fileName) {
		try {
			return new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String readUsingApacheCommonsIO(String fileName) {
		try {
			return FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String readUsingScanner(String fileName) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
			// we can use Delimiter regex as "\\A", "\\Z" or "\\z"
			String data = scanner.useDelimiter("\\A").next();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (scanner != null)
				scanner.close();
		}

	}

}




*/
