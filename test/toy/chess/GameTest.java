package toy.chess;

import junit.framework.TestCase;
import org.junit.Ignore;

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

  @Ignore("TODO Fix checkmate")
  public void testQuickCheckmateMoves() throws Exception {
    Game g = new Game("data/quick-checkmate.txt");
    g.play();
    assertEquals(Player.WHITE, g.getBoard().getPlayer());
    assertEquals(Player.BLACK, g.getBoard().getCheckedPlayer());
// TODO fix
//    assertEquals(Player.BLACK, g.getBoard().getCheckmatedPlayer());
  }

  public void testInvalidCode() throws Exception {
    try {
      Game.main(new String[]{"data/invalidCode.txt"});
      fail("Should have bombed");
    } catch (CodeLineException e) {
      assertTrue(e.getCause() instanceof CodeFormatException);
    }
  }

  /** Kasparov's opponent resigned, hence no check or mate. */
  public void testKasparovsImortal1999() throws Exception {
    Game g = new Game("data/Kasparovs-Immortal-1999.txt");
    g.play();
    assertEquals(Player.WHITE, g.getBoard().getPlayer());
    assertNull(g.getBoard().getCheckedPlayer());
    assertNull(g.getBoard().getCheckmatedPlayer());
  }

  public void testEndOncheck() throws Exception {
    Game g = new Game("data/endOnCheck.txt");
    g.play();
    assertEquals(Player.WHITE, g.getBoard().getPlayer());
    assertEquals(Player.BLACK, g.getBoard().getCheckedPlayer());
    assertNull(g.getBoard().getCheckmatedPlayer() + " is checkmated",
        g.getBoard().getCheckmatedPlayer());
  }



}
