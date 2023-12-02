package dev.dagore.advent2023.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
  /*
   * The newly-improved calibration document consists of lines of text; each line
   * originally contained a specific calibration value that the Elves now need to
   * recover. On each line, the calibration value can be found by combining the
   * first digit and the last digit (in that order) to form a single two-digit
   * number.
   * 
   * For example:
   * 
   * 1abc2
   * pqr3stu8vwx
   * a1b2c3d4e5f
   * treb7uchet
   * 
   * In this example, the calibration values of these four lines are 12, 38, 15,
   * and 77. Adding these together produces 142.
   * 
   * Consider your entire calibration document. What is the sum of all the
   * calibration values?
   * 
   * To begin, get your puzzle input.
   */
  private static Pattern integerPattern = Pattern.compile("\\d");

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("No input provided");
      return;
    }

    try {
      File file = new File(args[0]);
      Scanner scanner = new Scanner(file);

      int sumOfCalibrationValues = 0;

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        sumOfCalibrationValues += getCalibrationValueFrom(line);
      }

      System.out.println("Sum of calibration values: " + sumOfCalibrationValues);

      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }

  private static int getCalibrationValueFrom(String line) {
    List<Integer> integersList = getIntegersFrom(line);
    int first = integersList.get(0);
    int last = integersList.get(integersList.size() - 1);

    return first * 10 + last;
  }

  private static List<Integer> getIntegersFrom(String line) {
    List<Integer> integersList = new ArrayList<>();
    Matcher matcher = integerPattern.matcher(line);

    while (matcher.find()) {
      integersList.add(Integer.parseInt(matcher.group()));
    }

    return integersList;
  }
}
