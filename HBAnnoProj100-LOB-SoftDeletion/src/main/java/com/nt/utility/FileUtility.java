package com.nt.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;

import org.apache.commons.io.IOUtils;

public class FileUtility {

	// gives String representing cotent of text file (CLOB file)
	public static String readFileAsString(String filePath) throws Exception {
		String fileData = "";
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData += readData;

		}
		reader.close();
		return fileData;
	}// readFileAsString

	// Returns the contents of the Binary file(BLOB file) in the form of byte[].
	public static byte[] getBytesArrayFromFile(String fpath) {
		try {
			File file = new File(fpath);
			InputStream is = new FileInputStream(file);
			long length = file.length();

			// Create the byte array to hold the data
			byte[] bytes = new byte[(int) length];

			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}

			// Close the input stream and return bytes
			is.close();
			return bytes;
		} catch (Exception e) {
			return null;
		}
	}// method

	// gives Text File having the content represented by java.sql.Clob obj
	public static String createFileFromClob(Clob clb, String destPath) {
		try {
			Reader reader = clb.getCharacterStream();
			File data = new File(destPath);
			FileWriter writer = new FileWriter(data);
			
			IOUtils.copy(reader,writer);
			
			reader.close();
			writer.close();
		} catch (Exception e) {
		}
		return "Data Stored Successfully in to " + destPath;
	}//method

	// gives Binary File having the content represented by java.sql.Blob obj
	public static String createFileFromBlob(Blob blb, String destPath) {
		try {
			InputStream in = blb.getBinaryStream();
			FileOutputStream fos = new FileOutputStream(destPath);
			
			IOUtils.copy(in, fos);
            in.close();
			fos.close();

		} catch (Exception e) {
		}
		return "photo stored Successfully in to" + destPath;
	}

}
