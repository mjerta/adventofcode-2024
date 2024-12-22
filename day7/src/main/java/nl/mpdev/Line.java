package nl.mpdev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {
  private final Integer output;
  private List<Integer> numbersToCalculate;

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
}
