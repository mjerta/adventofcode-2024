package nl.mpdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.ManagementPermission;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    File orderingPuzzle = new File("./ordering-puzzle.txt");

    // Make a hashmap out of ordering list

    // Split out ordering-puzzle and add to the hashmap
    Map<Integer, Integer> map = createOrderingHashMap(orderingPuzzle);
    for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
      System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
    }
    System.out.println(map.get(11));


    // Create a list of list of every number inside the puzzle

    //  Make the first function to follow the logic of puzzle and filter them itno a new list

    // Create a function to get the the middle of each of this result and add them to an output variable

  }

  public static Map<Integer, Integer> createOrderingHashMap(File orderingPuzzle) {
    Map<Integer, Integer> map = new HashMap<>();
    try {
      Scanner scanner = new Scanner(orderingPuzzle);

      while (scanner.hasNextLine()) {
        String input = scanner.nextLine();
        Integer pipeIndex = input.indexOf("|");
        String firstPart = input.substring(0, pipeIndex);
        String secondPart = input.substring(pipeIndex + 1);
        map.put(Integer.parseInt(firstPart),Integer.parseInt(secondPart));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return map;
  }
}