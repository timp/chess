package toy.chess;

import junit.framework.TestCase;

/**
 * @author timp
 * @since 2015-06-30.
 */
public class RankTest extends TestCase {
  public void testRankToString() {
    assertEquals("1", Rank.ONE.toString());
  }

  public void testGetCoord() {
    assertEquals(2, Rank.THREE.getCoord());
  }

}
