package nl.mpdev;

import java.util.*;

public class Concatenation {
  private final List<String> results;
  private final Map<String, List<String>> concatenationLists;
  private final Set<String> firstStageResults;

  public Concatenation(List<String> input) {
    results = new ArrayList<>();
    firstStageResults = new HashSet<>();
    concatenationLists = new LinkedHashMap<>();

    // Parse input into concatenationLists
    for (String insideInput : input) {
      var splitString = insideInput.split(":");
      String key = splitString[0].trim();
      var splitNumbers = splitString[1].trim().split("\\s+");
      concatenationLists.put(key, List.of(splitNumbers));
    }

    generateInitialCalculations();
    generateConcatenationArrays();
  }

  /**
   * Generates results for the first stage of calculations and stores them in firstStageResults.
   */
  private void generateInitialCalculations() {
    int count = 0;
    for (Map.Entry<String, List<String>> entry : concatenationLists.entrySet()) {
      System.out.println(count++);
      String key = entry.getKey();
      List<String> numbers = entry.getValue();

      List<List<String>> stageResults = new ArrayList<>();
      generateRecursiveWithCalculations(numbers, 0, new ArrayList<>(), stageResults);

      // Format results with key and store
      for (List<String> result : stageResults) {
        String formatted = key + ": " + String.join(" ", result);
        firstStageResults.add(formatted);
      }
    }
  }

  /**
   * Generates final concatenation arrays using first-stage results.
   */
  private void generateConcatenationArrays() {
    System.out.println("second part of concatenations");
    Long count = 0L;
    for (String firstStage : firstStageResults) {
      System.out.println(count++);
      String[] split = firstStage.split(":");
      String key = split[0].trim();
      List<String> numbers = List.of(split[1].trim().split("\\s+"));

      List<List<String>> finalResults = new ArrayList<>();
      generateRecursive(numbers, 0, new ArrayList<>(), finalResults);

      // Format results with key and add to final results
      for (List<String> result : finalResults) {
        String formatted = key + ": " + String.join(" ", result);
        results.add(formatted);
      }
    }
  }

  /**
   * Recursive logic for initial calculations.
   */
  private void generateRecursiveWithCalculations(List<String> numbers, int index, List<String> current, List<List<String>> stageResults) {
    if (index == numbers.size()) {
      stageResults.add(new ArrayList<>(current));
      return;
    }

    // Option 1: Add the current number as a new entry
    current.add(numbers.get(index));
    generateRecursiveWithCalculations(numbers, index + 1, current, stageResults);
    current.remove(current.size() - 1); // Backtrack

    // Option 2: Concatenate with calculation
    if (!current.isEmpty()) {
      int lastIndex = current.size() - 1;
      String last = current.get(lastIndex);
      String concatenated = last + numbers.get(index);

      // Perform some calculation
      Calculation calculation = new Calculation(List.of(last, numbers.get(index)));
      Set<Long> calculatedResults = calculation.getResults();

      // Handle results from calculation
      for (Long result : calculatedResults) {
        current.set(lastIndex, result.toString());
        generateRecursiveWithCalculations(numbers, index + 1, current, stageResults);
      }
      current.set(lastIndex, last); // Backtrack
    }
  }

  /**
   * Recursive logic for standard concatenation.
   */
  private void generateRecursive(List<String> numbers, int index, List<String> current, List<List<String>> results) {
    if (index == numbers.size()) {
      results.add(new ArrayList<>(current));
      return;
    }

    // Option 1: Add the current number as a new entry
    current.add(numbers.get(index));
    generateRecursive(numbers, index + 1, current, results);
    current.remove(current.size() - 1); // Backtrack

    // Option 2: Concatenate
    if (!current.isEmpty()) {
      int lastIndex = current.size() - 1;
      String last = current.get(lastIndex);
      current.set(lastIndex, last + numbers.get(index)); // Concatenate
      generateRecursive(numbers, index + 1, current, results);
      current.set(lastIndex, last); // Backtrack
    }
  }

  public List<String> getResults() {
    return results;
  }
}
