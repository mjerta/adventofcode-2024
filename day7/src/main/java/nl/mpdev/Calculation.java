package nl.mpdev;

import java.util.*;

public class Calculation {
  private final String[] operators = {"+", "*"};
  private final Set<Long> results;
  private final List<Long> numbersToCalculate;

  public Calculation(List<String> input) {
    results = new HashSet<>();
    numbersToCalculate = input.stream().map(Long::valueOf).toList();
    executeCalculations(1, numbersToCalculate.getFirst());

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

  public List<Long> getNumbersToCalculate() {
    return numbersToCalculate;
  }

  public Set<Long> getResults() {
    return results;
  }
}
