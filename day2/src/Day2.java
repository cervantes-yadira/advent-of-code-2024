import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Solves the day 2 challenge.
 *
 * @author cervantes-yadira
 * @version 1.0
 */
public class Day2 {
    public static final String FILE_PATH = "day2/data/input.txt";

    /**
     * Calls methods to determine safe reports.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println(calculateSafeReports());
    }

    /**
     * Reads all reports from the input file and checks if they're safe.
     *
     * @return number of safe reports
     */
    public static int calculateSafeReports() {
        int totalSafeReports = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while((line = reader.readLine()) != null) {
                int[] report = Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                if(isReportSafe(report)) {
                    totalSafeReports++;
                }
            }
        } catch(IOException e) {
            throw new RuntimeException("Error reading the input file", e);
        }

        return totalSafeReports;
    }

    /**
     * Determines if a report is safe.
     * <p>
     * Reports are safe if they are consistently increasing/decreasing
     * and adjacent numbers have a difference of 1-3.
     *
     * @param report the report to be examined
     * @return true if safe, false otherwise
     */
    public static boolean isReportSafe(int[] report) {
        boolean isIncreasing = report[0] < report[1];
        int prev, curr, diff;

        for(int i = 1; i < report.length; i++) {
            prev = report[i - 1];
            curr = report[i];
            diff = Math.abs(prev - curr);

            if(diff < 1 || diff > 3) {
                return false;
            }

            if((isIncreasing && prev > curr) || (!isIncreasing && prev < curr)) {
                return false;
            }
        }

        return true;
    }
}
