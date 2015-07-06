package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-07-01.
 */
public class QueenTest extends TestCase {
  public void testValidMoves() {
    Board it = new Board();
    it.m("c2c4").m("c7c5");
    it.m("d1a4").m("h7h6");
    it.m("a4b5").m("h6h5");
    it.m("b5c5").m("g7g6");
    it.m("c5c8").m("g6g5");
  }
  public void testInvalidMove(){
    Board it = new Board();
    it.m("c2c4").m("c7c5");
    it.m("d1b3").m("h7h6");
    try {
      it.m("b3h4");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
  }

  public void testPathIsEmpty() {
    Board it = new Board();
    try {
      it.m("d1b3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
    try {
      it.m("d1d3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
    try {
      it.m("d1f3");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
    try {
      it.m("d8f6");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
    try {
      it.m("d8d6");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
    try {
      it.m("d8b6");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}


    it.m("b1a3").m("b8a6");
    it.m("g1h3").m("g8h6");

    try {
      it.m("d1b1");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
    try {
      it.m("d1g1");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
    try {
      it.m("d8g8");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
    try {
      it.m("d8g8");
      fail("Should have bombed");
    } catch (InvalidPieceMoveException e) {}
  }

}
