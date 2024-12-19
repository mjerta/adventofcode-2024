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
    List<Integer> startLocation = new ArrayList<>();
    Set<List<Integer>> visitedLocations = new HashSet<>(); // Used later to get total ammount of visit locations

    Map<List<Integer>, String> grid = createGridOfAllValues(puzzle);
    lookUpFirstStartLocation(startLocation, grid);

    walkThePuzzle("up", startLocation, grid, horizontalSize, verticalSize, visitedLocations);
    System.out.println(visitedLocations.size());
  }

  private static void walkThePuzzle(String direction, List<Integer> startLocation, Map<List<Integer>, String> grid, int horizontalSize,
                                    int verticalSize, Set<List<Integer>> visitedLocations) {

    boolean endPuzzle = false;
    while (!endPuzzle) {
      switch (direction) {
        case "up":
          for (int i = startLocation.getFirst(); i >= -1; i--) {
            List<Integer> currentPosition = List.of(i, startLocation.getLast());
            if (shouldTerminate(grid, currentPosition, startLocation, endPuzzle)) {
              endPuzzle = true;
              break;
            }
            if (lookingForObstacle(startLocation, grid, endPuzzle, currentPosition, false, i, visitedLocations)) {
              break;
            }
          }
          direction = "right";
          break;
        case "right":
          for (int i = (startLocation.getLast()); i <= horizontalSize; i++) {
            List<Integer> currentPosition = List.of(startLocation.getFirst(), i);
            if (shouldTerminate(grid, currentPosition, startLocation, endPuzzle)) {
              endPuzzle = true;
              break;
            }
            if (lookingForObstacle(startLocation, grid, endPuzzle, currentPosition, true, i, visitedLocations)) {
              break;
            }
          }
          direction = "down";
          break;
        case "down":
          for (int i = (startLocation.getFirst()); i <= verticalSize; i++) {
            List<Integer> currentPosition = List.of(i, startLocation.getLast());
            if (shouldTerminate(grid, currentPosition, startLocation, endPuzzle)) {
              endPuzzle = true;
              break;
            }
            if (lookingForObstacle(startLocation, grid, endPuzzle, currentPosition, false, i, visitedLocations)) {
              break;
            }

          }
          direction = "left";
          break;
        case "left":
          for (int i = (startLocation.getLast()); i >= -1; i--) {
            List<Integer> currentPosition = List.of(startLocation.getFirst(), i);
            if (shouldTerminate(grid, currentPosition, startLocation, endPuzzle)) {
              endPuzzle = true;
              break;
            }
            if (lookingForObstacle(startLocation, grid, endPuzzle, currentPosition, true, i, visitedLocations)) {
              break;
            }
          }
          direction = "up";
          break;
      }
    }
  }

  private static boolean lookingForObstacle(List<Integer> startLocation, Map<List<Integer>, String> grid, boolean endPuzzle,
                                            List<Integer> currentPosition, boolean isHorizontal, int i,
                                            Set<List<Integer>> visitedLocations) {
    if (grid.get(currentPosition).equals("#")) {
      System.out.println("Found it! Stopped at  " + startLocation);
      return true;
    }
    if(visitedLocations.isEmpty()) {
      System.out.println("already have");
    }
    grid.replace(currentPosition, "X");
    updateLocationBasedOnAxis(startLocation, isHorizontal, i);
    return false;
  }

  private static boolean shouldTerminate(Map<List<Integer>, String> grid, List<Integer> currentPosition, List<Integer> startLocation,
                                         boolean endPuzzle) {
    if (canExitTheGrid(grid, currentPosition)) {
      System.out.println("Exit at " + startLocation);
      return true;
    }
    return false;
  }

  private static void updateLocationBasedOnAxis(List<Integer> startLocation, boolean isHorizontal, int i) {
    if (!isHorizontal) {
      startLocation.set(0, i); // Update start location
    }
    else {
      startLocation.set(1, i); // Update start location
    }
  }

  private static boolean canExitTheGrid(Map<List<Integer>, String> grid, List<Integer> currentPosition) {
    return grid.get(currentPosition) == null || grid.get(currentPosition).equals("0");
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


