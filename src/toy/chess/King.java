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
    return "Pawn";
  }

  @Override
  public String getAbbreviation() {
    return getOwner() == Player.BLACK ? "P" : "p";
  }

  @Override
  public void validate(Position from, Position to) {

  }


}
