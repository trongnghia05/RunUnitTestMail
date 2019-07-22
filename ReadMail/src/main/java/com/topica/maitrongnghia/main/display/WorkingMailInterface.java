package com.topica.maitrongnghia.main.display;

import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;

public interface WorkingMailInterface {
	public boolean openMail(String hostMail, String mailStoreType, String username, String password);
	public boolean sendMail(String mailName, String message);
	public Message[] readAllMail();
	public Message[] readMailWithTime(String timeStart, String timeFinish);
	public boolean saveFileFromMail(MimeBodyPart part,String sender);

}
