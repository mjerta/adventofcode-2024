package nl.mpdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    FileReader fileReader = new FileReader("./puzzle.txt");
    List<String> linesOfFile = fileReader.getLines();

    for(String line: linesOfFile) {
      System.out.println(line);
    }

  }
}
