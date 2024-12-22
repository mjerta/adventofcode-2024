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
      line.executeCalculations(1, line.getNumbersToCalculate().getFirst());

      if (line.isHasValidNumber()) {
        numbersThatWhereTrue += line.getOutput();
      }
    }
    System.out.println(numbersThatWhereTrue);
  }
}