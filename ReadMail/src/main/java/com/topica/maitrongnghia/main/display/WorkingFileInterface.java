package com.topica.maitrongnghia.main.display;

import java.io.IOException;

public interface WorkingFileInterface {

    public boolean extractFile(String fileName) throws IOException;
    public boolean moveFile(String pathOld, String pathNew);
    public boolean renameFile(String namewOld, String nameNew, String pathFile);
    public String compareFile(String pathRightFile,String pathAcademyFile, String pathFileCompare);
    public void writeFile(String result,String pathFile);
    public String readFile(String path);


}
