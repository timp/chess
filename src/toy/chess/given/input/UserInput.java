//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
//  Derived from com.siteintel.progtest.input.UserInput
//

package toy.chess.given.input;

public interface UserInput {
  /**
   * This method obtains the next move from the next player and populates
   * the from[] and to[] arrays accordingly.
   * These arrays should be of length at least 2.
   * <p>
   * On exit, from[0] and from[1] store the x and y co-ordinate respectively
   * of the piece to be moved.
   * <p>
   * Similarly, to[0] and to[1] store the x and y co-ordinate respectively of
   * the destination location.
   * <p>
   * The x, y co-ordinates are in the range 0,0 (bottom left) to 7,7 (top right).
   * The same co-ordinate system is used for both players.
   * 0,0 equates to a1 in normal chess notation, where the board is viewed
   * from the perspective of the white player.
   * <p>
   * The first time this function is called it returns the move for player 1 (white)
   * (whose  pieces occupy the positions 0,0 to 7,1).
   * It then returns moves for alternating players.
   *
   * @return false when there are no more moves otherwise true
   */
  boolean nextMove(int[] var1, int[] var2) throws UserInputException;
}
