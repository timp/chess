package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class PawnTest extends TestCase {
  public void testDiagonalToEmptyFails() {
    Board it = new Board();
    try {
      it.m("b2c3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {
    }
  }

  public void testDiagonalTake() {
    Board it = new Board();
    it.m("a2a4");
    it.m("b7b5");
    it.m("a4b5");
  }

  public void testMayNotMoveTwoUnlessOnStartingPosition() {
    Board it = new Board();
    it.m("a2a4");
    it.m("g7g5");
    try {
      it.m("a4a6");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {
    }
    try {
      it.m("a4a7");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {
    }
  }


  public void testMoreThanOneHorizontalFail() {
    Board it = new Board();
    it.m("a2a4").m("g7g5");
    try {
      it.m("a4g5");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {
    }
  }

  public void testEnPassant() {
    Board it = new Board();
    it.m("b2b4").m("h7h5");
    it.m("b4b5").m("c7c5");
    it.m("b5c6"); // Capture en passant
    it.m("h5h4");
    it.m("g2g4");
    try {
      // Ensure only relevant piece can move diagonally
      // when board in en Passant state
      it.m("b7a6");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {
    }
    it.m("h4g3"); // Capture en passant
    it.m("d2d3").m("d7d6");
  }

  public void testQueening() {
    Board it = new Board();
    it.m("b2b4").m("c7c5");
    it.m("b4c5").m("d7d5");
    it.m("c5c6").m("c8e6");
    it.m("c6c7").m("e6g4");
    it.m("c7c8");// Queen
    assertEquals("q", it.pieceAt("c8").getAbbreviation());
  }

  public void testSquareUnoccupiedWhenNotCapturing() {
    Board it = new Board();
    it.m("b2b4").m("c7c5");
    it.m("b4b5").m("c5c4");
    try {
      it.m("c2c4");
      fail("should have bombed");
    } catch (InvalidPieceMoveException e) {}
    it.m("a2a3").m("c4c3");
    try {
      it.m("c2c3");
      fail("should have bombed");
    } catch (InvalidPieceMoveException e) {}
    assertEquals(
        "  abcdefgh  \n" +
        " +--------+ \n" +
        "8|RNBQKBNR|8\n" +
        "7|PP PPPPP|7\n" +
        "6|        |6\n" +
        "5| p      |5\n" +
        "4|        |4\n" +
        "3|p P     |3\n" +
        "2|  pppppp|2\n" +
        "1|rnbqkbnr|1\n" +
        " +--------+ \n" +
        "  abcdefgh  \n",it.toString());
  }
  public void testPathIsEmpty() {
    Board it = new Board();
    it.m("c2c4").m("h7h5");
    it.m("c4c5").m("h5h4");
    it.m("c5c6").m("h4h3");
    try{
      it.m("h2h4");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
  }

  public void testHashCode() {
    Pawn it = new Pawn(Player.WHITE);
    assertEquals(112, it.hashCode());
    it = new Pawn(Player.BLACK);
    assertEquals(143, it.hashCode());
  }
}