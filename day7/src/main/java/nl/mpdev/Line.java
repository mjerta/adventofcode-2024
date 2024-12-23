package nl.mpdev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {
  private final Long output;
  private final List<Long> numbersToCalculate;
  private final List<Long> numbersToCalculateFirstPart;
  private final List<List<Long>> numbersToCalculateSecondPart;
  private final String[] operators = {"+", "*"};
  private final List<Long> results;
  private final List<Long> resultsNumbersToCalculateFirstPart;
  private final List<Long> resultsNumbersToCalculateSecondPart;
  private List<Long> firstArrayItemNumberToCalculateSecondPart;
  private boolean hasValidNumber = false;
  private String firstPartWithFirstItemSecondPart;

  //constructor
  public Line(String input) {
    numbersToCalculate = new ArrayList<>();
    numbersToCalculateFirstPart = new ArrayList<>();
    firstArrayItemNumberToCalculateSecondPart = new ArrayList<>();
    numbersToCalculateSecondPart = new ArrayList<>();
    results = new ArrayList<>();
    resultsNumbersToCalculateFirstPart = new ArrayList<>();
    resultsNumbersToCalculateSecondPart = new ArrayList<>();
    var splittedString = input.split(":");
    this.output = Long.parseLong(splittedString[0]);
    var splittedNumbersAfterOutput = splittedString[1].trim().split("\\s+");
    Arrays.stream(splittedNumbersAfterOutput)
      .forEach(number -> numbersToCalculate.add(Long.parseLong(number)));
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

  public void executeCalculations(long currentIndex, long currentValue, List<Long> numbersToCalculate) {
    if (numbersToCalculate.size() == 1) {
      return;
    }
    if (currentIndex == numbersToCalculate.size()) {
      resultsNumbersToCalculateFirstPart.add(currentValue);
      return;
    }
    for (String operator : operators) {
      if (operator.equals("+")) {
        executeCalculations(currentIndex + 1, currentValue + numbersToCalculate.get((int) currentIndex), numbersToCalculate);
      }
      else if (operator.equals("*")) {
        executeCalculations(currentIndex + 1, currentValue * numbersToCalculate.get((int) currentIndex), numbersToCalculate);
      }
    }
  }

  public void addConcatenation() {
    for (int i = 1; i <= numbersToCalculate.size(); i++) {
      if (i % 2 != 0) {
        numbersToCalculateFirstPart.clear();
        var firstPart = numbersToCalculate.subList(0, i);
        // Not needed but just to display
        var firstItemFromSecondPart = numbersToCalculate.get(i);
        firstArrayItemNumberToCalculateSecondPart.add(firstItemFromSecondPart);
        var secondPart = numbersToCalculate.subList(i + 1, numbersToCalculate.size());
        System.out.print(firstPart);
        System.out.print(" || ");
        System.out.print(firstItemFromSecondPart + " ");
        if (!secondPart.isEmpty()) {
          System.out.print(secondPart);
        }
        numbersToCalculateFirstPart.addAll(firstPart);
        if (!secondPart.isEmpty()) {
          numbersToCalculateSecondPart.add(secondPart);
        }
        executeCalculations(1, numbersToCalculateFirstPart.getFirst(), numbersToCalculateFirstPart);
        for (Long result : resultsNumbersToCalculateFirstPart) {
          System.out.println();
          var test = result.toString() + firstItemFromSecondPart;
          System.out.println(test);
          if (!secondPart.isEmpty()) {
            List<Long> anotherList = new ArrayList<>();
            anotherList.add(Long.parseLong(test));
            anotherList.addAll(secondPart);
            System.out.println(anotherList);
          }
        }
      }
      if (i % 2 == 0) {
        // Not needed but just to display
        var firstPart = numbersToCalculate.subList(0, i);
        var firstItemFromSecondPart = numbersToCalculate.get(i);
        firstArrayItemNumberToCalculateSecondPart.add(firstItemFromSecondPart);
        var secondPart = numbersToCalculate.subList(i + 1, numbersToCalculate.size());
        if (!secondPart.isEmpty()) {
          numbersToCalculateSecondPart.add(secondPart);
        }
        System.out.print(firstPart);
        System.out.print(" || ");
        System.out.print(firstItemFromSecondPart + " ");
        if (!secondPart.isEmpty()) {
          System.out.print(secondPart);
        }

      }
      System.out.println();
    }
  }

  public void validateResults() {
//    System.out.println(results);
    if (!results.isEmpty()) {
      for (long result : results) {
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

  public List<Long> getResultsNumbersToCalculateFirstPart() {
    return resultsNumbersToCalculateFirstPart;
  }

  public List<Long> getFirstArrayItemNumberToCalculateSecondPart() {
    return firstArrayItemNumberToCalculateSecondPart;
  }

  public List<List<Long>> getNumbersToCalculateSecondPart() {
    return numbersToCalculateSecondPart;
  }
}