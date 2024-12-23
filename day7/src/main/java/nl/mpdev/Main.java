package nl.mpdev;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    FileReader fileReader = new FileReader("./puzzle.txt");
    List<String> linesOfFile = fileReader.getLines();
    long numbersThatWhereTrue = 0;

    // Loop through each line in the file
    for (String singleLine : linesOfFile) {
      Line line = new Line(singleLine);
      System.out.println(line.getOutput());
      System.out.println(line.getNumbersToCalculate());
      line.executeCalculations(1, line.getNumbersToCalculate().getFirst());

      line.validateResults();

      if (line.isHasValidNumber()) {
        numbersThatWhereTrue += line.getOutput();
      }
      line.addConcatenation();
      System.out.println("result from uneven part" + line.getResultsNumbersToCalculateFirstPart());
      System.out.println(line.getFirstArrayItemNumberToCalculateSecondPart());
      System.out.println(line.getNumbersToCalculateSecondPart());

    }

//    System.out.println(numbersThatWhereTrue);
  }
}