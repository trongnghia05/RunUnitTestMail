package com.topica.maitrongnghia.main.deploy;

import com.topica.maitrongnghia.main.config.MessageSend;
import com.topica.maitrongnghia.main.config.SourceType;
import com.topica.maitrongnghia.main.display.WorkingFileInterface;
import org.apache.log4j.PropertyConfigurator;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WorkingFile implements WorkingFileInterface {

    static Logger logger = Logger.getLogger("WorkingFile");


    public WorkingFile(String pathLogConfig) {
        super();
        PropertyConfigurator.configure(pathLogConfig);
    }
    public WorkingFile(){

    }

    @Override
    public boolean extractFile(String fileName) throws IOException {
        ZipEntry entry = null;
        ZipInputStream zipIs = null;
        try {
            WorkingMail workingMail = new WorkingMail();

            byte[] buffer = new byte[1024];
            int countFileRun = 0;
            File folder = new File(SourceType.PATH_OUTPUT_FILE_EXTRACT);
            if (!folder.exists())  folder.mkdirs();
            zipIs = new ZipInputStream(new FileInputStream(fileName));
            while ((entry = zipIs.getNextEntry()) != null) {
                String entryName = entry.getName();
                String[] fileNameFull = entryName.split("\\.");
                String fileExtension = fileNameFull[fileNameFull.length - 1];
                if ((fileExtension.equals(SourceType.FILE_RUN_TYPE)) && countFileRun == 0) {
                    String outFileName = SourceType.PATH_HOMEWORD + entryName;
                    int len;
                    OutputStream stream = null;
                    stream = new FileOutputStream(outFileName);
                    while ((len = zipIs.read(buffer)) > 0) {
                        stream.write(buffer, 0, len);
                    }
                    stream.close();
                    logger.log(Level.INFO, "Unzip: {0}", outFileName);

                    countFileRun++;
                } else if (countFileRun > 0) workingMail.sendMail(SourceType.MAIL_RECEIVER, MessageSend.MESSAGE_1);
            }
            return true;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception" + e);
        } finally {
            if(zipIs!=null){
                zipIs.close();
            }
        }
        return true;
    }

    @Override
    public boolean moveFile(String pathOld, String pathNew) {
        return false;
    }

    @Override
    public boolean renameFile(String namewOld, String nameNew, String pathFile) {
        return false;
    }

    @Override
    public String compareFile(String pathRightFile, String pathAcademyFile, String pathFileCompare) {
        return null;
    }

    @Override
    public void writeFile(String result, String pathFile) {
        // do something
    }

    @Override
    public String readFile(String path) {

        BufferedReader reader;
        String result="";
        String strLine;
        try {
            reader = new BufferedReader(new FileReader(path));
            while ((strLine = reader.readLine()) != null) {
                result = strLine;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }


}
