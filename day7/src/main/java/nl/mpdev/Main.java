package nl.mpdev;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    FileReader fileReader = new FileReader("./puzzle.txt");
    List<String> linesOfFile = fileReader.getLines();


    for(String singleLine: linesOfFile) {
      Line line = new Line(singleLine);
      System.out.println(line.getOutput());
      System.out.println(line.getNumbersToCalculate());
      Set<Integer> results = new HashSet<>();
      line.calculateBasedOnOperator(0, 0, results , true);
      line.calculateBasedOnOperator(0, 1, results , false);
      System.out.println(results);
      for(int number : line.getNumbersToCalculate()) {
        results.contains(number);

      }
    }
  }
}
