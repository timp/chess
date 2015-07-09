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
    String code = this.inputReader.readLine();
    this.lineNumber++;
    if (code == null) {
      throw new NoMoreMovesException();
    } else {
      try {
        return new MoveCode(code);
      } catch (CodeFormatException e) {
        throw new CodeLineException(this.fileName + ":" + this.lineNumber, e);
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
        } catch (InvalidMoveException e) {
          throw new CodeLineException(this.fileName + ":" + this.lineNumber, e);
        }
        System.out.print("Player " + player + " (");
        if (player.equals("1")) {
          System.out.print(board.getFirstMover());
          player = "2";
        } else {
          System.out.print(board.getFirstMover().getOpponent());
          player = "1";
        }
        System.out.println(") : move from " + mc.from() +
            " to " + mc.to());
        System.out.println(board.toString());
      }
    } catch (NoMoreMovesException eof) {
      System.out.println("End of file");
      if (board.getCheckmatedPlayer() != null) {
        System.out.println(board.getCheckmatedPlayer() + " in check mate");
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