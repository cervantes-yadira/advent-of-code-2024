import java.io.*;
import java.util.*;

/**
 * Solves the day 1 challenge.
 *
 * @author cervantes-yadira
 * @version 1.0
 */
public class Day1 {
    public static final String FILE_PATH = "day1/data/input.txt";

    /**
     * Calls methods to calculate the distance between 2 lists.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Queue<Integer>[] lists = readInputFile();
        System.out.println(calculateDistance(lists[0], lists[1]));
    }

    /**
     * Creates a File object from a given string filepath.
     *
     * @param filePath the filepath
     * @return the created file
     */
    public static File createFile(String filePath) {
        try {
            return new File(filePath);
        } catch (java.lang.RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the given file and creates the corresponding lists.
     *
     * @return an array of the created lists
     */
    public static Queue<Integer>[] readInputFile() {
        File input = createFile(FILE_PATH);
        Queue<Integer> list1 = new PriorityQueue<>();
        Queue<Integer> list2 = new PriorityQueue<>();

        try (Scanner scanner = new Scanner(input)){
            while(scanner.hasNextInt()) {
                list1.add(scanner.nextInt());

                if(scanner.hasNextInt()) {
                    list2.add(scanner.nextInt());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Queue[]{list1, list2};
    }

    /**
     * Calculates the total distance between 2 lists.
     *
     * @param list1 the first list
     * @param list2 the second list
     * @return the total distance
     */
    public static int calculateDistance(Queue<Integer> list1, Queue<Integer> list2) {
        int distance = 0;

        while(!list1.isEmpty() && !list2.isEmpty()) {
            distance += (Math.abs(list1.remove() - list2.remove()));
        }

        return distance;
    }
}