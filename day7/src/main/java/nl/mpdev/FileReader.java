package nl.mpdev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
  private List<String> lines;

  public FileReader(String filePath) {
    lines = new ArrayList<String>();
    try {
      File file = new File(filePath);
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        lines.add(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      System.out.println("File is not found");
    }
  }

  public List<String> getLines() {
    return lines;
  }
}
