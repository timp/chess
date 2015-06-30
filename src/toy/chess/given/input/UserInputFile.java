//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
//  Derived from com.siteintel.progtest.input.UserInputFile
//
package toy.chess.given.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserInputFile implements UserInput {
  private BufferedReader m_input;
  private int m_line_number = 0;
  private String m_filename;

  public UserInputFile(String filename) throws IOException {
    this.m_filename = filename;
    this.m_input = new BufferedReader(new FileReader(this.m_filename));
  }

  public boolean nextMove(int[] from, int[] to) throws UserInputException {
    try {
      String e = this.m_input.readLine();
      ++this.m_line_number;
      if (e == null) {
        return false;
      } else {
        e = e.toLowerCase();
        if (e.length() != 4) {
          throw new UserInputException("Line " + this.m_line_number + " of file " + this.m_filename + " does not contain four characters");
        } else {
          this.readCoordinate(from, e);
          this.readCoordinate(to, e.substring(2));
          return true;
        }
      }
    } catch (UserInputException var4) {
      throw var4;
    } catch (Exception var5) {
      throw new UserInputException(var5);
    }
  }

  private void readCoordinate(int[] a, String chars) throws UserInputException {
    char cx = chars.charAt(0);
    char cy = chars.charAt(1);
    a[0] = cx - 97;
    a[1] = cy - 49;
    if (a[0] >= 0 && a[0] <= 7) {
      if (a[1] < 0 || a[1] > 7) {
        throw new UserInputException("Line " + this.m_line_number + " of file " + this.m_filename + " invalid char " + cy);
      }
    } else {
      throw new UserInputException("Line " + this.m_line_number + " of file " + this.m_filename + " invalid char " + cx);
    }
  }

  public static void main(String[] args) {
    try {
      UserInputFile e = new UserInputFile(args[0]);
      int[] from = new int[2];
      int[] to = new int[2];

      while (e.nextMove(from, to)) {
        System.out.println("Move from " + from[0] + "," + from[1] + " to " + to[0] + "," + to[1]);
      }

      System.out.println("Game end");
    } catch (Exception var4) {
      var4.printStackTrace();
      System.exit(1);
    }

  }
}
