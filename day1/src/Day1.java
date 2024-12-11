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
        Queue<Integer>[] lists = readInputFile(FILE_PATH);
        //System.out.println(calculateDistance(lists[0], lists[1]));
        System.out.println(calculateSimilarityScore(lists[0], lists[1]));
    }

    /**
     * Reads the given file and creates the corresponding lists.
     *
     * @param filePath the file path
     * @return an array of the created lists
     */
    public static Queue<Integer>[] readInputFile(String filePath) {
        File input = new File(filePath);
        Queue<Integer> list1 = new PriorityQueue<>();
        Queue<Integer> list2 = new PriorityQueue<>();

        try(Scanner scanner = new Scanner(input)){
            while(scanner.hasNextInt()) {
                list1.add(scanner.nextInt());

                if(scanner.hasNextInt()) {
                    list2.add(scanner.nextInt());
                }
            }
        } catch(IOException e) {
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

    /**
     * Calculates a total similarity score between 2 lists.
     *
     * @param list1 the first list
     * @param list2 the second list
     * @return the total similarity score
     */
    public static int calculateSimilarityScore(Queue<Integer> list1, Queue<Integer> list2) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        int similarityScore = 0;

        while(!list1.isEmpty()) {
            frequencies.put(list1.poll(), 0);
        }

        while(!list2.isEmpty()) {
            int current = list2.poll();

            if(frequencies.containsKey(current)) {
                frequencies.replace(current, frequencies.get(current) + 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            similarityScore += entry.getKey() * entry.getValue();
        }

        return similarityScore;
    }
}