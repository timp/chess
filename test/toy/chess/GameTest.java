package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-15
 */
public class GameTest extends TestCase {
  public void testValidMoves() throws Exception {
    Game.main(new String[]{"data/sample-moves.txt"});
  }

  public void testInvalidMoves() throws Exception {
    try {
      Game.main(new String[]{"data/sample-moves-invalid.txt"});
      fail("Should have bombed");
    } catch (CodeLineException e) {
      assertTrue(e.toString().contains("data/sample-moves-invalid.txt:3"));
      assertTrue(e.getCause().toString(),
          e.getCause() instanceof InvalidPieceMoveException);
    }
  }

  public void testQuickCheckmateMoves() throws Exception {
    Game.main(new String[]{"data/quick-checkmate.txt"});
  }

  public void testInvalidCode() throws Exception {
    try {
      Game.main(new String[]{"data/invalidCode.txt"});
      fail("Should have bombed");
    } catch (CodeLineException e) {
      assertTrue(e.getCause() instanceof CodeFormatException);
    }
  }
}
