import java.io.*;
import java.util.ArrayList;

public class Homework{

    public static final String FILE_TEST = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\document\\filetest.txt";
    public static final String FILE_RESULT = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\academy result\\ketqua.txt";
    public static final String FILE_RIGHT = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\right result\\ketqua.txt";
    public static final String FILE_COMPARE = "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\compare result\\ketqua.txt";

    static ArrayList<String> arr = null;

    public static void main(String[] args) {

        arr = new ArrayList();

        readFile(FILE_TEST);
        for (int i = 0; i < arr.size(); i++) {

            String[] param = arr.get(i).split(" ");
            int a = Integer.parseInt(param[0]);
            int b = Integer.parseInt(param[1]);
            int c = Integer.parseInt(param[2]);
            giaiPTBac2(a, b, c);
        }

        writeFile2(compareFile(FILE_RIGHT, FILE_RESULT, FILE_COMPARE), FILE_COMPARE);

    }

    public static void readFile(String path) {
        BufferedReader reader;
        String strLine;
        try {
            reader = new BufferedReader(new FileReader(path));
            while ((strLine = reader.readLine()) != null) {
                arr.add(strLine);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String result, BufferedWriter bw) {
        try {

            bw.write(result);
            bw.newLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void giaiPTBac2(float a, float b, float c) {
        File file = new File(FILE_RESULT);
        try {
            FileWriter fileWriter = new FileWriter(file, true);

            BufferedWriter bw = new BufferedWriter(fileWriter);
            // kiểm tra các hệ số
            if (a == 0) {
                if (b == 0) {
                    writeFile("null", bw);

                } else {
                    writeFile(-c / b + "", bw);
                }
                return;
            }
            // tính delta
            float delta = b * b - 4 * a * c;
            float x1;
            float x2;
            // tính nghiệm
            if (delta > 0) {
                x1 = (float) ((-b + Math.sqrt(delta)) / (2 * a));
                x2 = (float) ((-b - Math.sqrt(delta)) / (2 * a));
                writeFile(x1 + " " + x2, bw);
            } else if (delta == 0) {
                x1 = (-b / (2 * a));
                writeFile(x1 + " " + x1, bw);
            }

            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String compareFile(String pathRightFile, String pathAcademyFile, String pathFileCompare) {

        File file = new File(pathFileCompare);
        BufferedReader readerServer;
        BufferedReader readerAcademy;

        String rightLine;
        String academyLine;
        String resultCompare = "";
        int right = 0;
        int wrong = 0;
        try {

            readerServer = new BufferedReader(new FileReader(pathRightFile));
            readerAcademy = new BufferedReader(new FileReader(pathAcademyFile));

            while ((rightLine = readerServer.readLine()) != null) {
                academyLine = readerAcademy.readLine();
                if (rightLine.equals(academyLine)) {
                    right++;
                } else {
                    wrong++;
                }

            }
            resultCompare = "true :" + right + "  false :" + wrong;

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return resultCompare;
    }


    public static void writeFile2(String result, String pathFIle) {
        try {

            FileWriter fw = new FileWriter(pathFIle, true);
            fw.write(result);
            fw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}