package br.com.guedes.sistemaPodologia.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import br.com.guedes.sistemaPodologia.controller.UsuarioAction;

public class JobsTask {
	
	private static final Logger LOGGER = Logger.getLogger(UsuarioAction.class);
	
	private final int TAMANHO_BUFFER = 12048;
	private byte[] dados = new byte[TAMANHO_BUFFER];
	private int cont;	

	/**
	 * 
	 * @throws Exception
	 */
	public void efetuarBackupEnvioEmail() throws Exception {
		
		LOGGER.info("Efetuando backup...");
		
		// zipando o arquivo.
		FileOutputStream destino = new FileOutputStream("C:/Sistema Podologia/bd/SISTEMAPODOLOGIA.ZIP");
		ZipOutputStream saida = new ZipOutputStream(new BufferedOutputStream(destino));
		FileInputStream streamDeEntrada = new FileInputStream(new File("C:/Sistema Podologia/bd/SISTEMAPODOLOGIA.FDB"));
		BufferedInputStream origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
		ZipEntry entry = new ZipEntry("SISTEMAPODOLOGIA.FDB");
		saida.putNextEntry(entry);
		while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {   
            saida.write(dados, 0, cont);   
        }
		saida.close();
        origem.close();
        
        // preparando o email
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
		// configurando o email
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication("jeanenewpebackup@gmail.com", "backupnewpe");
      		}
        });
        // arquivo em anexo.
        MimeBodyPart anexoBody = new MimeBodyPart();
        FileDataSource fds = new FileDataSource("C:/Sistema Podologia/bd/SISTEMAPODOLOGIA.ZIP");
        anexoBody.setDataHandler(new DataHandler(fds));
        anexoBody.setFileName(fds.getName());
        
        Multipart multipart = new MimeMultipart();  
        multipart.addBodyPart(anexoBody);  
        // envio do email.
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("jeanenewpebackup@gmail.com"));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress("jeanenewpe@gmail.com"));
        message.setSubject("Backup - Sistema Podologia");
        message.setText("Arquivo em anexo...");
        message.setContent(multipart);
        Transport.send(message);
	}
}
