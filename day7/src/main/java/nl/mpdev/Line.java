package nl.mpdev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Line {
  private final Integer output;
  private final List<Integer> numbersToCalculate;

  public Line(String input) {
    numbersToCalculate = new ArrayList<>();
    var splittedString = input.split(":");
    this.output = Integer.parseInt(splittedString[0]);
    var splittedNumbersAfterOutput = splittedString[1].trim().split("\\s+");
    Arrays.stream(splittedNumbersAfterOutput)
      .forEach(number -> numbersToCalculate.add(Integer.parseInt(number)));
  }

  public Integer getOutput() {
    return output;
  }

  public List<Integer> getNumbersToCalculate() {
    return numbersToCalculate;
  }

  public void calculateBasedOnOperator(
    int index,
    int currentResult,
    Set<Integer> results,
    boolean isAddition) {
    if(index == numbersToCalculate.size()){
      results.add(currentResult);
      return;
    }
    int currentNumber = numbersToCalculate.get(index);
    if (isAddition) {
      calculateBasedOnOperator(index + 1, currentResult + currentNumber,  results, true);
      calculateBasedOnOperator(index + 1, currentResult * currentNumber,  results, false);
    } else {
      calculateBasedOnOperator(index + 1, currentResult * currentNumber,  results, false);
      calculateBasedOnOperator(index + 1, currentResult + currentNumber,  results, true);
    }

  }
}
