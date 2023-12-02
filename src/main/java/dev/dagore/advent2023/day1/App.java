package dev.dagore.advent2023.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
   * --- Part Two ---
   * 
   * Your calculation isn't quite right. It looks like some of the digits are
   * actually spelled out with letters: one, two, three, four, five, six, seven,
   * eight, and nine also count as valid "digits".
   * 
   * Equipped with this new information, you now need to find the real first and
   * last digit on each line. For example:
   * 
   * two1nine
   * eightwothree
   * abcone2threexyz
   * xtwone3four
   * 4nineeightseven2
   * zoneight234
   * 7pqrstsixteen
   * 
   * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76.
   * Adding these together produces 281.
   * 
   * What is the sum of all of the calibration values?
   * 
   */
  private static final String[] integerStrings = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "one", "two", "three",
      "four",
      "five", "six", "seven", "eight", "nine" };
  private static final Map<String, Integer> writtenNumberToIntegerMap;

  static {
    Map<String, Integer> map = new HashMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
    map.put("four", 4);
    map.put("five", 5);
    map.put("six", 6);
    map.put("seven", 7);
    map.put("eight", 8);
    map.put("nine", 9);
    map.put("1", 1);
    map.put("2", 2);
    map.put("3", 3);
    map.put("4", 4);
    map.put("5", 5);
    map.put("6", 6);
    map.put("7", 7);
    map.put("8", 8);
    map.put("9", 9);

    writtenNumberToIntegerMap = Collections.unmodifiableMap(map);
  }

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
    List<Integer> foundIntegers = new ArrayList<>();

    for (int i = 0; i < line.length(); i++) {
      String subString = line.substring(i);
      for (String writtenNumber : integerStrings) {
        if (subString.startsWith(writtenNumber)) {
          foundIntegers.add(writtenNumberToIntegerMap.get(writtenNumber));
          break;
        }
      }
    }

    return foundIntegers;
  }
}
