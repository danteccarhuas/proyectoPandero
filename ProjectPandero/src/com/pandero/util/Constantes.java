package com.pandero.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constantes {

	public static final String ENTERO_MAS_DE_UN_DIGITO = "\\d+";
	public static final String ENTERO_SEIS_DIGITOS = "\\d{1,6}";
	public static final String ENTERO_DOS_DIGITOS = "\\d{1,2}";
	public static final String REAL_DE_DOS_DIGITOS = "\\d+\\.\\d{1,2}";
	public static final String ENTERO_NUEVE_DIGITOS = "\\d{9}";
	public static final String EMAIL = "[a-zA-Z0-9_]+[.[a-zA-Z0-9]+]*@[[a-zA-Z0-9_]+.[a-zA-Z0-9]+]+";

	public static byte[] getBytesFromFile(File file) throws IOException {
		if (file != null) {
			InputStream is = new FileInputStream(file);

			byte[] bytes = new byte[(int) file.length()];

			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			is.close();
			return bytes;
		} else {
			return null;
		}
	}

	public static File getFileFromBytes(byte[] fileBytes, String archivoDestino) {
		File f2 = null;
		try {
			f2 = new File(archivoDestino);
			OutputStream out = new FileOutputStream(f2);
			out.write(fileBytes);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f2;

	}
	
	public static String getContentType(String path){
		String contentType="";
		try {
	
			Path source = Paths.get(path);
			contentType=Files.probeContentType(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentType;
	}
	
	public static String getFileName(String path){
		String filename="";
		try {
			File file=new File(path);
			filename=file.getName();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}
}
