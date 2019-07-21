package com.topica.maitrongnghia.main.master;

import com.topica.maitrongnghia.main.config.SourceType;
import com.topica.maitrongnghia.main.deploy.WorkingMail;
import org.apache.log4j.PropertyConfigurator;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static WorkingMail workingMail = null;
    static ArrayList<Message> arrMessage = null;
    static Logger logger = Logger.getLogger(WorkingMail.class.getName());

    public static void main(String[] args) throws MessagingException {

        PropertyConfigurator.configure(SourceType.PATH_LOG_FILE_PROPERTIE);
        arrMessage = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(SourceType.SIZE_MAX_THREAD);
        String host = SourceType.MAIL_HOST;
        String mailStoreType = SourceType.MAIL_STORE_TYPE;
        String username = SourceType.MAIL_NAME;
        String pass = SourceType.MAIL_PASS;
        int timeRun = 0;

        workingMail = new WorkingMail(SourceType.PATH_LOG_FILE_PROPERTIE);
        workingMail.openMail(host, mailStoreType, username, pass);
        while (timeRun < SourceType.TIME_RUN_SERVER) {
            Message[] allMessage = workingMail.readAllMail();
            Message[] subMassage = new Message[10];
            Message[] restMassage = null;
            arrMessage = (ArrayList<Message>) convertArrayToList(allMessage);
            if (allMessage.length > SourceType.SIZE_MAX_TASK) {
                while (arrMessage.size() > SourceType.SIZE_MAX_TASK) {
                    subMassage = convertListToArray(SourceType.SIZE_MAX_TASK);
                    TaskMail taskMail = new TaskMail(subMassage);
                    executor.execute(taskMail);
                }
                restMassage = new Message[arrMessage.size()];
                if (!arrMessage.isEmpty())
                    restMassage = convertListToArray(arrMessage.size());
                TaskMail taskMail = new TaskMail(restMassage);
                executor.execute(taskMail);
                timeRun += SourceType.TIME_CHECK_MAIL;
                try {
                    Thread.sleep(SourceType.TIME_CHECK_MAIL);
                } catch (InterruptedException e) {
                    logger.log(Level.WARNING, "InterruptedException :{0}", e);
                }
            } else {
                TaskMail taskMail = new TaskMail(allMessage);
                executor.execute(taskMail);
            }

        }
    }

    public static List<Message> convertArrayToList(Message[] messages) {
        ArrayList<Message> arrMessage = new ArrayList<>();
        arrMessage.addAll(Arrays.asList(messages));
        return arrMessage;
    }

    public static Message[] convertListToArray(int size) throws MessagingException {
        Message[] subMassage = new Message[size];
        int i = 0;
        while (arrMessage.isEmpty()) {
            subMassage[i] = arrMessage.get(i);
            arrMessage.remove(i);
            i++;
        }
        return subMassage;
    }
}
