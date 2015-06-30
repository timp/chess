package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Knight extends Piece {

  public Knight(Player o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Knight";
  }

  /** See https://en.wikipedia.org/wiki/Descriptive_notation  */
  @Override
  public String getAbbreviation() {
    return getOwner() == Player.BLACK ? "N" : "n";
  }

  @Override
  public void validate(Position from, Position to) {
  }

}
