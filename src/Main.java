import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public final static int LEVEL = 3;
    private final static int GRAVITY = 10;
    
    public static void main(String[] args) {
        Util.runExample();
        //Util.runFirst();
        //Util.runSingle(2);
        //Util.runAll();
    }
    
    /**
     * This method gets called from the Util.run... Method
     *
     * @param inputScanner the Scanner of the inputFile
     * @param outputWriter the Writer to the outputFile
     */
    public static void run(Scanner inputScanner, PrintWriter outputWriter) {
        switch (LEVEL) {
            case 1:
                level1(inputScanner, outputWriter);
                break;
            case 2:
                level2(inputScanner, outputWriter);
                break;
            case 3:
                level3(inputScanner, outputWriter);
                break;
        }
    }
    
    /**
     * Der einfachheitshalber wird anders als in der Angabe ohne Addition der Erdanziehung gerechnet, weil es leiter negierbar ist.
     */
    private static int accLevel3(int pVelocity, int pHeight, int startHeight, int destHeight) {
        int diffHeight = Math.abs(destHeight - pHeight);
        int vz = destHeight >= startHeight ? 1 : -1;
        
        // Noch am Beschleunigen nach oben
        // + pVelocity ist wichtig, weil sonst werden wir eventuell zu schnell
        if (Math.abs(pHeight + pVelocity - startHeight) < Math.abs((destHeight - startHeight) / 2)) {
            return Math.max(Math.min(destHeight - startHeight, 10), -10);
        }
        // Solange noch weit genug entfern
        if (diffHeight >= 10) {
            // Voll bremsen, sofern Geschwindigkeit noch > 10, sonst Geschwindigkeit beibehalten
            return (Math.abs(pVelocity) > 10 ? -10 * vz : 0);
        }
        // Solange noch nicht Zielhöhe erreicht
        if (diffHeight > 0) {
            // Noch die letzten Meter beschleunigen, abzüglich der Geschwindigkeit
            return (diffHeight - Math.abs(pVelocity)) * vz;
        }
        // Solange Geschwindigkeit noch nicht 0 ist, benötigt für identischen Sinkflug
        if (pVelocity != 0) {
            return -pVelocity;
        }
        return 0;
    }
    
    private static void level3(Scanner inputScanner, PrintWriter outputWriter) {
        int N = inputScanner.nextInt();
        inputScanner.nextLine();
        int TimeLimit = inputScanner.nextInt();
        inputScanner.nextLine();
        
        for (int i = 0; i < N; i++) {
            int startHeight = 0;
            int destHeight = inputScanner.nextInt();
            System.out.println("Should reach: " + destHeight);
            
            int[] velocity = new int[TimeLimit];
            int[] height = new int[TimeLimit];
            velocity[0] = 0;
            height[0] = 0;
            System.out.println("t\ta\tv\th\tup");
            boolean up = true;
            for (int t = 1; t < TimeLimit; t++) {
                int acc = accLevel3(velocity[t - 1], height[t - 1], startHeight, destHeight);
                velocity[t] = velocity[t - 1] + acc;
                height[t] = height[t - 1] + velocity[t];
                outputWriter.print((acc + GRAVITY) + " ");
                System.out.println(t + "\t" + acc + "\t" + velocity[t] + "\t" + height[t] + "\t" + up);
                if (up && height[t] == destHeight && velocity[t] == 0) {
                    up = false;
                    startHeight = destHeight;
                    destHeight = 0;
                }
                if (!up && height[t] == 0 && velocity[t] == 0) {
                    break;
                }
            }
            System.out.println();
            outputWriter.println();
        }
    }
    
    private static void level2(Scanner inputScanner, PrintWriter outputWriter) {
        int N = inputScanner.nextInt();
        inputScanner.nextLine();
        for (int i = 0; i < N; i++) {
            int velocity = 0;
            int height = 0;
            String s = inputScanner.nextLine();
            String[] parts = s.split("\\s");
            for (String p : parts) {
                if (!p.equals("")) {
                    int acceleration = Integer.parseInt(p);
                    velocity = velocity + acceleration - 10;
                    height = height + velocity;
                }
            }
            System.out.println(height);
            outputWriter.println(height);
        }
    }
    
    private static void level1(Scanner inputScanner, PrintWriter outputWriter) {
        int N = inputScanner.nextInt();
        inputScanner.nextLine();
        for (int i = 0; i < N; i++) {
            int sum = 0;
            String s = inputScanner.nextLine();
            String[] parts = s.split("\\s");
            for (String p : parts) {
                if (!p.equals("")) {
                    int velocity = Integer.parseInt(p);
                    sum += velocity;
                }
            }
            System.out.println(sum);
            outputWriter.println(sum);
        }
    }
}