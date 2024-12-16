package nl.mpdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
  public static void main(String[] args) {

    File puzzle = new File("./puzzle.txt");
    Map<List<Integer>, String> test = createGridOfAllValues(puzzle);
    List<Integer> startLocation = new ArrayList<>();
    String direction = "up";

    test.forEach((key, value) -> {
      if (value.equals("^")) {
        startLocation.add(key.getFirst());
        startLocation.add(key.getLast());
      }
    });

    System.out.println(startLocation);

    boolean endPuzzle = false;
    while (!endPuzzle) {
      if (direction.equals("up")) {
        for (int i = startLocation.getFirst(); i >= 0; i--) {
          System.out.println(test.get(List.of(i, startLocation.getLast())));
          if (test.get(List.of(i, startLocation.getLast())).equals("#")) {
            System.out.println("Found it! Stopped at  " + startLocation);
            startLocation.set(1, startLocation.getLast() + 1);
            break;
          }
          startLocation.set(0, i);
          startLocation.set(1, startLocation.getLast());
        }
        direction = "right";
      }
      else if (direction.equals("right")) {
        for (int i = (startLocation.getLast()); i <= getHorizontalSize(puzzle) ; i++) {
          System.out.println(test.get(List.of(startLocation.getFirst(), i)));
          if (test.get(List.of(startLocation.getFirst(), i)).equals("#")) {
            startLocation.set(1, startLocation.getLast() + 1);
            System.out.println("Found it! Stopped at  " + startLocation);
            startLocation.set(1, startLocation.getFirst() + 1);
            break;
          }
          startLocation.set(0, startLocation.getFirst());
          startLocation.set(1, i);
        }
        direction = "bottom";
        endPuzzle = true;
      }
//      else if (direction.equals("bottom")) {
//        for (int i = (startLocation.getLast()); i <= getHorizontalSize(puzzle) ; i++) {
//          System.out.println(test.get(List.of(startLocation.getFirst(), i)));
//          if (test.get(List.of(startLocation.getFirst(), i)).equals("#")) {
//            startLocation.set(1, startLocation.getLast() + 1);
//            System.out.println("Found it! Stopped at  " + startLocation);
//            break;
//          }
//          startLocation.set(0, startLocation.getFirst());
//          startLocation.set(1, i);
//        }
//        direction = "bottom";
      }
    }

    // Locate all obstacles

    // Create a grid
    // Perhaps with hasmap where the cooordinates are used as a key
    // It could look like this (1,1 = .)

  }

  private static Map<List<Integer>, String> createGridOfAllValues(File puzzle) {
    Map<List<Integer>, String> test = new LinkedHashMap<>();
    try {
      Scanner scanner = new Scanner(puzzle);
      int indexOfLine = 0;
      while (scanner.hasNextLine()) {
        String puzzleLine = scanner.nextLine();
        for (int i = 0; i < puzzleLine.length(); i++) {
          List<Integer> key = Arrays.asList(indexOfLine, i);
          test.put(key, Character.toString(puzzleLine.charAt(i)));
        }
        indexOfLine++;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return test;
  }

  public static int getHorizontalSize(File puzzle) {
    try {
      Scanner scanner = new Scanner(puzzle);
      if (scanner.hasNextLine()) {
        return scanner.nextLine().length();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();

    }
    return 0;
  }
}
