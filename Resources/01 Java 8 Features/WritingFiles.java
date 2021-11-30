package tryWithResources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WritingFiles {

    public static void main(String[] args) throws IOException {
        File homeFolder = new File(System.getProperty("user.home"));
        String separator = File.separator;
        String newLine = System.getProperty("line.separator");

        File outputFile = new File(homeFolder, "test.txt");

        FileWriter fileWriter = new FileWriter(outputFile, true);

        fileWriter.write("Here are some numbers" + newLine);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < 100; i++) {
            bufferedWriter.write(i + newLine);
        }

        bufferedWriter.close();

        fileWriter.close();

    }
}
