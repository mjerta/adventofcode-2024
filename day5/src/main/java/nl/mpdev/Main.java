package nl.mpdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
  public static void main(String[] args) {

    File orderingPuzzle = new File("./ordering-puzzle.txt");
    File puzzle = new File("./puzzle.txt");
    boolean stateOfLine = false;

    // Make a hashmap out of ordering list
    // Split out ordering-puzzle and add to the hashmap
//    printOutArray(creatOrderingArr(orderingPuzzle, "\\|"));
    var test1 = creatOrderingArr(orderingPuzzle, "\\|");
    var test2 = creatOrderingArr(puzzle, ",");
    // Create a list of list of every number inside the puzzle
//    printOutArray(creatOrderingArr(puzzle, ","));
    //  Make the first function to follow the logic of puzzle and filter them itno a new list

    filterOuterArray(test1, test2);




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

  public static void filterOuterArray(List<List<Integer>> filterPuzzle, List<List<Integer>> puzzle) {
    for (List<Integer> innerlist : puzzle) {
      for (int i = 0; i < innerlist.size(); i++) {

        // LOOP OUT FILTERPUZZLE
        System.out.println(innerlist.get(i));
      }
    }

  }

  public static boolean lookUpNextCorrectNumber(Integer firstNumber, Integer secondNumber) {
    return (firstNumber < secondNumber);
  }
}