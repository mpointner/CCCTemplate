import java.io.*;
import java.util.Scanner;

public class Util {
    public static final String inputDirectoryName = "input";
    public static final String outputDirectoryName = "output";

    public static void runAll() {
        for (int i = 0; i <= 5; i++) {
            runSingle(i);
        }
    }

    /**
     * Runs the file levelX_example.in, where X is the configured level
     */
    public static void runExample() {
        runSingle(0);
    }

    /**
     * Runs the file levelX_1.in, where X is the configured level
     */
    public static void runFirst() {
        runSingle(1);
    }

    /**
     * This method runs one the input files named level1_example.in and level1_1.in to level1_5.in
     * 
     * To understand this code, take a look at SEW5 3xHIT -> "5a.5: Filezugriff in Java Datei" 
     * https://elearning.tgm.ac.at/mod/resource/view.php?id=121525
     *
     * @param input is the number after _ or 0 in case of example
     */
    public static void runSingle(int input) {
        // Check if input file exists and create output directory if need
        String inputString = input == 0 ? "example" : String.valueOf(input);
        File inputDirectory = new File(inputDirectoryName + "/level" + Main.LEVEL);
        File inputLevel = new File(inputDirectory, "level" + Main.LEVEL + "_" + inputString + ".in");
        if (!inputLevel.exists()) {
            throw new RuntimeException("Input level does not exist, you must create the folder structure and extract the ZIP there: " + inputLevel.getAbsolutePath());
        }
        File outputDirectory = new File(outputDirectoryName);
        if (!outputDirectory.exists()) {
            if (!outputDirectory.mkdir() || !outputDirectory.isDirectory()) {
                throw new RuntimeException("Could not create directory: " + outputDirectory.getAbsolutePath());
            }
        }
        File outputLevelDirectory = new File(outputDirectory, "level" + Main.LEVEL);
        if (!outputLevelDirectory.exists()) {
            if (!outputLevelDirectory.mkdir() || !outputLevelDirectory.isDirectory()) {
                throw new RuntimeException("Could not create directory: " + outputLevelDirectory.getAbsolutePath());
            }
        }
        File outputLevel = new File(outputDirectoryName + "/level" + Main.LEVEL + "/level" + Main.LEVEL + "_" + inputString + ".out");
        
        // Run
        try (Scanner inputScanner = new Scanner(new BufferedReader(new FileReader(inputLevel)))) {
            try (PrintWriter outputWriter = new PrintWriter(outputLevel)) {
                Main.run(inputScanner, outputWriter);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
