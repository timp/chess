package toy.chess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class Game {

  private BufferedReader inputReader;
  private int lineNumber = 0;
  private String fileName;

  private Board board = null;

  public Game(String fileName) throws IOException {
    this.fileName = fileName;
    this.inputReader = new BufferedReader(new FileReader(this.fileName));
  }

  public MoveCode nextMove()
      throws CodeFormatException, NoMoreMovesException, IOException {
    String code;
    while (true) {
      this.lineNumber++;
      code = this.inputReader.readLine();
      if (code == null) {
        throw new NoMoreMovesException();
      } else {
        // Allow comments and PGN  notes
        if (!(code.startsWith("#") || code.startsWith("["))) {
          try {
            return new MoveCode(code);
          } catch (CodeFormatException e) {
            throw new CodeLineException(this.fileName + ":" + this.lineNumber, e);
          }
        }
      }
    }
  }

  public void play() throws IOException {
    board = new Board();
    String player = "1";
    try {
      while (true) {
        MoveCode mc = nextMove();
        try {
          board = board.move(mc, true);
        } catch (Exception e) {
          throw new CodeLineException(this.fileName + ":" + this.lineNumber, e);
        }
        System.out.println("Move " + board.getMoveNumber()
            + " " + board.getPlayer()
            + " " + board.getPosition(mc.to().toString()).getPiece().getName()
            + " from " + mc.from()
            + " to " + mc.to());
        System.out.println(board.toString());
      }
    } catch (NoMoreMovesException eof) {
      System.out.println("End of file");
      if (board.getCheckedPlayer() != null) {
        if (board.getCheckmatedPlayer() == null) {
          System.out.println(board.getCheckedPlayer() + " in check");
        } else {
          System.out.println(board.getCheckmatedPlayer() + " in check mate");
        }
      } else {
        System.out.println(board.getPlayer() + " to move");
      }
    }
  }

  public Board getBoard() {
    return board;
  }

  public static void main(String[] args) throws IOException {
    System.out.println("Playing game from file " + args[0]);
    new Game(args[0]).play();
  }

}