import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static int LEVEL = 1;
    public static void main(String[] args) {
        //Util.runExample();
        //Util.runFirst();
        //Util.runSingle(2);
        Util.runAll();
    }

    /**
     * This method gets called from the Util.run... Method
     * @param inputScanner the Scanner of the inputFile
     * @param outputWriter the Writer to the outputFile
     */
    public static void run(Scanner inputScanner, PrintWriter outputWriter) {
        // Correct Solution to the Magic Money Challenge Level1 of the School Coding Contest from 2024-04-19
        int N = inputScanner.nextInt();
        System.out.println("Number of currencies: " + N);
        int C = inputScanner.nextInt();
        System.out.println("Number of coin values per currency: " + C);
        for (int iCurrency = 0; iCurrency < N; iCurrency++) {
            int[] coin = new int[C];
            // Read coin
            System.out.print("Coins: ");
            for (int iCoin = 0; iCoin < C; iCoin++) {
                coin[iCoin] = inputScanner.nextInt();
                System.out.print(coin[iCoin] + " ");
            }
            System.out.println();
            // Search smallest coin combination
            // Worst case if the coin are increasing numbers (e.g. 1 2 3 4), the smallest combination is the smallest coin with the biggest coin
            int smallestCoinCombination = coin[0] + coin[C - 1];
            for (int iCoin = 1; iCoin < C; iCoin++) {
                // The smallest combination is the one where the combination of the previous coin with the lowest coin combined is smaller than the current coin
                if (coin[0] + coin[iCoin - 1] < coin[iCoin]) {
                    smallestCoinCombination = coin[0] + coin[iCoin - 1];
                    break;
                }
            }
            System.out.println(smallestCoinCombination);
            outputWriter.println(smallestCoinCombination);
        }
    }
}