package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-15
 */
public class ChessTest extends TestCase {
  public void testValidMoves() throws Exception {
    Chess.main(new String[]{"data/sample-moves.txt"});
  }

  public void testInvalidMoves() throws Exception {
    Chess.main(new String[]{"data/sample-moves-invalid.txt"});
  }

  public void testQuickCheckmateMoves() throws Exception {
    Chess.main(new String[]{"data/quick-checkmate.txt"});
  }

}
