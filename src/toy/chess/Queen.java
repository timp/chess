package toy.chess;

import static toy.chess.Player.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Queen extends Piece {

  public Queen(Player o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Queen";
  }

  @Override
  public String getAbbreviation() {
    return getOwner() == BLACK ? "Q" : "q";
  }

  @Override
  public void validate(Position from, Position to) {

  }


}
