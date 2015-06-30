package toy.chess;

import toy.chess.given.input.UserInputException;
import toy.chess.given.input.UserInputFile;

import java.io.IOException;

public class Chess {

  public static void main(String[] args) throws UserInputException, IOException {

    Board board = new Board();

    System.out.println("Playing game from file " + args[0]);
    UserInputFile moves = new UserInputFile(args[0]);

    int[] from = new int[2];
    int[] to = new int[2];
    String player = "1";
    while (moves.nextMove(from, to)) {
      System.out.println("Player " + player + ": move from " + from[0] + "," + from[1] + " to " + to[0] + "," + to[1]);
      if (player.equals("1")) {
        player = "2";

      } else {
        player = "1";
      }

      board.move(from, to);

      System.out.println(board.toString());
    }


  }


}
