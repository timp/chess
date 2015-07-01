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
    Board board = new Board();
    //TODO Use Player
    String player = "1";
    try {
      while (true) {
        MoveCode mc = nextMove();

        System.out.println("Player " + player +
            ": move from " + mc.from() +
            " to " + mc.to() );
        if (player.equals("1")) {
          player = "2";
        } else {
          player = "1";
        }
        try {
          board.move(mc);
        } catch (InvalidMoveException e) {
          throw new CodeLineException(this.fileName + ":" + this.lineNumber, e);
        }
        System.out.println(board.toString());
      }
    } catch (NoMoreMovesException eof) {
      System.out.println("Game over");
    }

  }
  public static void main(String[] args) throws IOException {
    System.out.println("Playing game from file " + args[0]);
    Game game = new Game(args[0]);
    game.play();
  }

}