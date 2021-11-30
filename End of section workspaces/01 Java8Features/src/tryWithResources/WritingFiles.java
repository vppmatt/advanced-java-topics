package tryWithResources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WritingFiles {

    public static void main(String[] args) {
        File homeFolder = new File(System.getProperty("user.home"));
        String separator = File.separator;
        String newLine = System.getProperty("line.separator");

        File outputFile = new File(homeFolder, "test.txt");


        try (FileWriter fileWriter = new FileWriter(outputFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {

            fileWriter.write("Here are some numbers" + newLine);

            for (int i = 0; i < 100; i++) {
                bufferedWriter.write(i + newLine);
                System.out.println(100 / i);
            }

        } catch (IOException e) {
            System.out.println("IO problem");
        } catch (ArithmeticException e) {
            System.out.println("Math problem");
        }

    }
}
