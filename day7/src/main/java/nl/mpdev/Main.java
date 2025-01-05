package nl.mpdev;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    FileReader fileReader = new FileReader("./puzzle.txt");
    List<String> linesOfFile = fileReader.getLines();
    List<Long> resultToLookForFirstPart = new ArrayList<>();
    Set<Long> resultToLookForSecondPart = new HashSet<>();

    List<List<String>> extraResults = new ArrayList<>();
    // Loop through each line in the file
    for (String singleLine : linesOfFile) {
      Line line = new Line(singleLine);
      line.executeCalculations(1, line.getNumbersToCalculate().getFirst());
      extraResults.add(line.getExtraResults());
      line.validateResults();
      if (line.isHasValidNumber()) {
        resultToLookForFirstPart.add(line.getOutput());
      }
    }

    Concatenation concatenation = new Concatenation(linesOfFile);
    System.out.println("last calculations");
    for(String outerList : concatenation.getResults()) {
        Line line = new Line(outerList);
        line.executeCalculations(1, line.getNumbersToCalculate().getFirst());
        line.validateResults();
        if (line.isHasValidNumber()) {
          resultToLookForSecondPart.add(line.getOutput());
        }
    }

    long sum = resultToLookForFirstPart.stream().mapToLong(Long::longValue).sum();
    long sum2 = resultToLookForSecondPart.stream().mapToLong(Long::longValue).sum();
    System.out.println(sum);
    System.out.println(sum2);
  }
}