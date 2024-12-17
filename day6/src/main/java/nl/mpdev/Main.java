package nl.mpdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
  public static void main(String[] args) {

    File puzzle = new File("./puzzle.txt");
    int horizontalSize = getHorizontalSize(puzzle);
    int verticalSize = getVerticalSize(puzzle);
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

    System.out.println(test.get(List.of(100,0)));

    boolean endPuzzle = false;
    while (!endPuzzle) {
      if (direction.equals("up")) {
        for (int i = startLocation.getFirst(); i >= -1; i--) {
          if (test.get(List.of(i, startLocation.getLast())) == null || test.get(List.of(i, startLocation.getLast())).equals("0")) {
            endPuzzle = true;
            System.out.println("Exit at " + startLocation);
            break;
          }
//          System.out.println(test.get(List.of(i, startLocation.getLast())));
          if (test.get(List.of(i, startLocation.getLast())).equals("#")) {
            System.out.println("Found it! Stopped at  " + startLocation);
            break;
          }
          test.replace(List.of(i, startLocation.getLast()), "X");
          System.out.println(test.get(List.of(i, startLocation.getLast())));
          startLocation.set(0, i); // Replace location with moved position
        }
        direction = "right";
      }
      else if (direction.equals("right")) {
        for (int i = (startLocation.getLast()); i <= horizontalSize; i++) {
          if (test.get(List.of(startLocation.getFirst(), i)) == null || test.get(List.of(startLocation.getFirst(), i)).equals("0")) {
            endPuzzle = true;
            System.out.println("Exit at " + startLocation);
            break;
          }
//          System.out.println(test.get(List.of(startLocation.getFirst(), i)));
          if (test.get(List.of(startLocation.getFirst(), i)).equals("#")) {
            System.out.println("Found it! Stopped at  " + startLocation);
            break;
          }
          test.replace(List.of(startLocation.getFirst(), i), "X");
//          System.out.println(test.get(List.of(startLocation.getFirst(), i)));
          startLocation.set(1, i);
        }
        direction = "bottom";
      }
      else if (direction.equals("bottom")) {
        for (int i = (startLocation.getFirst()); i <= verticalSize; i++) {
          if (test.get(List.of(i, startLocation.getLast())) == null || test.get(List.of(i, startLocation.getLast())).equals("0")) {
            endPuzzle = true;
            System.out.println("Exit at " + startLocation);
            break;
          }
//          System.out.println(test.get(List.of(i, startLocation.getLast())));
          if (test.get(List.of(i, startLocation.getLast())).equals("#")) {
            System.out.println("Found it! Stopped at  " + startLocation);
            break;
          }
          test.replace(List.of(i, startLocation.getLast()), "X");
//          System.out.println(test.get(List.of(i, startLocation.getLast())));
          startLocation.set(0, i);
        }
        direction = "left";

      }
      else if (direction.equals("left")) {
        for (int i = (startLocation.getLast()); i >= -1; i--) {
          if(startLocation.getLast().equals(0)) {
            System.out.println("show me");
          }
          if (test.get(List.of(startLocation.getFirst(), i)) == null || test.get(List.of(startLocation.getFirst(), i)).equals("0")) {
            endPuzzle = true;
            System.out.println("Exit at " + startLocation);
            break;
          }
//          System.out.println(test.get(List.of(startLocation.getFirst(), i)));
          if (test.get(List.of(startLocation.getFirst(), i)).equals("#")) {
            System.out.println("Found it! Stopped at  " + startLocation);
            break;
          }
          test.replace(List.of(startLocation.getFirst(), i), "X");
//          System.out.println(test.get(List.of(startLocation.getFirst(), i)));
          startLocation.set(1, i);
        }
        direction = "up";
      }
    }
    int countX = 0;
    for (Map.Entry<List<Integer>, String> entry : test.entrySet()) {
      if (entry.getValue().equals("X")) {
        countX++;
      }
    }
    System.out.println(countX);

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

  public static int getVerticalSize(File puzzle) {
    int lineCounter = 0;
    try {
      Scanner scanner = new Scanner(puzzle);
      while (scanner.hasNextLine()) {
        scanner.nextLine();
        lineCounter++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return lineCounter;
  }

}


