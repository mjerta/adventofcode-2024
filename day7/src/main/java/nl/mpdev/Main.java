package nl.mpdev;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    FileReader fileReader = new FileReader("./puzzle.txt");
    List<String> linesOfFile = fileReader.getLines();

    for(String singleLine: linesOfFile) {
      Line line = new Line(singleLine);
      System.out.println(line.getOutput());
      System.out.println(line.getNumbersToCalculate());
    }

  }
}
