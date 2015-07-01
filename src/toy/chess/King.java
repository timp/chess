package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public class King extends Piece {

  public King(Player o) {
    super(o);
  }

  @Override
  public String getName() {
    return "King";
  }

  @Override
  public String getAbbreviation() {
    return getPlayer() == Player.BLACK ? "K" : "k";
  }

  @Override
  public void validate(Position from, Position to) {
    if (
        (Math.abs(to.getSquare().x() - from.getSquare().x())
            == Math.abs(to.getSquare().y() - from.getSquare().y()))) {
      if (Math.abs(to.getSquare().y() - from.getSquare().y()) != 1) {
        throw new InvalidPieceMoveException(
            "A " + getName() + " must move only one square diagonally");
      }
    } else {
      if (!((from.getSquare().x() == to.getSquare().x()
          && Math.abs(from.getSquare().y() - to.getSquare().y()) == 1)
          ||
          ((from.getSquare().y() == to.getSquare().y())
          && Math.abs(from.getSquare().x() - to.getSquare().x()) == 1)) ) {


        throw new InvalidPieceMoveException(
            "A " + getName() + " may only move in one square in straight lines:"
        + Math.abs(from.getSquare().y() - to.getSquare().y()));
      }
    }
  }


}
