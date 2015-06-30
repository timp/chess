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

  @Override
  public String getAbbreviation() {
    return getOwner() == Player.BLACK ? "K" : "k";
  }

  @Override
  public void validate(Position from, Position to) {
  }

}
