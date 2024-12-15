package nl.mpdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

  public static void main(String[] args) {

    File orderingPuzzle = new File("./ordering-puzzle.txt");
    File puzzle = new File("./puzzle.txt");
    int finalResult = 0;

    // Make a hashmap out of ordering list
    // Split out ordering-puzzle and add to the hashmap
//    printOutArray(creatOrderingArr(orderingPuzzle, "\\|"));
    var test1 = creatOrderingArr(orderingPuzzle, "\\|");
    var test2 = creatOrderingArr(puzzle, ",");
    // Create a list of list of every number inside the puzzle
//    printOutArray(creatOrderingArr(puzzle, ","));
    //  Make the first function to follow the logic of puzzle and filter them itno a new list

    var results = filterArray(test1, test2);

    for (List<Integer> result : results) {
      int middleIndex = result.size() / 2; // Calculate the middle index

      if (result.size() % 2 == 0) {
        // If even size, return the average of the two middle numbers
        var add = result.get(middleIndex - 1) + result.get(middleIndex) / 2;
        finalResult += add;
      }
      else {
        // If odd size, return the middle element
        var add = result.get(middleIndex);
        finalResult += add;
      }
    }
    System.out.println(finalResult);

    // Create a function to get the the middle of each of this result and add them to an output variable

  }

  public static List<List<Integer>> creatOrderingArr(File orderingPuzzle, String character) {
    List<List<Integer>> completeList = new ArrayList<>();
    try {
      Scanner scanner = new Scanner(orderingPuzzle);
      while (scanner.hasNextLine()) {
        String input = scanner.nextLine();
        String[] parts = input.split(character);
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
          numbers.add(Integer.valueOf(part));
        }
        completeList.add(numbers);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return completeList;
  }

  public static void printOutArray(List<List<Integer>> arr) {
    for (List<Integer> innerList : arr) {
      for (Integer number : innerList) {
        System.out.print(number + " ");
      }
      System.out.println();
    }
  }

  public static List<List<Integer>> filterArray(List<List<Integer>> filterPuzzle, List<List<Integer>> puzzle) {
    List<List<Integer>> returnList = new ArrayList<>();
    for (List<Integer> puzzleList : puzzle) {
      boolean isValid = true;
      for (int i = 0; i < puzzleList.size() - 1; i++) {
        int count = 0;
        var nextNumber = puzzleList.get(i + 1);
        for (List<Integer> rules : filterPuzzle) {
          for (int j = 0; j < rules.size(); j += 2) {
            if (Objects.equals(puzzleList.get(i), rules.get(j)) && Objects.equals(nextNumber, rules.get(j + 1))) {
              continue;
            }
            if (Objects.equals(puzzleList.get(i), rules.get(j + 1)) && Objects.equals(nextNumber, rules.get(j))) {
              isValid = false;
              break;
            }
          }
          if (!isValid) break;
        }
        if (!isValid) break;
      }
      if (isValid) {
        returnList.add(puzzleList);
      }
    }
    return returnList;
  }
}

