package dev.dagore.advent2023.day2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class App {
  /*
   * As you walk, the Elf shows you a small bag and some cubes which are either
   * red, green, or blue. Each time you play this game, he will hide a secret
   * number of cubes of each color in the bag, and your goal is to figure out
   * information about the number of cubes.
   * 
   * To get information, once a bag has been loaded with cubes, the Elf will reach
   * into the bag, grab a handful of random cubes, show them to you, and then put
   * them back in the bag. He'll do this a few times per game.
   * 
   * You play several games and record the information from each game (your puzzle
   * input). Each game is listed with its ID number (like the 11 in Game 11: ...)
   * followed by a semicolon-separated list of subsets of cubes that were revealed
   * from the bag (like 3 red, 5 green, 4 blue).
   * 
   * For example, the record of a few games might look like this:
   * 
   * Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
   * Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
   * Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
   * Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
   * Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
   * 
   * In game 1, three sets of cubes are revealed from the bag (and then put back
   * again). The first set is 3 blue cubes and 4 red cubes; the second set is 1
   * red cube, 2 green cubes, and 6 blue cubes; the third set is only 2 green
   * cubes.
   * 
   * The Elf would first like to know which games would have been possible if the
   * bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?
   * 
   * In the example above, games 1, 2, and 5 would have been possible if the bag
   * had been loaded with that configuration. However, game 3 would have been
   * impossible because at one point the Elf showed you 20 red cubes at once;
   * similarly, game 4 would also have been impossible because the Elf showed you
   * 15 blue cubes at once. If you add up the IDs of the games that would have
   * been possible, you get 8.
   * 
   * Determine which games would have been possible if the bag had been loaded
   * with only 12 red cubes, 13 green cubes, and 14 blue cubes. What is the sum of
   * the IDs of those games?
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("No input provided");
      return;
    }

    try {
      FileReader file = new FileReader(args[0]);
      Scanner scanner = new Scanner(file);
      int possibleGamesId = 0;

      while (scanner.hasNextLine()) {
        int gameId = getGameIdIfPossibleOrZero(scanner.nextLine());
        possibleGamesId += gameId;
      }

      System.out.println("The sum of the possible game ids is equal to: " + possibleGamesId);

      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }

  private static int getGameIdIfPossibleOrZero(String line) {
    int totalRedCubes = 12;
    int totalGreenCubes = 13;
    int totalBlueCubes = 14;

    StringTokenizer tokens = new StringTokenizer(line, ":,; ");
    // Take "Game" out.
    tokens.nextToken();

    // Take game id.
    int id = Integer.parseInt(tokens.nextToken());

    String previousToken = "";
    while (tokens.hasMoreTokens()) {
      String currentToken = tokens.nextToken();

      if (previousToken.matches("\\d+")) {
        int n = Integer.parseInt(previousToken);

        // If not possible id is set to 0.
        if ((currentToken.equals("red") && n > totalRedCubes) ||
            (currentToken.equals("green") && n > totalGreenCubes) ||
            (currentToken.equals("blue") && n > totalBlueCubes)) {
          id = 0;
        }
      }

      previousToken = currentToken;
    }

    return id;
  }
}
