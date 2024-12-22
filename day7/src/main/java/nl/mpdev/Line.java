package nl.mpdev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {
  private final Long output;
  private final List<Long> numbersToCalculate;
  private final String[] operators = {"+", "*"};
  private final List<Long> results;
  private boolean hasValidNumber = false;

  //constructor
  public Line(String input) {
    numbersToCalculate = new ArrayList<>();
    results = new ArrayList<>();
    var splittedString = input.split(":");
    this.output = Long.parseLong(splittedString[0]);
    var splittedNumbersAfterOutput = splittedString[1].trim().split("\\s+");
    Arrays.stream(splittedNumbersAfterOutput)
      .forEach(number -> numbersToCalculate.add(Long.parseLong(number)));
  }

  public void executeCalculations(long currentIndex, long currentValue) {
    // Base case: If we've processed all the numbers, print the current value (or store it)
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
    validateResults();
  }

  private void validateResults() {
    if (!results.isEmpty()) {
      for (long result : results) {
        if(output.equals(result)) {
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
}