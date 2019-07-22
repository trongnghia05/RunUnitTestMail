package com.topica.maitrongnghia.main.deploy;

import com.topica.maitrongnghia.main.config.MessageSend;
import com.topica.maitrongnghia.main.config.SourceType;
import com.topica.maitrongnghia.main.display.WorkingMailInterface;
import org.apache.log4j.PropertyConfigurator;
import test.TestCase;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkingMail implements WorkingMailInterface {

    static Logger logger = Logger.getLogger(WorkingMail.class.getName());
    Store store = null;
    Folder mailFolder = null;
    Message[] messages = null;
    Multipart multiPart = null;
    String log4jConfigFile = "";

    public WorkingMail(String pathLogConfig) {
        log4jConfigFile = pathLogConfig;

    }

    public WorkingMail() {

    }

    @Override
    public boolean openMail(String hostMail, String mailStoreType, String username, String password) {
        PropertyConfigurator.configure(log4jConfigFile);
        try {
            Properties properties = new Properties();
            properties.put(SourceType.PROPERTIE_HOST, hostMail);
            properties.put(SourceType.PROPERTIE_PORT, SourceType.MAIL_LOCATION_PORT);
            properties.put(SourceType.PROPERTIE_STARTTLS, "true");
            Session emailSession = Session.getDefaultInstance(properties);
            store = emailSession.getStore(SourceType.STORE_TYPE);
            store.connect(hostMail, username, password);
        } catch (NoSuchProviderException e) {
            logger.log(Level.WARNING, "NoSuchProviderException : {0}", e);
            return false;
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "MessagingException : {0}", e);
            return false;
        }


        return true;
    }

    @Override
    public boolean sendMail(String mailName, String message) {
        Transport transport = null;
        try {
            Properties mailServerProperties;
            Session getMailSession;
            MimeMessage mailMessage;

            mailServerProperties = System.getProperties();

            mailServerProperties.put(SourceType.MAIL_SERVER_PROPERTIE_HOST, SourceType.MAIL_SERVER_HOST);
            mailServerProperties.put(SourceType.MAIL_SERVER_PROPERTIE_AUTH, "true");
            mailServerProperties.put(SourceType.MAIL_SERVER_PROPERTIE_STARTTLS, "true");

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            mailMessage = new MimeMessage(getMailSession);

            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailName));
            mailMessage.setSubject(SourceType.MAIL_SUB_JECT);

            mailMessage.setText(message);

            transport = getMailSession.getTransport(SourceType.SESSION_TRANSPORT);

            transport.connect(SourceType.MAIL_TRANSPORT_HOST, SourceType.MAIL_NAME, SourceType.MAIL_PASS);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            logger.log(Level.WARNING, "AddressException" + e);
            return false;

        } catch (MessagingException e) {
            logger.log(Level.WARNING, "MessagingException" + e);
            return false;
        }
        return true;
    }

    @Override
    public Message[] readAllMail() {
        try {
            mailFolder = store.getFolder(SourceType.FORDER_TYPE);
            mailFolder.open(Folder.READ_ONLY);
            messages = mailFolder.getMessages();
            logger.log(Level.INFO, "messages.length---{0}", messages.length);
            return messages;
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "MessagingException :{0}", e);
        }
        return messages;
    }

    @Override
    public Message[] readMailWithTime(String timeStart, String timeFinish) {
        return new Message[0];
    }

    @Override
    public boolean saveFileFromMail(MimeBodyPart part, String sender) {
        WorkingMail workingMail = new WorkingMail();
        WorkingFile workingFile = new WorkingFile();

        try {
            String pathFile = SourceType.PATH_SAVE_FILE_ATTACHMENT + part.getFileName();
            part.saveFile(pathFile);
            logger.log(Level.INFO, "Save file :{0}", part.getFileName());
            if (workingFile.extractFile(pathFile)) {
                runTest();
                String result = workingFile.readFile(SourceType.PATH_FILE_COMPARE);
                workingMail.sendMail(sender, MessageSend.MESSAGE_4 + result);
                return true;
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException :{0}", e);
            return false;
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "MessagingException :{0}", e);
            return false;
        }
        return true;
    }

    public void runTest() {
        TestCase testCase = new TestCase();
        try {
            testCase.start();
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "InterruptedException : {0}", e);
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException : {0}", e);
        }
    }
}
