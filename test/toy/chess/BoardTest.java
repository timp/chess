package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015/06/29
 */
public class BoardTest extends TestCase {

  public void testToString() {
    Board it = new Board();
    System.out.println(it);
  }

  public void testValidateNoPeice() {
    Board it = new Board();
    try {
      it.validate(new Position(new Square(3, 3)), new Position(new Square(4, 4)));
      fail("Should have bombed");
    } catch (InvalidMoveException e) {
      e = null;
    }

  }

  public void testValidateOntopOfOwn() {
  }

  public void testNothingOnPathBishop() {

  }

  public void testNothingOnPathRook() {

  }

  public void testNothingOnPathQueen() {

  }

  public void testNothingOnPathPawn() {

  }

}
