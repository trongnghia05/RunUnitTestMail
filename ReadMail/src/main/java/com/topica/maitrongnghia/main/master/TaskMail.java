package com.topica.maitrongnghia.main.master;

import com.topica.maitrongnghia.main.config.MessageSend;
import com.topica.maitrongnghia.main.config.SourceType;
import com.topica.maitrongnghia.main.deploy.WorkingMail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskMail implements Runnable{

    WorkingMail workingMail = null;
    Logger logger = Logger.getLogger(WorkingMail.class.getName());
    final String REGEX = "<[\\w\\d@\\.]+>";
    Message[] messages;
    public TaskMail(Message[] message){
        this.messages = message;
    }

    @Override
    public void run() {
        workingMail = new WorkingMail();
        try {
            handleMail(messages);
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "MessagingException :{0}", e);
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException :{0}", e);
        }
    }


    public  void handleMail(Message[] messages) throws MessagingException, IOException {
        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            String sender ="";
            if (message.getSubject().equals(SourceType.SUB_JECT)) {
                if (message.getContentType().contains("multipart")) {
                    sender = getSender(Arrays.toString(message.getFrom()));
                    logInfoMail(message);
                    Multipart multiPart = (Multipart) message.getContent();
                    for (int j = 0; j < multiPart.getCount(); j++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(j);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            String[] str = part.getFileName().split("\\.");
                            if (str[str.length - 1].equals(SourceType.ATTACHMENT_TYPE))
                                workingMail.saveFileFromMail(part,sender);
                            else  workingMail.sendMail(sender, MessageSend.MESSAGE_2);
                        }
                    }
                }
            } else workingMail.sendMail(sender, MessageSend.MESSAGE_3);
        }
    }

    public  void logInfoMail(Message message) {
        try {
            logger.log(Level.WARNING, "Subject :{0}", message.getSubject());
            logger.log(Level.WARNING, "From :{0}", message.getFrom()[0]);
            logger.log(Level.WARNING, "Date Send :{0}", message.getSentDate());
        } catch (MessagingException e) {
            logger.log(Level.WARNING, "MessagingException :{0}", e);
        }

    }
    public  String  getSender(String str){
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(str);
        String result = "";
        while  (matcher.find()) {
           result =str.substring(matcher.start()+1, matcher.end()-1);

        }
        return result;
    }


}
