package com.topica.maitrongnghia.main.display;

public interface ValidatedInterface {

	public boolean checkSubJect(String subject);
	public boolean checkMailType(String type);
	public boolean checkAttachmentType(String type);
	public boolean checkSender(String sender);
	public boolean checkTime(String time);
	public boolean checkFileRun(String name);

}
