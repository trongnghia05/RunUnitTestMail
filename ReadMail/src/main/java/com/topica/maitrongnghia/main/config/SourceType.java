package com.topica.maitrongnghia.main.config;

public class SourceType {
	private SourceType() {
	    throw new IllegalStateException("Utility class");
	}
	public static final Integer SIZE_MAX_TASK = 10;
	public static final Integer SIZE_MAX_THREAD = 10;
	public static final String SUB_JECT = "ITLAB-HOMEWORK";
	public static final String FORDER_TYPE = "INBOX";
	public static final String OPEN_TYPE = "READ_ONLY";
	public static final String ATTACHMENT_TYPE = "zip";
	public static final String FILE_RUN_TYPE = "java";
	public static final String STORE_TYPE ="imaps";
	public static final Integer TIME_RUN_SERVER = 36000;
	public static final Integer TIME_CHECK_MAIL = 30000;
	public static final String PROPERTIE_HOST = "mail.imap.host";
	public static final String PROPERTIE_PORT = "mail.imap.port";
	public static final String MAIL_LOCATION_PORT = "995";
	public static final String PROPERTIE_STARTTLS = "mail.imap.starttls.enable";

	public static final String MAIL_HOST = "imap.gmail.com";
	public static final String MAIL_STORE_TYPE = "imap";
	public static final String MAIL_NAME = "nghiatestmail@gmail.com";
	public static final String MAIL_PASS = "maitrongnghia";

	public static final String PATH_SAVE_FILE_ATTACHMENT = "D:/Attachment/";
	public static final String PATH_OUTPUT_FILE_EXTRACT = "D:/Attachment/";
    public static final String PATH_FILE_EXTRACT = "D:/Attachment/css-circle.zip";

    //send mail

	public static final String MAIL_SERVER_PROPERTIE_HOST = "mail.smtp.port";
	public static final String MAIL_SERVER_PROPERTIE_AUTH = "mail.smtp.auth";
	public static final String MAIL_SERVER_PROPERTIE_STARTTLS = "mail.smtp.starttls.enable";
	public static final String MAIL_SERVER_HOST = "587";
	public static final String MAIL_RECEIVER = "maitrongnghia1998@gmail.com";
	public static final String MAIL_SUB_JECT = "RESULT ITLAB-HOMEWORK";
	public static final String SESSION_TRANSPORT = "smtps";
	public static final String MAIL_TRANSPORT_HOST = "smtp.gmail.com";

	//path homework

	public static final String PATH_HOMEWORD = "C:\\Users\\admin\\Desktop\\Homework\\";
	public static final String PATH_FILE_TEST = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\document\\filetest.txt";
	public static final String PATH_FILE_RESULT = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\academy result\\ketqua.txt";
	public static final String PATH_FILE_RIGHT = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\right result\\ketqua.txt";
	public static final String PATH_FILE_COMPARE = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\compare result\\ketqua.txt";

	//log

	public static final String PATH_LOG_FILE_PROPERTIE = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\resources\\log4j.properties" ;
}
