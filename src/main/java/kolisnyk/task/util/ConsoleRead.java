package kolisnyk.task.util;

import java.util.Scanner;
import kolisnyk.task.OutOfAttemptException;

public class ConsoleRead {

  /**
   * Read read positive number from console
   *
   * @param msg - show at first
   * @param msgIfMistake -show when user write not correct number
   * @param maxAttempt - max attempt for user to write correct number
   * @return number or throws OutOfAttemptException
   * @throws OutOfAttemptException when attempt is out
   */
  static public int readPositiveInt(String msg, String msgIfMistake, int maxAttempt)
      throws OutOfAttemptException {
    Scanner reader = new Scanner(System.in);
    System.out.print(msg);

    for (int i = 0; i < maxAttempt; i++) {
      try {
        String readLine = reader.nextLine().replaceAll(" +", "");//replace all spaces
        int number = Integer.valueOf(readLine);
        if (number > 0) {
          return number;
        }
      } catch (Exception e) {
        System.out.println(msgIfMistake);
      }
    }
    throw new OutOfAttemptException();
  }


  /**
   * @param msg - show at first
   * @return - string like one solid string. All spaces deletes
   */
  static public String readSolidString(String msg) {
    Scanner reader = new Scanner(System.in);
    System.out.print(msg);
    String readLine = reader.nextLine().replaceAll(" +", "");//replace all spaces
    return readLine;
  }

  /**
   * @param msg - show at first
   * @return -  double space and more replace by one space.
   */
  static public String readString(String msg) {
    Scanner reader = new Scanner(System.in);
    System.out.print(msg);
    String readLine = reader.nextLine().replaceAll(" +", " ");//replace many spaces by one
    return readLine;
  }
}
