package nl.mpdev;

import java.util.*;
import java.util.stream.Collectors;

public class Line {
  private final Long output;
  private final List<Long> numbersToCalculate;
  private final String[] operators = {"+", "*"};
  private final Set<Long> results;
  private final List<String> extraResults;
  private boolean hasValidNumber = false;

  //constructor
  public Line(String input) {
    numbersToCalculate = new ArrayList<>();
    results = new LinkedHashSet<>();
    extraResults = new ArrayList<>();
    var splittedString = input.split(":");
    this.output = Long.parseLong(splittedString[0]);
    var splittedNumbersAfterOutput = splittedString[1].trim().split("\\s+");
    Arrays.stream(splittedNumbersAfterOutput)
      .forEach(number -> numbersToCalculate.add(Long.parseLong(number)));
  }

  private static void printOutExample(List<Long> firstPart, Long firstItemFromSecondPart, List<Long> secondPart) {
    System.out.print(firstPart);
    System.out.print(" || ");
    System.out.print(firstItemFromSecondPart + " ");
    if (!secondPart.isEmpty()) {
      System.out.print(secondPart);
    }
  }

  public void executeCalculations(long currentIndex, long currentValue) {
    if (currentIndex == numbersToCalculate.size()) {
      results.add(currentValue);
      return;
    }
    for (String operator : operators) {

      if (operator.equals("+")) {
        executeCalculations(currentIndex + 1, currentValue + numbersToCalculate.get((int) currentIndex));

      }
      else if (operator.equals("*")) {
        executeCalculations(currentIndex + 1, currentValue * numbersToCalculate.get((int) currentIndex));
      }
    }
  }

  public void executeCalculationsWithConcat(long currentIndex, Long currentValue) {

    if (currentIndex == numbersToCalculate.size()) {
      results.add(currentValue);
      return;
    }

    for (String operator : operators) {
      if (operator.equals("+")) {
        executeCalculationsWithConcat(currentIndex + 1, currentValue + numbersToCalculate.get((int) currentIndex));
      }
      else if (operator.equals("*")) {
        executeCalculationsWithConcat(currentIndex + 1, currentValue * numbersToCalculate.get((int) currentIndex));
      }
    }
  }

  public void validateResults() {
    if (!results.isEmpty()) {
      for (long result : results) {
        if (output.equals(result)) {
          hasValidNumber = true;
          break;
        }
      }
    }
  }

  public void validateResults(Set<Long> listToValidate) {
    if (!listToValidate.isEmpty()) {
      for (long result : listToValidate) {
        if (output.equals(result)) {
          hasValidNumber = true;
          break;
        }
      }
    }
  }

  public Long getOutput() {
    return output;
  }

  public List<Long> getNumbersToCalculate() {
    return numbersToCalculate;
  }

  public boolean isHasValidNumber() {
    return hasValidNumber;
  }

  public Set<Long> getResults() {
    return results;
  }

  public List<String> getExtraResults() {
    return extraResults;
  }
}