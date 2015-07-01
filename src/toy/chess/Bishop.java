package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Bishop extends Piece {

  public Bishop(Player o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Bishop";
  }


  @Override
  public String getAbbreviation() {
    return getPlayer() == Player.BLACK ? "B" : "b";
  }

  @Override
  public void validate(Position from, Position to) {
    if (!(
        Math.abs(to.getSquare().x() - from.getSquare().x())
            == Math.abs(to.getSquare().y() - from.getSquare().y()))
    ) {
      throw new InvalidPieceMoveException(
          "A " + getName() + " must move diagonally");
    }
  }
}
