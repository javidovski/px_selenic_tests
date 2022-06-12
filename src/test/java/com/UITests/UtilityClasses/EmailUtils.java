package com.UITests.UtilityClasses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.HeaderTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.sun.mail.util.MailSSLSocketFactory;

public class EmailUtils {
	
	private static HashMap<String, String> getCredentials() {
		
		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials.put("username","incoming");
		credentials.put("password","Paytronix1");
        return credentials;	
	}
	
	private static HashMap<String,Object> connect(HashMap<String,String> params) 
			throws GeneralSecurityException, InterruptedException {
		
		HashMap<String,Object> returnParams = new HashMap<String,Object>();
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);

		HashMap<String, String> credentials = getCredentials();

		String host = "wal1-email2.paytronix.com";
		String port = "993";
		String username = credentials.get("username");
		String password = credentials.get("password");
		int loginAttempts = 0;

		
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imap");
		props.setProperty("mail.imap.host", host);
		props.setProperty("mail.imap.port", port);
		props.setProperty("mail.imap.ssl.enable", "true");
		props.setProperty("mail.imap.ssl.trust", "*");
		props.put("mail.imap.ssl.socketFactory", sf);


		boolean stackTracePrinted = false;
		while (loginAttempts < 5){
			try{
				Session session = Session.getDefaultInstance(props, null);
				Store store = session.getStore("imap");
				store.connect(host, username, password);
				Folder folder = loadInbox(params, store);
				returnParams.put("store", store);
				returnParams.put("folder", folder);
				return returnParams;

			}catch(Exception e){
				System.out.println(e.getMessage());
				if (stackTracePrinted == false) {                
					e.printStackTrace();
					stackTracePrinted = true;
				}
				loginAttempts += 1;
				Thread.sleep(1000);
			}
		}
		return returnParams;

	}
	
	private static Folder loadInbox(HashMap<String,String>  params, Store store) throws InterruptedException{
        int loadAttempts = 0;
        
        Folder folder = null;
        while (loadAttempts < 5){
            try{
                folder = store.getFolder("INBOX");
                folder.open(Folder.READ_WRITE);
                return folder;
            }catch(Exception e){
                assert loadAttempts < 5 : "INBOX ERROR: Unable to load the inbox within 5 retries.";
                loadAttempts += 1;
                Thread.sleep(1000);
            }
        }
        return folder;
    }
	
	private static Message[] findEmailBySubject(HashMap<String,String>  params, HashMap<String,Object> connection) throws MessagingException{
        SearchTerm searchTerm = new SubjectTerm(params.get("subject"));
        HeaderTerm headerUnsubscribe = new HeaderTerm("List-Unsubscribe", params.get("server"));
        HeaderTerm headerReceived = new HeaderTerm("Received", params.get("server"));
        OrTerm orTerm = new OrTerm(headerUnsubscribe, headerReceived);
        AndTerm andTerm = new AndTerm(searchTerm, orTerm);
        
        Message[] messages = ((Folder)connection.get("folder")).search(andTerm);

        return messages;
    }
	
	private static void deleteEmails(HashMap<String,Object> connection, Message[] messages) throws MessagingException{
        if (messages != null && messages.length > 0) {
            int count = messages.length;
            System.out.println("Found " + count + " emails to delete");
            for (Message msg : messages){
                msg.setFlag(Flags.Flag.DELETED, true);
            }
            disconnect(connection);
        }
        else{
            System.out.println("No Emails found to delete.");
        }
    }
	
	private static void disconnect(HashMap<String,Object> connection) throws MessagingException {
        ((Folder)connection.get("folder")).close(true);
        ((Store)connection.get("store")).close();
    }
	
	
	
	private static Message[] assertFindEmailBySubject(HashMap<String,String>  params) throws MessagingException, GeneralSecurityException, InterruptedException{
        int searchAttempts = 0;
        Message[] messages = null;
        int wait = 0;
        if (params.get("Wait") != null) {
          wait = Integer.parseInt(params.get("Wait"));
        }
       
        
        boolean emailExpected = true;
        if (params.get("Email Expected") != null) {
         emailExpected = Boolean.parseBoolean(params.get("Email Expected"));
        }
        
        while (searchAttempts < 30 || wait > 0){
        	System.out.println("Checking for the email ...");
            HashMap<String, Object> connection = connect(params);
 
            messages = findEmailBySubject(params,connection);
            if (emailExpected == false) {
            	assertEquals(0,messages.length,"EMAIL ERROR:  An email was found with the subject " + params.get("subject") + " sent from " + params.get("server"));
            }
            
            else {
               if ( messages.length > 0 ){
            	   //System.out.println("The email was found!");
                  return messages;
               }
               
            }
            
            searchAttempts += 1;
            disconnect(connection);
            Thread.sleep(1000);
            wait--;
        }
        
        if (emailExpected == true) {
        	//if we get here, messages.length must = 0
        	assertNotEquals(0,messages.length,"EMAIL ERROR:  No emails were found with the subject " + params.get("subject") + " sent from " + params.get("server"));
        }
        
        return messages;
    }
	
	public static void deleteEmailsBySubject(String subject, String fromServer) throws GeneralSecurityException, InterruptedException, MessagingException{

		HashMap<String,String> emailParams = new HashMap<String,String>();
		emailParams.put("subject",subject);
		emailParams.put("server",fromServer);
		HashMap<String,Object> connection = connect(emailParams);
        Message[] messages = findEmailBySubject(emailParams, connection);
        deleteEmails(connection, messages);
    }
	
	public static void verifyEmailReceived(String subject, String fromServer, String wait, String emailExpected) throws MessagingException, GeneralSecurityException, InterruptedException{
		
		HashMap<String,String> emailParams = new HashMap<String,String>();
		emailParams.put("subject",subject);
		emailParams.put("server",fromServer);
		emailParams.put("Wait", wait);
		emailParams.put("Email Expected", emailExpected);
		assertFindEmailBySubject(emailParams);
    }
	
	public static String returnEmailHRefURLBySubject(String subject, String fromServer, String wait, 
			String emailExpected) throws MessagingException, GeneralSecurityException, 
			InterruptedException, IOException {
        
		HashMap<String,String> emailParams = new HashMap<String,String>();
		emailParams.put("subject",subject);
		emailParams.put("server",fromServer);
		emailParams.put("Wait", wait);
		emailParams.put("Email Expected", emailExpected);
		Message[] messages = assertFindEmailBySubject(emailParams);
        String url = getHRefURL(messages);
        
        return url;
    }
	
	public static String getHRefURL(Message[] messages) throws MessagingException, IOException {
        String urlReg = "href=\"([^\"]*)";
        int count = messages.length;
        String foundURL = null;
        //System.out.println("Email message count: " + count);
        
        if (count > 0){
            for (Message msg : messages){
                Object content = msg.getContent();
                if (content instanceof Multipart){
                	//System.out.println("inside first if");
                    Multipart mp = (Multipart) content;
                    for (int i=0; i< mp.getCount(); i++){
                    	//System.out.println("inside first second for");
                        BodyPart bp = mp.getBodyPart(i);
                        if (Pattern.compile(Pattern.quote("text/html"),Pattern.CASE_INSENSITIVE).matcher(bp.getContentType()).find()){
                        	//System.out.println("inside second if");
                        	Object body = bp.getContent();
                        	// Looking for the first href with a URL match and returning
                        	//https://www.baeldung.com/java-matcher-find-vs-matches
                        	Pattern p = Pattern.compile(urlReg);
                        	Matcher m = p.matcher((CharSequence) body);
                        	if (m.find()) {
                        		//System.out.println("inside third if");
                        		String rawURL = m.group();
                        		foundURL = rawURL.replaceAll("&#43;", "+").replace("href=\"","");
                        		//System.out.println("foundURL is " + foundURL);
                        	}
                     
                        }
                    }
                }
            }
        }
        assertNotEquals(foundURL,null, "URL ERROR: Did not find a URL that matched the supplied Regex!");
        return foundURL;
	}

}

