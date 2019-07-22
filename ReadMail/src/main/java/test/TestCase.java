package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestCase{
    public void start() throws InterruptedException, IOException {
        int passed = 0;
        Process compileProc = Runtime.getRuntime().exec(String.format("javac %s", "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test\\Homework.java"));
        compileProc.waitFor();
        String tempRunCmd = String.join(" ", "java","-cp", "C:\\Users\\admin\\IdeaProjects\\ReadMaik\\ReadMail\\src\\main\\java\\test", "Homework");
        Process cmdProc = Runtime.getRuntime().exec(tempRunCmd);
        BufferedReader reader = new BufferedReader(new InputStreamReader(cmdProc.getInputStream()));
        String answer = reader.readLine();
        System.out.println(answer);
    }
}
