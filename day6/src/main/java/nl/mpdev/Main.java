package nl.mpdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
  static int countDuplicates = 0;

  public static void main(String[] args) {

    File puzzle = new File("./puzzle.txt");
    int horizontalSize = getHorizontalSize(puzzle);
    int verticalSize = getVerticalSize(puzzle);
    Map<List<Integer>, String> grid = createGridOfAllValues(puzzle);


    for (Map.Entry<List<Integer>, String> entry : grid.entrySet()) {
      String originalValue = entry.getValue();
      grid = createGridOfAllValues(puzzle);
      List<Integer> startLocation = new ArrayList<>();
      lookUpFirstStartLocation(startLocation, grid);
      Set<List<Integer>> visitedLocations = new HashSet<>();
      String startingDirection = "up";
      System.out.println(entry.getKey().getFirst());
      List<Integer> key = entry.getKey();
      walkThePuzzle(startingDirection, startLocation, grid, horizontalSize, verticalSize, visitedLocations);
      if (grid.get(entry.getKey()).equals("X")) {
        grid.replace(key, "#");
        System.out.println("Obstacle is placed on :" + key);
      }
      walkThePuzzle(startingDirection, startLocation, grid, horizontalSize, verticalSize, visitedLocations);
      if (key != startLocation) {
        grid.replace(key, originalValue);
      }
    }
    getAmmountOfWalkedPositions(grid);
  }

  private static void walkThePuzzle(String direction, List<Integer> startLocation, Map<List<Integer>, String> grid, int horizontalSize,
                                    int verticalSize, Set<List<Integer>> visitedLocations) {

    boolean endPuzzle = false;
    while (!endPuzzle) {
      switch (direction) {
        case "up":
          endPuzzle = walkUp(startLocation, grid, endPuzzle, visitedLocations);
          direction = "right";
          break;
        case "right":
          endPuzzle = walkRight(startLocation, horizontalSize, grid, endPuzzle, visitedLocations);
          direction = "down";
          break;
        case "down":
          endPuzzle = walkDown(startLocation, verticalSize, grid, endPuzzle, visitedLocations);
          direction = "left";
          break;
        case "left":
          endPuzzle = walkLeft(startLocation, grid, endPuzzle, visitedLocations);
          direction = "up";
          break;
      }
    }
  }

  private static void getAmmountOfWalkedPositions(Map<List<Integer>, String> grid) {
    int countX = 0;
    for (Map.Entry<List<Integer>, String> entry : grid.entrySet()) {
      if (entry.getValue().equals("X")) {
        countX++;
      }
    }
    System.out.println(countX);
  }

  private static boolean walkLeft(List<Integer> startLocation, Map<List<Integer>, String> grid, boolean endPuzzle,
                                  Set<List<Integer>> visitedLocations) {
    int previousRow = startLocation.get(1);
    List<Integer> currentPosition = List.of();
    for (int i = (startLocation.getLast()); i >= -1; i--) {
      currentPosition = List.of(startLocation.getFirst(), i);
//      if (startLocation.getLast().equals(0)) {
//        System.out.println("show me");
//      }
      if (grid.get(currentPosition) == null || grid.get(currentPosition).equals("0")) {
        endPuzzle = true;
//        System.out.println("Exit at " + startLocation);
        break;
      }
      if (grid.get(currentPosition).equals("#")) {
//        System.out.println("Found it! Stopped at  " + startLocation);
        if (!visitedLocations.add(List.of(startLocation.getFirst(), startLocation.getLast()))) {
//          int difference = Math.abs(previousRow - i); // Calculate the row difference
//          if (difference == 1 || difference == 0) { // If the difference is exactly one step
//            System.out.println("Found a match at " + currentPosition + " with a difference of 1 from the previous row.");
//            return true;
//          }
//          else {
//            System.out.println("Found a match at " + currentPosition + " but difference is " + difference);
//          }
          System.out.println("Found duplicate");
          return true;
        }
        break;
      }
      grid.replace(currentPosition, "X");
      startLocation.set(1, i);
    }
    return endPuzzle;
  }

  private static boolean walkDown(List<Integer> startLocation, int verticalSize, Map<List<Integer>, String> grid, boolean endPuzzle,
                                  Set<List<Integer>> visitedLocations) {
    int previousRow = startLocation.get(0);
    List<Integer> currentPosition = List.of();
    for (int i = (startLocation.getFirst()); i <= verticalSize; i++) {
      currentPosition = List.of(i, startLocation.getLast());
      if (grid.get(currentPosition) == null || grid.get(currentPosition).equals("0")) {
        endPuzzle = true;
//        System.out.println("Exit at " + startLocation);
        break;
      }
      if (grid.get(currentPosition).equals("#")) {
//        System.out.println("Found it! Stopped at  " + startLocation);
        if (!visitedLocations.add(List.of(startLocation.getFirst(), startLocation.getLast()))) {
//          int difference = Math.abs(previousRow - i); // Calculate the row difference
//          if (difference == 1 || difference == 0) { // If the difference is exactly one step
//            System.out.println("Found a match at " + currentPosition + " with a difference of 1 from the previous row.");
//            return true;
//          }
//          else {
//            System.out.println("Found a match at " + currentPosition + " but difference is " + difference);
//          }
          System.out.println("Found duplicate");
          return true;
        }
        break;
      }
      grid.replace(currentPosition, "X");
      startLocation.set(0, i);
    }
    return endPuzzle;
  }

  private static boolean walkRight(List<Integer> startLocation, int horizontalSize, Map<List<Integer>, String> grid, boolean endPuzzle,
                                   Set<List<Integer>> visitedLocations) {
    int previousRow = startLocation.get(1);
    List<Integer> currentPosition = List.of();
    for (int i = (startLocation.getLast()); i <= horizontalSize; i++) {
      currentPosition = List.of(startLocation.getFirst(), i);
      if (grid.get(currentPosition) == null || grid.get(currentPosition).equals("0")) {
        endPuzzle = true;
//        System.out.println("Exit at " + startLocation);
        break;
      }
      if (grid.get(currentPosition).equals("#")) {
//        System.out.println("Found it! Stopped at  " + startLocation);
        if (!visitedLocations.add(List.of(startLocation.getFirst(), startLocation.getLast()))) {
//          int difference = Math.abs(previousRow - i); // Calculate the row difference
//          if (difference == 1 || difference == 0) { // If the difference is exactly one step
//            System.out.println("Found a match at " + currentPosition + " with a difference of 1 from the previous row.");
//            return true;
//          }
//          else {
//            System.out.println("Found a match at " + currentPosition + " but difference is " + difference);
//          }
          System.out.println("Found duplicate");
          return true;
        }
        break;
      }
      grid.replace(currentPosition, "X");
      startLocation.set(1, i);
    }
    return endPuzzle;
  }

  private static boolean walkUp(List<Integer> startLocation, Map<List<Integer>, String> grid, boolean endPuzzle,
                                Set<List<Integer>> visitedLocations) {
    int previousRow = startLocation.get(0);
    List<Integer> currentPosition = List.of();
    for (int i = startLocation.getFirst(); i >= -1; i--) {
      currentPosition = List.of(i, startLocation.getLast());
      if (grid.get(currentPosition) == null || grid.get(currentPosition).equals("0")) {
        endPuzzle = true;
//        System.out.println("Exit at " + startLocation);
        break;
      }
      var valueCurrentPosition = grid.get(currentPosition);
      if (grid.get(currentPosition).equals("#")) {

//        System.out.println("Found it! Stopped at  " + startLocation);
        // get previous key to comapere and if they are the same return true.
        if (!visitedLocations.add(List.of(startLocation.getFirst(), startLocation.getLast()))) {
//          int difference = Math.abs(previousRow - i); // Calculate the row difference
//          if (difference == 1 || difference == 0) { // If the difference is exactly one step
//            System.out.println("Found a match at " + currentPosition + " with a difference of 1 from the previous row.");
//            return true;
//          }
//          else {
//            System.out.println("Found a match at " + currentPosition + " but difference is " + difference);
//          }
          System.out.println("Found duplicate");
          return true;
        }
        break;
      }
      grid.replace(currentPosition, "X");
      startLocation.set(0, i); // Replace location with moved position
    }
    return endPuzzle;
  }

  private static void lookUpFirstStartLocation(List startLocation, Map<List<Integer>, String> grid) {
    grid.forEach((key, value) -> {

      if (value.equals("^")) {
        startLocation.add(key.getFirst());
        startLocation.add(key.getLast());
      }
    });
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


