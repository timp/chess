package toy.chess;

import static toy.chess.Player.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Rook extends Piece {

  public Rook(Player o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Rook";
  }

  @Override
  public String getAbbreviation() {
    return getOwner() == BLACK ? "R" : "r";
  }

  @Override
  public void validate(Position from, Position to) {

  }


}
