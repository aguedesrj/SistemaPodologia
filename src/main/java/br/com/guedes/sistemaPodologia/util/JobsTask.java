package br.com.guedes.sistemaPodologia.util;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JobsTask {

	public void printCurrentTime() {  
		System.out.println("Current Time : " + Calendar.getInstance().getTime());  
	}
	
	public void efetuarBackupEnvioEmail() {
		try {
			System.out.println("Efetuando backup...");
			// zipando o arquivo.
			ZipFile zipFile = new ZipFile("/Users/andre/SGPB0270.sql");
			Enumeration<?> enumeration = zipFile.entries();
			while (enumeration.hasMoreElements()) {
				ZipEntry entrada = (ZipEntry) enumeration.nextElement();  
				System.out.println(entrada.getName());
			}
			zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
